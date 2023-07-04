package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Event;
import com.Ryoshi.DatabaseProgram.repository.*;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final EventRepository eventRepository;
    private final DogRepository dogRepository;
    private final MailRepository mailRepository;
    private final UserRepository userRepository;
    private final OwnerRepository ownerRepository;

    public CalendarController(EventRepository eventRepository, DogRepository dogRepository, MailRepository mailRepository, UserRepository userRepository,
                              OwnerRepository ownerRepository){
        this.eventRepository = eventRepository;
        this.dogRepository = dogRepository;
        this.mailRepository = mailRepository;
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping
    public String calendar(Model model, Principal principal){
        model.addAttribute("events", eventRepository.findAllByAuthor(userRepository.findByUsername(principal.getName())
                .orElseThrow(()->new UsernameNotFoundException("Not logged in"))));
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return "calendar/calendar";
    }

    @GetMapping("/{year}/{month}/{day}")
    public String getDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model, Principal principal){
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("events", eventRepository.findAllByDate(day,month,year, userRepository.findByUsername(principal.getName())
                .orElseThrow(()->new UsernameNotFoundException("Not logged in"))));
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return "calendar/day";
    }

    @GetMapping("/{year}/{month}/{day}/add-event")
    public String showAddEvent(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model, Principal principal){
        model.addAttribute("dogs", dogRepository.findAllByOwner(ownerRepository.findByUser(userRepository.findByUsername(principal.getName())
                .orElseThrow(()-> new UsernameNotFoundException("Not logged in")))));
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("event", new Event());
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return "calendar/add-event";
    }

    @PostMapping("/add-event")
    public String addEvent(@Valid Event event, Model model, Principal principal){
        event.setAuthor(userRepository.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("Not logged in")));
        eventRepository.save(event);
        return "redirect:/calendar/" + event.getEvent_year() + "/" + event.getEvent_month() + "/" + event.getEvent_day();
    }

    @GetMapping("/update-event/{id}")
    public String showUpdateEventWindow(@PathVariable long id, Model model, Principal principal){
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Owner Id:" + id));
        model.addAttribute("event",event);
        model.addAttribute("dogs",dogRepository.findAll());
        model.addAttribute("unreadMailCount", mailRepository.countAllByViewedAndRecipient(false,userRepository.findByUsername(principal.getName())
                .orElseThrow()));
        return "calendar/update-event";
    }

    @PostMapping("/update-event/{id}")
    public String updateEvent(@PathVariable long id, @Valid Event event, Model model){
        eventRepository.save(event);
        return "redirect:/calendar/" + event.getEvent_year() + "/" + event.getEvent_month() + "/" + event.getEvent_day();
    }

    @GetMapping("/delete-event/{id}")
    public String deleteEvent(@PathVariable long id, Model model){
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Owner Id:" + id));
        String ret = "redirect:/calendar/" + event.getEvent_year() + "/" + event.getEvent_month() + "/" + event.getEvent_day();
        eventRepository.deleteById(id);
        return ret;
    }

}
