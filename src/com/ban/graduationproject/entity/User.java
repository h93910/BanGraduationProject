package com.ban.graduationproject.entity;

import java.util.Date;

public class User {
	private long id;// 用户ID
	private String name;// 用户姓名
	private boolean sex;// 性别 0：女 1：男
	private long tel;// 电话号码
	private Date created;// 创建日期
	private Date deadline;// 到期时间
	private long carId;// 车编号
	private int station;// 帐号状态 -1：黑名单 0：已消户 1：正常 2：欠费
	private String weixinOpenId;// 微信号

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public String getWeixinOpenId() {
		return weixinOpenId;
	}

	public void setWeixinOpenId(String weixinOpenId) {
		this.weixinOpenId = weixinOpenId;
	}

	public User(String name, boolean sex, int tel, Date created, Date deadline,
			long carId, int station, String weixinOpenId) {
		super();
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.created = created;
		this.deadline = deadline;
		this.carId = carId;
		this.station = station;
		this.weixinOpenId = weixinOpenId;
	}

	public User() {
	}
}
