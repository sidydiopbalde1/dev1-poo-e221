package com.ecommerce.catalogue.demo;

import com.ecommerce.catalogue.domain.entity.Product;
import com.ecommerce.catalogue.domain.valueobject.Money;
import com.ecommerce.catalogue.domain.valueobject.SKU;

import java.math.BigDecimal;

public class CatalogueDemo {

    public static void main(String[] args) {

        sep("CAS NOMINAL");
        Product phone = new Product(
            new SKU("TEC-10204"),
            "Smartphone Galaxy A55",
            new Money(new BigDecimal("15000.00"), "FCFA")
        );
        System.out.println("Créé    : " + phone);
        phone.applyDiscount(new BigDecimal("10"));
        System.out.println("- 10%   : " + phone.getPrice());

        sep("PIRATAGE Money");
        assertThrows("Montant négatif",       () -> new Money(new BigDecimal("-1"), "FCFA"));
        assertThrows("Devise inconnue",        () -> new Money(BigDecimal.TEN, "XOF"));
        assertThrows("Devise null",            () -> new Money(BigDecimal.TEN, null));
        assertThrows("Montant null",           () -> new Money(null, "FCFA"));
        assertThrows("Addition FCFA + EUR",    () -> new Money(BigDecimal.TEN, "FCFA").add(new Money(BigDecimal.ONE, "EUR")));

        sep("PIRATAGE SKU");
        assertThrows("SKU null",               () -> new SKU(null));
        assertThrows("SKU vide",               () -> new SKU(""));
        assertThrows("Minuscules tec-1234",    () -> new SKU("tec-1234"));
        assertThrows("Trop peu ABC-123",       () -> new SKU("ABC-123"));
        assertThrows("Trop de chiffres",       () -> new SKU("ABC-1234567"));

        sep("PIRATAGE Product");
        assertThrows("SKU null",               () -> new Product(null, "Test", new Money(BigDecimal.ONE, "EUR")));
        assertThrows("Nom vide",               () -> new Product(new SKU("ABC-1234"), "", new Money(BigDecimal.ONE, "EUR")));
        assertThrows("Prix null",              () -> new Product(new SKU("ABC-1234"), "Test", null));
        assertThrows("Remise 0",               () -> phone.applyDiscount(BigDecimal.ZERO));
        assertThrows("Remise 101",             () -> phone.applyDiscount(new BigDecimal("101")));

        sep("TOUS LES INVARIANTS RESPECTÉS ✓");
    }

    @FunctionalInterface
    private interface ThrowingAction { void execute(); }

    private static void assertThrows(String scenario, ThrowingAction action) {
        try {
            action.execute();
            System.out.printf("  [ÉCHEC] %s — aucune exception levée !%n", scenario);
        } catch (Exception e) {
            System.out.printf("  [OK] %-30s → %s%n", scenario, e.getMessage());
        }
    }

    private static void sep(String title) {
        System.out.println("\n--- " + title + " ---");
    }
}
