package org.example.solution;

import org.example.solution.util.Category;

import java.time.LocalDate;


public class Product {
    private static int idCounter = 0;
    private int id;
    private String name;
    private double unitPurchasePrice;
    private Category category;
    private LocalDate expirationDate;
    private int quantity;

    public Product(String name, double unitPurchasePrice, Category category, LocalDate expirationDate, int quantity){
        this.id = generateId();
        this.name = name;
        this.unitPurchasePrice = unitPurchasePrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    private synchronized int generateId(){
        return idCounter++;
    }



    public boolean isExpired(){
        return expirationDate.isBefore(LocalDate.now());
    }
}
