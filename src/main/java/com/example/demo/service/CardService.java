package com.example.demo.service;

import com.example.demo.models.CardModel;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.dto.Account;
import com.example.demo.repository.dto.Bank;
import com.example.demo.repository.dto.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CardService {

    private CardRepository cardRepository;
    private BankRepository bankRepository;
    @Autowired
    public CardService(CardRepository cardRepository, BankRepository bankRepository) {
        this.cardRepository = cardRepository;
        this.bankRepository = bankRepository;
    }
    public ArrayList<Card> getAll(){
        return (ArrayList<Card>) cardRepository.findAll();
    }

    public void addCard(CardModel cardModel) {
        Card card = new Card();
        Account account = new Account();
        account.setAccountId(cardModel.getCardholderId());
        Bank bank = new Bank();
        bank.setBankId(cardModel.getManufacturerId());
        card.setAccount(account);
        card.setBank(bank);
        card.setCurrency(cardModel.getCurrency());
        card.setMoney(0);
        cardRepository.save(card);
    }
    public void deleteCard(long id) {
        cardRepository.delete(cardRepository.findById(id).get());
    }
    public ArrayList<Card> getAllCardsByAccountId(long accountId) {
        return (ArrayList<Card>) cardRepository.findCardsByAccount_AccountId(accountId);
    }
}
