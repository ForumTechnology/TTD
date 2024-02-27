package com.example.security.repository;

import com.example.security.model.Blog.Category;
import com.example.security.model.Blog.CommentBlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentBlogRepo extends JpaRepository<CommentBlog, Long> {
}
