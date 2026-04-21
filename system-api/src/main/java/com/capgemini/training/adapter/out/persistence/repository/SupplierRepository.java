package com.capgemini.training.adapter.out.persistence.repository;

import com.capgemini.training.adapter.out.persistence.entity.SupplierJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierJpaEntity, Long> {}