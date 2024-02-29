package com.example.security.service.BlogService;

import com.example.security.model.Blog.CommentBlog;
import com.example.security.repository.ICommentBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentBlogService implements ICommentBlogService {
    @Autowired
    ICommentBlogRepo commentBlogRepo;

    @Override
    public List<CommentBlog> findAllCommentBlogs() {
        return commentBlogRepo.findAll();
    }

    @Override
    public void save(CommentBlog commentBlog) {
        commentBlogRepo.save(commentBlog);
    }
}


