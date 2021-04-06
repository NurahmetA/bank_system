package com.example.demo.controller;

import com.example.demo.repository.dto.Account;
import com.example.demo.service.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class RegistrationController {

    private AccountDetailsService accountService;


    @Autowired
    public RegistrationController(AccountDetailsService accountService){
        this.accountService = accountService;
    }

    @GetMapping()
    public String signUpPage(Model model){
        model.addAttribute("accountForm", new Account());
        return "registration";
    }


    @PostMapping("/add")
    public String addAccount(@ModelAttribute("accountForm") Account account){
        accountService.signup(account);
        return "redirect:/login";
    }
}