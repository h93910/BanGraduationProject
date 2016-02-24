package com.ban.graduationproject.entity;

import java.util.Date;

public class Record {
	private long id;// 流水号
	private long carId;// 车编号
	private Date time;// 时间
	private boolean action;// 停放车,0：放 1：拿

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean getAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}

	public Record(long carId, Date time, boolean action) {
		super();
		this.carId = carId;
		this.time = time;
		this.action = action;
	}
	public Record(){
		
	}
}
