package com.readerspath.backend.service;

import com.readerspath.backend.model.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);

    Category findCategoryByName(String name);

    List<Category> getCategories();
}
