package com.chrisbaileydeveloper.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.chrisbaileydeveloper.domain.Product;

public class ProductDB {
	
	// Create and populate Product table in H2 Database
	public static void createPopulateH2() {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        
     		final String createProductTable = "DROP TABLE IF EXISTS Product;" 
     				+ "CREATE TABLE Product(" 
     				+ "ProductID INT NOT NULL AUTO_INCREMENT,"
     				+ "ProductCode VARCHAR(10) NOT NULL DEFAULT '',"
     				+ "ProductDescription VARCHAR(100) NOT NULL DEFAULT '',"
     				+ "ProductPrice DECIMAL(7,2) NOT NULL DEFAULT '0.00',"
     				+ "PRIMARY KEY (ProductID));";

     		final String populateProductTable = "INSERT INTO Product VALUES"
     				+ "('1', 'gw01', 'Coldplay - Ghost Stories', '14.95'),"
     				+ "('2', 'u201', 'U2 - Songs of Innocence', '13.95'),"
     				+ "('3', 'nb01', 'Nickelback - No Fixed Address', '13.95'),"
     				+ "('4', 'cb01', 'Chris Bailey - I Will Always Remember', '14.95');";

     		try {
     			conn.createStatement().executeUpdate(createProductTable);
     			conn.createStatement().executeUpdate(populateProductTable);

     		} catch (SQLException sQLException) {
     			sQLException.printStackTrace();

     			
     		} finally {
     		  //@formatter:off
     			try {if (conn != null) conn.close();} catch (Exception e) {};
         	  //@formatter:on
     		}
	}
	
	// Retrieve products from H2 Database
	public static List<Product> createList() {
		List<Product> products = new ArrayList<Product>();

		ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();

			String query = "select * from product";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String prodId = rs.getString("ProductCode");
				String description = rs.getString("ProductDescription");
				double price = rs.getDouble("ProductPrice");

				Product p = new Product(prodId, description, price);
				products.add(p);
			}

			return products;

		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
			return null;

		} finally {
			//@formatter:off
			try {if (rs != null) rs.close();} catch (Exception e) {};
			try {if (stmt != null) stmt.close();} catch (Exception e) {};
			try {if (conn != null) conn.close();} catch (Exception e) {};
			//@formatter:on
		}

	}

	
}
