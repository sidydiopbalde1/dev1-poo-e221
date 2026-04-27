package com.ecommerce.catalogue.domain.entity;

public class Item {
    private String itemId;
    private String productId;
    private int quantity;
    private double price;

    public Item(String itemId, String productId, int quantity, double price) {
        this.itemId = itemId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
