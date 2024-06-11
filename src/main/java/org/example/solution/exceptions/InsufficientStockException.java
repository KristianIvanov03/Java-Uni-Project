package org.example.solution.exceptions;

import org.example.solution.Product;

public class InsufficientStockException extends RuntimeException {
    private Product product;
    private int requiredQuantity;

    public InsufficientStockException(Product product, int requiredQuantity) {
        super("Insufficient stock for product: " + product.getName() + ", required quantity: " + requiredQuantity);
        this.product = product;
        this.requiredQuantity = requiredQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }
}
