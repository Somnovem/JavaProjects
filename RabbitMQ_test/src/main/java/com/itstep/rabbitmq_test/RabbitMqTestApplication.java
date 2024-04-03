package com.itstep.rabbitmq_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitMqTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqTestApplication.class, args);
    }
}
