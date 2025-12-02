package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.models.Product;
import com.improvemyskills.ecom.repository.ProductRepository;
import com.improvemyskills.ecom.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return getAllDiscountProducts(0.9);
    }

    @Override
    public List<Product> getAllDiscountProducts(Double discount) {
        List<Product> updatedProductList = discountProcessor(productRepository.findAll(),
                discount);
        return productRepository.saveAll(updatedProductList);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.getByCategoryId(categoryId);
    }

    private List<Product> discountProcessor(List<Product> products, Double discount){
        return products.stream()
                .map(p -> {
                    Product product = new Product();
                    product.setPrice(discount*p.getPrice());
                    product.setDiscount(p.getDiscount());
                    product.setName(p.getName());
                    product.setReference(p.getReference());
                    product.setCategoryId(p.getCategoryId());
                    return product;
                })
                .collect(Collectors.toList());
    }
}
