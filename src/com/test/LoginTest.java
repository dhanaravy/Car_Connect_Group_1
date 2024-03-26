package com.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exception.DatabaseConnectionException;
import com.model.Admin;
import com.model.Customer;
import com.service.LoginService;



public class LoginTest {
	CustomerDao customerDao=new CustomerDaoImpl();
	AdminDao adminDao=new AdminDaoImpl();
	LoginService loginService = new LoginService();
	
	Scanner sc = new Scanner(System.in);
	@Test
	public  void fetchAllAdmin() {
		
		
	}
	public void checkAdminUsername() {
		List<Admin> list;
		try {
			list = loginService.fetchAllAdmin();
			String username="raj";
			Assert.assertEquals(false, loginService.checkAdminUsername(list, username));
			
		} catch (SQLException | DatabaseConnectionException e) {
			Assert.assertEquals("unable to establish a connection to the database :(",e.getMessage());
			e.printStackTrace();
		}
		
	
		
	}
	public void fetchAllCustomer() {
		
	}
	public void checkCustomerUsername() {
		List<Customer> list;
		try {
			list=loginService.fetchAllCustomer();
			String username="ram";
			Assert.assertEquals(true, loginService.checkCustomerUsername(list, username));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loginCheck() {
		List<Customer> list;
		
		
		
		String firstname=sc.nextLine();
		String lastname=sc.nextLine();
		String username=sc.nextLine();
		String password=sc.nextLine();
	}

}
