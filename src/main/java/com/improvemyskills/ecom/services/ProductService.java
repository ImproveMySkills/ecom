package com.improvemyskills.ecom.services;

import com.improvemyskills.ecom.dto.ProductDto;
import com.improvemyskills.ecom.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);
    Product update(Product product);
    Optional<Product> getProduct(Long productId);
    List<Product> getAllProducts();
    List<Product> getAllDiscountProducts(Double discount);
    List<Product> getProductsByCategoryId(Long categoryId);
    Page<Product> getPaginatedProducts(int page, int size);
}
