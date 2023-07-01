package com.Ryoshi.DatabaseProgram.controller;


import com.Ryoshi.DatabaseProgram.model.User;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import com.Ryoshi.DatabaseProgram.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/add-user")
    public String addNewUser(User user, BindingResult result){
        userRepository.save(user);
        return "redirect:/user/add-user";
    }

    @GetMapping("/add-user")
    public String showAddUser(Model model){
        model.addAttribute("user", new User());
        return "/user/add-user";
    }

}
