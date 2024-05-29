package com.example.nasdaq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ScriptUtils;
import com.example.nasdaq.model.DTO.UserDto;
import com.example.nasdaq.model.Repository.UserRepository;
import com.example.nasdaq.service.UserService;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class authController {
    @Autowired
    private UserService userService;


    @GetMapping("/loginPage")
    public String getLoginPage(@RequestParam(value = "errorMessage", required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage);
        return "/login";
    }

    @GetMapping("/registerPage")
    public String getRegisterPage() throws Exception{
        return "/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserDto dto, HttpServletResponse response) throws Exception{
        log.info("[WebController][register] dto > " + dto.toString());
        userService.joinUser(dto, response);

        //ScriptUtils.alert(response, "회원가입에 성공하였습니다. 로그인 페이지로 이동합니다!");
        return "redirect:/loginPage";
    }
}

