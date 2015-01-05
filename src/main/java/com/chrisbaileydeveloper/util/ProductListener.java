package com.chrisbaileydeveloper.util;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.chrisbaileydeveloper.data.ProductDB;
import com.chrisbaileydeveloper.domain.Product;

public class ProductListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        
        // Create and populate the embedded H2 database.
        ProductDB.createPopulateH2();
        
        // Retreive products from database and place into ServletContext.
        List<Product> products = ProductDB.createList();
        sc.setAttribute("products", products);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
    
}
