package org.example.solution;

import org.example.solution.util.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {
    private List<Product> products;
    private List<Cashier> cashiers;
    private List<Receipt> receipts;
    private Map<Category, Double> markupPercentages;
    private int daysBeforeExpiration;
    private double discountPercentage;

    public Store(Map<Category, Double> markupPercentages, int daysBeforeExpiration, double discountPercentage) {
        this.products = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.markupPercentages = markupPercentages;
        this.daysBeforeExpiration = daysBeforeExpiration;
        this.discountPercentage = discountPercentage;
    }

    // Methods to add products, cashiers, and handle sales

    public void addCashier(Cashier cashier){
        this.cashiers.add(cashier);
    }
    public double totalSalaryExpenses(){
        return cashiers.stream().mapToDouble(cashier -> cashier.getSalary()).sum();
    }

    public List<Product> getProducts() { return products; }
    public List<Cashier> getCashiers() { return cashiers; }
    public List<Receipt> getReceipts() { return receipts; }
    public Map<Category, Double> getMarkupPercentages() { return markupPercentages; }
    public int getDaysBeforeExpiration() { return daysBeforeExpiration; }
    public double getDiscountPercentage() { return discountPercentage; }
}
