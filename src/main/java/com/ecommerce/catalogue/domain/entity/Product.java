package com.ecommerce.catalogue.domain.entity;

import com.ecommerce.catalogue.domain.valueobject.Money;
import com.ecommerce.catalogue.domain.valueobject.SKU;
import com.ecommerce.catalogue.exception.InvalidDiscountException;
import com.ecommerce.catalogue.exception.InvalidProductException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.UUID;

public final class Product {

    private final UUID   id;
    private final SKU    sku;
    private       String name;
    private       Money  price;

    public Product(SKU sku, String name, Money price) {
        if (sku == null)
            throw new InvalidProductException("Le SKU est obligatoire.");
        if (name == null || name.isBlank())
            throw new InvalidProductException("Le nom du produit ne peut pas être vide.");
        if (price == null)
            throw new InvalidProductException("Le prix est obligatoire.");
        this.id    = UUID.randomUUID();
        this.sku   = sku;
        this.name  = name.strip();
        this.price = price;
    }

    public void applyDiscount(BigDecimal percentage) {
        if (percentage == null) throw new InvalidDiscountException(0);
        double pct = percentage.doubleValue();
        if (pct < 0.1 || pct > 100) throw new InvalidDiscountException(pct);

        BigDecimal multiplier = BigDecimal.ONE
            .subtract(percentage.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP));

        this.price = new Money(
            this.price.amount().multiply(multiplier).setScale(2, RoundingMode.HALF_UP),
            this.price.currency()
        );
    }

    public UUID  getId()    { return id;    }
    public SKU   getSku()   { return sku;   }
    public String getName() { return name;  }
    public Money getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Product{id=%s, sku=%s, name='%s', price=%s}".formatted(id, sku, name, price);
    }
}
