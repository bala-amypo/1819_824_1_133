package com.example.demo.controller;
import org.springframework.web.bind.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController{
    @Autowired
    ProductService obj;

    @PostMapping("/")
    public Product cproduct(@RequestBody Product product){
        return obj.createProduct(product);
    }
    
    @GetMapping("/{id}")
    public Product gproductbyid(Long id){
        return obj.getProductById(id);
    }

    @GetMapping("/")
    public List<Product> getp(){
        return obj.
    }



}