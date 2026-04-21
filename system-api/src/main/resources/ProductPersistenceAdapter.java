package com.capgemini.training.adapter.out.persistence;

import com.capgemini.training.adapter.out.persistence.mapper.ProductMapper;
import com.capgemini.training.adapter.out.persistence.repository.ProductRepository;
import com.capgemini.training.application.port.out.LoadProductPort;
import com.capgemini.training.application.port.out.SaveProductPort;
import com.capgemini.training.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductPersistenceAdapter implements LoadProductPort, SaveProductPort {

    private final ProductRepository repository;

    public ProductPersistenceAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product saveProduct(Product product) {
        var saved = repository.save(ProductMapper.toEntity(product));
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Product loadProduct(UUID id) {
        return repository.findById(id)
                .map(ProductMapper::toDomain)
                .orElseThrow();
    }

    @Override
    public List<Product> loadAllProducts() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteProduct(UUID id) {
        repository.deleteById(id);
    }
}