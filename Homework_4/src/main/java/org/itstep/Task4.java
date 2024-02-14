package org.itstep;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Task4 implements Runnable {

    private class Projector {
        public String name;
        public int releaseYear;
        public double price;
        public String manufacturer;

        public Projector(String name, int releaseYear, double price, String manufacturer) {
            this.name = name;
            this.releaseYear = releaseYear;
            this.price = price;
            this.manufacturer = manufacturer;
        }

        @Override
        public String toString() {
            return "Name: " + name + " Year of release: " + releaseYear +
                    " Manufacturer: " + manufacturer + " Price: " + price;
        }
    }


    private ArrayList<Projector> projectors;

    public Task4() {
        projectors = new ArrayList<>();

        int fillRandomProjectors = 10;
        Random random = new Random();
        String[] names = {"Epson", "Optoma", "Sony", "BenQ", "LG"};
        String[] manufacturers = {"ABC Electronics", "XYZ Corporation", "Tech Innovations", "Gadget Solutions"};

        for (int i = 0; i < fillRandomProjectors; i++) {
            String name = names[random.nextInt(names.length)];
            int releaseYear = 2000 + random.nextInt(25);
            double price = 200 + random.nextDouble() * 1800;
            String manufacturer = manufacturers[random.nextInt(manufacturers.length)];
            projectors.add(new Projector(name, releaseYear, price, manufacturer));
        }
    }

    private void printAllProjectors() {
        this.projectors.stream()
                        .forEach(projector -> System.out.println(projector));
    }

    private void printProjectorsOfManufacturer(String manufacturer) {
        this.projectors.stream()
                .filter(projector -> projector.manufacturer.equals(manufacturer))
                .forEach(projector -> System.out.println(projector));
    }

    private void printProjectorsOfCurrentYear() {
        this.projectors.stream()
                .filter(projector -> projector.releaseYear == Year.now().getValue())
                .forEach(projector -> System.out.println(projector));
    }

    private void printProjectorsMoreExpensiveThan(double price) {
        this.projectors.stream()
                .filter(projector -> projector.price > price)
                .forEach(projector -> System.out.println(projector));
    }

    private void printProjectorsSortedByPriceAsc() {
        this.projectors.stream()
                .sorted(Comparator.comparingDouble(projector -> projector.price))
                .forEach(projector -> System.out.println(projector));
    }

    private void printProjectorsSortedByPriceDesc() {
        this.projectors.stream()
                .sorted(Comparator.comparingDouble((Projector projector) -> projector.price).reversed())
                .forEach(projector -> System.out.println(projector));
    }

    private void printProjectorsSortedByReleaseYearAsc() {
        this.projectors.stream()
                .sorted(Comparator.comparingInt(projector -> projector.releaseYear))
                .forEach(projector -> System.out.println(projector));
    }

    private void printProjectorsSortedByReleaseYearDesc() {
        this.projectors.stream()
                .sorted(Comparator.comparingInt((Projector projector) -> projector.releaseYear).reversed())
                .forEach(projector -> System.out.println(projector));
    }

    @Override
    public void run() {
        printAllProjectors();
        System.out.println("--------------------------------------------");
        printProjectorsOfManufacturer("XYZ Corporation");
        System.out.println("--------------------------------------------");
        printProjectorsOfCurrentYear();
        System.out.println("--------------------------------------------");
        printProjectorsMoreExpensiveThan(999.99);
        System.out.println("--------------------------------------------");
        printProjectorsSortedByPriceAsc();
        System.out.println("--------------------------------------------");
        printProjectorsSortedByPriceDesc();
        System.out.println("--------------------------------------------");
        printProjectorsSortedByReleaseYearAsc();
        System.out.println("--------------------------------------------");
        printProjectorsSortedByReleaseYearDesc();
    }
}
