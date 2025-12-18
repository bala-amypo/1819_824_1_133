package com.example.demo.controller;
import org.springframework.web.bind.annotation.RestController;

public class StoreController{
    @Autowired
    StoreService obj;
    @PostMapping("/")
    public Store StoreCreation(Store store){
        return obj.createStore(store);
    }
    @GetMapping("/{id}")
    Public Store StoreIdGet(Long id){
        return obj.getStoreId(id);
    }

    @GetMapping("/")
    Public Store ShowallStore(){
        return obj.getAllStores();
    }

}