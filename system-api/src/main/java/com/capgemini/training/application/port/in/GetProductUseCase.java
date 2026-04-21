package com.capgemini.training.application.port.in;

import com.capgemini.training.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GetProductUseCase {

    Product getById(UUID id);

    Page<Product> list(Pageable pageable);
}