package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Owner;
import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController {

    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository, DogRepository dogRepository) {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/newOwner")
    public String newOwner(Owner owner){
        return "add-owner";
    }

    @PostMapping("/add-owner")
    public String addOwner(@Valid Owner owner){
        ownerRepository.save(owner);
        return "redirect:/index";
    }

}
