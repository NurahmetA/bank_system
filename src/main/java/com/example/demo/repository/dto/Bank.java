package com.example.demo.repository.dto;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankId;
    @Column(name = "bankname")
    private String bankName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bank", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Card> cards;


    public Bank() {
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long id) {
        this.bankId = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}