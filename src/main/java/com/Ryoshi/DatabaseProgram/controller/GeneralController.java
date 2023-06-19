package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.OwnerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    private final OwnerRepository ownerRepository;
    private final DogRepository dogRepository;

    public GeneralController(OwnerRepository ownerRepository, DogRepository dogRepository) {
        this.ownerRepository = ownerRepository;
        this.dogRepository = dogRepository;
    }

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("owner", ownerRepository.findAll());
        model.addAttribute("dogs", dogRepository.findAll());
        return "index";
    }

    @GetMapping("/index")
    public String showIndex(Model model){
        model.addAttribute("owner", ownerRepository.findAll());
        model.addAttribute("dogs", dogRepository.findAll());
        return "index";
    }

}
