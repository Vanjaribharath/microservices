package com.capgemini.training.adapter.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "system-api", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/product")
    String getProduct();
}

