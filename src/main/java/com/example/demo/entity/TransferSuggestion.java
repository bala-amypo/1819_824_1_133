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
    @JoinColumn(name = "source_store_id")
    private Store sourceStore;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_store_id")
    private Store targetStore;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "suggested_quantity", nullable = false)
    private Integer suggestedQuantity;

    private String reason;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Integer priority;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @PrePersist
    public void init() {
        this.generatedAt = LocalDateTime.now();
        if (this.status == null) this.status = "PENDING";
        if (this.priority == null) this.priority = 1;
    }

    public Long getId() { return id; }
    public Store getSourceStore() { return sourceStore; }
    public Store getTargetStore() { return targetStore; }
    public Product getProduct() { return product; }
    public Integer getSuggestedQuantity() { return suggestedQuantity; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public Integer getPriority() { return priority; }
    public LocalDateTime getGeneratedAt() { return generatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setSourceStore(Store sourceStore) { this.sourceStore = sourceStore; }
    public void setTargetStore(Store targetStore) { this.targetStore = targetStore; }
    public void setProduct(Product product) { this.product = product; }
    public void setSuggestedQuantity(Integer suggestedQuantity) { this.suggestedQuantity = suggestedQuantity; }
    public void setReason(String reason) { this.reason = reason; }
    public void setStatus(String status) { this.status = status; }
    public void setPriority(Integer priority) { this.priority = priority; }
}
