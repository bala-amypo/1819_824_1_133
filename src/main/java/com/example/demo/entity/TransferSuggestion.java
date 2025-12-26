package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_suggestions")
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Store sourceStore;

    @ManyToOne
    private Store targetStore;

    private int suggestedQuantity;

    private String reason;

    private String status;

    private int priority;

    private LocalDateTime generatedAt;

    public TransferSuggestion() {}

    @PrePersist
    public void prePersist() {
        this.generatedAt = LocalDateTime.now();
        if (this.priority == 0) {
            this.priority = 1;
        }
        if (this.status == null) {
            this.status = "PENDING";
        }
    }

    // ---------------- GETTERS ----------------

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Store getSourceStore() {
        return sourceStore;
    }

    public Store getTargetStore() {
        return targetStore;
    }

    public int getSuggestedQuantity() {
        return suggestedQuantity;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    // ---------------- SETTERS (REQUIRED BY SERVICE & TESTS) ----------------

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public void setSuggestedQuantity(int suggestedQuantity) {
        this.suggestedQuantity = suggestedQuantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
