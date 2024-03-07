package com.example.security.model.Blog;

import com.example.security.model.User;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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



    @Column(name = "creation_date")
    private String creationDate;

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    public String formatDate(){
        LocalDateTime now = LocalDateTime.now();

        // Định dạng theo mẫu cụ thể và in ra chuỗi
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd , HH:mm"));
        return formattedDateTime;
    }

    public Blog() {
         this.creationDate = formatDate();
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


}
