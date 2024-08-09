package com.readerspath.backend.service.impl;

import com.readerspath.backend.model.Category;
import com.readerspath.backend.repository.CategoryRepository;
import com.readerspath.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String addCategory(Category category) {
        return "";
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }
}
