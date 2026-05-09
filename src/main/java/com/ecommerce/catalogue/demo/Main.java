package com.ecommerce.catalogue.demo;

import java.math.BigDecimal;

import com.ecommerce.catalogue.domain.entity.Account;
import com.ecommerce.catalogue.domain.valueobject.Money1;
import com.ecommerce.catalogue.domain.valueobject.PhoneNumber;

public class Main {
    public static void main(String[] args) {
       
        try{

            Money1 invalid = new Money1(new BigDecimal("-10"), "USD"); 
        } catch (IllegalArgumentException e) {
             System.out.println("Erreur attendue (montant négatif) : " + e.getMessage());
        }

         try {
            PhoneNumber p1 = new PhoneNumber("771234567");
            PhoneNumber p2 = new PhoneNumber("+221781234567");

            Account acc1 = new Account(p1, new Money1(new BigDecimal("10000"), "XOF"));
            Account acc2 = new Account(p2, new Money1(new BigDecimal("5000"), "EUR"));

          
            acc1.transfererVers(acc2, new Money1(new BigDecimal("1000"), "XOF"));
        } catch (Exception e) {
            System.out.println("Erreur attendue (devise différente) : " + e.getMessage());
        }

         try {
            PhoneNumber p1 = new PhoneNumber("701234567");
            PhoneNumber p2 = new PhoneNumber("761234567");

            Account acc1 = new Account(p1, new Money1(new BigDecimal("1000"), "XOF"));
            Account acc2 = new Account(p2, new Money1(new BigDecimal("5000"), "XOF"));

           
            acc1.transfererVers(acc2, new Money1(new BigDecimal("2000"), "XOF"));

        } catch (Exception e) {
            System.out.println("Erreur attendue (solde insuffisant) : " + e.getMessage());
        }
    }

}
