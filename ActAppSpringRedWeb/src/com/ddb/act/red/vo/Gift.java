package com.ddb.act.red.vo;

import java.math.BigDecimal;

/**
 * 礼品实体
 * @author fumy
 *
 * @date 2015年7月13日上午10:32:33
 */
public class Gift {
	
	private int giftId;
	private String giftName;
	private int giftType;
	private BigDecimal winRate;
	private int reGte;
	private int everyDayNumLimit;		//每日限量
	private int curDAyUsedQuantity;		//今日已被抽中数量
	private int Quantity;				//总数量
	private String picFile;
	private String picFileName;
	private int usedQuantity;			//该奖品已被抽中数量
	private int coupons;
	private String awardIntro;
	private String useCodeLink;
	
	public int getCurDAyUsedQuantity() {
		return curDAyUsedQuantity;
	}
	public void setCurDAyUsedQuantity(int curDAyUsedQuantity) {
		this.curDAyUsedQuantity = curDAyUsedQuantity;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
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
	public BigDecimal getWinRate() {
		return winRate;
	}
	public void setWinRate(BigDecimal winRate) {
		this.winRate = winRate;
	}
	public int getReGte() {
		return reGte;
	}
	public void setReGte(int reGte) {
		this.reGte = reGte;
	}
	public int getEveryDayNumLimit() {
		return everyDayNumLimit;
	}
	public void setEveryDayNumLimit(int everyDayNumLimit) {
		this.everyDayNumLimit = everyDayNumLimit;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getPicFile() {
		return picFile;
	}
	public void setPicFile(String picFile) {
		this.picFile = picFile;
	}
	public String getPicFileName() {
		return picFileName;
	}
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	public int getUsedQuantity() {
		return usedQuantity;
	}
	public void setUsedQuantity(int usedQuantity) {
		this.usedQuantity = usedQuantity;
	}
	public int getCoupons() {
		return coupons;
	}
	public void setCoupons(int coupons) {
		this.coupons = coupons;
	}
	public String getAwardIntro() {
		return awardIntro;
	}
	public void setAwardIntro(String awardIntro) {
		this.awardIntro = awardIntro;
	}
	public String getUseCodeLink() {
		return useCodeLink;
	}
	public void setUseCodeLink(String useCodeLink) {
		this.useCodeLink = useCodeLink;
	}

}
