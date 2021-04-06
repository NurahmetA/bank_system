package com.example.demo.models;

public class TransactionModel {

    private double money;
    private long senderId;
    private long receiverId;


    public TransactionModel(){}

    public TransactionModel(long money, long senderId, long receiverId) {
        this.money = money;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
    }


    @Override
    public String toString() {
        return "TransactionForm{" +
                "money=" + money +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }
}