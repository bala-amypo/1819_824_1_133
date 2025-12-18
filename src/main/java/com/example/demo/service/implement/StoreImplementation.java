package com.example.demo.service.implement;
import com.example.demo.service.StoreService;
public class StoreImplementation implements StoreService{
    @Autowired
    StoreRepository obj;
    Store createStore(Store store){
          obj.save(store);
    }

    Store getStoreId(Long id){

        obj.findById(id);
    }

    Store getAllStores(){
        obj.findAll();
    }
}