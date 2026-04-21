//package com.capgemini.training.adapter.in.web;
//
//import com.capgemini.training.adapter.out.feign.MiddlewareClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/bff")
//public class BffController {
//
//    private final MiddlewareClient client;
//
//    public BffController(MiddlewareClient client) {
//        this.client = client;
//    }
//
//    @GetMapping("/products")
//    public String getProducts() {
//        return client.getProducts();
//    }
//}
//
