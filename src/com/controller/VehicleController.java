package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.VehicleNotFoundException;
import com.model.Vehicle;
import com.service.VehicleService;

public class VehicleController {
	public static void main(String[] args,Scanner sc){
		VehicleService vehicleService = new VehicleService();
		while (true) {
			System.out.println();
			System.out.println("**********VEHICLE OPERATION**********");
			System.out.println("press 1. Get vehicle By Id");
			System.out.println("press 2. Get vehicle By Number Plate");
			System.out.println("press 3. Get Available Vehicles");
			System.out.println("press 4. Add Vehicle");
			System.out.println("press 5. Update Vehicle");
			System.out.println("press 6. Remove Vehicle");
			System.out.println("press 7. Search Vehicle by model");
			System.out.println("press 0. for exit");
			System.out.println("**************************************");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting...Thank you!!!");
				break;
			}
			switch (input) {
			case 1:
				try {
					List<Vehicle> list = vehicleService.fetchAllVehicle();
					System.out.println("Enter Vehicle Id:");
					int id = sc.nextInt();
					Vehicle v = vehicleService.getVehicleById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-20s%-15s", "Id",
							"Make", "Model", "Year", "Color", "Registration Number", "Availability",
							"Daily Rate"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15f",
							v.getId(),v.getMake(),v.getModel(),v.getYear(),v.getColor(),v.getRegistrationNumber(),
							v.getAvailability(),v.getDailyRate() ));
				} catch (DatabaseConnectionException | SQLException  | VehicleNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 2:
				try {
					List<Vehicle> list = vehicleService.fetchAllVehicle();
					System.out.println("Enter Vehicle's registration number:");
					String registrationNumber = sc.next();
					Vehicle v = vehicleService.getVehicleByNumberPlate(list, registrationNumber);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-20s%-15s", "Id",
							"Make", "Model", "Year", "Color", "Registration Number", "Availability",
							"Daily Rate"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15f",
							v.getId(),v.getMake(),v.getModel(),v.getYear(),v.getColor(),v.getRegistrationNumber(),
							v.getAvailability(),v.getDailyRate() ));
				} catch (DatabaseConnectionException | SQLException  | VehicleNotFoundException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 3:
				try {
					List<Vehicle> list = vehicleService.fetchAllAvailableVehicle();
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-20s%-15s", "Id",
							"Make", "Model", "Year", "Color", "Registration Number", "Availability",
							"Daily Rate"));
					for(Vehicle v:list)
					{
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15f",
							v.getId(),v.getMake(),v.getModel(),v.getYear(),v.getColor(),v.getRegistrationNumber(),
							v.getAvailability(),v.getDailyRate() ));
					}
				} catch (DatabaseConnectionException | SQLException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 4:
				System.out.println("Make:");
				String make=sc.next();
				System.out.println("Model:");
				String model=sc.next();
				System.out.println("Year:");
				String year=sc.next();
				System.out.println("Color:");
				String color=sc.next();
				System.out.println("Registration Number:");
				String registrationNumber=sc.next();
				System.out.println("Availability:");
				int availability=sc.nextInt();
				System.out.println("Daily Rate:");
				double dailyRate=sc.nextDouble();
				try {
					vehicleService.addVehicle(make,model,year,color,registrationNumber,availability,dailyRate);
					System.out.println("Registration Successfull!!");
				} catch (DatabaseConnectionException |SQLException e) {
					System.out.println(e.getMessage());
				} 
				break;
			case 5:
				try {
					List<Vehicle> list = vehicleService.fetchAllVehicle();
					System.out.println("Enter Vehicle Id to be updated:");
					int id = sc.nextInt();
					System.out.println();
					Vehicle v = vehicleService.getVehicleById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-20s%-15s", "Id",
							"Make", "Model", "Year", "Color", "Registration Number", "Availability",
							"Daily Rate"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15f",
							v.getId(),v.getMake(),v.getModel(),v.getYear(),v.getColor(),v.getRegistrationNumber(),
							v.getAvailability(),v.getDailyRate() ));
					System.out.println("What do you want to update?");
					sc.nextLine();				
					String field=sc.nextLine();
					System.out.println("Enter the new value:");
					String newVal=sc.next();
					vehicleService.updateVehicle(id,field,newVal);
					System.out.println("Record updated Successfully");
				} catch (SQLException | DatabaseConnectionException | InvalidInputException | VehicleNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter Vehicle Id to be deleted");
				int id = sc.nextInt();
				try {
					List<Vehicle> list = vehicleService.fetchAllVehicle();
					vehicleService.getVehicleById(list, id);
					vehicleService.deleteVehicleById(id);
					System.out.println("Vehicle Removed!!");
				} catch (SQLException | DatabaseConnectionException | VehicleNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				try {
					System.out.println("Enter the model");
					String modell=sc.next();
					List<Vehicle> list = vehicleService.fetchVehicleOfModel(modell);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%-15s", "Id",
							"Make", "Model", "Year", "Color", "Registration Number", "Availability",
							"Daily Rate"));
					for(Vehicle v:list)
					{
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-20s%-15f",
							v.getId(),v.getMake(),v.getModel(),v.getYear(),v.getColor(),v.getRegistrationNumber(),
							v.getAvailability(),v.getDailyRate() ));
					}
					
				} catch (SQLException |DatabaseConnectionException e) {
					System.out.println(e.getMessage());
				} 
				break;
			default:
				try
				{
					throw new InvalidInputException("Enter a correct choice");
				}catch(InvalidInputException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
}
