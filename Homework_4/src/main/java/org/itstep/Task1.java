package org.itstep;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Task1 implements Runnable {

    private int[] generateRandomNumbers(int count, int min, int max) {
        Random random = new Random();
        return random.ints(count, min, max + 1).toArray();
    }

    private static boolean isMirrorable(int number) {
        if (number < 0) number *= -1;
        String numStr = Integer.toString(number);
        return new StringBuilder(numStr).reverse().toString().equals(numStr);
    }

    @Override
    public void run() {
        int[] numbers = generateRandomNumbers(20, -1000, 1000);
        System.out.print("Generated numbers: ");

        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        long positiveCount = IntStream.of(numbers).filter(n -> n > 0).count();
        long negativeCount = IntStream.of(numbers).filter(n -> n < 0).count();
        long twoDigitCount = IntStream.of(numbers).filter(n -> n >= 10 && n <= 99 || n <= -10 && n >= -99).count();
        long mirrorableCount = IntStream.of(numbers).filter(Task1::isMirrorable).count();

        System.out.println("Positive Numbers: " + positiveCount);
        System.out.println("Negative Numbers: " + negativeCount);
        System.out.println("Two-Digit Numbers: " + twoDigitCount);
        System.out.println("Mirrorable Numbers: " + mirrorableCount);
    }
}
