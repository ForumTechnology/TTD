package com.example.security.service.User;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void addNewUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
