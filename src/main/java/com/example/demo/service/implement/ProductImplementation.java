public class ProductImplementation implements ProductService{
    @Autowired
    ProductRepository obj;
    public Product createProduct(Product product){
        return obj.save(product);
    }

    public Product 
}