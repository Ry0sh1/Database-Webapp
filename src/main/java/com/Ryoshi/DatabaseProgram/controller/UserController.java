package com.Ryoshi.DatabaseProgram.controller;


import com.Ryoshi.DatabaseProgram.model.User;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add-user")
    public String addNewUser(User user, BindingResult result){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user/add-user";
    }

    @GetMapping("/add-user")
    public String showAddUser(Model model){
        model.addAttribute("user", new User());
        return "/user/add-user";
    }

}
