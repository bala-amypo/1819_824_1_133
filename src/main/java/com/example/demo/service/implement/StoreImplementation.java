package com.example.demo.service.implement;
import com.example.demo.service.StoreService;
import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
public class StoreImplementation implements StoreService{
    @Autowired
    StoreRepository obj;
    Store createStore(Store store){
          return obj.save(store);
    }

    Store getStoreId(Long id){

        return obj.findById(id);
    }

    Store getAllStores(){
        return obj.findAll();
    }
}