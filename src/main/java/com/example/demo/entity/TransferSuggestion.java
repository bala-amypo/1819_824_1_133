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

    @Column(nullable = false)
    private Integer suggestedQuantity;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String status;

    // 🔴 REQUIRED BY DB
    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @PrePersist
    public void prePersist() {
        if (priority == null) {
            priority = 1;
        }
        generatedAt = LocalDateTime.now();
    }

    // ---------- getters & setters ----------

    public void setSourceStore(Store sourceStore) {
        this.sourceStore = sourceStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSuggestedQuantity(Integer suggestedQuantity) {
        this.suggestedQuantity = suggestedQuantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
