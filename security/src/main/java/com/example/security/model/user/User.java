package com.example.security.model.user;

import com.example.security.model.Blog.Blog;
import com.example.security.model.Blog.CommentBlog;
import com.example.security.model.Blog.LikeBlog;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<CommentBlog> comments;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<LikeBlog> likes;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Blog> blogs;
    private Boolean status;
    private String userName;
    private String phoneNumber;
    private String birthDay;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<CommentBlog> getComments() {
        return comments;
    }

    public void setComments(List<CommentBlog> comments) {
        this.comments = comments;
    }

    public List<LikeBlog> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeBlog> likes) {
        this.likes = likes;
    }

    public User(Long id, String email, String password, List<Role> roles, List<Blog> blogs, Boolean status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.blogs = blogs;
        this.status = status;
    }

    public User(Long id, String email, String password, List<Role> roles, Boolean status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.status = status;
    }

    public User(String email, String password, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public User() {
        this.status = true;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}