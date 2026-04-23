package com.ecommerce.catalogue.domain.valueobject;

import com.ecommerce.catalogue.exception.InvalidSKUException;

import java.util.regex.Pattern;

public record SKU(String value) {

    private static final Pattern SKU_PATTERN = Pattern.compile("^[A-Z]{3}-\\d{4,6}$");

    public SKU {
        if (value == null || value.isBlank() || !SKU_PATTERN.matcher(value).matches())
            throw new InvalidSKUException(value);
    }

    @Override
    public String toString() { return value; }
}
