package org.example.solution;

import org.example.solution.util.Category;

import java.time.LocalDate;
import java.util.Map;


public class Product {
    private static int idCounter = 0;
    private int id;
    private String name;
    private double unitPurchasePrice;
    private Category category;
    private LocalDate expirationDate;
    private int quantity;
    private Store store;

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

    public String getName(){
        return this.name;
    }

    public double calculateSellingPrice(){
        Map<Category, Double> markupPercentages = store.getMarkupPercentages();
        int daysBeforeExpiration = store.getDaysBeforeExpiration();
        double discountPercentage = store.getDiscountPercentage();

        double markupPercentage = markupPercentages.getOrDefault(category, 0.0);
        double sellingPrice = unitPurchasePrice * (1 + markupPercentage / 100);
        if (expirationDate.isBefore(LocalDate.now().plusDays(daysBeforeExpiration))) {
            sellingPrice *= (1 - discountPercentage / 100);
        }
        return sellingPrice;
    }



    public boolean isExpired(){
        return expirationDate.isBefore(LocalDate.now());
    }
}
