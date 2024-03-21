package com.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.dto.LoginDto;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidCredentialsException;
import com.model.Admin;
import com.model.Customer;

public class LoginService {
		CustomerDao customerDao=new CustomerDaoImpl();
		AdminDao adminDao=new AdminDaoImpl();
		public void createCustomer(String firstName, String lastName, String email, String phoneNumber, String address,
			String username, String password, String registrationDate)
			throws DatabaseConnectionException, SQLException {

			customerDao.createCustomer(firstName, lastName, email, phoneNumber, address, username, password,
				registrationDate);

		}
		public void createAdmin(String firstName, String lastName, String email, String phoneNumber, String username,
				String password, String role) throws SQLException, DatabaseConnectionException {
			
			adminDao.createAdmin(firstName, lastName, email, phoneNumber,username, password,role,LocalDate.now().toString());
		}
		
		public List<Admin> fetchAllAdmin() throws SQLException, DatabaseConnectionException {
		List<Admin> list = adminDao.fetchAllAdmins();
		return list;
	}
		public boolean checkAdminUsername(List<Admin> list,String username) {
			
			for(Admin a:list)
			{
				if(a.getUserName().equals(username))
					return true;
			}
			return false;
		}
		public List<Customer> fetchAllCustomer() throws SQLException, DatabaseConnectionException {
			List<Customer> list = customerDao.fetchAllCustomers();
			return list;
		}
		public boolean checkCustomerUsername(List<Customer> list, String username) {
			for(Customer c:list)
			{
				if(c.getUsername().equals(username))
					return true;
			}
			return false;
		}
		public LoginDto loginCheck(List<Admin> adminlist, List<Customer> customerlist, String username) throws InvalidCredentialsException {
			for(Customer c:customerlist)
			{
				if(c.getUsername().equals(username))
				{
					return new LoginDto(c.getId(),c.getFirstName(),c.getLastName(),c.getUsername(),c.getPassword(),"customer");
				}
			}
			for(Admin a:adminlist)
			{
				if(a.getUserName().equals(username))
				{
					return new LoginDto(a.getId(),a.getFirstName(),a.getLastName(),a.getUserName(),a.getPassword(),"admin");
				}
			}
			throw new InvalidCredentialsException("Invalid Credentials!");
		}

}
