package com.example.security.service.User;

import com.example.security.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    void addNewUser(User user);
    User findUserByEmail(String email);
}
