package com.ecommerce.finalexam.product;

import java.util.List;

public interface ProductService{
    Product findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    List<Product> getAllProducts();
    Product update(Product product);


    List<Product> searchProducts(String name);
}
