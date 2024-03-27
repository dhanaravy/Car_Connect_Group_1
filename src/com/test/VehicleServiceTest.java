package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.VehicleNotFoundException;
import com.model.Vehicle;
import com.service.VehicleService;

public class VehicleServiceTest {
	VehicleService vehicleService = new VehicleService();

	@Test
	public void getVehicleByIdTest() {
		try {
			List<Vehicle> list = vehicleService.fetchAllVehicle();
			
			// Usecase1
			int id=1;
			Vehicle vehicle=new Vehicle( 1,"petrol","BMW","2018","red","12A",0,650.0);
			Assert.assertEquals(vehicle, vehicleService.getVehicleById(list,id)); 
			
			// Usecase2
			int id1=10;
			Vehicle vehicle1=new Vehicle( 10,"petrol","BMW","2013","white","3A",1,760.0);
			Assert.assertEquals(vehicle1, vehicleService.getVehicleById(list,id1)); 
			
			// Usecase3
			int id2=11;
			Vehicle vehicle2=new Vehicle( 10,"petrol","BMW","2013","white","3A",1,760.0);
			Assert.assertEquals(vehicle2, vehicleService.getVehicleById(list,id2)); 
		}catch (SQLException e ) {
			Assert.fail();
		}catch(DatabaseConnectionException e) {
			Assert.assertEquals("Unable to establish a connection to the database :(", e.getMessage());
		}catch(VehicleNotFoundException e) {
			Assert.assertEquals("Vehicle not found :(\nRecheck the Vehicle Id", e.getMessage());
		}
	}
	
	@Test
	public void getVehicleByNumberPlateTest() {
		try {
			List<Vehicle> list = vehicleService.fetchAllVehicle();
			
			// Usecase1
			String registrationNumber="12A";
			Vehicle vehicle=new Vehicle( 1,"petrol","BMW","2018","red","12A",0,650.0);
			Assert.assertEquals(vehicle, vehicleService.getVehicleByNumberPlate(list,registrationNumber)); 
			
			// Usecase2
			String registrationNumber1="3A";
			Vehicle vehicle1=new Vehicle( 10,"petrol","BMW","2013","white","3A",1,760.0);
			Assert.assertEquals(vehicle1, vehicleService.getVehicleByNumberPlate(list,registrationNumber1));
			
			// Usecase3
			String registrationNumber2="1A";
			Vehicle vehicle2=new Vehicle( 1,"petrol","BMW","2018","red","12A",1,650.0);
			Assert.assertEquals(vehicle2, vehicleService.getVehicleByNumberPlate(list,registrationNumber2));
		}catch (SQLException e ) {
			Assert.fail();
		}catch(DatabaseConnectionException e) {
			Assert.assertEquals("Unable to establish a connection to the database :(", e.getMessage());
		}catch(VehicleNotFoundException e) {
			Assert.assertEquals("Vehicle not found :(\nRecheck the Vehicle Number", e.getMessage());
		}
	}
	
	@Test
	public void fetchVehicleOfModelTest() {
		try {
			// Usecase1
			List<Vehicle> list=new ArrayList<>();
			String model="petrol";
			Vehicle vehicle=new Vehicle( 1,"petrol","BMW","2018","red","12A",0,650.0);
			Vehicle vehicle1=new Vehicle( 4,"petrol","SKODA","2022","blue","9A",1,300.0);
			Vehicle vehicle2=new Vehicle( 8,"petrol","TATA","2023","black","5A",0,400.0);
			Vehicle vehicle3=new Vehicle( 10,"petrol","BMW","2013","white","3A",1,760.0);
			
			list.add(vehicle);
			list.add(vehicle1);
			list.add(vehicle2);
			list.add(vehicle3);
			Assert.assertEquals(list,vehicleService.fetchVehicleOfModel(model));
			
			// Usecase2
			List<Vehicle> list1=new ArrayList<>();
			String model1="disel";
			Vehicle v=new Vehicle( 2,"disel","BENZ","2019","red","11A",1,600.0);
			Vehicle v1=new Vehicle( 5,"disel","NISSAN","2018","green","8A",0,550.0);
			Vehicle v2=new Vehicle( 6,"disel","AUDI","2012","blue","7A",0,450.0);
			Vehicle v3=new Vehicle( 7,"disel","TATA","2008","black","6A",1,400.0);
						
			list1.add(v);
			list1.add(v1);
			list1.add(v2);
			list1.add(v3);
			Assert.assertEquals(list1,vehicleService.fetchVehicleOfModel(model1));
			
			// Usecase2
			List<Vehicle> list2=new ArrayList<>();
			String model3="EV";
			Vehicle ve=new Vehicle( 3,"EV","MARUTHI","2020","white","10A",0,250.0);
			Vehicle ve1=new Vehicle( 9,"EV","SKODA","2024","blue","4A",0,360.0);
			
			list2.add(ve);
			list2.add(ve1);
			Assert.assertEquals(list2,vehicleService.fetchVehicleOfModel(model3));
			
		} catch (DatabaseConnectionException e) {
			Assert.assertEquals("Unable to establish a connection to the database :(", e.getMessage());
		} catch (SQLException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void addVehicleTest() {
		try {
			int initialRecordCount=vehicleService.fetchAllVehicle().size();
			vehicleService.addVehicle("battery","BMW" ,"2023","red", "15A", 1,1000.0);
			int updatedRecordCount=vehicleService.fetchAllVehicle().size();
			
			Assert.assertEquals(initialRecordCount+1, updatedRecordCount);
		} catch (SQLException e) {
			Assert.fail();
		} catch (DatabaseConnectionException e) {
			Assert.assertEquals("Unable to establish a connection to the database :(", e.getMessage());
		}
	}
	
	@Test
	public void updateVehicleTest() {
		try {
			List<Vehicle> list=vehicleService.fetchAllVehicle();
			Vehicle vehicle=list.get(0);
			int id=vehicle.getId();
			vehicleService.updateVehicle(id, "availability", "0");
			
			Vehicle updatedVehicle=vehicleService.getVehicleById(list, id);
			Assert.assertEquals(0,updatedVehicle.getAvailability());
		} catch (SQLException e) {
			Assert.fail();
		} catch (DatabaseConnectionException e) {
			Assert.assertEquals("Unable to establish a connection to the database :(", e.getMessage());
		} catch (InvalidInputException e) {
			Assert.assertEquals("Sorry!! Vehicle Id could not be updated :<", e.getMessage());
		} catch (VehicleNotFoundException e) {
			Assert.assertEquals("Vehicle not found :(\nRecheck the Vehicle Id", e.getMessage());
		}
		
	}
	
	@Test
	    public void fetchAllAvailableVehicleTest() {
	        try {
	            List<Vehicle> availableVehicles = vehicleService.fetchAllAvailableVehicle();
	            for (Vehicle vehicle : availableVehicles) {
	                Assert.assertEquals(1, vehicle.getAvailability());
	            }
	        } catch (SQLException e) {
	            Assert.fail();
	        }catch (DatabaseConnectionException e) {
				Assert.assertEquals("Unable to establish a connection to the database :(", e.getMessage());
			}
	    }

	    @Test
	    public void fetchAllVehicleTest() {
	        try {
	            List<Vehicle> allVehicles = vehicleService.fetchAllVehicle();
	            Assert.assertTrue(allVehicles.size() > 0);
	        } catch (SQLException e) {
	            Assert.fail();
	        }catch (DatabaseConnectionException e) {
				Assert.assertEquals("Unable to establish a connection to the database :(", e.getMessage());
			}
	    }
	
}
