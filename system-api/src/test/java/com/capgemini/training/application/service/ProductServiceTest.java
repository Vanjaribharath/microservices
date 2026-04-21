package com.capgemini.training.application.service;

import com.capgemini.training.adapter.out.persistence.entity.CategoryJpaEntity;
import com.capgemini.training.adapter.out.persistence.entity.SupplierJpaEntity;
import com.capgemini.training.adapter.out.persistence.repository.CategoryRepository;
import com.capgemini.training.adapter.out.persistence.repository.SupplierRepository;
import com.capgemini.training.application.port.out.LoadProductPort;
import com.capgemini.training.application.port.out.SaveProductPort;
import com.capgemini.training.domain.model.Product;
import com.capgemini.training.domain.model.ProductStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.capgemini.training.adapter.out.persistence.entity.CategoryJpaEntity;
import com.capgemini.training.adapter.out.persistence.entity.SupplierJpaEntity;
import com.capgemini.training.adapter.out.persistence.repository.CategoryRepository;
import com.capgemini.training.adapter.out.persistence.repository.SupplierRepository;
import com.capgemini.training.application.port.out.LoadProductPort;
import com.capgemini.training.application.port.out.SaveProductPort;
import com.capgemini.training.domain.model.Product;
import com.capgemini.training.domain.model.ProductStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    SaveProductPort savePort;

    @Mock
    LoadProductPort loadPort;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    ProductService service;

    @Test
    void create_shouldSaveProduct() {
        // given
        when(categoryRepository.findById(1L))
                .thenReturn(Optional.of(
                        CategoryJpaEntity.builder()
                                .id(1L)
                                .name("Cat")
                                .description("Category description")
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build()
                ));

        when(supplierRepository.findById(1L))
                .thenReturn(Optional.of(
                        SupplierJpaEntity.builder()
                                .id(1L)
                                .name("Sup")
                                .description("Supplier description")
                                .createdAt(LocalDateTime.now())
                                .updatedAt(LocalDateTime.now())
                                .build()
                ));

        when(savePort.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Product product = service.create(
                "Widget",
                new BigDecimal("9.99"),
                1L,
                1L
        );

        // then
        assertThat(product).isNotNull();
        assertThat(product.name()).isEqualTo("Widget");

        verify(categoryRepository).findById(1L);
        verify(supplierRepository).findById(1L);
        verify(savePort).save(any());
    }

    @Test
    void getById_shouldReturnProduct() {
        // given
        Product product = sampleProduct();

        when(loadPort.loadById(product.id()))
                .thenReturn(Optional.of(product));

        // when
        Product result = service.getById(product.id());

        // then
        assertThat(result).isEqualTo(product);
    }

    @Test
    void getById_shouldThrowIfProductNotFound() {
        // given
        UUID id = UUID.randomUUID();

        when(loadPort.loadById(id))
                .thenReturn(Optional.empty());

        // then
        assertThrows(RuntimeException.class,
                () -> service.getById(id));
    }

    @Test
    void list_shouldReturnPageOfProducts() {
        // given
        Page<Product> page = new PageImpl<>(List.of(sampleProduct()));

        when(loadPort.loadAll(any(Pageable.class)))
                .thenReturn(page);

        // when
        Page<Product> result = service.list(Pageable.ofSize(10));

        // then
        assertThat(result.getContent()).hasSize(1);
    }

    @Test
    void delete_shouldDelegateToSavePort() {
        // given
        UUID id = UUID.randomUUID();

        // when
        service.delete(id);

        // then
        verify(savePort).deleteById(id);
    }

    // -------------------------------------------------------------------------
    // Helper
    // -------------------------------------------------------------------------

    private Product sampleProduct() {
        return new Product(
                UUID.randomUUID(),
                "Sample",
                new BigDecimal("1.00"),
                1L,
                1L,
                ProductStatus.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}