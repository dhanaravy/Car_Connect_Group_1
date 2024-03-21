package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.DatabaseConnectionException;
import com.model.Vehicle;

public interface VehicleDao {

	List<Vehicle> fetchAllVehicle() throws SQLException, DatabaseConnectionException;

	List<Vehicle> fetchAllAvailableVehicle() throws SQLException, DatabaseConnectionException;

	void addVehicle(String make, String model, String year, String color, String registrationNumber, int availability,
			double dailyRate) throws SQLException, DatabaseConnectionException;

	void updatevehicle(int id, String fieldd, String newVal) throws DatabaseConnectionException, SQLException;

	void deleteVehicleById(int id) throws SQLException, DatabaseConnectionException;

	List<Vehicle> fetchVehicleOfModel(String modell) throws DatabaseConnectionException, SQLException;

}
