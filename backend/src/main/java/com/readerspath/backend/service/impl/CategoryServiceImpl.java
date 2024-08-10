package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.Category;
import com.readerspath.backend.repository.CategoryRepository;
import com.readerspath.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
