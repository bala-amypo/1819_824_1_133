package com.example.demo.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
@Entity
public class ProductEntity{
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
     @Column(unique=true,nullable=false)
     private String sku;
     @Column(unique=true);
     private String name;
     private String category;
     private Boolean active=true;
     

     public void setId(Long id){
        this.id=id;
     }
     public Long getId(){
        return id;
     }

     public void setSku(String sku){
        this.sku=sku;
     }
     public String getSku(){
        return sku;
     }

     public void setName(String name){
        this.name=name;
     }
     public String getName(){
        return name;
     }

     public void setCategory(String category){
        this.category=category;
     }
     public String getCategory(){
        return category;
     }

     public Boolean isActive(){
        return active;
     }
     public void setActive(Boolean active){
        this.active=active;
     }

     public ProductEntity(long id,String sku,String address,String region,Boolean active){
        this.id=id;
        this.sku=sku;
        this.address=address;
        this.category=category;
        this.active=active;
        
     }

     public ProductEntity(){

     }

}