package com.Ryoshi.DatabaseProgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @GetMapping("/index")
    public String showIndex(Model model){
        return "index";
    }

}
