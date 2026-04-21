package com.capgemini.training.middleware.domain.response;

public record EnrichedProductResponse(
        String displayName,
        String formattedPrice,
        String status
) {}