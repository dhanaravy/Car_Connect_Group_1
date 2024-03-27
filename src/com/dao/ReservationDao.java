package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.exception.DatabaseConnectionException;
import com.model.Customer;
import com.model.Reservation;
import com.model.Vehicle;

public interface ReservationDao {

	List<Reservation> fetchAllReservation() throws SQLException, DatabaseConnectionException;

    void updateReservation(int id, String fieldd, String newVal) throws SQLException, DatabaseConnectionException;

	void deleteReservationById(int id) throws SQLException, DatabaseConnectionException;

	List<Customer> fetchAllCustomers() throws SQLException, DatabaseConnectionException;

	List<Vehicle> fetchAllVehicles() throws SQLException, DatabaseConnectionException;

	void insertRecordInReservation(int customerId, int vehicleId, LocalDate startDate, LocalDate endDate, double totalCost,
			String status) throws SQLException, DatabaseConnectionException;
	
	void updateVehicleAvailability(int vehicleId, int i) throws SQLException, DatabaseConnectionException;

	void updateVehicleAvailabilityAdd(int vId) throws SQLException, DatabaseConnectionException;

	 

	 

}
