package com.ddb.act.red.vo;

/**
 * 参与资格规则配置实体
 * @author fumy
 *
 * @date 2015年7月13日上午10:23:10
 */
public class Config {
	
	private int id;
	private int FreeTimes;				//免费次数
	private int PayTimes;				//付费次数
	private int PayTimesCoupons;		//付费次数需要花费
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFreeTimes() {
		return FreeTimes;
	}
	public void setFreeTimes(int freeTimes) {
		FreeTimes = freeTimes;
	}
	public int getPayTimes() {
		return PayTimes;
	}
	public void setPayTimes(int payTimes) {
		PayTimes = payTimes;
	}
	public int getPayTimesCoupons() {
		return PayTimesCoupons;
	}
	public void setPayTimesCoupons(int payTimesCoupons) {
		PayTimesCoupons = payTimesCoupons;
	}
}
