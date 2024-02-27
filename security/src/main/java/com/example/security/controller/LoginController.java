package com.example.security.controller;

import com.example.security.service.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//@RequestMapping({"/login","/"})
@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    @GetMapping
    public String loginForm() {
        return "login";
    }

}
