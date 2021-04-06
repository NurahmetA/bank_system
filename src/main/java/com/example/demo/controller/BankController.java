package com.example.demo.controller;

import com.example.demo.repository.dto.Bank;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bank")
public class BankController {
    private BankService bankService;

    @Autowired
    public BankController(BankService bankService){
        this.bankService = bankService;
    }

    @PostMapping
    public String create(@ModelAttribute Bank bank){
        bankService.create(bank);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable long id){
        bankService.delete(id);
        return "redirect:/admin";
    }

}
