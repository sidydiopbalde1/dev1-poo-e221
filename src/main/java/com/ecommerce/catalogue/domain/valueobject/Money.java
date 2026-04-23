package com.ecommerce.catalogue.domain.valueobject;

import com.ecommerce.catalogue.exception.CurrencyMismatchException;
import com.ecommerce.catalogue.exception.InvalidCurrencyException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public record Money(BigDecimal amount, String currency) {

    private static final Set<String> SUPPORTED_CURRENCIES = Set.of("FCFA", "EUR");

    public Money {
        if (amount == null)
            throw new IllegalArgumentException("Le montant ne peut pas être null.");
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Le montant ne peut pas être négatif : %s.".formatted(amount));
        if (currency == null || !SUPPORTED_CURRENCIES.contains(currency))
            throw new InvalidCurrencyException(currency);
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money add(Money other) {
        if (other == null)
            throw new IllegalArgumentException("L'opérande ne peut pas être null.");
        if (!this.currency.equals(other.currency))
            throw new CurrencyMismatchException(this.currency, other.currency);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(amount.toPlainString(), currency);
    }
}
