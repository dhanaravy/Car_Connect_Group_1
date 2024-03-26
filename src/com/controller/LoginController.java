package com.controller;
import java.sql.SQLException;
import java.util.*;

import com.exception.AdminNotFoundException;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidCredentialsException;
import com.exception.InvalidInputException;
import com.model.*;
import com.service.AdminService;
import com.service.CustomerService;
import com.service.LoginService;
import com.dto.LoginDto;

public class LoginController {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		LoginService loginService=new LoginService();
		while (true) {
			System.out.println();
			System.out.println("**********LOGIN OPERATION**********");
			System.out.println("press 1. Sign up for Customer");
			System.out.println("press 2. Sign up for Admin");
			System.out.println("press 3. Login");
			System.out.println("press 4. Password Reset");
			System.out.println("press 0. Exit");
			System.out.println("**************************************");
			int input = sc.nextInt();
			if(input==0)
			{
				System.out.println("Exiting....Thank you!!!");
				break;
			}
			switch(input)
			{
			case 1:
				try {
					List<Customer>list = loginService.fetchAllCustomer();
					int flag=0;
					String username1="";
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
					do
					{
					if(flag!=0)
					{
						System.out.println("Username already Exists!!\nTry anyother....");
					}
					flag++;
					System.out.println("Enter Username:");
					username1=sc.next();
					}while(loginService.checkCustomerUsername(list,username1)); 
					System.out.println("Enter Password:");
					String password=sc.next();
					System.out.println("Enter Registration Date:");
					String registrationDate=sc.next();
					loginService.createCustomer(firstName,lastName,email,phoneNumber,address,username1,password,registrationDate);
					System.out.println("Signed Up successully!!");
				} catch (DatabaseConnectionException |SQLException e) {
					System.out.println(e.getMessage());
				} 
				break;
			case 2:
				try {
					List<Admin>list = loginService.fetchAllAdmin();
					String username1="";
					int flag=0;
					System.out.println("Enter First Name:");
					String firstName1=sc.next();
					System.out.println("Enter Last Name:");
					String lastName1=sc.next();
					System.out.println("Enter Email Id:");
					String email1=sc.next();
					System.out.println("Enter Phone Number:");
					String phoneNumber1=sc.next();
					do
					{
					if(flag!=0)
					{
						System.out.println("Username already Exists!!\nTry anyother....");
					}
					flag++;
					System.out.println("Enter Username:");
					username1=sc.next();
					}while(loginService.checkAdminUsername(list,username1)); 
					System.out.println("Enter Password:");
					String password1=sc.next();
					System.out.println("Enter Role:");
					String role=sc.next();
					loginService.createAdmin(firstName1,lastName1,email1,phoneNumber1,username1,password1,role);
					System.out.println("Signed Up successully!!");
				} catch (DatabaseConnectionException |SQLException e) {
					System.out.println(e.getMessage());
				} 
				break;
			case 3:
				try {
					List<Admin> adminlist = loginService.fetchAllAdmin();
					List<Customer> customerlist = loginService.fetchAllCustomer();
					System.out.println("Enter username:");
					String userName=sc.next();
					System.out.println("Enter password");
					String password=sc.next();
					LoginDto loginDto=loginService.loginCheck(adminlist,customerlist,userName);
					if(loginDto.getPassword().equals(password))
					{
						System.out.println("Logging In....");
						System.out.println("Welcome "+loginDto.getFirstName()+" "+loginDto.getLastName());
						if(loginDto.getRole().equals("customer"))
							ReservationController.main(args,sc);
						else
							AdminController.main(args,sc);
					}
					else
						throw new InvalidCredentialsException("Invalid Credentials!");
				} catch (SQLException | DatabaseConnectionException | InvalidCredentialsException | InvalidInputException | AdminNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				try {
					List<Admin> adminlist = loginService.fetchAllAdmin();
					List<Customer> customerlist = loginService.fetchAllCustomer();
					System.out.println("Enter username:");
					String userName=sc.next();
					System.out.println("Enter Already existing password:");
					String password=sc.next();
					LoginDto loginDto=loginService.loginCheck(adminlist,customerlist,userName);
					if(loginDto.getPassword().equals(password))
					{
						System.out.println("Hi "+loginDto.getFirstName()+" "+loginDto.getLastName()+"\nEnter your new Password:");
						String newPassword=sc.next();
						if(loginDto.getRole().equals("customer"))
						{
							CustomerService customerService=new CustomerService();
							customerService.updateCustomer(loginDto.getId(), "password", newPassword);
						}
						else
						{
							AdminService adminService=new AdminService();
							adminService.updateAdmin(loginDto.getId(), "password", newPassword);
						}
					}
					else
						throw new InvalidCredentialsException("Invalid Credentials!");
					System.out.println("Pasword changed!!!");
				} catch (SQLException | DatabaseConnectionException | InvalidCredentialsException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			}
		}
	}
}
