package org.example;

import org.example.solution.CashRegister;
import org.example.solution.Cashier;
import org.example.solution.Product;
import org.example.solution.Store;
import org.example.solution.util.Category;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Markup percentages hashmap
        Map<Category, Double> map = new HashMap<>();
        map.putIfAbsent(Category.FOOD, 10.0);
        map.putIfAbsent(Category.NON_FOOD, 5.0);

        //creating store object
        Store store = new Store("My Store", map, 5, 5.0);

        //creating and assigning cashiers
        Cashier cashier1 = new Cashier("Ivan Ivanov", 1500.0);
        Cashier cashier2 = new Cashier("Alexandra Petrova", 1500.0);
        Cashier cashier3 = new Cashier("Petur Iordaov", 1900.0);
        store.addCashier(cashier1);
        store.addCashier(cashier2);
        store.addCashier(cashier3);
        System.out.println("Total cost for salaries: " + store.calculateTotalSalaryCosts());

        //Cash registers and adding them to store
        CashRegister cashRegister1 = new CashRegister();
        CashRegister cashRegister2 = new CashRegister();
        CashRegister cashRegister3 = new CashRegister();
        store.addCashRegister(cashRegister1);
        store.addCashRegister(cashRegister2);
        store.addCashRegister(cashRegister3);
        cashRegister1.setCashier(cashier1);
        cashRegister2.setCashier(cashier2);
        cashRegister3.setCashier(cashier3);

        //Creating and assigning products to store
        LocalDate date = LocalDate.of(2024, 10, 22);
        Product potatoes = new Product("potatoes", 0.60, Category.FOOD, date, 20);
        Product fish = new Product("fish", 6.0, Category.FOOD, date, 10);
        Product washingPowder = new Product("washing powder", 6.20, Category.NON_FOOD ,date, 30);
        Product soap = new Product("soap", 1.20, Category.NON_FOOD, date, 50);
        Product apples = new Product("apples", 0.80, Category.FOOD, date, 15);
        store.addProduct(potatoes);
        store.addProduct(fish);
        store.addProduct(washingPowder);
        store.addProduct(soap);
        store.addProduct(apples);
        System.out.println();
        System.out.println();
        System.out.println("Total Cost for Products in Store: " + store.getTotalCostForProducts());
        System.out.println();

        //Making sale
        cashRegister1.addSale(potatoes, 2);
        cashRegister1.addSale(fish, 1);
        cashRegister1.generateReceipt();
        cashRegister2.addSale(washingPowder, 5);
        cashRegister2.addSale(soap, 10);
        cashRegister2.generateReceipt();
        cashRegister3.addSale(apples, 3);
        cashRegister3.addSale(fish, 2);
        cashRegister3.addSale(soap, 2);
        cashRegister3.generateReceipt();

        System.out.println("Total receipts: " + store.getReceipts().size());
        System.out.println("Store revenue: " + store.calculateTotalRevenue());
        System.out.println("Store profit: " + store.calculateProfit());
    }
    
}