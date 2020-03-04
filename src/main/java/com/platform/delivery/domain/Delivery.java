package com.platform.delivery.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Delivery {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private double distance;
	private String status;

	
	public Delivery() {
	}
	
	public Delivery(double distance, String status) {
		this.distance = distance;
		this.status = status;
	}

	public Delivery(int id, double distance, String status) {
		this.id = id;
		this.distance = distance;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
