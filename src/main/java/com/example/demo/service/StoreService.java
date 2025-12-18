package com.example.demo.service;
import com.example.demo.entity.Store;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface StoreService{
    Store createStore(Store store);
    Store getStoreId(Long id);
    List<Store> getAllStores();
}