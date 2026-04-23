package com.ecommerce.catalogue.exception;

public final class CurrencyMismatchException extends IllegalArgumentException {
    public CurrencyMismatchException(String left, String right) {
        super("Impossible d'additionner des montants de devises différentes : %s + %s.".formatted(left, right));
    }
}
