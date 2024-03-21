package com.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import com.dao.ReservationDaoImpl;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.ReservationException;
import com.dao.ReservationDao;
import com.model.Customer;
import com.model.Reservation;
import com.model.Vehicle;


public class ReservationService {
	ReservationDao reservationDao=new ReservationDaoImpl();
	

	public List<Reservation> fetchAllReservation() throws SQLException, DatabaseConnectionException {
		List<Reservation> list = reservationDao.fetchAllReservation();
		return list;
	}

	public Reservation getReservationById(List<Reservation> list, int id) throws ReservationException {
		for (Reservation r : list) {
			if (r.getId() == id)
				return r;
		}
		throw new ReservationException("Reservation not found :(\nRecheck the Reservation Id");
	}

	public Reservation getReservationByCustomerId(List<Reservation> list, int id)throws ReservationException {
		for (Reservation r : list) {
			if (r.getCustomerId() == id)
				return r;
		}
		throw new ReservationException("Reservation not found :(\nRecheck the Reservation Id");
	}

	 

	public void updateReservation(int id, String field, String newVal) throws  ReservationException, SQLException, DatabaseConnectionException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new ReservationException("Sorry!! Reservation  could not be updated :<");
		else
			reservationDao.updateReservation(id,fieldd,newVal);
		}

	public void deleteReservationById(int id) throws SQLException, DatabaseConnectionException {
		reservationDao.deleteReservationById(id);
		
	}
	
	public List<Customer> fetchAllCustomers() throws SQLException, DatabaseConnectionException {
		List<Customer> list = reservationDao.fetchAllCustomers();
		return list;
	}

	public Customer ValidateCustomerId(List<Customer> list, int customerId) throws InvalidInputException {
		for (Customer c : list) {
			if (c.getId() == customerId)
				return c;
		}
		throw new InvalidInputException("Cuustomer not found :(\nRecheck the Customer Id");
	}

	public List<Vehicle> fetchAllVehicles() throws SQLException, DatabaseConnectionException {
		List<Vehicle> list = reservationDao.fetchAllVehicles();
		return list;
	}

	public boolean isTicketAvailable(List<Vehicle> list, int vehicleId) {
		for(Vehicle v : list) {
			if(v.getId() == vehicleId) {
				if(v.getAvailability()==1)
					return true; 
			}
		}
		return false;
	}

	
	public void insertRecordInReservation(List<Vehicle> list1, int customerId, int vehicleId, LocalDate startDate,
			LocalDate endDate) throws SQLException, DatabaseConnectionException {
	     double totalCost=0;
		 String status = null;
		 for(Vehicle v : list1) {
				if(v.getId() == vehicleId) {
					totalCost=10*v.getDailyRate();
					break;
				}
				status="completed";
		 }
		 reservationDao.insertRecordInReservation(customerId,vehicleId,startDate,endDate,totalCost,status);
		
	}

	 

	

	

}	 


