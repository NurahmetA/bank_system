package com.example.demo.service.handler;

import com.example.demo.models.TransactionModel;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.dto.Card;
import com.example.demo.repository.dto.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class TransactionHandler extends Thread {
    private TransactionRepository transactionRepository;
    private TransactionModel transactionModel;
    private CardRepository cardRepository;

    public TransactionHandler(TransactionRepository transactionRepository, CardRepository cardRepository, TransactionModel transactionModel) {
        this.transactionRepository = transactionRepository;
        this.cardRepository = cardRepository;
        this.transactionModel = transactionModel;
        start();
    }

    public void run() {
        Optional<Card> senderOptionalCard = cardRepository.findById(transactionModel.getSenderId());
        Optional<Card> receiverOptionalCard = cardRepository.findById(transactionModel.getReceiverId());
        double money = transactionModel.getMoney();
        if (senderOptionalCard.isPresent() && receiverOptionalCard.isPresent()) {
            if (money > 0) {
                Card senderCard = senderOptionalCard.get();
                Card receiverCard = receiverOptionalCard.get();
                transfer(senderCard, receiverCard, money);
            }
        }
    }


    public synchronized void transfer(Card sender, Card receiver, double money) {

        if (isSameUser(sender, receiver)) {
            transferWithoutCommission(sender, receiver, money);
        }
        if (isSameBank(sender, receiver) && !isSameUser(sender, receiver)) {
            double convertedMoney = converter(money, sender.getCurrency(), "KZT");
            if (convertedMoney > 100000) {
                transferWithCommission(sender, receiver, money);
            } else {
                transferWithoutCommission(sender, receiver, money);
            }
        }
        if (!isSameBank(sender, receiver) && !isSameUser(sender, receiver)) {
            transferWithCommission(sender, receiver, money);
        }
    }

    private void transferWithoutCommission(Card sender, Card receiver, double money) {
        transferHandler(sender, receiver, money, money);
    }

    private void transferWithCommission(Card sender, Card receiver, double money) {
        double senderMoney = (money * 1.1);
        transferHandler(sender, receiver, money, senderMoney);
    }

    private void transferHandler(Card sender, Card receiver, double money, double senderMoney) {
        sender.setMoney(sender.getMoney() - senderMoney);
        double receiverMoney = converter(money, sender.getCurrency(), receiver.getCurrency());
        receiver.setMoney(receiver.getMoney() + receiverMoney);
        cardRepository.save(sender);
        cardRepository.save(receiver);
        saveTransaction(sender, receiver, senderMoney, receiverMoney);
    }


    private void saveTransaction(Card senderCard, Card receiverCard, double senderMoney, double receiverMoney) {

        Transaction transaction = new Transaction();
        transaction.setMethod("Receive");
        transaction.setMoney(receiverMoney);
        transaction.setCard(receiverCard);
        transaction.setCurrency(receiverCard.getCurrency());
        transactionRepository.save(transaction);
        logWriter(transaction);

        Transaction transaction1 = new Transaction();
        transaction1.setMethod("Send");
        transaction1.setMoney(senderMoney);
        transaction1.setCard(senderCard);
        transaction1.setCurrency(senderCard.getCurrency());
        transactionRepository.save(transaction1);
        logWriter(transaction1);
    }


    private boolean isSameBank(Card sender, Card receiver) {
        if (sender.getBank().getBankId() == receiver.getBank().getBankId()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSameUser(Card sender, Card receiver) {
        if (sender.getAccount().getAccountId() == receiver.getAccount().getAccountId()) {
            return true;
        } else {
            return false;
        }
    }

    private double converter(double money, String userCurrency, String wantedCurrency) {
        if (wantedCurrency.equals("KZT")) {
            if (userCurrency.equals("EUR")) {
                return money * 507;
            }
            if (userCurrency.equals("USD")) {
                return money * 417;
            }
        }

        if (wantedCurrency.equals("EUR")) {
            if (userCurrency.equals("KZT")) {
                return (money * 0.0019);
            }
            if (userCurrency.equals("USD")) {
                return (money * 0.82);
            }
        }

        if (wantedCurrency.equals("USD")) {
            if (userCurrency.equals("KZT")) {
                return (money * 0.0022);
            }
            if (userCurrency.equals("EUR")) {
                return (money * 1.21);
            }
        }
        return money;
    }


    public void logWriter(Transaction transaction){
        File file = new File("C:\\Users\\Nurahmet\\IdeaProjects\\demo\\logger.txt");
        String log = transaction.getTransactionId() + " " + transaction.getCurrency()
                + " "  + transaction.getMoney() + " "  + transaction.getCard().getCardId() + " "
                + transaction.getMethod();
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(log);
            fileWriter.append(System.getProperty("line.separator"));
            fileWriter.flush();
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
