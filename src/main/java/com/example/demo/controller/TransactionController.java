package com.example.demo.controller;

import com.example.demo.models.TransactionModel;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public String doTransaction(@PathVariable long id, Model model){
        model.addAttribute("senderId", id);
        model.addAttribute("transactionForm", new TransactionModel());
        return "transaction";
    }


    @GetMapping("/get/{id}")
    public String getAllByCardId(@PathVariable long id, Model model){
        model.addAttribute("transactions", transactionService.getAllByCardId(id));
        return "cardtransactions";
    }



    @PostMapping(value = "/transfer")
    public String transfer(@ModelAttribute("transactionForm") TransactionModel transactionModel, @ModelAttribute("senderId") Long id){
        transactionModel.setSenderId(id);
        transactionService.transfer(transactionModel);
        System.out.println(transactionModel);
        return "redirect:/account";
    }
}