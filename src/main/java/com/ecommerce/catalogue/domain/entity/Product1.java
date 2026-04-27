package com.ecommerce.catalogue.domain.entity;


public class Product1 {

    private String name;
    private Double  price;

    public Product1(String name, Double price) {
        this.name  = name.strip();
        this.price = price;
    }




    public String getName() { return name;  }
    public Double getPrice() { return price; }

}
