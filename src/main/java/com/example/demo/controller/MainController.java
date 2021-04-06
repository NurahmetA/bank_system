package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping()
    public String homePage(){
        return "home";
    }

    @GetMapping("/login")
    public String authorization(){
        return "login";
    }

    @GetMapping("/logout")
    public String logOut(){
        return "home";
    }


}