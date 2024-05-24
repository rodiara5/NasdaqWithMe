package com.example.nasdaq.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/errors")
public class CustomErrorController implements ErrorController{
    
    @GetMapping
    public String handleError(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        
        if (statusCode != null) {
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500"; // error-500.html
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error-400"; // error-400.html
            }
        }
        return "error-ticker";
    }
}
