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
        if (unitPurchasePrice < 0 || quantity < 0) {
            throw new IllegalArgumentException("Price and quantity cannot be negative");
        }
        this.id = generateId();
        this.name = name;
        this.unitPurchasePrice = unitPurchasePrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.store = null;
    }

    private synchronized int generateId(){
        return idCounter++;
    }

    public String getName(){
        return this.name;
    }
    public Category getCategory(){return this.category;}
    public LocalDate getExpirationDate(){return this.expirationDate;}
    public int getQuantity(){
        return this.quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public double getUnitPurchasePrice(){
        return this.unitPurchasePrice;
    }
    public void setStore(Store store){
        this.store = store;
    }
    public void setExpirationDate(LocalDate date){
        this.expirationDate = date;
    }
    public Store getStore(){
        return this.store;
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
        return Math.round(sellingPrice * 100.0) / 100.0;
    }



    public boolean isExpired(){
        return expirationDate.isBefore(LocalDate.now());
    }
}
