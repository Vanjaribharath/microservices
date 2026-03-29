package com.capgemini.training.application.port.in;

import com.capgemini.training.domain.model.Product;

public interface CreateProductUseCase {
    Product create(String name, double price);
}
