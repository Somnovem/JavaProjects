package org.itstep;

import java.util.Random;

public class Task1 implements Runnable {
    private int[] array;
    private int sum;
    private double average;
    public Task1() {
        this.array = new int[10];
        this.sum = 0;
        this.average = 0.0;
    }
    @Override
    public void run() {
        Thread thread1 = new Thread(()->{
            fillArrayWithRandomNumbers();
        });

        Thread thread2 = new Thread(()->{
            try{
                thread1.join();
                sum = calculateSum();
                System.out.println("Sum: " + sum);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(()->{
            try{
                thread1.join();
                average = calculateAverage();
                System.out.println("Average: " + average);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private void fillArrayWithRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i < this.array.length; i++){
            array[i] = random.nextInt(100);
        }
        System.out.println("Filled up the array");
    }

    private int calculateSum() {
        int total = 0;
        for (int num : array) total += num;
        return total;
    }

    private double calculateAverage() {
        return (double)this.calculateSum() / this.array.length;
    }
}
