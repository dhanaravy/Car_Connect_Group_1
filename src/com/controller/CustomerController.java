package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Customer;
import com.service.CustomerService;
import java.util.*;
public class CustomerController {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CustomerService customerService = new CustomerService();
		while (true) {
			System.out.println();
			System.out.println("**********CUSTOMER OPERATION**********");
			System.out.println("press 1. Get Customer By Id");
			System.out.println("press 2. Get Customer By Username");
			System.out.println("press 3. Register Customer");
			System.out.println("press 4. Update Customer");
			System.out.println("press 5. Delete Customer");
			System.out.println("press 0. for exit");
			System.out.println("******************************4********");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting...Thank you!!!");
				break;
			}
			switch (input) {
			case 1:
				try {
					List<Customer> list = customerService.fetchAllCustomers();
					System.out.println("Enter Customer Id:");
					int id = sc.nextInt();
					Customer c = customerService.getCustomerById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Customer Id",
							"First Name", "Last Name", "Phone Number", "Address", "User Name", "Password",
							"Registration Date"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s%s", c.getId(),
							c.getFirstName(), c.getLastName(), c.getPhoneNumber(), c.getAddress(), c.getUsername(),
							c.getPassword(), c.getRegistrationDate().toString()));
				} catch (DatabaseConnectionException | SQLException | InvalidInputException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 2:
				try {
					List<Customer> list = customerService.fetchAllCustomers();
					System.out.println("Enter UserName:");
					String name = sc.next();
					Customer c = customerService.getCustomerByUsername(list, name);
					System.out.println();
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Customer Id",
							"First Name", "Last Name", "Phone Number", "Address", "User Name", "Password",
							"Registration Date"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s%s", c.getId(),
							c.getFirstName(), c.getLastName(), c.getPhoneNumber(), c.getAddress(), c.getUsername(),
							c.getPassword(), c.getRegistrationDate().toString()));
				} catch (SQLException | DatabaseConnectionException | InvalidInputException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 3:
				System.out.println("Enter First Name:");
				String firstName=sc.next();
				System.out.println("Enter Last Name:");
				String lastName=sc.next();
				System.out.println("Enter Email Id:");
				String email=sc.next();
				System.out.println("Enter Phone Number:");
				String phoneNumber=sc.next();
				System.out.println("Enter Address:");
				String address=sc.next();
				System.out.println("Enter Username:");
				String username=sc.next();
				System.out.println("Enter Password:");
				String password=sc.next();
				System.out.println("Enter Registration Date:");
				String registrationDate=sc.next();
				try {
					customerService.createCustomer(firstName,lastName,email,phoneNumber,address,username,password,registrationDate);
					System.out.println("Registration Successfull!!");
				} catch (DatabaseConnectionException |SQLException e) {
					System.out.println(e.getMessage());
				} 
				break;
			case 4:
				try {
					List<Customer>list = customerService.fetchAllCustomers();
					System.out.println("Enter Customer Id to be updated:");
					int id = sc.nextInt();
					System.out.println();
					Customer c = customerService.getCustomerById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Id",
							"First Name", "Last Name", "Phone Number", "Address", "Username", "Password",
							"Registration Date"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s%s", c.getId(),
							c.getFirstName(), c.getLastName(), c.getPhoneNumber(), c.getAddress(), c.getUsername(),
							c.getPassword(), c.getRegistrationDate().toString()));
					System.out.println("What do you want to update?");
					sc.nextLine();				
					String field=sc.nextLine();
					System.out.println("Enter the new value:");
					String newVal=sc.next();
					customerService.updateCustomer(id,field,newVal);
					System.out.println("Record updated Successfully");
				} catch (SQLException | DatabaseConnectionException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				System.out.println("Enter Customer Id to be deleted");
				int id = sc.nextInt();
				try {
					customerService.deleteCustomerById(id);
					System.out.println("Records Deleted!!");
				} catch (SQLException | DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
		}
	}
}
