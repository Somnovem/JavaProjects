package org.example;
import java.util.*;

public class Main {

    private static void task1() {
        System.out.println("\"Your time is limited");
        System.out.println("\tso don't waste it");
        System.out.println("\t\tliving someone else's life\"");
        System.out.println("\t\t\tSteve Jobs");
    }

    private static void task2() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a floating-point number to take percent from: ");
            float number = scanner.nextFloat();

            System.out.print("Enter a floating-point percent to take: ");
            float percent = scanner.nextFloat();

            float result = number * (percent / 100);
            System.out.println("Result: " + result);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid floating-point number.");
        } finally {
            scanner.close();
        }
    }

    private static void task3() {
        Scanner scanner = new Scanner(System.in);

        int digit1, digit2, digit3;

        do {
            System.out.print("Enter the first digit (0-9): ");
            digit1 = scanner.nextInt();
        } while (digit1 < 0 || digit1 > 9);

        do {
            System.out.print("Enter the second digit (0-9): ");
            digit2 = scanner.nextInt();
        } while (digit2 < 0 || digit2 > 9);

        do {
            System.out.print("Enter the third digit (0-9): ");
            digit3 = scanner.nextInt();
        } while (digit3 < 0 || digit3 > 9);

        scanner.close();
        int result = digit1*100+digit2*10+digit3;
        System.out.println("Result: " + result);
    }

    private static void task4() {
        System.out.print("Enter an int 6-digit number to swap digits in: ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        scanner.close();
        String numStr = Integer.toString(number);
        if (numStr.length() != 6) {
            throw new IllegalArgumentException("Number must be a 6-digit number");
        }
        char[] charArray = numStr.toCharArray();

        char temp = charArray[0];
        charArray[0] = charArray[5];
        charArray[5] = temp;

        temp = charArray[1];
        charArray[1] = charArray[4];
        charArray[4] = temp;

        int result = Integer.parseInt(new String(charArray));
        System.out.println("Result: " + result);
    }

    public static void task5(){
        System.out.print("Enter month number: ");
        Scanner scanner = new Scanner(System.in);
        int monthNumber = scanner.nextInt();
        scanner.close();

        if (monthNumber < 1 || monthNumber > 12) {
            throw new IllegalArgumentException("Invalid month number. Month number should be between 1 and 12.");
        }

        // Determine the season based on the month number
        String season;
        switch (monthNumber) {
            case 12:
            case 1:
            case 2:
                season = "Winter";
                break;
            case 3:
            case 4:
            case 5:
                season = "Spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "Summer";
                break;
            default:
                season = "Fall";
                break;
        }

        System.out.println("Current time of year: " + season);
    }

    private static void task6() {
        System.out.print("Enter distance in meters: ");
        Scanner scanner = new Scanner(System.in);
        double meters = scanner.nextDouble();
        System.out.print("Choose system to translate into:\n" +
                            "1 - Miles\n" +
                            "2 - Inches\n" +
                            "3 - Yards\n" +
                            "Choice: "
        );
        int choice = scanner.nextInt();
        scanner.close();
        double result = 0;
        switch (choice) {
            case 1:
                result = meters * 0.000621371;
                break;
            case 2:
                result = meters * 39.3701;
                break;
            case 3:
                result = meters * 1.09361;
                break;
            default:
                System.out.println("Invalid choice");
        }
        System.out.println("Result: " + result);
    }

    private static void task7() {
        System.out.print("Enter first number: ");
        Scanner scanner = new Scanner(System.in);
        int lowerBound = scanner.nextInt();
        System.out.print("Enter second number: ");
        int upperBound = scanner.nextInt();
        scanner.close();
        if (lowerBound > upperBound){
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        int counter = lowerBound;
        while (counter <= upperBound) {
            if (counter % 2 != 0) System.out.println("Found new odd number: " + counter);
            ++counter;
        }
    }

    private static void task8() {
        System.out.print("Enter first number: ");
        Scanner scanner = new Scanner(System.in);
        int lowerBound = scanner.nextInt();
        System.out.print("Enter second number: ");
        int upperBound = scanner.nextInt();
        scanner.close();
        if (lowerBound > upperBound){
            int temp = lowerBound;
            lowerBound = upperBound;
            upperBound = temp;
        }
        int counter = lowerBound;
        while (counter <= upperBound) {
            for (int i = 1; i <=10; ++i) {
                System.out.print(counter+"*"+i+" = "+(counter*i)+ " ");
            }
            System.out.print("\n");
            ++counter;
        }
    }

    private static void task9() {
        int arraySize = 10;
        int[] randomArray = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            randomArray[i] = random.nextInt(21) - 10; // Generates numbers from -10 to 10
        }
        System.out.println("Random Array:");
        int min = randomArray[0];
        int max = randomArray[0];
        int positiveNumbers = 0;
        int negativeNumbers = 0;
        int zeros = 0;
        for (int num : randomArray) {
            System.out.print(num + " ");
            if(num > max) max = num;
            if(num < min) min = num;
            if(num > 0) ++positiveNumbers;
            else {
                if (num == 0) ++zeros;
                else ++negativeNumbers;
            }
        }
        System.out.print("\n");
        System.out.println("Positive numbers: "+ positiveNumbers);
        System.out.println("Negative numbers: "+ negativeNumbers);
        System.out.println("Zeros: "+ zeros);
        System.out.println("Max: "+ max);
        System.out.println("Min: "+ min);
    }

    private static void printArrayList(ArrayList<Integer> arrayList) {
        for (int num : arrayList) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void task10() {
        int arraySize = 10;
        ArrayList<Integer> randomArray = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            randomArray.add(random.nextInt(21) - 10); // Generates numbers from -10 to 10
        }
        ArrayList<Integer> oddArray = new ArrayList<>();
        ArrayList<Integer> evenArray = new ArrayList<>();
        ArrayList<Integer> positiveArray = new ArrayList<>();
        ArrayList<Integer> negativeArray = new ArrayList<>();
        for (int i = 0; i < arraySize; i++) {
            int num = randomArray.get(i);

            if (num % 2 == 0) {
                evenArray.add(num);
            } else {
                oddArray.add(num);
            }

            if (num >= 0) {
                positiveArray.add(num);
            } else {
                negativeArray.add(num);
            }
        }

        System.out.println("Random Array:");
        printArrayList(randomArray);
        System.out.println("Odd Array:");
        printArrayList(oddArray);
        System.out.println("Even Array:");
        printArrayList(evenArray);
        System.out.println("Positive Array:");
        printArrayList(positiveArray);
        System.out.println("Negative Array:");
        printArrayList(negativeArray);
    }

    private static void reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    private static void task12() {
        int arraySize = 10;
        int[] randomArray = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            randomArray[i] = random.nextInt(21) - 10; // Generates numbers from -10 to 10
        }

        System.out.print("Original array:");
        for (int num : randomArray) {
            System.out.print(num + " ");
        }
        System.out.print("\n");
        System.out.print("Sort asc(1) or desc(2): ");
        Scanner scanner = new Scanner(System.in);
        int sortChoice = scanner.nextInt();
        scanner.close();
        if(sortChoice < 1 || sortChoice > 2) {
            System.out.println("Invalid input");
            return;
        }
        Arrays.sort(randomArray);
        if(sortChoice == 2) reverseArray(randomArray);

        System.out.print("Sorted array:");
        for (int num : randomArray) {
            System.out.print(num + " ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        task12();
    }
}