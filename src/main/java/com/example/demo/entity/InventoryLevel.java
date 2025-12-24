package com.example.demo.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_levels")
@Access(AccessType.PROPERTY)
public class InventoryLevel {

    private Long id;
    private Store store;
    private Product product;
    private Integer quantity;
    private LocalDateTime lastUpdated;

    public InventoryLevel() {
    }

    public InventoryLevel(Store store, Product product, Integer quantity) {
        this.store = store;
        this.product = product;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_id", nullable = true)
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @PrePersist
    @PreUpdate
    private void updateTimestamp() {
        this.lastUpdated = LocalDateTime.now();
    }
}
