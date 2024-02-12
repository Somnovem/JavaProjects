package org.itstep.task3;

import java.util.Optional;

public class Fine {
    private FineType type;
    public FineType getType () {
        return type;
    }

    private String city;
    public String getCity () {
        return city;
    }

    private double amount;
    public double getAmount () {
        return amount;
    }

    public Fine(Optional<FineType> type, String city, double amount) {
        this.type = type;
        this.city = city;
        this.amount = amount;
    }
}
