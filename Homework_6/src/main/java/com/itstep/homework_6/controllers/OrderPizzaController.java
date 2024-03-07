package com.itstep.homework_6.controllers;

import com.itstep.homework_6.repositories.OrderRepository;
import com.itstep.homework_6.repositories.PizzaRepository;
import com.itstep.homework_6.repositories.ToppingRepository;
import com.itstep.homework_6.models.Order;
import com.itstep.homework_6.models.Pizza;
import com.itstep.homework_6.models.Topping;
import com.itstep.homework_6.requests.MakeOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderPizzaController {

    @Autowired
    private final PizzaRepository pizzaRepository;

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ToppingRepository toppingRepository;

    public OrderPizzaController(PizzaRepository pizzaRepository,
                                OrderRepository orderRepository,
                                ToppingRepository toppingRepository) {
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
        this.toppingRepository = toppingRepository;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody MakeOrderRequest orderRequest) {
        String deliveryAddress = orderRequest.getDeliveryAddress();
        if (!isInDeliveryZone(deliveryAddress)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unfortunately, this address is not within our reach: " + deliveryAddress);
        }

        Pizza pizza = pizzaRepository.findByName(orderRequest.getPizzaName());
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setPhoneNumber(orderRequest.getPhoneNumber());
        order.setEmail(orderRequest.getEmail());
        order.setDeliveryAddress(deliveryAddress);
        order.setPizza(pizza);

        Order successfulOrder = orderRepository.save(order);
        return ResponseEntity.ok(successfulOrder);
    }

    @PostMapping("/createCustomPizza")
    public ResponseEntity<?> createCustomPizza(@RequestParam String pizzaName,
                                               @RequestParam Set<Long> toppingIds) {
        Pizza customPizza = new Pizza();
        customPizza.setName(pizzaName);
        Set<Topping> toppings = new HashSet<>();

        for (Long toppingId : toppingIds) {
            Topping topping = toppingRepository.findById(toppingId)
                    .orElseThrow(
                            () -> new RuntimeException("Topping not found. Requested ID: " + toppingId)
                    );
            toppings.add(topping);
        }

        customPizza.setToppings(toppings);

        return ResponseEntity.ok(pizzaRepository.save(customPizza));
    }

    private boolean isInDeliveryZone(String address) {
        Random random = new Random();
        return random.nextInt(1,11) == 1; //Temporary solution due to lack of actual info
    }
}