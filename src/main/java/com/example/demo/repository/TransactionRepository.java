package com.example.demo.repository;

import com.example.demo.repository.dto.Card;
import com.example.demo.repository.dto.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findByCard(Card card);

    List<Transaction> getTransactionsByCard_CardId(long cardId);
}
