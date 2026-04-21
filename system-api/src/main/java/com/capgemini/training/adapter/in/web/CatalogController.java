package com.capgemini.training.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    @GetMapping("/overview")
    public Map<String, Object> overview() {
        return Map.of(
                "totalProducts", 2,
                "categoryCount", 0
        );
    }
}