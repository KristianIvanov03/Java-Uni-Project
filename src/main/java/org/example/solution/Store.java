package org.example.solution;

import org.example.solution.util.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {
    private String name;
    private Map<Category, Double> markupPercentages;
    private List<Product> products;
    private List<Cashier> cashiers;
    private List<Receipt> receipts;
    private List<Sale> soldProducts;
    private int daysBeforeExpiration;
    private double discountPercentage;
    private double totalCostForProducts;

    public Store(String name, Map<Category, Double> markupPercentages, int daysBeforeExpiration, double discountPercentage) {
        this.name = name;
        this.markupPercentages = markupPercentages;
        this.products = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.soldProducts = new ArrayList<>();
        this.daysBeforeExpiration = daysBeforeExpiration;
        this.discountPercentage = discountPercentage;
        this.totalCostForProducts = 0;
        System.out.println("Store created successfully");
    }

    public void addProduct(Product product){
        product.setStore(this);
        products.add(product);
        totalCostForProducts = this.totalCostForProducts + product.getUnitPurchasePrice() * product.getQuantity();
        System.out.println(product.getName() + " added to inventory!");
    }
    public void addCashRegister(CashRegister cashRegister){
        cashRegister.setStore(this);
    }

    //Markup percentages for Store
    public Map<Category, Double> getMarkupPercentages() {
        return markupPercentages;
    }

    public List<Product> getProducts() {
        return products;
    }

    //Cashier func
    public List<Cashier> getCashiers() {
        return cashiers;
    }
    public void addCashier(Cashier cashier){
        this.cashiers.add(cashier);
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }
    public void addSoldProducts(List<Sale> sales) {
        soldProducts.addAll(sales);
    }

    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }
    public int totalAmountOfReceipts(){return receipts.size();}

    public int getDaysBeforeExpiration() {
        return daysBeforeExpiration;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getTotalCostForProducts(){
        return this.totalCostForProducts;
    }

    // Метод за изчисляване на разходите за заплати на касиери
    public double calculateTotalSalaryCosts() {
        return cashiers.stream().mapToDouble(Cashier::getSalary).sum();
    }

    // Метод за изчисляване на приходите от продадени стоки
    public double calculateTotalRevenue() {
        return receipts.stream().mapToDouble(Receipt::getTotalAmount).sum();
    }

    // Метод за изчисляване на печалбата на магазина
    public double calculateProfit() {
        return calculateTotalRevenue() - calculateTotalSalaryCosts() - this.totalCostForProducts;
    }
}

