package com.example.security.model.Blog;

import com.example.security.model.User;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String type;
    private int likeBlog;
    private int viewBlog;
    private String author;
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Blog(Long id, String name, String type, int likeBlog, int viewBlog, String author, Boolean status, LocalDateTime creationDate, User user, Category category, List<CommentBlog> comments) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.likeBlog = likeBlog;
        this.viewBlog = viewBlog;
        this.author = author;
        this.status = status;
        this.creationDate = creationDate;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Blog() {
        this.creationDate = LocalDateTime.now();
        this.status = false;
    }


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

    public int getLikeBlog() {
        return likeBlog;
    }

    public void setLikeBlog(int likeBlog) {
        this.likeBlog = likeBlog;
    }

    public int getViewBlog() {
        return viewBlog;
    }

    public void setViewBlog(int viewBlog) {
        this.viewBlog = viewBlog;
    }

    public Blog(Long id, String name, String type, int likeBlog, int viewBlog, String author, LocalDateTime creationDate, User user, Category category, List<CommentBlog> comments) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.likeBlog = likeBlog;
        this.viewBlog = viewBlog;
        this.author = author;
        this.creationDate = creationDate;
        this.user = user;
        this.category = category;
        this.comments = comments;
    }
}
