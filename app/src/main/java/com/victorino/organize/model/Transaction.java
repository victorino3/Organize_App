package com.victorino.organize.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.victorino.organize.config.FirebaseConfig;
import com.victorino.organize.helper.DateCustom;
import com.victorino.organize.helper.EncodeB64;

public class Transaction {
    private double money;
    private String category;
    private String description;
    private String date;

    public Transaction() {
    }

    public void saveTransactionExpense(String dateToSplit){
        FirebaseAuth auth = FirebaseConfig.getAuth();
        String idUser = EncodeB64.encodeMail(auth.getCurrentUser().getEmail());
        String mothYear = DateCustom.splitDate(dateToSplit);
        DatabaseReference movement = FirebaseConfig.getUserConn();
        movement.child("Transaction")
                .child(idUser)
                .child(mothYear)
                .push()
                .setValue(this);
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
