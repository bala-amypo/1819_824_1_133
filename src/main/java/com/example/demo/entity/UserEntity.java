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
     private String role;
     @Column(unique=true)
     private String email;
     private String password;
     private Boolean active=true;
     

     public void setId(Long id){
        this.id=id;
     }
     public Long getId(){
        return id;
     }

     public void setRole(String role){
        this.role=role;
     }
     public String getRole(){
        return role;
     }

     public void setEmail(String email){
        this.email=email;
     }
     public String getEmail(){
        return email;
     }

     public void setPassword(String password){
        this.password=password;
     }
     public String getPassword(){
        return password;
     }

     public Boolean isActive(){
        return active;
     }
     public void setActive(Boolean active){
        this.active=active;
     }

     public ProductEntity(long id,String role,String email,String password,Boolean active){
        this.id=id;
        this.role=role;
        this.email=email;
        this.password=password;
        this.active=active;
        
     }

     public ProductEntity(){

     }

}