package com.ecommerce.application;
                                                                          //Sarika Malve
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class UserAction implements UserPhase {
	
	private static final String Driver_Name = "oracle.jdbc.driver.OracleDriver";
	private static final String Driver_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "System";
	private static final String password = "System";
	
	Scanner scanner = new Scanner(System.in);
	
	public void userRegistration() {                                    //Sarika Malve
		
		try {
			// Step-1- Loading driver class
			Class.forName(Driver_Name);
			// Step-2- Establish connection
			Connection con = DriverManager.getConnection(Driver_URL, username, password);
			// Step-3- Create Prepared Statement
			
			System.out.println("Do you Want to Register ? (Yes/No) ");
			
			String buy = scanner.next();
			
			if(buy.equals("Yes")) {
			
			PreparedStatement ps = con.prepareStatement("insert into UserDetails(UserDetail_firstname, UserDetail_lastname, UserDetail_username , UserDetail_password, UserDetail_city, UserDetail_mail_id, UserDetail_mobile_number) values(?, ?, ?, ?, ?, ?, ?)");
			//insert into UserDetails
			System.out.println("Enter Your First name>>");
			String Fn = scanner.next();
			
			System.out.println("Enter Your Last name>>");
			String Ln = scanner.next();
			
			System.out.println("Enter the Username>>");
			String username = scanner.next();
			
			System.out.println("Enter the Password>>");
			String password = scanner.next();
			
			System.out.println("Enter Your City>>");
			String city = scanner.next();
			
			System.out.println("Enter Your mail_Id>>");
			String mail_id = scanner.next();
			
			System.out.println("Enter Your Mobile_Number>>");
			String mobile_number = scanner.next();
			
			ps.setString(1, Fn);
			ps.setString(2, Ln);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, city);
			ps.setString(6, mail_id);
			ps.setString(7, mobile_number);
			
			// Step-4- Submit SQL statement to database
			 int rowsInserted = ps.executeUpdate();
		    // Step-5- Process results
		        if (rowsInserted > 0) {
		        	System.out.println("----------------------------------");
		            System.out.println("Record is inserted successfully...");
		            System.out.println("----------------------------------");
		        } else {
		        	System.out.println("----------------------------------");
		            System.out.println("Failed to insert the record.");
		            System.out.println("----------------------------------");
		        }
			// Step-6- Release resources
			con.close();
			ps.close();
			}
			else {
				
				System.out.println("Thank you for your visit and We hope  next time you find something suitable to enrich you further.. ");
			}
			
		 } catch(Exception e) {	
			e.printStackTrace();
		 }			
	}
		
}
