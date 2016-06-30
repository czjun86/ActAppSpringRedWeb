package com.ddb.act.red.vo;

import java.util.Date;

public class UserGiftVo {

	private int uid;
	private int giftId;
	private String phone;
	private Date giftSendTime;
	private String giftName;
	private int giftType;
	private String cardNo;
	private String cardPwd;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardPwd() {
		return cardPwd;
	}
	public void setCardPwd(String cardPwd) {
		this.cardPwd = cardPwd;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public Date getGiftSendTime() {
		return giftSendTime;
	}
	public void setGiftSendTime(Date giftSendTime) {
		this.giftSendTime = giftSendTime;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public int getGiftType() {
		return giftType;
	}
	public void setGiftType(int giftType) {
		this.giftType = giftType;
	}
	
	
}
