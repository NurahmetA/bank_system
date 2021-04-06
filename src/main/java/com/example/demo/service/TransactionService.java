package com.example.demo.service;

import com.example.demo.models.TransactionModel;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.dto.Transaction;
import com.example.demo.service.handler.TransactionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CardRepository cardRepository) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
    }

    public void transfer(TransactionModel transactionModel) {
        new TransactionHandler(transactionRepository, cardRepository, transactionModel);
    }

    public ArrayList<Transaction> getAll(){
        return (ArrayList<Transaction>) transactionRepository.findAll();
    }

    public ArrayList<Transaction> getAllByCardId(long cardId){
        return (ArrayList<Transaction>) transactionRepository.getTransactionsByCard_CardId(cardId);
    }

}
