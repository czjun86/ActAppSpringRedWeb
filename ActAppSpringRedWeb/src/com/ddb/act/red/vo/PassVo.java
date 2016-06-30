package com.ddb.act.red.vo;

import java.math.BigDecimal;

/**
 * 关卡实体
 * @author fumy
 * 
 */
public class PassVo {
	
	private int id;
	private int passNum;
	private BigDecimal winRate;	//过关率
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPassNum() {
		return passNum;
	}
	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}
	public BigDecimal getWinRate() {
		return winRate;
	}
	public void setWinRate(BigDecimal winRate) {
		this.winRate = winRate;
	}
}
