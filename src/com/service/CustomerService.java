package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.*;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Customer;

public class CustomerService {

	CustomerDao customerDao = new CustomerDaoImpl();

	public List<Customer> fetchAllCustomers() throws SQLException, DatabaseConnectionException 
	{
		List<Customer> list = customerDao.fetchAllCustomers();
		return list;
	}

	public Customer getCustomerById(List<Customer> list, int id) throws InvalidInputException 
	{
		for (Customer c : list) {
			if (c.getId() == id)
				return c;
		}
		throw new InvalidInputException("Cuustomer not found :(\nRecheck the Customer Id");
	}

	public Customer getCustomerByUsername(List<Customer> list, String name) throws InvalidInputException
	{
		for (Customer c : list) 
		{
			if (c.getUsername().equals(name))
				return c;
		}
		throw new InvalidInputException("Incorrect Username :<");
	}

	public void createCustomer(String firstName, String lastName, String email, String phoneNumber, String address,
			String username, String password, String registrationDate)
			throws DatabaseConnectionException, SQLException 
	{

		customerDao.createCustomer(firstName, lastName, email, phoneNumber, address, username, password,
				registrationDate);

	}

	public void updateCustomer(int id, String field, String newVal) throws DatabaseConnectionException, SQLException, InvalidInputException 
	{
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new InvalidInputException("Sorry!! Customer Id could not be updated :<");
		else
			customerDao.updateCustomer(id,fieldd,newVal);
		
	}

	public void deleteCustomerById(int id) throws SQLException, DatabaseConnectionException 
	{
		
		customerDao.deleteCustomerById(id);
		
	}

}
