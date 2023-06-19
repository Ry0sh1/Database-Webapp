package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Owner;
import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private final DogRepository dogRepository;

    public OwnerController(OwnerRepository ownerRepository, DogRepository dogRepository, DogRepository dogRepository1) {
        this.ownerRepository = ownerRepository;
        this.dogRepository = dogRepository1;
    }

    @GetMapping("/owner")
    public String showOwner(Model model){
        model.addAttribute("owner", ownerRepository.findAll());
        return "/owner/owner";
    }

    @GetMapping("/new-owner")
    public String newOwner(Owner owner){
        return "/owner/add-owner";
    }

    @PostMapping("/add-owner")
    public String addOwner(@Valid Owner owner){
        ownerRepository.save(owner);
        return "redirect:/index";
    }

    @GetMapping("/update-owner/{id}")
    public String ownerById(@PathVariable long id, Model model){
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Owner Id:" + id));
        model.addAttribute("owner", owner);
        return "/owner/update-owner";
    }

    @PostMapping("/update-owner/{id}")
    public String updateOwner(@PathVariable long id, Owner owner){
        ownerRepository.save(owner);
        return "redirect:/index";
    }

    @GetMapping("/delete-owner/{id}")
    public String deleteOwner(@PathVariable long id){
        dogRepository.deleteAllByOwnerId(id);
        ownerRepository.deleteById(id);
        return "redirect:/index";
    }

}
