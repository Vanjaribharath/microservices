package com.capgemini.training.middleware.mapper;

import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;
import com.capgemini.training.middleware.domain.response.EnrichedProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(
            target = "displayName",
            expression = "java(product.name().toUpperCase())"
    )
    @Mapping(
            target = "formattedPrice",
            expression = "java(format(product.price()))"
    )
    EnrichedProductResponse toEnriched(ProductDTO product);

    default String format(BigDecimal price) {
        return NumberFormat
                .getCurrencyInstance(Locale.US)
                .format(price);
    }
}
