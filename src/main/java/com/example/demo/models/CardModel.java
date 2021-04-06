package com.example.demo.models;

public class CardModel {
    private int manufacturerId;
    private long cardholderId;
    private long money;
    private String currency;

    public CardModel(){}
    public CardModel(int manufacturerId, long cardholderId, long money, String currency){
        this.manufacturerId = manufacturerId;
        this.cardholderId = cardholderId;
        this.money = money;
        this.currency = currency;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public long getCardholderId() {
        return cardholderId;
    }

    public void setCardholderId(long cardholderId) {
        this.cardholderId = cardholderId;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}