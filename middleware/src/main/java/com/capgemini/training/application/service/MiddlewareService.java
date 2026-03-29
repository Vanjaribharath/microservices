package com.capgemini.training.application.service;

import com.capgemini.training.adapter.out.feign.ProductClient;
import org.springframework.stereotype.Service;

@Service
public class MiddlewareService {
    private final ProductClient client;

    public MiddlewareService(ProductClient client) {
        this.client = client;
    }

    public  String getProduct() {
        return client.getProduct();
    }
}
