package com.capgemini.training.adapter.out.persistence.repository;

import com.capgemini.training.adapter.out.persistence.entity.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {}