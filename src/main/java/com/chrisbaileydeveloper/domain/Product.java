package com.chrisbaileydeveloper.domain;

import java.text.NumberFormat;

public class Product {

    private String prodId;
    private String description;
    private double price;

    public Product(String prodId, String description, double price) {
        this.prodId = prodId;
        this.description = description;
        this.price = price;
    }

    public String getProdId() {
        return prodId;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
    
    public String getCurrency() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(getPrice());
    }
}
