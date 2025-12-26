package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_suggestions")
public class TransferSuggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_store_id")
    private Store sourceStore;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_store_id")
    private Store targetStore;

    @Column(name = "suggested_quantity")
    private int suggestedQuantity;

    private String reason;

    @Enumerated(EnumType.STRING)
    private TransferStatus status = TransferStatus.PENDING;

    @Column(nullable = false)
    private int priority;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @PrePersist
    public void initDefaults() {
        this.generatedAt = LocalDateTime.now();
        this.priority = 1;
    }
    
    public enum TransferStatus {
    PENDING,
    APPROVED,
    REJECTED,
    COMPLETED
}


    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getSourceStore() {
        return sourceStore;
    }

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    public Store getTargetStore() {
        return targetStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public int getSuggestedQuantity() {
        return suggestedQuantity;
    }

    public void setSuggestedQuantity(int suggestedQuantity) {
        this.suggestedQuantity = suggestedQuantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
