package com.capgemini.training.middleware.application.service;

import com.capgemini.training.middleware.adapter.out.feign.repository.OrderRepository;
import com.capgemini.training.middleware.domain.Order;   // ✅ CORRECT IMPORT
import com.capgemini.training.middleware.domain.command.CreateOrderCommand;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ObservationRegistry observationRegistry;

    public Order createOrder(CreateOrderCommand command) {

        return Observation
                .createNotStarted("order.create", observationRegistry)
                .observe(() -> {

                    log.info("Creating order");

                    validate(command);

                    Order order = Order.create(command);   // ✅ NOW RESOLVES
                    return orderRepository.save(order);
                });
    }

    private void validate(CreateOrderCommand command) {
        if (command.quantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}