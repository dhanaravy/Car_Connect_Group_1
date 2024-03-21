package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.DatabaseConnectionException;
import com.model.Customer;
import com.model.Reservation;
import com.model.Vehicle;
import com.util.DBUtil;

public class ReservationDaoImpl implements ReservationDao {

	public List<Reservation> fetchAllReservation() throws DatabaseConnectionException, SQLException {
		List<Reservation> list = new ArrayList<>();
		Connection conn = DBUtil.getDbConn();

		String sql = "select * from reservation";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id = rst.getInt("id");
			
			int customerId = rst.getInt("customer_id");
			int vehicleId = rst.getInt("vehicle_id");
			LocalDate startDate = rst.getDate("start_date").toLocalDate();
			LocalDate endDate = rst.getDate("end_date").toLocalDate();
			double totalCost=rst.getDouble("total_cost");
			String status = rst.getString("status");
			Reservation r = new Reservation(id, customerId, vehicleId, startDate, endDate, totalCost, status);
			list.add(r);
		}

		DBUtil.DBClose();
		return (list);
	}

	 

	@Override
	public void updateReservation(int id, String field, String newVal) throws DatabaseConnectionException,SQLException {
		Connection conn = DBUtil.getDbConn();
		String sql = "update reservation set "+field+"=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(field.equals("start_date"))
			pstmt.setDate(1, Date.valueOf(newVal));
		else if(field.equals("end_date"))
		    pstmt.setDate(1, Date.valueOf(newVal));
		else
			pstmt.setString(1,newVal);
		
		pstmt.setInt(2, id);
		pstmt.executeUpdate();
		DBUtil.DBClose();
		
	}

	@Override
	public void deleteReservationById(int id) throws DatabaseConnectionException,SQLException {
		Connection conn = DBUtil.getDbConn();
		String sql = "delete from reservation where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBUtil.DBClose();
		
	}



	@Override
	public List<Customer> fetchAllCustomers() throws DatabaseConnectionException, SQLException {
		List<Customer> list = new ArrayList<>();
		Connection conn = DBUtil.getDbConn();

		String sql = "select * from customer";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id = rst.getInt("id");
			
			String firstName = rst.getString("first_name");
			String lastName = rst.getString("last_name");
			String email = rst.getString("email");
			
			String phoneNumber = rst.getString("phone_number");
			
			String address = rst.getString("address");
			String username = rst.getString("username");
			String password = rst.getString("password");
			LocalDate registrationDate = rst.getDate("registration_date").toLocalDate();
			/*Customer c = new Customer(id, firstName, lastName, email, phoneNumber, address, username, password,
					registrationDate);*/
			
			Customer c = new Customer( id, firstName,   lastName,   email,  phoneNumber,   address,   username,
					  password,  registrationDate); 
			list.add(c);
		}

		DBUtil.DBClose();
		return (list);
	}



	@Override
	public List<Vehicle> fetchAllVehicles() throws DatabaseConnectionException,SQLException {
		List<Vehicle> list = new ArrayList<>();
		Connection conn = DBUtil.getDbConn();

		String sql = "select * from vehicle";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id = rst.getInt("id");
			
			String model = rst.getString("model");
			String make = rst.getString("make");
			
			String year = rst.getString("year");
			
			String color = rst.getString("color");
			
			String registrationNumber = rst.getString("registration_number");
			int availability = rst.getInt("availability");
			int dailyRate = rst.getInt("daily_rate");
			
			/*Vehicle c= new Vehicle(id,model,make,year,color,registrationNumber,availability,dailyRate);*/	
			Vehicle c= new Vehicle(id,model,make,year,color,registrationNumber,availability,dailyRate);
			list.add(c);
		}

		DBUtil.DBClose();
		return (list);
	}



	@Override
	public void insertRecordInReservation(int customerId, int vehicleId, LocalDate startDate, LocalDate endDate,
			double totalCost, String status) throws SQLException, DatabaseConnectionException {
		Connection conn = DBUtil.getDbConn();

		String sql = "insert into reservation(customer_id,vehicle_id,start_date,end_date,total_cost,status) values(?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, customerId);
		pstmt.setInt(2, vehicleId);
		pstmt.setString(3, startDate.toString());
		pstmt.setString(4, endDate.toString());
		pstmt.setDouble(5, totalCost);
		pstmt.setString(6, status);
		
		pstmt.executeUpdate();

		DBUtil.DBClose();
	}



	


	 

	
	


	

}
