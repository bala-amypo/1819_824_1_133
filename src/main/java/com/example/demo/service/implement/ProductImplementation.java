package com.example.demo



public class ProductImplementation implements ProductService{
    @Autowired
    ProductRepository obj;
    public Product createProduct(Product product){
        return obj.save(product);
    }

    public Product getProductById(Long id){
        return obj.findById(id);
    }

    public List<Product> getAllProducts(){
        return obj.findall();
    }
}