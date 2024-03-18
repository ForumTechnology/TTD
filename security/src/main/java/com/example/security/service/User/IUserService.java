package com.example.security.service.User;

import com.example.security.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Page<User> findAlll(Pageable pageable);
    List<User> findAll();
    void save(User user);
    void addNewUser(User user);
    User findUserByEmail(String email);
    void delete(long id);
    Optional<User> findUserById(long id);
}
