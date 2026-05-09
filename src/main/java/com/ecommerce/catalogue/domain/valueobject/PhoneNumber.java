package com.ecommerce.catalogue.domain.valueobject;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null || !isValid(value)) {
            throw new IllegalArgumentException("Numéro sénégalais invalide");
        }
    }

    private static boolean isValid(String number) {
        // Formats acceptés : +22177XXXXXXX, 77XXXXXXX etc.
        return number.matches("^(\\+221)?(77|78|70|76)\\d{7}$");
    }
}
