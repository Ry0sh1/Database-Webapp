package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Dogs;
import com.Ryoshi.DatabaseProgram.model.Owner;
import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.OwnerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class DogController {

    private final DogRepository dogRepository;
    private final OwnerRepository ownerRepository;

    public DogController(DogRepository dogRepository, OwnerRepository ownerRepository) {
        this.dogRepository = dogRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/dogs")
    public String showDogs(Model model){
        model.addAttribute("dogs", dogRepository.findAll());
        Set<String> t = new HashSet<>(dogRepository.getBreed());
        model.addAttribute("breeds", t);
        model.addAttribute("owner",ownerRepository.findAll());
        return "/dogs/dogs";
    }

    @GetMapping("/add-dogs")
    public String showSignUpForm(Dogs dogs, Model model) {
        model.addAttribute("owner", ownerRepository.findAll());
        model.addAttribute("dogs", new Dogs());
        return "dogs/add-dogs";
    }

    @PostMapping("/add-dogs")
    public String addDogs(@Valid Dogs dogs, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "dogs/add-dogs";
        }
        dogRepository.save(dogs);
        return "redirect:/dogs";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Dogs dogs = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Dog Id:" + id));

        model.addAttribute("owner", ownerRepository.findAll());
        model.addAttribute("dogs", dogs);
        return "dogs/update-dogs";
    }

    @PostMapping("/update/{id}")
    public String updateDogs(@PathVariable("id") long id, @Valid Dogs dogs,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            dogs.setId(id);
            return "dogs/update-dogs";
        }

        dogRepository.save(dogs);
        return "redirect:/dogs";
    }

    @GetMapping("/delete/{id}")
    public String deleteDogs(@PathVariable("id") long id, Model model) {
        Dogs dogs = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Dog Id:" + id));
        dogRepository.delete(dogs);
        return showDogs(model);
    }

    @GetMapping("/dogs/filter/breed/{breed}")
    public String getFilteredByBreed(Model model, @PathVariable String breed){
        model.addAttribute("dogs", dogRepository.findAllByBreed(breed));
        Set<String> t = new HashSet<>(dogRepository.getBreed());
        model.addAttribute("breeds", t);
        model.addAttribute("owner",ownerRepository.findAll());
        return "dogs/dogs";
    }

    @GetMapping("/dogs/filter/owner/{owner}")
    public String getFilteredByOwner(Model model, @PathVariable Owner owner){
        model.addAttribute("dogs", dogRepository.findAllByOwner(owner));
        Set<String> t = new HashSet<>(dogRepository.getBreed());
        model.addAttribute("breeds", t);
        model.addAttribute("owner",ownerRepository.findAll());
        return "dogs/dogs";
    }

}
