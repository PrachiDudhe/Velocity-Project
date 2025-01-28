package com.ecommerce.application;
                                                                     //Prachi Dudhe
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class UserAction2 implements UserPhase1 {
	
		
		private static final String Driver_Name = "oracle.jdbc.driver.OracleDriver";
		private static final String Driver_URL = "jdbc:oracle:thin:@localhost:1521:xe";
		private static final String username = "System";
		private static final String password = "System";
		
		Scanner scanner = new Scanner(System.in);
		
	@Override
	public void userLogin() {    
			
			try {
				// Step-1- Loading driver class
				Class.forName(Driver_Name);
				// Step-2- Establish connection
				Connection con = DriverManager.getConnection(Driver_URL, username, password);
				//insert into UserDetails
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
		                 System.out.println("You logged in successfully!");
		                 System.out.println("----------------------------------");
		             } else {
		                 System.out.println("Wrong input, kindly check your username and password.");
		             }
		             
		         con.close();
		         ps6.close(); 
		         
			}catch(Exception e) {
				e.printStackTrace();
			}		
		}
	
	@Override
    public void purchaseItems() {            
		
		try {
		       // Step-1: Load driver class
		       Class.forName(Driver_Name);
		       // Step-2: Establish connection
		       Connection con = DriverManager.getConnection(Driver_URL, username, password);

		       // Step-3: Retrieve items from the cart
		       System.out.println("Do you want to proceed with the purchase? (Yes/No)");
		       String purchase = scanner.next();

		        if (purchase.equalsIgnoreCase("Yes")) {
		        	
		            PreparedStatement ps = con.prepareStatement("SELECT * FROM Cart ORDER BY product_id ASC");
		            ResultSet rs = ps.executeQuery();

		            System.out.println("----------------------------------");
		            System.out.println("Your Cart Items:");
		            System.out.println("----------------------------------");

		            int totalAmount = 0;

		            while (rs.next()) {
		            	
		                int productId = rs.getInt("product_id");
		                String productName = rs.getString("product_name");
		                String productDescription = rs.getString("product_discription");
		                int productPrice = rs.getInt("product_price");
		                int cartQuantity = rs.getInt("cart_quantity");
		                int itemTotal = productPrice * cartQuantity;

		                // Display item details
		                System.out.println("Product ID: " + productId);
		                System.out.println("Product Name: " + productName);
		                System.out.println("Product Description: " + productDescription);
		                System.out.println("Price: " + productPrice);
		                System.out.println("Quantity: " + cartQuantity);
		                System.out.println("Total Price for Item: " + itemTotal);
		                System.out.println("----------------------------------");

		                // Accumulate total amount
		                totalAmount = totalAmount + itemTotal;
		            }

		            // Display the total bill
		            System.out.println("Total Bill Amount: " + totalAmount);
		            System.out.println("----------------------------------");

		            // Step-4: Confirm purchase and clear the cart
		            System.out.println("Do you want to confirm the purchase? (Yes/No)");
		            String confirm = scanner.next();

		            if (confirm.equals("Yes")) {
		                // Clear the cart
		                PreparedStatement psClear = con.prepareStatement("DELETE FROM Cart");
		                psClear.executeUpdate();

		                System.out.println("Purchase successful! Your cart has been cleared.");
		                System.out.println("----------------------------------");

		               //psInventory.close();
		                psClear.close();
		            } else {
		                System.out.println("Purchase cancelled. Your cart remains intact.");
		                System.out.println("----------------------------------");
		            }

		            rs.close();
		            ps.close();
		            
		        } else {
		            System.out.println("You chose not to proceed with the purchase.");
		        }

		        con.close();
		    } catch (SQLException e) {
		        System.out.println("SQL Error: " + e.getMessage());
		        e.printStackTrace();
		    } catch (Exception e) {
		        System.out.println("Error: " + e.getMessage());
		        e.printStackTrace();
		    }
		}
}
