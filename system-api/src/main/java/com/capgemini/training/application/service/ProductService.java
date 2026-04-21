package com.capgemini.training.application.service;

import com.capgemini.training.adapter.out.persistence.repository.CategoryRepository;
import com.capgemini.training.adapter.out.persistence.repository.SupplierRepository;
import com.capgemini.training.adapter.out.persistence.entity.CategoryJpaEntity;
import com.capgemini.training.adapter.out.persistence.entity.SupplierJpaEntity;
import com.capgemini.training.application.port.in.CreateProductUseCase;
import com.capgemini.training.application.port.in.GetProductUseCase;
import com.capgemini.training.application.port.out.LoadProductPort;
import com.capgemini.training.application.port.out.SaveProductPort;
import com.capgemini.training.domain.model.Product;
import com.capgemini.training.domain.model.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final LoadProductPort loadPort;
    private final SaveProductPort savePort;

    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;



    @Override
    public Product create(String name,
                          BigDecimal price,
                          Long categoryId,
                          Long supplierId) {


        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));


        supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = new Product(
                UUID.randomUUID(),     // id
                name,
                price,
                categoryId,
                supplierId,
                ProductStatus.DRAFT,   // status
                LocalDateTime.now(),   // createdAt
                LocalDateTime.now()    // updatedAt
        );

        return savePort.save(product);
    }


    public Product create(String name, BigDecimal price) {
        return create(name, price, null, null); // no category/supplier in V1
    }

    @Override
    public Product update(UUID id,
                          String name,
                          BigDecimal price,
                          Long categoryId,
                          Long supplierId) {

        Product existing = getById(id);

        Product updated = new Product(
                id,
                name,
                price,
                categoryId,
                supplierId,
                existing.status(),
                existing.createdAt(),
                LocalDateTime.now()
        );
        return savePort.save(updated);
    }

    @Override
    public Product getById(UUID id) {
        return loadPort.loadById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public Product getProduct(UUID id) {
        return getById(id);
    }


    @Override
    public Page<Product> list(Pageable pageable) {
        return loadPort.loadAll(pageable);
    }

    public List<Product> getAllProducts() {
        return loadPort.loadAll(Pageable.unpaged()).getContent();
    }

    @Override
    public void delete(UUID id) {
        savePort.deleteById(id);
    }

    public void deleteProduct(UUID id) {
        delete(id);
    }
}