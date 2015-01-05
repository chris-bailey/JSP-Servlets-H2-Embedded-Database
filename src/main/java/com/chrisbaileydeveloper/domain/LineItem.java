package com.chrisbaileydeveloper.domain;

import java.text.NumberFormat;

public class LineItem {
    private Product product;
    private int quantity;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getPrice() {
        return product.getPrice() * quantity;
    }
    
    public String getCurrency() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(getPrice());
    }
}
