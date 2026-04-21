package com.capgemini.training.adapter.out.persistence;

import com.capgemini.training.adapter.out.persistence.mapper.ProductMapper;
import com.capgemini.training.adapter.out.persistence.repository.ProductRepository;
import com.capgemini.training.application.port.out.LoadProductPort;
import com.capgemini.training.application.port.out.SaveProductPort;
import com.capgemini.training.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter
        implements LoadProductPort, SaveProductPort {

    private final ProductRepository repo;

    @Override
    public Product save(Product product) {
        var saved = repo.save(ProductMapper.toEntity(product));
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> loadById(UUID id) {
        return repo.findById(id).map(ProductMapper::toDomain);
    }

    @Override
    public Page<Product> loadAll(Pageable pageable) {
        return repo.findAll(pageable).map(ProductMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }
}