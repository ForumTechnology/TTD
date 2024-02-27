package com.example.security.model.Blog;

import com.example.security.model.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String author;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @OneToMany(mappedBy = "blog",cascade = CascadeType.ALL)
    private List<CommentBlog> comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CommentBlog> getComments() {
        return comments;
    }

    public void setComments(List<CommentBlog> comments) {
        this.comments = comments;
    }

    public Blog() {
    }

    public Blog(Long id, String name, String type, String author, User user, Category category, List<CommentBlog> comments) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.author = author;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }
}
