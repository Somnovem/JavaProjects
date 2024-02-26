package org.itstep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Task3 implements Runnable {

    private static int fileCount = 0;
    private static int directoryCount = 0;

    private static void copyDirectory(Path source, Path destination) throws IOException {
        Files.walk(source)
                .forEach(sourcePath -> {
                    try {
                        Path destinationPath = destination.resolve(source.relativize(sourcePath));
                        if (Files.isDirectory(sourcePath)) {
                            Files.createDirectories(destinationPath);
                            directoryCount++;
                        } else {
                            Files.copy(sourcePath, destinationPath);
                            fileCount++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Source path: ");
        String sourcePathStr = scanner.nextLine();
        System.out.print("Destination path: ");
        String destinationPathStr = scanner.nextLine();
        scanner.close();

        File sourceDirectory = new File(sourcePathStr);
        File destinationDirectory = new File(destinationPathStr);

        if (!sourceDirectory.exists() || !sourceDirectory.isDirectory()) {
            System.out.println("Source directory does not exist or is not a directory.");
            return;
        }

        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdirs();
        }

        if (!destinationDirectory.isDirectory()) {
            System.out.println("Destination path is not a directory.");
            return;
        }

        Thread copyThread = new Thread(() -> {
            try {
                copyDirectory(sourceDirectory.toPath(), destinationDirectory.toPath());
                System.out.println("Copy completed.");
                System.out.println("Total files copied: " + fileCount);
                System.out.println("Total directories created: " + directoryCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        copyThread.start();
    }
}
