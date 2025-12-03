package com.improvemyskills.ecom.services;

import com.improvemyskills.ecom.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(Category category);
    Optional<Category> getCategory(Long categoryId);
    List<Category> getAllCategories();
    Category update(Category category);
}
