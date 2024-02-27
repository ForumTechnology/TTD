package com.example.security.repository;


import com.example.security.model.Blog.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogRepository extends JpaRepository<Blog,Long> {
    Page<Blog> findAll(Pageable pageable);
}
