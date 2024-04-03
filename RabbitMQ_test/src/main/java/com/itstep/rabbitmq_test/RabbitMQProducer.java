package com.itstep.rabbitmq_test;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("myQueue",message);
    }

    @Scheduled(fixedRate = 3000)
    public void sendMessage1() {
        rabbitTemplate.convertAndSend("myQueue", "Car drove off");
    }

    @Scheduled(fixedRate = 5000)
    public void sendMessage2() {
        rabbitTemplate.convertAndSend("myQueue", "Truck drove off");
    }

    @Scheduled(fixedRate = 10000)
    public void sendMessage3() {
        rabbitTemplate.convertAndSend("myQueue", "Bike drove off");
    }
}
