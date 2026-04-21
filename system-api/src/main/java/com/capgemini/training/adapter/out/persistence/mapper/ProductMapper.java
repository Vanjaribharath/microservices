package com.capgemini.training.adapter.out.persistence.mapper;

import com.capgemini.training.adapter.out.persistence.ProductJpaEntity;
import com.capgemini.training.domain.model.Product;

public class ProductMapper {

    public static Product toDomain(ProductJpaEntity e) {
        return new Product(
                e.getId(),
                e.getName(),
                e.getPrice(),
                e.getCategoryId(),
                e.getSupplierId(),
                e.getStatus(),
                e.getCreatedAt(),
                e.getUpdatedAt()
        );
    }

    public static ProductJpaEntity toEntity(Product p) {
        return new ProductJpaEntity(
                p.id(),
                p.name(),
                p.price(),
                p.categoryId(),
                p.supplierId(),
                p.status(),
                p.createdAt(),
                p.updatedAt()
        );
    }
}