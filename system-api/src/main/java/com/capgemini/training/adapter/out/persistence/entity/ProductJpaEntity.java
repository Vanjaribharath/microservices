package com.capgemini.training.adapter.out.persistence;

import com.capgemini.training.domain.model.ProductStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    private UUID id;

    private String name;

    private BigDecimal price;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    protected ProductJpaEntity() {}

    public ProductJpaEntity(UUID id,
                            String name,
                            BigDecimal price,
                            Long categoryId,
                            Long supplierId,
                            ProductStatus status,
                            LocalDateTime createdAt,
                            LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public Long getCategoryId() { return categoryId; }
    public Long getSupplierId() { return supplierId; }
    public ProductStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // Setters (optional)
    public void setId(UUID id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public void setStatus(ProductStatus status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}