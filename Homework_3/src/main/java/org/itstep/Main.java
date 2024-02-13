package org.itstep;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

interface FourParameterOperator<T> {
    T apply(T a, T b, T c, T d);
}

public class Main {
    static class Fraction {
        int numerator;
        int denominator;

        Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        Fraction add(Fraction other) {
            int resultNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
            int resultDenominator = this.denominator * other.denominator;
            return new Fraction(resultNumerator, resultDenominator);
        }

        Fraction subtract(Fraction other) {
            int resultNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
            int resultDenominator = this.denominator * other.denominator;
            return new Fraction(resultNumerator, resultDenominator);
        }

        Fraction multiply(Fraction other) {
            int resultNumerator = this.numerator * other.numerator;
            int resultDenominator = this.denominator * other.denominator;
            return new Fraction(resultNumerator, resultDenominator);
        }

        Fraction divide(Fraction other) {
            int resultNumerator = this.numerator * other.denominator;
            int resultDenominator = this.denominator * other.numerator;
            return new Fraction(resultNumerator, resultDenominator);
        }

        @Override
        public String toString() {
            return numerator + "/" + denominator;
        }
    }

    public static int sumWithPredicate(Integer[] array, Predicate<Integer> predicate) {
        int sum = 0;
        for (Integer num : array) {
            if (predicate.test(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static <T extends Comparable<T>> void main(String[] args) {

        // ---------------------------- TASK 1 --------------------------

        Predicate<Integer> isLeapYear = year -> (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        Function<String[],Long> countDaysBetween = dates -> {
            LocalDate startDate = LocalDate.parse(dates[0]);
            LocalDate endDate = LocalDate.parse(dates[1]);
            return ChronoUnit.DAYS.between(startDate,endDate);
        };

        Function<String[],Long> countWeeksBetween = dates -> {
            LocalDate startDate = LocalDate.parse(dates[0]);
            LocalDate endDate = LocalDate.parse(dates[1]);
            return ChronoUnit.WEEKS.between(startDate,endDate);
        };

        Function<String, String> getDayOfWeek = dateStr -> {
            LocalDate date = LocalDate.parse(dateStr);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            return dayOfWeek.toString();
        };

        // Leap Year lambda test
//        int year = 2024;
//        if (isLeapYear.test(year)) {
//            System.out.println(year + " is a leap year.");
//        } else {
//            System.out.println(year + " is not a leap year.");
//        }

        // Days between dates
//        String[] dates = {"2024-02-10", "2024-02-13"};
//        long daysBetween = countDaysBetween.apply(dates);
//        System.out.println("Days between the two dates: " + daysBetween);

        // Weeks between dates
//        String[] dates = {"2024-01-10", "2024-02-13"};
//        long weeksBetween = countWeeksBetween.apply(dates);
//        System.out.println("Weeks between the two dates: " + weeksBetween);

        // Day of week by date
//        String date = "2024-01-10";
//        String dayOfWeek = getDayOfWeek.apply(date);
//        System.out.println("Day of week: " + dayOfWeek);


        // ---------------------------- TASK 2 --------------------------

        BiFunction<Fraction, Fraction, Fraction> addFractions = (fraction1, fraction2) -> fraction1.add(fraction2);

        BiFunction<Fraction, Fraction, Fraction> subtractFractions = (fraction1, fraction2) -> fraction1.subtract(fraction2);

        BiFunction<Fraction, Fraction, Fraction> multiplyFractions = (fraction1, fraction2) -> fraction1.multiply(fraction2);

        BiFunction<Fraction, Fraction, Fraction> divideFractions = (fraction1, fraction2) -> fraction1.divide(fraction2);

        Fraction fraction1 = new Fraction(1, 2);
        Fraction fraction2 = new Fraction(1, 3);

//        System.out.println("Adding fractions: " + addFractions.apply(fraction1, fraction2));
//
//        System.out.println("Subtracting fractions: " + subtractFractions.apply(fraction1, fraction2));
//
//        System.out.println("Multiplying fractions: " + multiplyFractions.apply(fraction1, fraction2));
//
//        System.out.println("Dividing fractions: " + divideFractions.apply(fraction1, fraction2));

        // ---------------------------- TASK 3 --------------------------

        BinaryOperator<T> maxBy2 = (a, b) -> a.compareTo(b) >= 0 ? a : b;
        BinaryOperator<T> minBy2 = (a, b) -> a.compareTo(b) <= 0 ? a : b;

        FourParameterOperator<T> maxBy4 = (a, b, c, d) -> {
            T maxAB = maxBy2.apply(a, b);
            T maxCD = maxBy2.apply(c, d);
            return maxBy2.apply(maxAB, maxCD);
        };

        FourParameterOperator<T> minBy4 = (a, b, c, d) -> {
            T minAB = minBy2.apply(a, b);
            T minCD = minBy2.apply(c, d);
            return minBy2.apply(minAB, minCD);
        };

        // ---------------------------- TASK 4 --------------------------

        Predicate<Integer> positivePredicate = num -> num > 0;
        Integer[] numbers = {1, -2, 3, -4, 5};
//        int sum = sumWithPredicate(numbers, positivePredicate);
//        System.out.println("Sum of positive numbers: " + sum);
    }
}