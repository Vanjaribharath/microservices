
package com.capgemini.training.adapter.out.persistence;

import com.capgemini.training.domain.model.Product;

public class ProductMapper {

    public static Product toDomain(ProductJpaEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice()
        );
    }

    public static ProductJpaEntity toEntity(Product product) {
        return new ProductJpaEntity(
                product.id(),
                product.name(),
                product.price()
        );
    }
}