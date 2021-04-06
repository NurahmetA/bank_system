package com.example.demo.service;

import com.example.demo.repository.BankRepository;
import com.example.demo.repository.dto.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository){
        this.bankRepository = bankRepository;
    }


    public List<Bank> getAllBanks(){
        return bankRepository.findAll();

    }

    public ArrayList<Bank> getAll() {
        ArrayList<Bank> banks =  (ArrayList<Bank>) bankRepository.findAll();
        for (Bank bank: banks) {
            bank.setCards(null);
        }
        return banks;
    }

    public Bank getById(long id) {
        Optional<Bank> bankOptional = bankRepository.findById(id);
        if(bankOptional.isPresent()){
            Bank bank =  bankOptional.get();
            bank.setCards(null);
            return bank;
        }
        return null;
    }

    public void create(Bank bank) {
        bankRepository.save(bank);
    }

    public void update(Bank bank) {
        bankRepository.save(bank);
    }

    public void delete(long id) {
        Bank bank = new Bank();
        bank.setBankId(id);
        bankRepository.delete(bank);
    }
}
