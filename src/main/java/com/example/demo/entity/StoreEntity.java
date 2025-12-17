package com.example.demo.entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
@Entity
public class StoreEntity{
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
     @Column(unique=true,nullable=false)
     private String storeName;
     private String address;
     private String region;
     private Boolean active=true;
     

     public void setId(Long id){
        this.id=id;
     }
     public Long getId(){
        return id;
     }

     public void setStoreName(String storeName){
        this.storeName=storeName;
     }
     public String getStoreName(){
        return storeName;
     }

     public void setAddress(String address){
        this.address=address;
     }
     public String getAddress(){
        return address;
     }

     public void setRegion(String storeName){
        this.region=region;
     }
     public String getRegion(){
        return region;
     }

     public Boolean isActive(){
        return active;
     }
     public void setActive(Boolean active){
        this.active=active;
     }

     public StoreEntity(long id,String storeName,String address,String region,Boolean active){
        this.id=id;
        this.storeName=storeName;
        this.address=address;
        this.region=region;
        this.active=active;
        
     }

     public StoreEntity(){

     }

}