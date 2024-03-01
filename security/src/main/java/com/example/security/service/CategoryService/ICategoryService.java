package com.example.security.service.CategoryService;


import com.example.security.model.Blog.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> finAll();
    Category findByName(String name);
    Category findById(Long id);
}
