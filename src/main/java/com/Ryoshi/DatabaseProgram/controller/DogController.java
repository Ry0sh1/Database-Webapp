package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Dogs;
import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DogController {

    private final DogRepository dogRepository;

    public DogController(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Dogs dogs) {
        return "add-dogs";
    }

    @PostMapping("/addDogs")
    public String addDogs(@Valid Dogs dogs, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-dogs";
        }

        dogRepository.save(dogs);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Dogs dogs = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Dog Id:" + id));

        model.addAttribute("dogs", dogs);
        return "update-dogs";
    }

    @PostMapping("/update/{id}")
    public String updateDogs(@PathVariable("id") long id, @Valid Dogs dogs,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            dogs.setId(id);
            return "update-dogs";
        }

        dogRepository.save(dogs);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteDogs(@PathVariable("id") long id, Model model) {
        Dogs dogs = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Dog Id:" + id));
        dogRepository.delete(dogs);
        return "redirect:/index";
    }

    // additional CRUD methods
}
