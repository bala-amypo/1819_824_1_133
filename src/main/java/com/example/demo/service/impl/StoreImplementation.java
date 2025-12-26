package com.example.demo.service.impl;
import java.util.List;
import com.example.demo.service.StoreService;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class StoreImplementation implements StoreService{
    @Autowired
    StoreRepository obj;

    public Store createStore(Store store){
          return obj.save(store);
    }

    public Store getStoreId(Long id){

        return obj.findById(id).orElse(null);
    }

    public List<Store> getAllStores(){
        return obj.findAll();
    }
}