package org.itstep;

import java.util.ArrayList;

public class Task2 implements Runnable {

    private enum ProductType {
        MEAT,
        FISH,
        MILK,
    }

    private class Product {
        public String name;
        public ProductType type;
        @Override
        public String toString() {
            return "Name: " + name + " Type: " + type;
        }
        public Product(String name, ProductType type) {
            this.name = name;
            this.type = type;
        }
    }

    private ArrayList<Product> products;

    public Task2() {
        products = new ArrayList<>();
        products.add(new Product("Pork",ProductType.MEAT));
        products.add(new Product("Beef",ProductType.MEAT));
        products.add(new Product("Pork",ProductType.MEAT));
        products.add(new Product("Tuna",ProductType.FISH));
        products.add(new Product("Salmon",ProductType.MEAT));
        products.add(new Product("20% Milk",ProductType.MILK));
        products.add(new Product("25% Milk",ProductType.MILK));
    }

    private void printDb() {
        this.products.stream().forEach(product -> System.out.println(product));
    }

    private void printProductsWithNamesShorterThan5Letters() {
        this.products.stream().filter(product -> product.name.length() < 5)
                            .forEach(product -> System.out.println(product));
    }

    private long getProductCountWithName(String name) {
        return this.products.stream().filter(product -> product.name.equals(name))
                .count();
    }

    private void printProductsThatStartWith(char letter) {
        this.products.stream().filter(product -> product.name.charAt(0) == letter)
                .forEach(product -> System.out.println(product));
    }

    private void printMilkProducts() {
        this.products.stream().filter(product -> product.type == ProductType.MILK)
                .forEach(product -> System.out.println(product));
    }

    @Override
    public void run() {
        this.printDb();
        System.out.println("--------------------------------------------");
        this.printProductsWithNamesShorterThan5Letters();
        System.out.println("--------------------------------------------");
        System.out.println("Product named 'Pork': " + this.getProductCountWithName("Pork"));
        System.out.println("--------------------------------------------");
        this.printProductsThatStartWith('p');
        System.out.println("--------------------------------------------");
        this.printMilkProducts();
    }
}
