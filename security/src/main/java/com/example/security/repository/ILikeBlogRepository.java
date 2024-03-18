package com.example.security.repository;

import com.example.security.model.Blog.Blog;
import com.example.security.model.Blog.LikeBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeBlogRepository extends JpaRepository<LikeBlog,Long> {
    LikeBlog findByUserIdAndBlogId(Long userId, Long blogId);
}
