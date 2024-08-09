package com.readerspath.backend.service;

import com.readerspath.backend.model.Category;

public interface CategoryService {
    Category addCategory(Category category);

    Category findCategoryByName(String name);
}
