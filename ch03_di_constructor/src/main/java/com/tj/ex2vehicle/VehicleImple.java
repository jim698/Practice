package com.tj.ex2vehicle;

public class VehicleImple implements Vehicle {

	private String vehicle; // 탈 것
	private int speed;

	@Override
	public void ride(String name) {

		System.out.println(name + "은(는) " + vehicle + "을(를)" + speed + "km/h 속도로 간다.");

	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
