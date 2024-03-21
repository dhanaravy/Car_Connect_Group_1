package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.VehicleDao;
import com.dao.VehicleDaoImpl;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.VehicleNotFoundException;
import com.model.Vehicle;

public class VehicleService {
	VehicleDao vehicleDao=new VehicleDaoImpl();
	public List<Vehicle> fetchAllVehicle() throws SQLException, DatabaseConnectionException {
		List<Vehicle> list = vehicleDao.fetchAllVehicle();
		return list;
	}
	public Vehicle getVehicleById(List<Vehicle> list, int id) throws VehicleNotFoundException {
		for (Vehicle v : list) {
			if (v.getId() == id)
				return v;
		}
		throw new VehicleNotFoundException("Vehicle not found :(\nRecheck the Vehicle Id");
	}
	public Vehicle getVehicleByNumberPlate(List<Vehicle> list, String registrationNumber) throws VehicleNotFoundException {
		for (Vehicle v : list) {
			if (v.getRegistrationNumber().equals(registrationNumber))
				return v;
		}
		throw new VehicleNotFoundException("Vehicle not found :(\nRecheck the Vehicle Number");
	}
	public List<Vehicle> fetchAllAvailableVehicle() throws SQLException, DatabaseConnectionException {
		List<Vehicle> list = vehicleDao.fetchAllAvailableVehicle();
		return list;
	}
	public void addVehicle(String make, String model, String year, String color, String registrationNumber,
			int availability, double dailyRate) throws SQLException, DatabaseConnectionException {
		vehicleDao.addVehicle(make,model, year, color,registrationNumber,availability,  dailyRate);
		
	}
	public void updateVehicle(int id, String field, String newVal) throws InvalidInputException, DatabaseConnectionException, SQLException {
		String fieldd=field.toLowerCase().replace(" ","_");
		if(fieldd.equals("id"))
			throw new InvalidInputException("Sorry!! Vehicle Id could not be updated :<");
		else
			vehicleDao.updatevehicle(id,fieldd,newVal);
		
	}
	public void deleteVehicleById(int id) throws SQLException, DatabaseConnectionException {
		vehicleDao.deleteVehicleById(id);
		
	}
	public List<Vehicle> fetchVehicleOfModel(String modell) throws DatabaseConnectionException, SQLException {
		List<Vehicle> list = vehicleDao.fetchVehicleOfModel(modell);
		return list;
	}
	
}
