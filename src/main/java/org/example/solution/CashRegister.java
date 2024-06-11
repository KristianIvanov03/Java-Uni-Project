package org.example.solution;

import org.example.solution.exceptions.InsufficientStockException;
import org.example.solution.exceptions.ExpiredProductException;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {
    private Cashier cashier;
    private Store store;
    private List<Sale> sales;

    public CashRegister() {
        this.cashier = null;
        this.store = null;
        this.sales = new ArrayList<>();
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setCashier(Cashier cashier){
        if(this.cashier == null){
            this.cashier = cashier;
        }else{
            System.out.println("Already logged cashier");
        }
    }
    public void removeCashier(){
        if(this.cashier != null){
            this.cashier = null;
        }else{
            System.out.println("No cashier to be logged out");
        }
    }
    public Cashier getCashier(){
        return this.cashier;
    }
    public List<Sale> getSales(){
        return this.sales;
    }

    public void addSale(Product product, int quantity) throws ExpiredProductException {
        if (cashier == null) {
            throw new IllegalStateException("No cashier is currently logged in.");
        }
        if (product.isExpired()) {
            throw new ExpiredProductException(product);
        }
        sales.add(new Sale(product, quantity));
    }

    public void generateReceipt() throws InsufficientStockException {
        if (cashier == null) {
            throw new IllegalStateException("No cashier is currently logged in.");
        }
        // Check if all sales can be fulfilled
        for (Sale sale : sales) {
            Product product = sale.getProduct();
            int quantity = sale.getQuantity();

            // Check if there is enough quantity in the store
            if (product.getQuantity() < quantity) {
                throw new InsufficientStockException(product, quantity);
            }
        }

        // Deduct the quantities from the store's product list
        for (Sale sale : sales) {
            Product product = sale.getProduct();
            int quantity = sale.getQuantity();
            product.setQuantity(product.getQuantity() - quantity);
        }

        // Create and save the receipt
        Receipt receipt = new Receipt(cashier, new ArrayList<>(sales));
        System.out.println(receipt.toString());
        store.addSoldProducts(sales);
        sales.clear();
        receipt.saveToFile();
        store.addReceipt(receipt);
    }


}


