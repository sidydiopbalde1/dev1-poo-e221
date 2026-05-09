package com.ecommerce.catalogue.domain.valueobject;

import java.math.BigDecimal;


public record Money1(BigDecimal amount, String currency) {

    public Money1 {
        if (amount == null || currency == null) {
            throw new IllegalArgumentException("Montant et devise requis");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être strictement positif");
        }
    }

    public Money1 add(Money1 other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Devises incompatibles");
        }

        return new Money1(this.amount.add(other.amount), this.currency);
    }

    public Money1 subtract(Money1 other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Devises incompatibles");
        }

        BigDecimal result = this.amount.subtract(other.amount);

        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Résultat négatif interdit");
        }

        return new Money1(result, this.currency);
    }
}
