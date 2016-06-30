package com.ddb.act.red.vo;

import java.util.Date;

/**
 * 参与用户记录实体
 * @author fumy
 *
 * @date 2015年7月13日上午10:41:14
 */
public class UserInfo {
	
	private int id;
	private String uid;
	private String userName;
	private String lanAccount;
	private String trueName;
	private String phone;
	private String address;
	private int giftId;
	private int useCoupons;
	private int receiveCoupons;
	private String transactionId;
	private Date actTime;
	private int passNum;		//通关数
	private int giftSendstate;
	private Date giftSendTime;
	private int giftType;
	private String giftName;
	private String cardNo;
	private String cardPwd;
	
	public int getPassNum() {
		return passNum;
	}
	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}
	public String getGiftName() {
		return giftName;
	}
	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLanAccount() {
		return lanAccount;
	}
	public void setLanAccount(String lanAccount) {
		this.lanAccount = lanAccount;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	public int getUseCoupons() {
		return useCoupons;
	}
	public void setUseCoupons(int useCoupons) {
		this.useCoupons = useCoupons;
	}
	public int getReceiveCoupons() {
		return receiveCoupons;
	}
	public void setReceiveCoupons(int receiveCoupons) {
		this.receiveCoupons = receiveCoupons;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Date getActTime() {
		return actTime;
	}
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
	public int getGiftSendstate() {
		return giftSendstate;
	}
	public void setGiftSendstate(int giftSendstate) {
		this.giftSendstate = giftSendstate;
	}
	public Date getGiftSendTime() {
		return giftSendTime;
	}
	public void setGiftSendTime(Date giftSendTime) {
		this.giftSendTime = giftSendTime;
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
	public int getGiftType() {
		return giftType;
	}
	public void setGiftType(int giftType) {
		this.giftType = giftType;
	}
	
}