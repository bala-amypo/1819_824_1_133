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

@Entity
public class InventoryLevel{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;  

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name
}