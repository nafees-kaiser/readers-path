package com.readerspath.backend.service;

import com.readerspath.backend.model.Category;

public interface CategoryService {
    String addCategory(Category category);

    Category findCategoryByName(String name);
}
