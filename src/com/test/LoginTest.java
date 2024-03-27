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
import com.dto.LoginDto;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidCredentialsException;
import com.model.Admin;
import com.model.Customer;
import com.service.LoginService;



public class LoginTest {
	CustomerDao customerDao=new CustomerDaoImpl();
	AdminDao adminDao=new AdminDaoImpl();
	LoginService loginService = new LoginService();
	
	Scanner sc = new Scanner(System.in);
	
	@Test
	
	public void checkAdminUsername() {
	    List<Admin> list;
	    try {
	        list = loginService.fetchAllAdmin();
	        String validUsername = "jermy123";
	        Assert.assertTrue(loginService.checkAdminUsername(list, validUsername));
	        
	        String invalidUsername = "jermy"; // wrong input
	        Assert.assertFalse(loginService.checkAdminUsername(list, invalidUsername));
	        
	    } catch (SQLException | DatabaseConnectionException e) {
	        Assert.assertEquals("unable to establish a connection to the database :(", e.getMessage());
	        e.printStackTrace();
	    }
	}

		
	
		
	
	
	@Test
	public void checkCustomerUsername() {
		List<Customer> list;
		try {
			list=loginService.fetchAllCustomer();
			
			String username="sandiyav";
			Assert.assertTrue(loginService.checkCustomerUsername(list, username));
			
			
	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			
			e.printStackTrace();
		}
		
	}
	@Test
	public void loginCheck() throws SQLException, DatabaseConnectionException {
		List<Customer> list;
		try {
		list=loginService.fetchAllCustomer();
		List<Admin> list1;
		list1 = loginService.fetchAllAdmin();
		int id=sc.nextInt();
		String firstName="Jermy";
		String lastName="Gilbert";
		
		
		String username="jermy123";
		String password="415263";
		String role="admin";
		LoginDto loginDto = new LoginDto(id,firstName,lastName,username,password,role);
		
			Assert.assertEquals(loginDto,loginService.loginCheck(list1, list, username));
		} catch (InvalidCredentialsException e) {
			
			e.printStackTrace();
		}
	}

}
