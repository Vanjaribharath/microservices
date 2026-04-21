package com.capgemini.training.middleware.domain.command;

public record CreateOrderCommand(
        String productId,
        int quantity,
        String type
) {}