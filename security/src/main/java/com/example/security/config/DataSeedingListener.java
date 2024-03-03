package com.example.security.config;

import com.example.security.common.EncryptPasswordUtils;
import com.example.security.model.Blog.Category;
import com.example.security.model.Role;
import com.example.security.model.User;
import com.example.security.repository.ICategoryRepository;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.service.CategoryService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ICategoryRepository categoryService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_USER") == null) {
            roleRepository.save(new Role("ROLE_USER"));
        }

        if (categoryService.findOneByName("Phần Cứng") == null) {
            categoryService.save(new Category("Phần Cứng"));
        }

        if (categoryService.findOneByName("Phần Mềm") == null) {
            categoryService.save(new Category("Phần Mềm"));
        }

        if (categoryService.findOneByName("Bảo Mật") == null) {
            categoryService.save(new Category("Bảo Mật"));
        }


        //them admin
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            // mã hóa mật khẩu
            admin.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123456"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_USER"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        //Them user
        if (userRepository.findByEmail("member@gmail.com") == null) {
            User user = new User();
            user.setEmail("member@gmail.com");
            // mã hóa mật khẩu
            user.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123456"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
        //Them user
        if (userRepository.findByEmail("member2@gmail.com") == null) {
            User user = new User();
            user.setEmail("member2@gmail.com");
            // mã hóa mật khẩu
            user.setPassword(EncryptPasswordUtils.EncryptPasswordUtils("123456"));
            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
