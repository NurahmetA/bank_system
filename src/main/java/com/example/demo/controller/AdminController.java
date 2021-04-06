package com.example.demo.controller;

import com.example.demo.repository.dto.Bank;
import com.example.demo.service.AccountDetailsService;
import com.example.demo.service.BankService;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private AccountDetailsService accountDetailsService;
    private BankService bankService;
    private CardService cardService;

    @Autowired
    public AdminController(AccountDetailsService accountDetailsService, BankService bankService, CardService cardService) {
        this.accountDetailsService = accountDetailsService;
        this.bankService = bankService;
        this.cardService = cardService;
    }

    @GetMapping
    public String admin(Model model){
        model.addAttribute("bankForm", new Bank());
        model.addAttribute("accounts", accountDetailsService.getAll());
        model.addAttribute("banks", bankService.getAll());
        model.addAttribute("cards", cardService.getAll());

        return "admin";
    }


}