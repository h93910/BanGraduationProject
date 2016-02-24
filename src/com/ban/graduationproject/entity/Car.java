package com.ban.graduationproject.entity;

public class Car {
	private long id;// 车编号
	private int type;// 车类型
	private String carNumber;// 车牌号

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public Car(int type, String carNumber) {
		super();
		this.type = type;
		this.carNumber = carNumber;
	}
	
	public Car() {
	}
}
