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
import com.util.DateDifference;


public class ReservationService {
	ReservationDao reservationDao=new ReservationDaoImpl();
	DateDifference dateDifference=new DateDifference();

	public List<Reservation> fetchAllReservation() throws SQLException, DatabaseConnectionException {
		List<Reservation> list = reservationDao.fetchAllReservation();
		return list;
	}

	public Reservation getReservationById(List<Reservation> list, int id) throws ReservationException {
		for (Reservation r : list) {
			if (r.getId() == id)
				return r;
		}
		throw new ReservationException("Reservation not found!!! Please recheck the Reservation Id");
	}

	public Reservation getReservationByCustomerId(List<Reservation> list, int id)throws ReservationException {
		for (Reservation r : list) {
			if (r.getCustomerId() == id)
				return r;
		}
		throw new ReservationException("Reservation not found for the entered customer Id");
	}

	 

	public void updateReservation(int id, String field, String newVal) throws  ReservationException, SQLException, DatabaseConnectionException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new ReservationException("Reservation ID could not be updated");
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
		throw new InvalidInputException("Customer is not found!!Recheck the entered Customer Id");
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
					totalCost=(dateDifference.calculateDaysDifference(startDate, endDate))*v.getDailyRate();
					break;
				}
				status="completed";
		 }
		 reservationDao.insertRecordInReservation(customerId,vehicleId,startDate,endDate,totalCost,status);
		
	}

	public void updateVehicleAvailability(List<Vehicle> list1, int vehicleId) throws SQLException, DatabaseConnectionException {
		int i=0;
		for(Vehicle v: list1) {
			if(v.getId()==vehicleId)
				i=0;
				reservationDao.updateVehicleAvailability(vehicleId,i);
		}

	
	}

	public boolean validateReservationId(List<Reservation> list, int id) throws ReservationException {
	     for(Reservation r:list) {
	    	 if(r.getId()==id)
	    		 return true;
	    		 
	     }
	    throw new ReservationException("Sorry!! Reservation id is not found to delete it");
		
	}

	 

}	 


