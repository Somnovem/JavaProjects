package com.itstep.rabbitmq_test;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
    @RabbitListener(queues = "myQueue")
    public void listen(String in) {
        System.out.println("Message read from myQueue: " + in);
    }
}
