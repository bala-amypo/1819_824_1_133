package com.example.demo.service.implement;
import com.example.demo.service.StoreService;
import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
public class StoreImplementation implements StoreService{
    @Autowired
    StoreRepository obj;
    public Store createStore(Store store){
          return obj.save(store);
    }

    public Store getStoreId(Long id){

        return obj.findById(id);
    }

    public List<Store> getAllStores(){
        return obj.findAll();
    }
}