package com.capgemini.training.adapter.out.persistence.repository;

import com.capgemini.training.adapter.out.persistence.ProductJpaEntity;
import com.capgemini.training.domain.model.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, UUID> {

    Page<ProductJpaEntity> findByStatus(ProductStatus status, Pageable pageable);

    List<ProductJpaEntity> findByCategoryId(Long categoryId);

    @Query("""
            SELECT p FROM ProductJpaEntity p 
            WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :q, '%'))
            """)
    Page<ProductJpaEntity> search(@Param("q") String q, Pageable pageable);
}