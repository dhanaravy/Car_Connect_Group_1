package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.AdminNotFoundException;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.*;
import com.service.*;

public class AdminController {
	public static void main(String[] args, Scanner sc)
 {
		AdminService adminService=new AdminService();
		while(true) {
		
		System.out.println("Select an option:");
        System.out.println("1. Manage Reservations");
        System.out.println("2. Manage Vehicles");
        System.out.println("3. Manage Customer");
        System.out.println("4. Manage Admin");
		System.out.println("0. Go to Login Page");
        int adminOption = sc.nextInt();
        
        if (adminOption == 1) {
            ReservationController.main(args,sc);
        } else if (adminOption == 2) {
            VehicleController.main(args,sc);
        } 
        else if (adminOption == 3) {
            CustomerController.main(args);
        } 
        
        else if (adminOption == 4) {
		while (true) {
			System.out.println();
			System.out.println("***ADMIN OPERATION***");
			System.out.println("press 1. Get Admin By Id");
			System.out.println("press 2. Get Admin By Username");
			System.out.println("press 3. Update Admin");
			System.out.println("press 4. Delete Admin");
			System.out.println("Press 5. Display All Reservation By Date Range");
			System.out.println("press 0. for exit");
			System.out.println("**************");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting...Thank you!!!");
				break;
			}
			switch (input) {
			case 1:
				try {
					List<Admin> list = adminService.fetchAllAdmins();
					System.out.println("Enter  Admin Id:");
					int id = sc.nextInt();
					Admin a =adminService.getAdminById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s", "Admin Id",
							"First Name", "Last Name", "Phone Number", "User Name", "Password","Role",
							"JoinDate"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s", a.getId(),
							a.getFirstName(), a.getLastName(), a.getPhoneNumber(), a.getUserName(),
							a.getPassword(), a.getJoinDate().toString()));
				} catch (DatabaseConnectionException | SQLException | InvalidInputException | AdminNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 2:
				try {
					List<Admin> list = adminService.fetchAllAdmins();
					System.out.println("Enter UserName:");
					String name = sc.next();
					Admin a = adminService.getAdminByUsername(list, name);
					System.out.println();
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Admin Id",
							"First Name", "Last Name", "Phone Number", "User Name", "Password","Role",
							"JoinDate"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s%s", a.getId(),
							a.getFirstName(), a.getLastName(), a.getPhoneNumber(), a.getUserName(),
							a.getPassword(),a.getRole(), a.getJoinDate().toString()));
				} catch (SQLException | DatabaseConnectionException | InvalidInputException | AdminNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			
        case 3:
        	System.out.println("Update the Admin Information");
        	
        	try {
				List<Admin>list = adminService.fetchAllAdmins();
				System.out.println("Enter Admin Id to be updated:");
				int id = sc.nextInt();
				System.out.println();
				Admin a= adminService.getAdminById(list, id);
				System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Admin Id",
						"First Name", "Last Name", "Phone Number", "User Name", "Password","Role",
						"JoinDate"));
				System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s%s", a.getId(),
						a.getFirstName(), a.getLastName(), a.getPhoneNumber(), a.getUserName(),
						a.getPassword(),a.getRole(), a.getJoinDate().toString()));
			
				System.out.println("What do you want to update?");
				sc.nextLine();				
				String field=sc.nextLine();
				System.out.println("Enter the new value:");
				String newVal=sc.next();
				adminService.updateAdmin(id,field,newVal);
				System.out.println("Record updated Successfully");
			} catch (SQLException | DatabaseConnectionException | InvalidInputException | AdminNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			System.out.println("Enter Admin Id to be deleted");
			int id = sc.nextInt();
			try {
				adminService.deleteAdminById(id);
				System.out.println("Records Deleted!!");
			} catch (SQLException | DatabaseConnectionException e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 5:
			try {
			    System.out.println("Enter the Date Range");
			    System.out.println("Enter the Start Date");
			    String startDate = sc.next();
			    System.out.println("Enter the End Date");
			    String endDate = sc.next();
			    
			    List<Reservation> reservations = adminService.displayReservationByDate(startDate, endDate);
			    
			    System.out.println(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s", 
			            "Reservation Id", "Customer Id", "Vehicle Id", "Start Date", 
			            "End Date", "Total Cost", "Status"));


			    for (Reservation r : reservations) {
			        System.out.println(String.format("%-20d%-20d%-20d%-20s%-20s%-20f%-20s", 
			                r.getId(), r.getCustomerId(), r.getVehicleId(), 
			                r.getStartDate().toString(), r.getEndDate().toString(), 
			                r.getTotalCost(), r.getStatus()));
			    }
			} catch (Exception e) {
			    e.printStackTrace(); 
			}
			
	}
		}
}
        sc.close();
        
	}
	}
}