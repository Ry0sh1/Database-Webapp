package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Dogs;
import com.Ryoshi.DatabaseProgram.model.Owner;
import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.MailRepository;
import com.Ryoshi.DatabaseProgram.repository.OwnerRepository;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/dogs")
@PreAuthorize("hasAuthority('ADMIN')")
public class DogController {

    private final DogRepository dogRepository;
    private final OwnerRepository ownerRepository;
    private final MailRepository mailRepository;
    private final UserRepository userRepository;

    public DogController(DogRepository dogRepository, OwnerRepository ownerRepository, MailRepository mailRepository, UserRepository userRepository) {
        this.dogRepository = dogRepository;
        this.ownerRepository = ownerRepository;
        this.mailRepository = mailRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showDogs(Model model, Principal principal){
        model.addAttribute("dogs", dogRepository.findAll());
        Set<String> t = new HashSet<>(dogRepository.getBreed());
        model.addAttribute("breeds", t);
        model.addAttribute("owner",ownerRepository.findAll());
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return "/dogs/dogs";
    }

    @GetMapping("/add-dogs")
    public String showSignUpForm(Dogs dogs, Model model, Principal principal) {
        model.addAttribute("owner", ownerRepository.findAll());
        model.addAttribute("dogs", new Dogs());
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
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
    public String showUpdateForm(@PathVariable("id") long id, Model model, Principal principal) {
        Dogs dogs = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Dog Id:" + id));

        model.addAttribute("owner", ownerRepository.findAll());
        model.addAttribute("dogs", dogs);
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
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
    public String deleteDogs(@PathVariable("id") long id, Model model, Principal principal) {
        Dogs dogs = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Dog Id:" + id));
        dogRepository.delete(dogs);
        return "redirect:/dogs";
    }

    @GetMapping("/filter/breed/{breed}")
    public String getFilteredByBreed(Model model, @PathVariable String breed, Principal principal){
        model.addAttribute("dogs", dogRepository.findAllByBreed(breed));
        createFilterModel(model, principal);
        return "dogs/dogs";
    }

    @GetMapping("/filter/owner/{owner}")
    public String getFilteredByOwner(Model model, @PathVariable Owner owner, Principal principal){
        model.addAttribute("dogs", dogRepository.findAllByOwner(owner));
        createFilterModel(model, principal);
        return "dogs/dogs";
    }

    private Model createFilterModel(Model model, Principal principal){
        Set<String> t = new HashSet<>(dogRepository.getBreed());
        model.addAttribute("breeds", t);
        model.addAttribute("owner",ownerRepository.findAll());
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return model;
    }

}
