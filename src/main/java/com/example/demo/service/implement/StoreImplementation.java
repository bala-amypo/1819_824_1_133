package com.example.demo.service.implemtn

public class StoreImplementation implements StoreService{
    @AutoWired
    StoreRepository obj;
    Store createStore(Store store){
          obj.save(store);
    }

    Store getStoreId(Long id){

        obj.getById(id);
    }

    Store fetAllProducts(){
        obj.findAll();
    }
}