package com.itstep.homework_6.requests;

import lombok.Data;

@Data
public class MakeOrderRequest {
    private String pizzaName;

    private String customerName;

    private String phoneNumber;

    private String email;

    private String deliveryAddress;
}