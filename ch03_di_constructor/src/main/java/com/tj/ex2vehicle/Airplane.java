package com.tj.ex2vehicle;

public class Airplane implements Vehicle {

	@Override
	public void ride(String name) {
		System.out.println(name +"은(는) 비행기로 500km/h로 날아간다.");

	}

}
