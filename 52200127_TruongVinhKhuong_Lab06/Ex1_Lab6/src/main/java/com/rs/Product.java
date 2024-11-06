package com.rs;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Product {
    @Setter @Getter private int id;
    @Setter @Getter private String name;
    @Getter @Setter private double price;
    @Getter @Setter private String description;


    public Product() {
    }
    public Product(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

}