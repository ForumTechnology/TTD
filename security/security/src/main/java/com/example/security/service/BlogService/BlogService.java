package com.example.security.service.BlogService;


import com.example.security.model.Blog.Blog;
import com.example.security.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository iBlogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return iBlogRepository.findAll(pageable);
    }

    @Override
    public void addNewBlog(Blog blog) {
        iBlogRepository.save(blog);
    }

    @Override
    public Blog findOne(long id) {
       return iBlogRepository.findById(id).get();
    }

    @Override
    public void delete(long id) {
        iBlogRepository.deleteById(id);
    }
}
