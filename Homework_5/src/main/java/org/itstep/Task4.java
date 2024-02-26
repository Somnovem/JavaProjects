package org.itstep;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task4 implements Runnable {

    private static final String MERGED_FILE_PATH = "merged_result.txt";
    private static final String BANNED_WORDS_FILE_PATH = "bannedWords.txt";

    private static void scanAndMergeFiles(String sourceDirectoryPath, String keyword) {
        File sourceDirectory = new File(sourceDirectoryPath);
        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            System.out.println("Invalid source directory path.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MERGED_FILE_PATH))) {
            Files.walk(Paths.get(sourceDirectoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(filePath -> {
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                            String line;
                            boolean keywordFound = false;
                            while ((line = reader.readLine()) != null) {
                                if (line.contains(keyword)) {
                                    keywordFound = true;
                                    break;
                                }
                            }
                            if (keywordFound) {
                                writer.write("File: " + filePath.toString() + "\n");
                                writer.write("---------------------\n");
                                Files.lines(filePath).forEach(fileLine -> {
                                    try {
                                        writer.write(fileLine + "\n");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                });
                                writer.write("\n");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Merged result written to " + MERGED_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeBannedWords() {
        List<String> bannedWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BANNED_WORDS_FILE_PATH))) {
            String word;
            while ((word = reader.readLine()) != null) {
                bannedWords.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(MERGED_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter("cleaned_result.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String bannedWord : bannedWords) {
                    line = line.replaceAll(bannedWord, "");
                }
                writer.write(line + "\n");
            }
            System.out.println("Banned words removed from result.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Source path: ");
        String sourcePathStr = scanner.nextLine();
        File sourceDirectory = new File(sourcePathStr);
        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            System.out.println("Source directory does not exist or is not a directory.");
            return;
        }
        System.out.print("Key word: ");
        String keyWord = scanner.nextLine();
        scanner.close();

        Thread thread1 = new Thread(() -> scanAndMergeFiles(sourcePathStr, keyWord));
        Thread thread2 = new Thread(Task4::removeBannedWords);

        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }
}