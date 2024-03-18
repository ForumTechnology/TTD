package com.example.security.model.Blog;

import com.example.security.model.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class CommentBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String content;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "blog")
    private Blog blog;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public CommentBlog(Long id, String content, LocalDateTime dateTime, User user, Blog blog) {
        Id = id;
        this.content = content;
        this.dateTime = dateTime;
        this.user = user;
        this.blog = blog;
    }

    public CommentBlog() {
        this.dateTime = LocalDateTime.now();
    }
}
