package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.model.Event;
import com.Ryoshi.DatabaseProgram.repository.DogRepository;
import com.Ryoshi.DatabaseProgram.repository.EventRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final EventRepository eventRepository;
    private final DogRepository dogRepository;

    public CalendarController(EventRepository eventRepository, DogRepository dogRepository){
        this.eventRepository = eventRepository;
        this.dogRepository = dogRepository;
    }

    @GetMapping
    public String calendar(Model model){
        model.addAttribute("events", eventRepository.findAll());
        return "calendar/calendar";
    }

    @GetMapping("/{year}/{month}/{day}")
    public String getDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model){
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("events", eventRepository.findAllByDate(day,month,year));
        return "calendar/day";
    }

    @GetMapping("/{year}/{month}/{day}/add-event")
    public String showAddEvent(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model){
        model.addAttribute("dogs",dogRepository.findAll());
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("event", new Event());
        return "calendar/add-event";
    }

    @PostMapping("/add-event")
    public String addEvent(@Valid Event event, Model model){
        eventRepository.save(event);
        return "redirect:/calendar/" + event.getEvent_year() + "/" + event.getEvent_month() + "/" + event.getEvent_day();
    }

    @GetMapping("/update-event/{id}")
    public String showUpdateEventWindow(@PathVariable long id, Model model){
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Owner Id:" + id));
        model.addAttribute("event",event);
        model.addAttribute("dogs",dogRepository.findAll());
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
