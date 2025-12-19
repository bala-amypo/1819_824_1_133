package com.demo.example.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.example.demo.entity.Store;
import com.example.demo.entity.Product;
import java.util.LocalDateTime;

@Entity
public class InventoryLevel{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;  

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(nullable=false)
    private int quantity;

    private LocalDateTime lastUpdated;
    @PrePersist
    @PreUpdate

    public void updatedTimestamp(){
          this.latUpdated
    }
    

}