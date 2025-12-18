public class StoreImplementation implements StoreService{
    @AutoWired
    StoreRepository obj;
    Store createStore(Store store){
          obj.save(store);
    }

    Store getStoreId(Long id)
}