package com.ecommerce.catalogue.domain.entity;

import java.util.UUID;

import com.ecommerce.catalogue.domain.valueobject.Money1;
import com.ecommerce.catalogue.domain.valueobject.PhoneNumber;

public class Account {

    private final UUID id;
    private Money1 balance;
    private final PhoneNumber phoneNumber;

    public Account(PhoneNumber phoneNumber, Money1 initialBalance) {
        if (initialBalance == null) {
            throw new IllegalArgumentException("Solde initial requis");
        }

        this.id = UUID.randomUUID();
        this.phoneNumber = phoneNumber;
        this.balance = initialBalance;
    }

    public UUID getId() {
        return id;
    }

    public Money1 getBalance() {
        return balance;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

 
    public void crediter(Money1 montant) {
        this.balance = this.balance.add(montant);
    }

   
    public void debiter(Money1 montant) {
        this.balance = this.balance.subtract(montant);
    }

    
    public void transfererVers(Account destinataire, Money1 montant) {
        if (destinataire == null) {
            throw new IllegalArgumentException("Destinataire invalide");
        }

        // Vérifie devise automatiquement via Money1
        this.debiter(montant);
        destinataire.crediter(montant);
    }
}
