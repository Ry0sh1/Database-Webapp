package com.Ryoshi.DatabaseProgram.controller;


import com.Ryoshi.DatabaseProgram.model.User;
import com.Ryoshi.DatabaseProgram.repository.MailRepository;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailRepository mailRepository;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, MailRepository mailRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailRepository = mailRepository;
    }

    @GetMapping
    public String showUser(Model model, Principal principal){
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(true,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        model.addAttribute("users",userRepository.findAll());
        return "/user/user";
    }

    @PostMapping("/add-user")
    public String addNewUser(User user, BindingResult result){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/user/add-user";
    }

    @GetMapping("/add-user")
    public String showAddUser(Model model, Principal principal){
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        model.addAttribute("user", new User());
        return "/user/add-user";
    }

}
