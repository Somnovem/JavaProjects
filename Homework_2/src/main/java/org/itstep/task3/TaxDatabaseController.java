package org.itstep.task3;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class TaxDatabaseController {
    private ArrayList<Client> clients = new ArrayList<>();

    private void printFineInfo(Fine fine) {
        System.out.println("Fine type: " + fine.getType());
        System.out.println("Fine amount: " + fine.getAmount());
        System.out.println("Fine city: " + fine.getCity());
    }

    private void printClientInfo(Client client) {
        System.out.println("Client's firstname: " + client.getFirstname());
        System.out.println("Client's lastname: " + client.getLastname());
        for (Fine fine: client.getFines()) {
            printFineInfo(fine);
        }
    }

    private void printOutDatabase() {
        for (Client client : clients) {
            printClientInfo(client);
        }
    }

    private void printClientById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter client id: ");
        String input = scanner.nextLine();
        try {
            UUID uuid = UUID.fromString(input);
            for (Client client : clients) {
                if (client.getId() == uuid) {
                    printClientInfo(client);
                    return;
                }
            }
            System.out.println("No user with such id.");
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid UUID format.");
        }
        finally {
            scanner.close();
        }
    }

    private void printFinesByType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter type: ");
        System.out.println("    0 - Civil");
        System.out.println("    1 - Auto");
        System.out.println("    2 - Court");
        int type = scanner.nextInt();
        scanner.close();
        if (type < 0 || type > 2) {
            System.out.println("Invalid type index");
            return;
        }
        for (Client client : clients) {
            for (Fine fine : client.getFines()) {
                if (fine.getType().ordinal() == type) printFineInfo(fine);
            }
        }
    }

    private void printFinesByCity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();
        scanner.close();
        for (Client client : clients) {
            for (Fine fine : client.getFines()) {
                if (fine.getCity().equals(city)) printFineInfo(fine);
            }
        }
    }

    private void addClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter firstname: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter lastname: ");
        String lastname = scanner.nextLine();
        scanner.close();
        clients.add(new Client(firstname,lastname));
    }

    private void addFineToClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter client id: ");
        String input = scanner.nextLine();
        try {
            UUID uuid = UUID.fromString(input);
            for (Client client : clients) {
                if (client.getId() == uuid) {

                    System.out.println("Enter city:");
                    String city = scanner.nextLine();

                    System.out.println("Enter type: ");
                    System.out.println("    0 - Civil");
                    System.out.println("    1 - Auto");
                    System.out.println("    2 - Court");
                    int type = scanner.nextInt();
                    scanner.close();
                    if (type < 0 || type > 2) {
                        System.out.println("Invalid type index");
                        return;
                    }

                    System.out.println("Enter fine amount: ");
                    double amount = scanner.nextDouble();

                    clients.remove(client);
                    client.addFine(new Fine(FineType.valueOf(type),city,amount));

                    return;
                }
            }
            System.out.println("No user with such id.");
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid UUID format.");
        }
        scanner.close();
    }

}
