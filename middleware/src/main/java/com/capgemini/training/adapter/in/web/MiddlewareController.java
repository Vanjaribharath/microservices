package com.capgemini.training.adapter.in.web;

import com.capgemini.training.application.service.MiddlewareService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiddlewareController {
    private final MiddlewareService service;

    public MiddlewareController(MiddlewareService service) {
        this.service = service;
    }

    @GetMapping("/middleware/product")
    public String getProduct() {
        return service.getProduct();
    }
}
