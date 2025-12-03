package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.models.Category;
import com.improvemyskills.ecom.repository.CategoryRepository;
import com.improvemyskills.ecom.services.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }
}
