package com.test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import com.exception.DatabaseConnectionException;
import com.exception.ReservationException;
import com.model.Reservation;
import com.model.Vehicle;
import com.service.ReservationService;

public class ReservationServiceTest {
	
	ReservationService reservationService=new ReservationService();
	Reservation reservation=new Reservation();
	
	 @Test
	 public void fetchReservationByIdTest() throws SQLException, DatabaseConnectionException {
		try {	
	    List<Reservation> list=new ArrayList<>();
	    
	    /*Use case 1*/
		int id=2;
		Reservation expectedReservation=new Reservation(2,5,10,LocalDate.parse("2024-02-08"),LocalDate.parse("2024-02-15"),8000," pending");
		Assert.assertEquals(expectedReservation,reservationService.getReservationById(list,id));
	
		/*Use case 2:Valid Id*/
		id=4;
		Reservation expectedReservation1=new Reservation(4,4,5,LocalDate.parse("2024-01-25"),LocalDate.parse("2024-02-10"),10000,"confirmed");
		Assert.assertEquals(expectedReservation1,reservationService.getReservationById(list,id));
		
		/*Use case 2:Invalid Id*/
		id=12;
		Reservation expectedReservation2=new Reservation(4,4,5,LocalDate.parse("2024-01-25"),LocalDate.parse("2024-02-10"),10000,"confirmed");
		Assert.assertEquals(expectedReservation2,reservationService.getReservationById(list,id));
		
		}catch(ReservationException e) {
			Assert.assertEquals("Reservation not found!!! Please recheck the Reservation Id", e.getMessage());
		}
	}
	 
	 @Test
	 public void getReservationByCustomerIdTest() throws SQLException, DatabaseConnectionException{
		 try {	
			    List<Reservation> list=new ArrayList<>();
			    
			    /*Use case 1*/
				int id=5;
				Reservation expectedReservation=new Reservation(2,5,10,LocalDate.parse("2024-02-08"),LocalDate.parse("2024-02-15"),8000," pending");
				Assert.assertEquals(expectedReservation,reservationService.getReservationByCustomerId(list,id));
			
				/*Use case 2:Valid Id*/
				id=4;
				Reservation expectedReservation1=new Reservation(4,4,5,LocalDate.parse("2024-01-25"),LocalDate.parse("2024-02-10"),10000,"confirmed");
				Assert.assertEquals(expectedReservation1,reservationService.getReservationByCustomerId(list,id));
				
				/*Use case 2:Invalid Id*/
				id=12;
				Reservation expectedReservation2=new Reservation(4,4,5,LocalDate.parse("2024-01-25"),LocalDate.parse("2024-02-10"),10000,"confirmed");
				Assert.assertEquals(expectedReservation2,reservationService.getReservationByCustomerId(list,id));
				
				}catch(ReservationException e) {
					Assert.assertEquals("Reservation not found for the entered customer Id", e.getMessage());
				}
			}
	 @Test
	 public void reserveVehicleTest() {
			try {
				List<Vehicle> list = reservationService.fetchAllVehicles();
				/*Use case 1:*/
				 int initialCount=reservationService.fetchAllReservation().size();
				 reservationService.insertRecordInReservation(list, 1, 7, LocalDate.parse("2024-03-17"), LocalDate.parse("2024-03-20"));
				 int updatedRecordCount=reservationService.fetchAllReservation().size();
				 Assert.assertEquals(initialCount+1, updatedRecordCount);
			} catch (SQLException e) {
				 System.out.println(e.getMessage());
			} catch (DatabaseConnectionException e) {
				System.out.println(e.getMessage());
			}	 
	 }
	 
	 @Test
	 public void fetchAllReservationTest() {
		 try {
			List<Reservation> allReservations=reservationService.fetchAllReservation();
			/*Use case 1:To check all reservations*/
			Assert.assertTrue(allReservations.size()>0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		}
	 }
	 
	 
	 @Test
	 public void updateReservationTest() {
		 try {
			/*Use case 1:To update status*/
			List<Reservation> list=reservationService.fetchAllReservation();
			Reservation r=list.get(0);
			int id=r.getId();
			reservationService.updateReservation(id,"status","confirmed");
			Reservation updatedReservation=reservationService.getReservationById(list, id);
			Assert.assertEquals("confirmed",updatedReservation.getStatus());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (DatabaseConnectionException e) {
			System.out.println(e.getMessage());
		} catch (ReservationException e) {
			System.out.println(e.getMessage());
		}
	 }
	 }

