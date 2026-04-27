package com.ecommerce.catalogue.domain.entity;

public class Transaction {

    private String id;
    private String devise;
    private Double amount;

    public Transaction(String id, String devise, Double amount) {
        this.id = id;
        this.devise = devise;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getDevise() {
        return devise;
    
    }
    public Double getAmount() {
        return amount;
    }
}
