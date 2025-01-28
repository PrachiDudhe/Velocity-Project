package com.ecommerce.application;
                                                                  //Pavan Gore
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class UserAction1 implements Role{
	
	private static final String Driver_Name = "oracle.jdbc.driver.OracleDriver";
	private static final String Driver_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "System";
	private static final String password = "System";
	
	
Scanner scanner = new Scanner(System.in);
	

	@Override
	public void viewProducts() {                                  //Pavan Gore
		
		   System.out.println("----------------------------------");
		   System.out.println("View the available products");
		   System.out.println("----------------------------------");
		try {
			// Step 1- Loading driver class
			Class.forName(Driver_Name);
			// Step 2- Establish connection
			Connection con = DriverManager.getConnection(Driver_URL, username, password);
			// Step 3- Create Prepared Statement
			PreparedStatement ps1 = con.prepareStatement("select * from Products ORDER BY product_id ASC");
			// Step 4- Submit SQL statement to database
			ResultSet rs = ps1.executeQuery();
			//Step 5 - retrieve the data from end user
			while(rs.next()) {
				
				System.out.println("Product Id>>" + rs.getInt(1));
				System.out.println("Product Name>>>>" + rs.getString(2));
				System.out.println("Product Description>>" + rs.getString(3));
				System.out.println("Available Quantity>>" + rs.getInt(5));
				System.out.println("Price>>" + rs.getInt(4));
				System.out.println("----------------------------------");
			}
			// Step 6- Release resources
			con.close();
			ps1.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
}
