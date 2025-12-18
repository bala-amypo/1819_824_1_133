public class StoreController{
    @Autowired
    StoreService obj;
    @PostMapping("/")
    public Store StoreCreation(Store store){
        return obj.CreateStore(store);
    }
    @GetMapping("/{id}")
    Public Store StoreIdGet(Long id){
        
    }

}