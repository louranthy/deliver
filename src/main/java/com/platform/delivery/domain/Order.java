package com.platform.delivery.domain;

import javax.validation.constraints.Size;

public class Order {

	@Size(min=2, max=2, message="Origin array size should be 2")
	private String[] origin;
	@Size(min=2, max=2, message="Destination array size should be 2")
	private String[] destination;
	public String[] getOrigin() {
		return origin;
	}
	public void setOrigin(String[] origin) {
		this.origin = origin;
	}
	public String[] getDestination() {
		return destination;
	}
	public void setDestination(String[] destination) {
		this.destination = destination;
	}
	

}
