package com.ddb.act.red.vo;

import java.util.Date;

public class ExchangeVo {

	private int id;
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private String lanAcount;
	private String uid;
	private int exchangeNum;
	private int exchangeType;
	public int getExchangeType() {
		return exchangeType;
	}
	public void setExchangeType(int exchangeType) {
		this.exchangeType = exchangeType;
	}
	private Date exchangeTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLanAcount() {
		return lanAcount;
	}
	public void setLanAcount(String lanAcount) {
		this.lanAcount = lanAcount;
	}
	public int getExchangeNum() {
		return exchangeNum;
	}
	public void setExchangeNum(int exchangeNum) {
		this.exchangeNum = exchangeNum;
	}
	public Date getExchangeTime() {
		return exchangeTime;
	}
	public void setExchangeTime(Date exchangeTime) {
		this.exchangeTime = exchangeTime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
