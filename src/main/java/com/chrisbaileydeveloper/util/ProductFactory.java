package com.chrisbaileydeveloper.util;

import java.util.List;

import com.chrisbaileydeveloper.domain.Product;

public class ProductFactory {
    
	public static Product createProduct(Object products, String prodId) {
		
        // Attempt to find the prodId and then return the Product.
        for (Product p: (List<Product>) products) {
            if (p.getProdId().equals(prodId)) {
                return p;
            }
        }
        
        return null; 
    }
}
