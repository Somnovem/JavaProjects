package org.itstep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Task2 implements Runnable {
    private static final Semaphore semaphore = new Semaphore(0);
    private static final List<Integer> numbers = new ArrayList<>();

    private static void fillFileWithRandomNumbers(String FILE_PATH) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                int randomNumber = random.nextInt(100) + 1;
                writer.write(Integer.toString(randomNumber));
                writer.newLine();
            }
            System.out.println("Random numbers written to file.");
            semaphore.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private static void findAndDisplayPrimes(String FILE_PATH) {
        try {
            semaphore.acquire();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("primes.txt"))){
                String line;
                System.out.print("Prime numbers: ");
                writer.write("Prime numbers: ");
                while ((line = reader.readLine()) != null) {
                    int number = Integer.parseInt(line);
                    if (isPrime(number)) {
                        System.out.print(number + " ");
                        writer.write(number + " ");
                    }
                }
                System.out.println();
                writer.newLine();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();
            semaphore.release();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void displayFactorials(String FILE_PATH) {
        try {
            semaphore.acquire();
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("factorials.txt"))){
                String line;
                System.out.println("Factorials:");
                writer.write("Factorials:\n");
                while ((line = reader.readLine()) != null) {
                    int number = Integer.parseInt(line);
                    System.out.println("Factorial of " + number + " = " + factorial(number));
                    writer.write("Factorial of " + number + " = " + factorial(number));
                    writer.newLine();
                }
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            reader.close();
            semaphore.release();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input path to a file: ");
        String filePath = scanner.nextLine();
        scanner.close();

        if(!filePath.endsWith(".txt")){
           System.out.println("Not a .txt file");
           return;
        }

        File file = new File(filePath);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create the file");
                return;
            }
        }

        Thread thread1 = new Thread(() -> fillFileWithRandomNumbers(filePath));
        Thread thread2 = new Thread(() -> findAndDisplayPrimes(filePath));
        Thread thread3 = new Thread(() -> displayFactorials(filePath));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}


