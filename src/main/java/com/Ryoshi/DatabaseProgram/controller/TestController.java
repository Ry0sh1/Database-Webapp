package com.Ryoshi.DatabaseProgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test/api")
public class TestController {

    @GetMapping
    public String testUsername(Principal principal){
        return "Hallo " + principal.getName();
    }

}
