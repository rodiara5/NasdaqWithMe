package com.example.nasdaq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class graph {
    
    @GetMapping("/wc")
    public String wordcloud() {
        return "cloud";
    }

    @GetMapping("/bar")
    public String bar() {
        return "bar";
    }

    @GetMapping("/network")
    public String network() {
        return "network";
    }
}
