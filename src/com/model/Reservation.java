package com.model;

import java.time.LocalDate;
import java.util.Objects;
 

public class Reservation {
	private int id;
	private int customerId;
	private int vehicleId;
	private LocalDate startDate;
	private LocalDate endDate;
	private double totalCost;
	private String status;
	
	public Reservation() {}

	public Reservation(int id, int customerId, int vehicleId, LocalDate startDate, LocalDate endDate, double totalCost,
			String status) {
		this.id = id;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.status = status;
	}

	public Reservation(int customerId, int vehicleId, LocalDate startDate, LocalDate endDate, double totalCost,
			String status) {
	    this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalCost = totalCost;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", customerId=" + customerId + ", vehicleId=" + vehicleId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", totalCost=" + totalCost + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, endDate, id, startDate, status, totalCost, vehicleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return customerId == other.customerId && Objects.equals(endDate, other.endDate) && id == other.id
				&& Objects.equals(startDate, other.startDate) && Objects.equals(status, other.status)
				&& Double.doubleToLongBits(totalCost) == Double.doubleToLongBits(other.totalCost)
				&& vehicleId == other.vehicleId;
	}
	
	
	
	
	
	

	
}
