package com.example.security.repository;


import com.example.security.model.Blog.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

    Category findOneByName(String name);
}