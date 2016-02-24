package com.ban.graduationproject.entity;

import java.util.Date;

public class Recharge {
	private long id;// 流水号
	private long who;// 车主ID
	private int money;// 金额
	private String receipt;// 交易码
	private Date time;// 时间

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWho() {
		return who;
	}

	public void setWho(long who) {
		this.who = who;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Recharge(long who, int money, String receipt, Date time) {
		super();
		this.who = who;
		this.money = money;
		this.receipt = receipt;
		this.time = time;
	}

	public Recharge(){
		
	}
}
