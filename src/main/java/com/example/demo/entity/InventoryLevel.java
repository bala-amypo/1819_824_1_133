package com.demo.example.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
@Entity
public class InventoryLevel{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY);
    private Long id;  
}