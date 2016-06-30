package com.ddb.act.red.vo;

import java.util.Date;

/**
 * 分享数据实体
 * @author fumy
 *
 * @date 2015年7月15日上午8:24:59
 */
public class ShareVo {
	
	private int id;
	private int uId;
	private String lanAccount;
	private Date shareTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getLanAccount() {
		return lanAccount;
	}
	public void setLanAccount(String lanAccount) {
		this.lanAccount = lanAccount;
	}
	public Date getShareTime() {
		return shareTime;
	}
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}
}
