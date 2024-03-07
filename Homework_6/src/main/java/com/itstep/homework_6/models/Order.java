package com.itstep.homework_6.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;

    private String phoneNumber;

    private String email;

    private String deliveryAddress;

    @ManyToOne
    private Pizza pizza;

    public Order() {}
}