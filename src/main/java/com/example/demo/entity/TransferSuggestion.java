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
    private Store sourceStore;

    @ManyToOne(optional = false)
    private Store targetStore;

    @Column(nullable = false)
    private int suggestedQuantity;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String status = "PENDING";

    @Column(nullable = false)
    private int priority = 1;

    private LocalDateTime generatedAt;

    @PrePersist
    public void onCreate() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    // ---------- GETTERS ----------
    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public Store getSourceStore() { return sourceStore; }
    public Store getTargetStore() { return targetStore; }
    public int getSuggestedQuantity() { return suggestedQuantity; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public int getPriority() { return priority; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }

    // ---------- SETTERS (TESTS REQUIRE THESE) ----------
    public void setId(Long id) { this.id = id; }
    public void setProduct(Product product) { this.product = product; }
    public void setSourceStore(Store sourceStore) { this.sourceStore = sourceStore; }
    public void setTargetStore(Store targetStore) { this.targetStore = targetStore; }
    public void setSuggestedQuantity(int suggestedQuantity) { this.suggestedQuantity = suggestedQuantity; }
    public void setReason(String reason) { this.reason = reason; }
    public void setStatus(String status) { this.status = status; }
    public void setPriority(int priority) { this.priority = priority; }
}
