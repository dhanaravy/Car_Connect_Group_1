package com.model;

public class Vehicle {
	private int id;
	private String model;
	private String make;
	private String year;
	private String color;
	private String registrationNumber;
	private int availability;
	private double dailyRate;
	
	public Vehicle() {

	}

	public Vehicle(String model, String make, String year, String color, String registrationNumber, int availability,
			double dailyRate) {
		this.model = model;
		this.make = make;
		this.year = year;
		this.color = color;
		this.registrationNumber = registrationNumber;
		this.availability = availability;
		this.dailyRate = dailyRate;
	}

	public Vehicle(int id, String model, String make, String year, String color, String registrationNumber,
			int availability, double dailyRate) {
		this.id = id;
		this.model = model;
		this.make = make;
		this.year = year;
		this.color = color;
		this.registrationNumber = registrationNumber;
		this.availability = availability;
		this.dailyRate = dailyRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + ", make=" + make + ", year=" + year + ", color=" + color
				+ ", registrationNumber=" + registrationNumber + ", availability=" + availability + ", dailyRate="
				+ dailyRate + "]";
	}
}
