package com.example.demo.entity;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_suggestions")
@Access(AccessType.PROPERTY)
public class TransferSuggestion {

    private Long id;
    private Store sourceStore;
    private Store targetStore;
    private Product product;
    private Integer quantity;
    private String priority;
    private LocalDateTime suggestedAt;
    private String status;

    public TransferSuggestion() {
    }

    public TransferSuggestion(
            Store sourceStore,
            Store targetStore,
            Product product,
            Integer quantity,
            String priority) {

        this.sourceStore = sourceStore;
        this.targetStore = targetStore;
        this.product = product;
        this.quantity = quantity;
        this.priority = priority;
        this.status = "PENDING";
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
    @JoinColumn(name = "source_store_id", nullable = false)
    public Store getSourceStore() {
        return sourceStore;
    }

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_store_id", nullable = false)
    public Store getTargetStore() {
        return targetStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
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

    @Column(nullable = false)
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getSuggestedAt() {
        return suggestedAt;
    }

    public void setSuggestedAt(LocalDateTime suggestedAt) {
        this.suggestedAt = suggestedAt;
    }

    @Column(nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
