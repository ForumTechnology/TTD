package com.example.security.service.BlogService;

import com.example.security.model.Blog.CommentBlog;

import java.util.List;

public interface ICommentBlogService {
    List<CommentBlog> findAllCommentBlogs();

}
