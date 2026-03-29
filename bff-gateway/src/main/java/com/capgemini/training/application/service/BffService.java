package com.capgemini.training.application.service;

import com.capgemini.training.adapter.out.feign.MiddlewareClient;
import org.springframework.stereotype.Service;

@Service
public class BffService {

    private final MiddlewareClient client;
    public BffService(MiddlewareClient client) {
        this.client = client;
    }

    public String getProduct() {
        return client.getProduct();
    }
}
