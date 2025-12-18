package com.example.demo.service;
import com.example.demo.entity.Store;
import java.util.List;

public interface StoreService{
    Store createStore(Store store);
    Store getStoreId(Long id);
    List<Store> getAllStores();
}