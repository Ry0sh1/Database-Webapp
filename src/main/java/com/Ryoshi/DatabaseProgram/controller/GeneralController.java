package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.repository.MailRepository;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class GeneralController {

    private final MailRepository mailRepository;
    private final UserRepository userRepository;

    public GeneralController(MailRepository mailRepository, UserRepository userRepository) {
        this.mailRepository = mailRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String showLogIn(Model model, Principal principal){
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return "index";
    }
    @GetMapping("/home")
    public String showHome(Model model, Principal principal){
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return "index";
    }
    @GetMapping("/error")
    public String showError(){
        return "error";
    }

}
