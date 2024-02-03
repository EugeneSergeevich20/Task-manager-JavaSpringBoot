package com.example.taskmanager.controller;

import com.example.taskmanager.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserServiceImpl userService;

    public HomeController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("home")
    public String mainPage(Model model){
        model.addAttribute("user", userService.getAuthUser());
        return "main_page";
    }
}
