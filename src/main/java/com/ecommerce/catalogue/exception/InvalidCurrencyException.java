package com.ecommerce.catalogue.exception;

public final class InvalidCurrencyException extends IllegalArgumentException {
    public InvalidCurrencyException(String currency) {
        super("Devise non supportée : '%s'. Valeurs acceptées : FCFA, EUR.".formatted(currency));
    }
}
