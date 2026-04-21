package com.capgemini.training.middleware.domain;

import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;

import java.util.List;

public record CatalogOverview(
        List<ProductDTO> featured,
        long totalProducts,
        int categoryCount

) {}

//package com.capgemini.training.middleware.domain;
//
//public record CatalogOverview(
//        int totalProducts,
//        int categoryCount
//) {}