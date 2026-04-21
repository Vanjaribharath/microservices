package com.capgemini.training.middleware.domain.response;

import java.util.List;

public record CatalogOverviewResponse(
        List<EnrichedProductResponse> featured,
        int categoryCount,
        long totalProducts
) {}