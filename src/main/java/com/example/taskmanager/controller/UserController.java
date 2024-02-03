package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("personal-cabinet")
    private String personalPage(Model model){

        User user = userService.getAuthUser();

        if (user != null) {
            model.addAttribute("user", user);
            return "user/personal_account";
        }
        else {
            return "auth/login_page";
        }
    }

    @PostMapping("edit-process")
    private String editUserProcess(@ModelAttribute("user") User user){
        User userAuth = userService.getAuthUser();
        userService.updateUser(userAuth, user);
        return "redirect:/user/personal-cabinet";
    }

}
