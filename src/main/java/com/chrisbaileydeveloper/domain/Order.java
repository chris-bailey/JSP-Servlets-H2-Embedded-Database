package com.chrisbaileydeveloper.domain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<LineItem> items;

    public Order() {
        items = new ArrayList<LineItem>();
    }

    public List<LineItem> getItems() {
        return items;
    }
    
    public void addItem(LineItem item) {
        // If the LineItem already exists then the quantity will be replaced.
        for (LineItem li: items) {
            if (li.getProduct().getProdId().equals(item.getProduct().getProdId())) {
                li.setQuantity(item.getQuantity());
                return;
            }
        }
        
        //If the LineItem is new then add it.
        items.add(item);
    }
    
    public void removeItem(LineItem item) {
        for (LineItem li: items) {
            if (li.getProduct().getProdId().equals(item.getProduct().getProdId())) {
                items.remove(li);
                return;
            }
        }
    }
    
    public double getTotalPrice() {
        double total = 0;
        
        for (LineItem li: items) {
            total += li.getPrice();
        }
        
        return total;
    }

    public String getTotalPriceCurrency() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(getTotalPrice());
    }
}
