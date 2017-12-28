package com.somnath;

public class Address {
	private String city, state, pin;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public String toString()
	{
		return "City: "+city+"<br/>State: "+state+"<br/>Pin: "+pin;
	}
}
