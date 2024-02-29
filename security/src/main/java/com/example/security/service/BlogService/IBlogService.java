package com.example.security.service.BlogService;

import com.example.security.model.Blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    Page<Blog>findAll(Pageable pageable);
    void addNewBlog(Blog blog);
    List<Blog> findAlll();
    Blog findOne(long id);
    void delete(long id);
    void save(Blog blog);
}
