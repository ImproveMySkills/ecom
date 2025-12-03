package com.improvemyskills.ecom.controllers;

import com.improvemyskills.ecom.models.Category;
import com.improvemyskills.ecom.models.Customer;
import com.improvemyskills.ecom.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return ResponseEntity.ok(
                categoryService.save(category)
        );
    }

    @GetMapping("/categories/{id}")
    ResponseEntity<Category> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(
                categoryService.getCategory(id).get()
        );
    }

    @GetMapping("/categories")
    ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(
                categoryService.getAllCategories()
        );
    }

    @PutMapping("/categories")
    ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok(
                categoryService.update(category)
        );
    }
}
