package com.capgemini.training.middleware.domain;

import com.capgemini.training.middleware.domain.command.CreateOrderCommand;

public class Order {

    private final String productId;
    private final int quantity;
    private final String type;

    private Order(String productId, int quantity, String type) {
        this.productId = productId;
        this.quantity = quantity;
        this.type = type;
    }

    /**
     * ✅ Factory method used by OrderService
     */
    public static Order create(CreateOrderCommand command) {
        return new Order(
                command.productId(),
                command.quantity(),
                command.type()
        );
    }

    // getters if needed
}