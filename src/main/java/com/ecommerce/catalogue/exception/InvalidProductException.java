package com.ecommerce.catalogue.exception;

public final class InvalidProductException extends IllegalArgumentException {
    public InvalidProductException(String message) {
        super(message);
    }
}
