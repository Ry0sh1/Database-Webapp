package com.Ryoshi.DatabaseProgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar/calendar";
    }

    @GetMapping("/calendar/{year}/{month}/{day}")
    public String getDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, Model model){
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        return "calendar/day";
    }

}
