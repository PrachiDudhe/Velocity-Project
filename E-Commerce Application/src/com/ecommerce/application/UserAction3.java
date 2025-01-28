package com.ecommerce.application;
                                                                     //Aishwarya Khade
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class UserAction3 implements UserPhase2 {
	
	private static final String Driver_Name = "oracle.jdbc.driver.OracleDriver";
	private static final String Driver_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "System";
	private static final String password = "System";
	
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void addToCart() {
		
		try {
			// Step-1- Loading driver class
			Class.forName(Driver_Name);
			// Step-2- Establish connection
			Connection con = DriverManager.getConnection(Driver_URL, username, password);
			
			System.out.println("To Buy the product Kindly Enter Below Details..");
			System.out.println("----------------------------------");
			
		    System.out.println("Enter the username>>>>");
			String username = scanner.next();
						
			System.out.println("Enter the password>> ");
		    String password = scanner.next();
  
	       //  PreparedStatement ps6 = con.prepareStatement("Select * from UserDetails "); 
	         PreparedStatement ps6 = con.prepareStatement("SELECT * FROM UserDetails WHERE UserDetail_username = ? AND UserDetail_password = ?" );

	             // Set query parameters
	             ps6.setString(1, username);
	             ps6.setString(2, password);

	             // Execute the query
	             ResultSet rs1 = ps6.executeQuery();

	             // Check if user exists
	             if (rs1.next()) {
	            	    String u = rs1.getString(3);
						String p = rs1.getString(4); 
						
	               if(u.equals(username)&& p.equals(password)) {
			
			          System.out.println("You logged in successfully!");
                      System.out.println("----------------------------------");
		              System.out.println("Enter the product id to buy product>>");
		              
		              int Id = scanner.nextInt();
		
		              System.out.println("Enter the Quantity>>");
		              int Quant = scanner.nextInt();	
		              // Step-3- Create Prepared Statement
		              //Display full product details 
		              PreparedStatement ps3 = con.prepareStatement("select * from products where product_id = ?");
		                ps3.setInt(1, Id);
		
		              ResultSet rs = ps3.executeQuery();
		     
		                if (rs.next()) {
	
			              int productId = rs.getInt("product_id");
			              String productName = rs.getString("product_name");
                          String productDiscription = rs.getString("product_discription");
                          int productPrice = rs.getInt("product_price");
                          int availableQuantity = rs.getInt("product_quantity");

                          // Display product details
                          System.out.println("Product ID: " + productId);
                          System.out.println("Product Name: " + productName);
                          System.out.println("Product Discription: " + productDiscription);
                          System.out.println("Price: " + productPrice);
                          System.out.println("Available Quantity: " + availableQuantity);
            
                            if (Quant > availableQuantity) {
            	             System.out.println("----------------------------------");
                             System.out.println("Requested Quantity is not available in Stock.Cannot Add to Stock.");
                             System.out.println("----------------------------------");
                           
                            } else {
            		
                          System.out.println("Do you want to add to the cart (Yes/No)?");
                
                           String addcart = scanner.next();	
				           if(addcart.equals("Yes")) {
					
					            PreparedStatement ps4 = con.prepareStatement("insert into cart(cart_id, cart_quantity, product_id, product_name, product_discription, product_price, product_quantity) values (?,?,?,?,?,?,?)");
							
					
					                   ps4.setInt(1, Id);
					                   ps4.setInt(2, Quant);
					                   ps4.setInt(3, productId);
					                   ps4.setString(4, productName);
					                   ps4.setString(5, productDiscription);
					                   ps4.setInt(6, productPrice);
					                   ps4.setInt(7, availableQuantity);
					
					                   ps4.executeUpdate();
					
					            System.out.println("----------------------------------");
					            System.out.println("Successfully Added to Cart");
					
					        con.commit();
					        ps4.close();
					
                       } else {
                	        System.out.println("----------------------------------");
					        System.out.println("We invite you to explore our additional products and We hope you find something suitable to enrich your cart further.");
					        System.out.println("----------------------------------");
			       	  }		
                   } 
                            
                 } else {
					System.out.println("This Product ID is not available in stock. Kindly verify the product ID.");
			   }
		
		       rs.close();
		       con.close();
		       ps3.close();
             }
	    
           } else {
	                 System.out.println("Wrong input, kindly check your username and password.");
	    }
 
	    }catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
                e.printStackTrace();
        } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();	
    }	
}

	@Override
	public void viewCart() {
		
		
		try {
			Class.forName(Driver_Name);
			// Step-2- Establish connection
			Connection con = DriverManager.getConnection(Driver_URL, username, password);
			
			System.out.println("You Want to View Your Cart ? (Yes/No)");
			
			String cart = scanner.next();
			
			if(cart.equals("Yes")) {
				System.out.println("----------------------------------");
				System.out.println("Cart Details as Follows");
				System.out.println("----------------------------------"); 
				PreparedStatement ps7 = con.prepareStatement("select * from Cart ORDER BY product_id ASC");
				// Step 4- Submit SQL statement to database
				ResultSet rs1 = ps7.executeQuery();
				//Step 5 - retrieve the data from end user
				while(rs1.next()) {
					System.out.println("Product Id>>" + rs1.getInt(3));
					System.out.println("Product Name>>>>" + rs1.getString(4));
					System.out.println("Product Description>>" + rs1.getString(5));
					System.out.println("Price>>" + rs1.getInt(6));
					System.out.println("Quantity of Item>>" + rs1.getInt(2));
					System.out.println("----------------------------------");
				}
				// Step 6- Release resources
				con.close();
				ps7.close();
				rs1.close();
			}
		
		}catch(Exception e) {
			e.printStackTrace();		
		}		
	}

}
