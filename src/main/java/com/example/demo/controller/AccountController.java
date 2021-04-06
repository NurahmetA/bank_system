package com.example.demo.controller;

import com.example.demo.models.CardModel;
import com.example.demo.repository.dto.Account;
import com.example.demo.repository.dto.Bank;
import com.example.demo.repository.dto.Card;
import com.example.demo.service.AccountDetailsService;
import com.example.demo.service.BankService;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountDetailsService accountService;
    private CardService cardService;
    private BankService bankService;

    @Autowired
    public AccountController(CardService cardService, AccountDetailsService accountService, BankService bankService){
        this.cardService = cardService;
        this.accountService = accountService;
        this.bankService = bankService;
    }

    @GetMapping()
    public String accountPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Account account = accountService.getAccountByUsername(username);
        account.setPassword(null);

        ArrayList<Card> cards = cardService.getAllCardsByAccountId(account.getAccountId());
        model.addAttribute("account", account);
        model.addAttribute("cards", cards);

        ArrayList<String> currencies = new ArrayList<>();
        currencies.add("KZT");
        currencies.add("USD");
        currencies.add("EUR");
        model.addAttribute("currencies", currencies);

        ArrayList<Bank> banks = (ArrayList<Bank>) bankService.getAllBanks();
        model.addAttribute("banks", banks);

        model.addAttribute("cardForm", new CardModel());

        return "account";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable long id){
        accountService.delete(id);
        return "redirect:/admin";
    }




}
