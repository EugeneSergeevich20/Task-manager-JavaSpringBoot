package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/")
public class AuthController {

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String loginPage(){
        return "auth/login_page";
    }

    @GetMapping("register")
    public String registerPage(@ModelAttribute("userReg") User user){
        return "auth/register_page";
    }

    @PostMapping("register-process")
    public String registerProcess(@ModelAttribute("userReg") User user){
        userService.register(user);
        return "redirect:/auth/login";
    }

}
