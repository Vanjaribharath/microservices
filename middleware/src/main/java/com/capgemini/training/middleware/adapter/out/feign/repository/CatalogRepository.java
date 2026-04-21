package com.capgemini.training.middleware.adapter.out.feign.repository;

import com.capgemini.training.middleware.adapter.out.feign.SystemApiClient;
import com.capgemini.training.middleware.domain.CatalogOverview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CatalogRepository {

    private final SystemApiClient systemApiClient;

    public CatalogOverview fetchOverview() {

        var featured = systemApiClient.getFeaturedProducts();

        return new CatalogOverview(
                featured,
                featured.size(),
                0
        );
    }
}