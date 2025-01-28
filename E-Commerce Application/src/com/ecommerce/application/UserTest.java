package com.ecommerce.application;
import java.util.Scanner;

public class UserTest {

	static int choice;
	
	public static void main(String[] args) {
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Welcome to E-Commerce based application \r\n"+
					 "---------------------------------\r\n" +
					"Please select the Below Domain based sequence to perform your desired task \r\n"
				+ "---------------------------------\r\n" 
				+   "1) User Operation \r\n" + 
				    "---------------------------------\r\n" +
					"1. User Registration \r\n" + 
					"2. User Login \r\n" + 
					"3. User view Product item as Sorted Order \r\n" + 
					"4. Buy Product \r\n" + 
					"5. View Cart        \r\n" + 
					"6. Purchase the item        \r\n" + 
					"---------------------------------\r\n"+
					
					"2) Admin Operation \r\n" + 
					"---------------------------------\r\n"+
					"7. Add product item \r\n" + 
					"8. Calculate Bill \r\n" + 
					"9. Display amount to End User \r\n" + 
					"10.Check Quantity \r\n" + 
					"11. Check registered user \r\n" + 
					"12. Check the particular user history \r\n" + 
					"---------------------------------\r\n"+
					
					"3) Guest Operation \r\n" + 
					"---------------------------------\r\n"+
					"13. View product item \r\n" + 
					"14. Not purchase item \r\n"+
			      "---------------------------------\r\n");
			
			
	    System.out.println("Enter Your Choice..");
			
			choice =scanner.nextInt();
			
	      switch(choice) {
			
			case 1:                                      // Sarika Malve
				UserAction ua = new UserAction();
				ua.userRegistration();
				
			break;
			
			case 2:                                      // Prachi Dudhe
				System.out.println("User Login");
				UserAction2 ua1 = new UserAction2();
				ua1.userLogin();
				
			break;
			
			case 3:                                       //Pavan Gore
				UserAction1 ua2 = new UserAction1();	
				ua2.viewProducts();
				
			break;
			
			case 4:                                      // Aishwarya Khade
				UserAction3 ua3 = new UserAction3();
				ua3.addToCart();  
				
			break;
				
			case 5:                                      // Aishwarya Khade
				UserAction3 ua4 = new UserAction3();
				ua4.viewCart();
				
			break;
			
			case 6:                                      // Prachi Dudhe
				UserAction2 ua5 = new UserAction2();
				ua5.purchaseItems();
				
			break;
			
			default:
				System.out.println("Kindly Choose number between 1 to 6 to perform operation");
			break;
		}

	  }
}
