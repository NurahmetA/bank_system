package com.example.demo.restcontroller;

import com.example.demo.repository.dto.Bank;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/bank")
public class BankRestController {
    private BankService bankService;

    @Autowired
    public BankRestController(BankService bankService){
        this.bankService = bankService;
    }

    @GetMapping(produces = "application/json", consumes = "application/json")
    public ArrayList<Bank> getAll(){
        return bankService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public Bank getById(@PathVariable int id){
        return bankService.getById(id);
    }


    @PostMapping(produces = "application/json", consumes = "application/json")
    public void create(@RequestBody Bank bank){
        bankService.create(bank);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public void update(@RequestBody Bank bank){
        bankService.update(bank);
    }

    @DeleteMapping(value ="/{id}", produces = "application/json", consumes = "application/json" )
    public void delete(@PathVariable int id){
        bankService.delete(id);
    }



}
