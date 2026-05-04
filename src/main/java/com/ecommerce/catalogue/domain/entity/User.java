package com.ecommerce.catalogue.domain.entity;

public class User {
    private String id;
    private String nom;
    private String login;
    private String password;
    private Boolean isActive;
    private String role;
    private String address;

        public User(String nom, String login, String password, Boolean isActive, String role, String address, String id) {
             this.id = id;
             this.nom = nom;
             this.login = login;
             this.password = password;
             this.isActive = isActive;
             this.role = role;  
             this.address = address;
        
        }

    
    public String getNom() {
        return nom;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    public String getRole() {
        return role;
    }
    public String getAddress() {
        return address;
    }
}