package org.itstep.task3;

import java.util.ArrayList;
import java.util.UUID;

public class Client {
    private UUID id;
    public UUID getId () {
        return id;
    }

    private ArrayList<Fine> fines = new ArrayList<>();
    public  ArrayList<Fine> getFines () {
        return fines;
    }

    private String firstname;
    public String getFirstname() {
        return firstname;
    }

    private String lastname;
    public String getLastname() {
        return firstname;
    }

    public Client(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = UUID.randomUUID();
    }

    public Client(String firstname, String lastname, UUID id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    public void addFine(Fine fine) {
        this.fines.add(fine);
    }
}
