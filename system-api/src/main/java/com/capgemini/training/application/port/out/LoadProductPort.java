package com.capgemini.training.application.port.out;

import com.capgemini.training.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface LoadProductPort {
    Optional<Product> loadById(UUID id);
    Page<Product> loadAll(Pageable pageable);
}