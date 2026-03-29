package com.capgemini.training.adapter.in.web;


import com.capgemini.training.application.service.BffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BffController {

    private final BffService service;

    public BffController(BffService service) {
        this.service = service;
    }

    @GetMapping("/bff/product")
    public String getProduct() {
        return service.getProduct();
    }
}
