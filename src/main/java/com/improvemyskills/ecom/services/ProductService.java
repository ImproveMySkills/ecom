package com.improvemyskills.ecom.services;

import com.improvemyskills.ecom.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);
    Product update(Product product);
    Optional<Product> getProduct(Long productId);
    List<Product> getAllProducts();
    List<Product> getAllDiscountProducts(Double discount);
    List<Product> getProductsByCategoryId(Long categoryId);

}
