package com.Ryoshi.DatabaseProgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalendarController {

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar/calendar";
    }

}
