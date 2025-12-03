package com.improvemyskills.ecom.controllers;

import com.improvemyskills.ecom.models.Product;
import com.improvemyskills.ecom.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    ResponseEntity<Product> saveProduct(@RequestBody Product product){
        return ResponseEntity.ok(
                productService.save(product)
        );
    }

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(
                productService.getProduct(id).get()
        );
    }

    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    @GetMapping("/products/category/{id}")
    ResponseEntity<List<Product>> getProductsByCategoryId(
            @PathVariable Long id){
        return ResponseEntity.ok(
                productService.getProductsByCategoryId(id)
        );
    }

    @PutMapping("/products")
    ResponseEntity<Product> updateProduct(@RequestBody Product product){
        return ResponseEntity.ok(
                productService.update(product)
        );
    }


    @GetMapping("/products/paginated")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        return productService.getPaginatedProducts(page, size);
    }


}
