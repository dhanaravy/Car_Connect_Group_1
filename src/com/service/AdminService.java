package com.service;

import java.sql.SQLException;
import java.util.List;
import com.dao.*;
import com.exception.AdminNotFoundException;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.model.Admin;
import com.model.Customer;
import com.model.Reservation;

public class AdminService {
	AdminDao adminDao=new AdminDaoImpl();
	public List<Admin> fetchAllAdmins() throws SQLException, DatabaseConnectionException {
		List<Admin> list = adminDao.fetchAllAdmins();
		return list;
	}
	public Admin getAdminById(List<Admin> list, int id) throws InvalidInputException, AdminNotFoundException {
		for (Admin a : list) {
			if (a.getId() == id)
				return a;
		}
		throw new AdminNotFoundException("Admin not found :(\nRecheck the Admin Id");
	}
	public Admin getAdminByUsername(List<Admin> list, String name) throws InvalidInputException, AdminNotFoundException {
		for (Admin a : list) {
			if (a.getUserName().equals(name))
				return a;
		}
		throw new AdminNotFoundException("Incorrect Username :<");
	}
	public void createAdmin(String firstName, String lastName, String email, String phoneNumber, String username,
			String password, String role, String joinDate) throws SQLException, DatabaseConnectionException {
		adminDao.createAdmin(firstName, lastName, email, phoneNumber, username, password,role,joinDate);
		
	}
	public void updateAdmin(int id, String field, String newVal) throws InvalidInputException, SQLException, DatabaseConnectionException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new InvalidInputException("Sorry!! Admin Id could not be updated :<");
		else
			adminDao.updateAdmin(id,fieldd,newVal);
		
	}
	public void deleteAdminById(int id) throws SQLException, DatabaseConnectionException {
		adminDao.deleteAdminById(id);
		
	}
	public List<Reservation> displayReservationByDate(String startDate, String endDate) throws SQLException, DatabaseConnectionException {
		return adminDao.displayReservationByDate(startDate,endDate);
		
	}
	

}
