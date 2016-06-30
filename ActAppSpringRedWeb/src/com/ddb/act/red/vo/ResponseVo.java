package com.ddb.act.red.vo;


/**
 * 返回对象
 * @author fumy
 *
 * @date 2015年7月14日上午8:50:33
 */
public class ResponseVo<T> {
	private T t ;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	private Gift gift;
	private int chance;
	private boolean Share;
	
	
	public boolean isShare() {
		return Share;
	}
	public void setShare(boolean share) {
		Share = share;
	}
	public Gift getGift() {
		return gift;
	}
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	public int getChance() {
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
	}
	
	
}
