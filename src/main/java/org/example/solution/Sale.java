package org.example.solution;

public class Sale {
    private Product product;
    private int quantity;

    public Sale(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProductName() {
        return this.product.getName();
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public double getTotalPrice() {
        return product.calculateSellingPrice() * quantity;
    }
}
