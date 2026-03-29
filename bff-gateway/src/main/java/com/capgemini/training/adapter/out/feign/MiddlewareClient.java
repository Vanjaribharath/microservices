package com.capgemini.training.adapter.out.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="middleware", url="http://localhost:8082")
public interface MiddlewareClient {

    @GetMapping("middleware/product")
    String getProduct();
}
