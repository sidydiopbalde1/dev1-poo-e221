package com.ecommerce.catalogue.domain.entity;

public class Item {
    private int itemId;
    private String name;
    private String productId;
    private int quantity;
    private double price;


    public Item(int itemId, String name, String productId, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
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
