package com.example.security.service.BlogService;

import com.example.security.model.Blog.LikeBlog;
import com.example.security.repository.ILikeBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeBlogService implements ILikeBlogService{
    @Autowired
    ILikeBlogRepository iLikeBlogRepository;
    @Override
    public List<LikeBlog> findAllLikes() {
        return null;
    }

    @Override
    public void save(LikeBlog likeBlog) {
        iLikeBlogRepository.save(likeBlog);
    }

    @Override
    public void delete(Long id) {
        iLikeBlogRepository.deleteById(id);
    }

    @Override
    public LikeBlog findByUserIdAndBlogId(Long userId, Long blogId) {
        return iLikeBlogRepository.findByUserIdAndBlogId(userId,blogId);
    }
}
