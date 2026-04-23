package com.ecommerce.catalogue.exception;

public final class InvalidSKUException extends IllegalArgumentException {
    public InvalidSKUException(String value) {
        super("Format SKU invalide : '%s'. Format attendu : 3 lettres majuscules + tiret + 4 à 6 chiffres (ex: TEC-10204).".formatted(value));
    }
}
