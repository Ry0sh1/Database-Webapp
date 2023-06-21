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

@Controller
public class CalendarController {

    private final EventRepository eventRepository;
    private final DogRepository dogRepository;

    public CalendarController(EventRepository eventRepository, DogRepository dogRepository){
        this.eventRepository = eventRepository;
        this.dogRepository = dogRepository;
    }

    @GetMapping("/calendar")
    public String calendar(Model model){
        model.addAttribute("events", eventRepository.findAll());
        return "calendar/calendar";
    }

    @GetMapping("/calendar/{year}/{month}/{day}")
    public String getDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model){
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("events", eventRepository.findAllByDate(day,month,year));
        return "calendar/day";
    }

    @GetMapping("/calendar/{year}/{month}/{day}/add-event")
    public String showAddEvent(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model){
        model.addAttribute("dogs",dogRepository.findAll());
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("event", new Event());
        return "calendar/add-event";
    }

    @PostMapping("/calendar/add-event")
    public String addEvent(@Valid Event event, Model model){
        eventRepository.save(event);
        return "redirect:/calendar/" + event.getEvent_year() + "/" + event.getEvent_month() + "/" + event.getEvent_day();
    }

}
