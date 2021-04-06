package com.example.demo.restcontroller;

import com.example.demo.repository.dto.Account;
import com.example.demo.service.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
    private AccountDetailsService accountService;

    @Autowired
    public AccountRestController(AccountDetailsService accountService){
        this.accountService = accountService;
    }

    @GetMapping(produces = "application/json", consumes = "application/json")
    public ArrayList<Account> getAll(){
        return accountService.getAll();
    }

    @GetMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public Account getById(@PathVariable int id){
        return accountService.getById(id);
    }


    @PostMapping(produces = "application/json", consumes = "application/json")
    public void create(@RequestBody Account account){
        accountService.create(account);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public void update(@RequestBody Account account){
        accountService.update(account);
    }

    @DeleteMapping(value ="/{id}", produces = "application/json", consumes = "application/json" )
    public void delete(@PathVariable int id){
        accountService.delete(id);
    }

}
