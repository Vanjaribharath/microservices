package com.capgemini.training.middleware.application.service;



import com.capgemini.training.middleware.adapter.out.feign.SystemApiClient;
import com.capgemini.training.middleware.adapter.out.feign.dto.CategoryDTO;
import com.capgemini.training.middleware.adapter.out.feign.dto.ProductDTO;
import com.capgemini.training.middleware.domain.CatalogOverview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogOrchestrationServiceTest {

    @Mock
    SystemApiClient systemApi;

    @InjectMocks
    CatalogOrchestrationService service;

    @Test
    void returnsDataWhenAllDownstreamsSucceed() {
        when(systemApi.getProducts(0, 10))
                .thenReturn(List.of(new ProductDTO(null, "Prod",
                        BigDecimal.TEN, 1L, 1L, "ACTIVE")));
        when(systemApi.getCategories())
                .thenReturn(List.of(new CategoryDTO(1L, "Cat")));

        CatalogOverview overview = service.getOverview();

        assertThat(overview.featured()).hasSize(1);
        assertThat(overview.categoryCount()).isEqualTo(1);
    }

    @Test
    void degradesGracefullyWhenCategoriesFail() {
        when(systemApi.getProducts(0, 10))
                .thenReturn(List.of(new ProductDTO(null, "Prod",
                        BigDecimal.TEN, 1L, 1L, "ACTIVE")));
        when(systemApi.getCategories())
                .thenThrow(new RuntimeException("Downstream"));

        CatalogOverview overview = service.getOverview();

        assertThat(overview.featured()).hasSize(1);
        assertThat(overview.categoryCount()).isEqualTo(0);
    }
}