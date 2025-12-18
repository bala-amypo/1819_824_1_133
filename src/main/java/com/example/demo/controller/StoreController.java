package com.example.demo.controller;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.StoreService;

@RestController
@RequestMapping("/api/stores")
public class StoreController{
    @Autowired
    StoreService obj;
    @PostMapping("/")
    public Store StoreCreation(@RequestBody Store store){
        return obj.createStore(store);
    }
    @GetMapping("/{id}")
    public Store StoreIdGet(@PathVariable Long id){
        return obj.getStoreId(id);
    }

    @GetMapping("/")
    public List<Store> ShowallStore(){
        return obj.getAllStores();
    }

}