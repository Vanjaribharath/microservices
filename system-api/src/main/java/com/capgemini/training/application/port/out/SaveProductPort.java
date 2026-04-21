package com.capgemini.training.application.port.out;

import com.capgemini.training.domain.model.Product;
import java.util.UUID;

public interface SaveProductPort {
    Product save(Product product);
    void deleteById(UUID id);
}