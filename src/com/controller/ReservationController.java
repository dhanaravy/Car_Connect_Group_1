package com.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.exception.DatabaseConnectionException;
import com.exception.InvalidInputException;
import com.exception.ReservationException;
import com.model.Customer;
import com.model.Reservation;
import com.model.Vehicle;
import com.service.ReservationService;

public class ReservationController {

	public static void main(String[] args,Scanner sc) {
		ReservationService reservationService=new ReservationService();
		while (true) {
			System.out.println();
			System.out.println("**********RESERVATION OPERATION**********");
			System.out.println("press 1. Get Reservation By Id");
			System.out.println("press 2. Get Reservation By CustomerId");
			System.out.println("press 3. To Reserve a Vehicle");
			System.out.println("press 4. Update Reservation");
			System.out.println("press 5. Cancel Reservation");
			System.out.println("press 0. for exit");
			System.out.println("*****************************************");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting...Thank you!!!");
				break;
			}
			switch (input) {
			case 1:
				try {
					List<Reservation> list = reservationService.fetchAllReservation();
					System.out.println("Enter Reservation Id:");
					int id = sc.nextInt();
					Reservation r = reservationService.getReservationById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Reservation Id",
							"Customer Id", "Vehicle Id", "Start Date", "End Date", "Total Cost", "Status"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%s", r.getId(),
							r.getCustomerId(), r.getVehicleId(), r.getStartDate().toString(), r.getEndDate().toString(), r.getTotalCost(),
							r.getStatus()));
				} catch (DatabaseConnectionException | SQLException | ReservationException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 2:
				try {
					List<Reservation> list = reservationService.fetchAllReservation();
					System.out.println("Enter Customer Id:");
					int id = sc.nextInt();
					Reservation r = reservationService.getReservationByCustomerId(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Reservation Id",
							"Customer Id", "Vehicle Id", "Start Date", "End Date", "Total Cost", "Status"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%s", r.getId(),
							r.getCustomerId(), r.getVehicleId(), r.getStartDate().toString(), r.getEndDate().toString(), r.getTotalCost(),
							r.getStatus()));
				} catch (DatabaseConnectionException | SQLException | ReservationException e) {
					System.out.println(e.getMessage());
					break;
				}
				break;
			case 3:
				try {
				List<Customer> list = reservationService.fetchAllCustomers();
				System.out.println("Enter your ID: ");
				int customerId = sc.nextInt();	
				sc.nextLine();
				Customer c = reservationService.ValidateCustomerId(list, customerId);
				System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Customer Id",
						"First Name", "Last Name", "Phone Number", "Address", "User Name", "Password",
						"Registration Date"));
				System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s%s", c.getId(),
						c.getFirstName(), c.getLastName(), c.getPhoneNumber(), c.getAddress(), c.getUsername(),
						c.getPassword(), c.getRegistrationDate().toString()));
				System.out.println();
				List<Vehicle> list1 = reservationService.fetchAllVehicles();
				System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%-15s%s", "Vehicle Id",
						"Model", "Make", "Year", "Color", "Reg Number", "Availability",
						"Daily rate"));
				for(Vehicle v : list1) {
							System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%-15s%s", v.getId(),
							v.getModel(), v.getMake(), v.getYear(), v.getColor(), v.getRegistrationNumber(),
							v.getAvailability(), v.getDailyRate()));
				}
				System.out.println();
				System.out.println("Enter vehicle ID to reserve: ");
				int vehicleId = sc.nextInt();
				sc.nextLine();
				boolean isAvailable = reservationService.isTicketAvailable(list1,vehicleId);
				if(isAvailable == false) {
					System.out.println("Vehicle not available");
					break; 
				}
				System.out.println("Enter start date in the format 'yyyy-mm-dd': ");
				String startdate=sc.nextLine();
				LocalDate startDate = LocalDate.parse(startdate);
				System.out.println("Enter end date in the format 'yyyy-mm-dd': ");
				String enddate=sc.next();
				LocalDate endDate = LocalDate.parse(enddate);
				reservationService.insertRecordInReservation(list1,customerId,vehicleId,startDate,endDate);
				reservationService.updateVehicleAvailability(list1,vehicleId);
				System.out.println("Vehicle reserved Successfully");
				}catch (DatabaseConnectionException | SQLException | InvalidInputException e) {
					System.out.println(e.getMessage());
				}
					break;
			case 4:
				try {
					List<Reservation> list = reservationService.fetchAllReservation();
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Reservation Id",
							"Customer Id", "Vehicle Id", "Start Date", "End Date", "Total Cost", "Status"));
					for(Reservation r:list) {
						System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%s", r.getId(),
								r.getCustomerId(), r.getVehicleId(), r.getStartDate().toString(), r.getEndDate().toString(), r.getTotalCost(),
								r.getStatus()));
					}
					System.out.println("");
					System.out.println("Enter Reservation Id to be updated:");
					int id = sc.nextInt();
					System.out.println();
					Reservation r = reservationService.getReservationById(list, id);
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Reservation Id",
							"Customer Id", "Vehicle Id", "Start Date", "End Date", "Total Cost", "Status"));
					System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%s", r.getId(),
							r.getCustomerId(), r.getVehicleId(), r.getStartDate().toString(), r.getEndDate().toString(), r.getTotalCost(),
							r.getStatus()));
					System.out.println("What do you want to update?");
					sc.nextLine();				
					String field=sc.nextLine();
					System.out.println("Enter the new value:");
					String newVal=sc.next();
					reservationService.updateReservation(id,field,newVal);
					System.out.println("Record updated Successfully");
				} catch (SQLException | DatabaseConnectionException | ReservationException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				try {
					List<Reservation> list = reservationService.fetchAllReservation();
					System.out.println(String.format("%-15s%-15s%-15s%-15s%-15s%-15s%s", "Reservation Id",
							"Customer Id", "Vehicle Id", "Start Date", "End Date", "Total Cost", "Status"));
					for(Reservation r:list) {
						System.out.println(String.format("%-15d%-15s%-15s%-15s%-15s%-15s%s", r.getId(),
								r.getCustomerId(), r.getVehicleId(), r.getStartDate().toString(), r.getEndDate().toString(), r.getTotalCost(),
								r.getStatus()));
					}
					System.out.println("");
					System.out.println("Enter Reservation Id to be deleted");
					int id = sc.nextInt();
					boolean check=reservationService.validateReservationId(list,id);
					reservationService.updateVehicleAvailabilityAdd(list,id);
					if(check==true)
						reservationService.deleteReservationById(id);	   
					System.out.println("Records Deleted!!");
				} catch (SQLException | DatabaseConnectionException | ReservationException e) {
					System.out.println(e.getMessage());
				}
			}
			
		}
		
	}
}
		

	