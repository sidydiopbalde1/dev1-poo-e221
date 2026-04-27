package com.ecommerce.catalogue.domain.entity;

import com.ecommerce.catalogue.domain.valueobject.Money;
import com.ecommerce.catalogue.domain.valueobject.SKU;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public final class Catalogue {

    private final List<Product> products = new ArrayList<>();

    // -------------------------------------------------------------------------
    // Mutations
    // -------------------------------------------------------------------------

    public void add(Product product) {
        if (product == null) throw new IllegalArgumentException("Produit null interdit.");
        products.add(product);
    }

    // -------------------------------------------------------------------------
    // Streams — lecture seule (vue non modifiable)
    // -------------------------------------------------------------------------

    /** Tous les produits d'une devise donnée. */
    public List<Product> findByCurrency(String currency) {
        return products.stream()
            .filter(p -> p.getPrice().currency().equals(currency))
            .toList();
    }

    /** Tous les produits triés par prix croissant. */
    public List<Product> sortedByPriceAsc() {
        return products.stream()
            .sorted(Comparator.comparing(p -> p.getPrice().amount()))
            .toList();
    }

    /** Produit le moins cher. */
    public Optional<Product> cheapest() {
        return products.stream()
            .min(Comparator.comparing(p -> p.getPrice().amount()));
    }

    /** Produit le plus cher. */
    public Optional<Product> mostExpensive() {
        return products.stream()
            .max(Comparator.comparing(p -> p.getPrice().amount()));
    }

    /**
     * Somme totale d'une devise.
     * Lève une exception si on mélange les devises dans le reduce
     * (Money.add() s'en charge).
     */
    public Optional<Money> totalByCurrency(String currency) {
        return products.stream()
            .map(Product::getPrice)
            .filter(m -> m.currency().equals(currency))
            .reduce(Money::add);
    }

    /** Produits groupés par devise. */
    public Map<String, List<Product>> groupByCurrency() {
        return products.stream()
            .collect(Collectors.groupingBy(p -> p.getPrice().currency()));
    }

    /** Liste de tous les SKUs du catalogue. */
    public List<String> allSkus() {
        return products.stream()
            .map(p -> p.getSku().value())
            .toList();
    }

    /** Vérifie si un SKU est déjà présent dans le catalogue. */
    public boolean skuExists(SKU sku) {
        return products.stream()
            .anyMatch(p -> p.getSku().equals(sku));
    }

    /** Applique une remise à tous les produits d'une devise. */
    public void applyDiscountByCurrency(String currency, BigDecimal percentage) {
        products.stream()
            .filter(p -> p.getPrice().currency().equals(currency))
            .forEach(p -> p.applyDiscount(percentage));
    }

    public int size() { return products.size(); }
}
