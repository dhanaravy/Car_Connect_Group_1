package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.DatabaseConnectionException;
import com.model.Vehicle;
import com.util.DBUtil;

public class VehicleDaoImpl implements VehicleDao {

	public List<Vehicle> fetchAllVehicle() throws SQLException, DatabaseConnectionException {
		List<Vehicle> list = new ArrayList<>();
		Connection conn = DBUtil.getDbConn();

		String sql = "select * from vehicle";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id=rst.getInt("id");
			String model=rst.getString("model");
			String make=rst.getString("make");
			String year=rst.getString("year");
			String color=rst.getString("color");
			String registrationNumber=rst.getString("registration_number");
			int availability= rst.getInt("availability");
			double dailyRate= rst.getDouble("daily_rate");
			
			
			Vehicle c = new Vehicle(id,model,make,year,color,registrationNumber,availability,dailyRate);
			list.add(c);
		}
		
		DBUtil.DBClose();
		return list;
	}


	public List<Vehicle> fetchAllAvailableVehicle() throws SQLException, DatabaseConnectionException {
		List<Vehicle> list = new ArrayList<>();
		Connection conn = DBUtil.getDbConn();

		String sql = "select * from vehicle where availability=1";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id=rst.getInt("id");
			String model=rst.getString("model");
			String make=rst.getString("make");
			String year=rst.getString("year");
			String color=rst.getString("color");
			String registrationNumber=rst.getString("registration_number");
			int availability= rst.getInt("availability");
			double dailyRate= rst.getDouble("daily_rate");
			
			
			Vehicle c = new Vehicle(id,model,make,year,color,registrationNumber,availability,dailyRate);
			list.add(c);
		}

		DBUtil.DBClose();
		return (list);
	}


	public void addVehicle(String make, String model, String year, String color, String registrationNumber,
			int availability, double dailyRate) throws SQLException, DatabaseConnectionException {
		Connection conn = DBUtil.getDbConn();

		String sql = "insert into vehicle(make,model, year, color,registration_number,availability,daily_rate) values(?,?,?,?,?,?,?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, make);
		pstmt.setString(2, model);
		pstmt.setString(3, year);
		pstmt.setString(4, color);
		pstmt.setString(5, registrationNumber);
		pstmt.setInt(6, availability);
		pstmt.setDouble(7, dailyRate);

		pstmt.executeUpdate();

		DBUtil.DBClose();
		
	}

	public void updatevehicle(int id, String field, String newVal) throws DatabaseConnectionException, SQLException {

		Connection conn = DBUtil.getDbConn();

		String sql = "update vehicle set "+field+"=? where id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		if(field.equals("availability"))
			pstmt.setInt(1,Integer.parseInt(newVal));
		else if(field.equals("daily_rate"))
			pstmt.setDouble(1,Double.parseDouble(newVal));
		else
			pstmt.setString(1,newVal);
		pstmt.setInt(2, id);


		pstmt.executeUpdate();

		DBUtil.DBClose();
	}


	public void deleteVehicleById(int id) throws SQLException, DatabaseConnectionException {
		
		Connection conn = DBUtil.getDbConn();
		
		String sql = "delete from reservation where vehicle_id=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);

		pstmt.executeUpdate();

		sql = "delete from vehicle where id=?";

		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, id);

		pstmt.executeUpdate();

		DBUtil.DBClose();
		
	}


	public List<Vehicle> fetchVehicleOfModel(String modell) throws DatabaseConnectionException, SQLException {
		List<Vehicle> list = new ArrayList<>();
		Connection conn = DBUtil.getDbConn();

		String sql = "select * from vehicle where model=?";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, modell);

		ResultSet rst = pstmt.executeQuery();

		while (rst.next()) {
			int id=rst.getInt("id");
			String model=rst.getString("model");
			String make=rst.getString("make");
			String year=rst.getString("year");
			String color=rst.getString("color");
			String registrationNumber=rst.getString("registration_number");
			int availability= rst.getInt("availability");
			double dailyRate= rst.getDouble("daily_rate");
			
			
			Vehicle c = new Vehicle(id,model,make,year,color,registrationNumber,availability,dailyRate);
			list.add(c);
		}

		DBUtil.DBClose();
		return (list);
	}
}
