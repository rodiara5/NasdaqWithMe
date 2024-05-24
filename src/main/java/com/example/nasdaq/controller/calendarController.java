package com.example.nasdaq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/nasdaq")
@Controller
public class calendarController {
    @GetMapping("/calendar")
    public String move_calendar(){
        return "calendar";
    }
}
