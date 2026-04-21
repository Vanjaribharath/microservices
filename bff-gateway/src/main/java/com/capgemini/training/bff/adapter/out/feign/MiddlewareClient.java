//package com.capgemini.training.adapter.out.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@FeignClient(name="middleware", url= "${middleware.url}")
//
//public interface MiddlewareClient {
//
//    @GetMapping("/bff/products")
//    String getProducts();
//}