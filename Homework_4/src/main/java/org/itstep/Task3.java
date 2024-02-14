package org.itstep;

import java.util.ArrayList;
import java.util.Random;

public class Task3 implements Runnable {

    private enum DeviceColor {
        BLACK, WHITE, SILVER, GOLD, ROSE_GOLD
    }

    private enum DeviceType {
        PHONE, TABLET, LAPTOP, SMARTWATCH
    }

    private class Device {
        public String name;
        public int releaseYear;
        public double price;
        public DeviceColor color;
        public DeviceType type;

        public Device(String name, int releaseYear, double price, DeviceColor color, DeviceType type) {
            this.name = name;
            this.releaseYear = releaseYear;
            this.price = price;
            this.color = color;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Name: " + name +" Year of release: " + releaseYear +
                    " Color: " + color +" Type: " + type + " Price: " + price;
        }
    }

    private ArrayList<Device> devices;

    public Task3() {
        devices = new ArrayList<>();

        int fillRandomDevices = 10;
        Random random = new Random();
        String[] names = {"iPhone", "Galaxy", "Pixel", "iPad", "Surface", "MacBook", "Apple Watch", "Fitbit"};
        for (int i = 0; i < fillRandomDevices; i++) {
            String name = names[random.nextInt(names.length)];
            int releaseYear = 2000 + random.nextInt(25);
            double price = 100 + random.nextDouble() * 2000;
            DeviceColor color = DeviceColor.values()[random.nextInt(DeviceColor.values().length)];
            DeviceType type = DeviceType.values()[random.nextInt(DeviceType.values().length)];
            devices.add(new Device(name, releaseYear, price, color, type));
        }
    }

    private void printAllDevices() {
        this.devices.stream().forEach(device -> System.out.println(device));
    }

    private void printDevicesOfColor(DeviceColor color) {
        this.devices.stream().filter(device -> device.color == color)
                .forEach(device -> System.out.println(device));
    }

    private void printDevicesOfYear(int year) {
        this.devices.stream().filter(device -> device.releaseYear == year)
                .forEach(device -> System.out.println(device));
    }

    private void printDevicesOfType(DeviceType type) {
        this.devices.stream().filter(device -> device.type == type)
                .forEach(device -> System.out.println(device));
    }

    private void printDevicesMoreExpensiveThan(double price) {
        this.devices.stream().filter(device -> device.price > price)
                .forEach(device -> System.out.println(device));
    }

    private void printDevicesOfYear(int rangeStart, int rangeEnd) {
        this.devices.stream().filter(device -> device.releaseYear >= rangeStart && device.releaseYear <= rangeEnd)
                .forEach(device -> System.out.println(device));
    }

    @Override
    public void run() {
        printAllDevices();
        System.out.println("--------------------------------------------");
        printDevicesOfColor(DeviceColor.SILVER);
        System.out.println("--------------------------------------------");
        printDevicesOfYear(2019);
        System.out.println("--------------------------------------------");
        printDevicesOfType(DeviceType.SMARTWATCH);
        System.out.println("--------------------------------------------");
        printDevicesMoreExpensiveThan(1999.99);
        System.out.println("--------------------------------------------");
        printDevicesOfYear(2020,2022);
    }
}
