package com.Ryoshi.DatabaseProgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @GetMapping("/index")
    public String showLogIn(Model model){
        return "index";
    }
    @GetMapping("/home")
    public String showHome(Model model){
        return "home";
    }

}
