package com.capgemini.training.middleware.adapter.out.feign.repository;

import com.capgemini.training.middleware.domain.Order; // ✅ CORRECT IMPORT
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    public Order save(Order order) {
        // Simulated persistence
        // In real life this would call system‑api or database
        return order;
    }
}