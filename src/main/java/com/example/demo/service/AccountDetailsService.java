package com.example.demo.service;

import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.dto.Account;
import com.example.demo.repository.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
public class AccountDetailsService implements UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;

    @Autowired
    public AccountDetailsService(AccountRepository accountRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null){
            throw new UsernameNotFoundException("Account with such username doesn't exit");
        }
        return account;
    }

    public void signup(Account account) {
        Account accountDB = accountRepository.findByUsername(account.getUsername());
        if(accountDB == null){
            account.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
            account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
            accountRepository.save(account);
        }
    }


    public Account getAccountByUsername(String username){
        return accountRepository.findByUsername(username);
    }


    public ArrayList<Account> getAll(){
        ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findAll();
        for (Account account : accounts){
            account.setCards(null);
        }
        return accounts;
    }


    public void create(Account account) {
        accountRepository.save(account);
    }

    public void update(Account account) {
        accountRepository.save(account);
    }

    public void delete(long id){
        Account account = new Account();
        account.setAccountId(id);
        accountRepository.delete(account);
    }

    public Account getById(int id) {
        Optional<Account> accountOptional = accountRepository.findById((long) id);
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            account.setCards(null);
            return account;
        }
        return null;
    }


}
