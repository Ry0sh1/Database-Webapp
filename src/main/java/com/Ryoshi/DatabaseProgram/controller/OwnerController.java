package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Owner;
import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.OwnerRepository;
import com.Ryoshi.DatabaseProgram.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
@PreAuthorize("hasAuthority('ADMIN')")
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private final DogRepository dogRepository;
    private final UserRepository userRepository;

    public OwnerController(OwnerRepository ownerRepository, DogRepository dogRepository, DogRepository dogRepository1,
                           UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.dogRepository = dogRepository1;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showOwner(Model model){
        model.addAttribute("owner", ownerRepository.findAll());
        return "owner/owner";
    }

    @GetMapping("/new-owner")
    public String newOwner(Owner owner, Model model){
        model.addAttribute("user", userRepository.findAll());
        model.addAttribute("owner", new Owner());
        return "owner/add-owner";
    }

    @PostMapping("/add-owner")
    public String addOwner(@Valid Owner owner, BindingResult result, Model model){
        if(result.hasErrors()){
            return "owner/add-owner";
        }
        owner.getUser().setOwner(owner);
        ownerRepository.save(owner);
        return "redirect:/owner";
    }

    @GetMapping("/update-owner/{id}")
    public String ownerById(@PathVariable long id, Model model){
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Owner Id:" + id));
        model.addAttribute("owner", owner);
        return "owner/update-owner";
    }

    @PostMapping("/update-owner/{id}")
    public String updateOwner(@PathVariable long id, BindingResult result, Owner owner, Model model){
        if (result.hasErrors()){
            owner.setId(id);
            return "owner/update-owner";
        }
        ownerRepository.save(owner);
        return "redirect:/owner";
    }

    @GetMapping("/delete-owner/{id}")
    public String deleteOwner(@PathVariable long id, Model model){
        dogRepository.deleteAllByOwnerId(id);
        ownerRepository.deleteById(id);
        return "redirect:/owner";
    }

}
