package com.ecommerce.catalogue.domain.entity;

public class Student {

    private String name;
    private double note;

    public Student(String name, double note) {
        this.name = name;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public double getNote() {
        return note;
    }

    
}