package org.example.solution.exceptions;

import org.example.solution.Product;

public class ExpiredProductException extends RuntimeException {
    private Product product;

    public ExpiredProductException(Product product) {
        super("Product is expired: " + product.getName());
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}

