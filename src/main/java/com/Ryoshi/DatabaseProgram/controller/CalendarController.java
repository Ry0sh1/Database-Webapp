package com.Ryoshi.DatabaseProgram.controller;

import com.Ryoshi.DatabaseProgram.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CalendarController {

    private final EventRepository eventRepository;

    public CalendarController(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar/calendar";
    }

    @GetMapping("/calendar/{year}/{month}/{day}")
    public String getDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model){
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        String date = year + "" + month + "" + day;
        model.addAttribute("events", eventRepository.findAllByDate(date));
        return "calendar/day";
    }

    @GetMapping("/calendar/{year}/{month}/{day}/add-event")
    public String addEvent(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model){
        return "calendar/add-event";
    }

}
