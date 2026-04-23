package com.ecommerce.catalogue.exception;

public final class InvalidDiscountException extends IllegalArgumentException {
    public InvalidDiscountException(double percentage) {
        super("Pourcentage de remise invalide : %.2f. La valeur doit être comprise entre 0.1 et 100.".formatted(percentage));
    }
}
