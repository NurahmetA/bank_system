package com.example.demo.controller;

import com.example.demo.models.CardModel;
import com.example.demo.service.AccountDetailsService;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/card")
public class CardController {
    private AccountDetailsService accountService;
    private CardService cardService;

    @Autowired
    public CardController(AccountDetailsService accountService, CardService cardService ){
        this.accountService = accountService;
        this.cardService = cardService;
    }


    @GetMapping()
    public String Card(Model model){
        model.addAttribute(new CardModel());
        return "card";
    }

    @PostMapping("/add")
    public String addCard(@ModelAttribute CardModel cardModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        accountService.getAccountByUsername(username);
        cardModel.setCardholderId(accountService.getAccountByUsername(username).getAccountId());

        cardService.addCard(cardModel);

        return "redirect:/account";
    }


    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable long id){
        cardService.deleteCard(id);
        return "redirect:/admin";
    }
}