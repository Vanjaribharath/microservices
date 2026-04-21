//package com.capgemini.training.middleware.adapter.in.web;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@RequestMapping("/api/v1/catalog")
//@RequiredArgsConstructor
//public class CatalogController {
//
//    private final RestTemplate restTemplate;
//
//    @GetMapping("/overview")
//    public Object overview() {
//        return restTemplate.getForObject(
//                "http://localhost:8082/api/v1/catalog/overview",
//                Object.class
//        );
//    }
//}

//package com.capgemini.training.middleware.adapter.in.web;
//
//import com.capgemini.training.middleware.application.service.CatalogService;
//import com.capgemini.training.middleware.domain.CatalogOverview;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class CatalogController {
//
//    private final CatalogService catalogService;
//
//    @GetMapping("/api/v1/catalog/overview")
//    public CatalogOverview overview() {
//        return catalogService.getCatalogOverview();
//    }
//}

package com.capgemini.training.middleware.adapter.in.web;

import com.capgemini.training.middleware.application.service.CatalogService;
import com.capgemini.training.middleware.domain.CatalogOverview;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
//@RequiredArgsConstructor
//public class CatalogController {
//
//    private final CatalogService catalogService;
//
//    @GetMapping("/api/v1/catalog/overview")
//    public CatalogOverview overview() {
//        return catalogService.getCatalogOverview();
//    }
//}

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final RestTemplate restTemplate;

    @GetMapping("/overview")
    public Object overview() {
        return restTemplate.getForObject(
                "http://localhost:8082/api/v1/catalog/overview",
                Object.class
        );
    }
}