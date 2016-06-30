package com.ddb.act.red.bo;

import java.util.List;

import kkd.common.dao.dbuitl.JDBC;
import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.JDBC.MyBack;
import kkd.common.util.StringUtil;

import com.ddb.act.red.dao.UserInfoDao;
import com.ddb.act.red.vo.UserGiftVo;
import com.ddb.act.red.vo.UserInfo;

public class UserInfoBo {

	UserInfoDao dao=new UserInfoDao();
	JDBC jdbc=new JDBC();
	
	/**
	 * 查询用户剩余参与次数
	 * @param lanId
	 * @return
	 */
	public int queryUserTimes(final String uid){
		if(StringUtil.isEmpty(uid)) return 0;
		return (Integer) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.countUserTimes(dh, uid);
			}
		});
	}
	
	
	/**
	 * 查询兑换所需花费
	 * @return
	 */
	public int queryNeedPays(){
		return (Integer) jdbc.execute(new MyBack() {
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.selectNeedPay(dh);
			}
		});
	}
	
	/**
	 * 添加用户数据
	 * @param u
	 * @return
	 */
	public boolean addUserInfo(final UserInfo u){
		if(u==null) return false;
		return (Boolean) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.insertUserInfo(dh, u);
			}
		});
	}
	
	/**
	 * 更新发送状态
	 * @param u
	 * @return
	 */
	public boolean updateSendInfo(final UserInfo u){
		if(u==null) return false;
		return (Boolean) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.updateSendInfo(dh, u);
			}
		});
	}
	
	/**
	 * 查询用户奖品列表
	 * @param lanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserGiftVo> queryUserGift(final String uid ,int pageSize,int currPage){
		if(StringUtil.isEmpty(uid)) return null;
		return (List<UserGiftVo>) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.queryUserGift(dh, uid,pageSize,currPage);
			}
		});
	}
	
	/**
	 * 查询轮播列表
	 * @param lanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserGiftVo> queryLbUserGift(){
		return (List<UserGiftVo>) jdbc.execute(new MyBack() {
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.queryLbUserGift(dh);
			}
		});
	}
	
	/**
	 * 查询关卡胜率
	 * @param passNum
	 * @return
	 */
	public double queryPassRate(final int passNum){
		if(passNum==0) return 0;
		return (Double) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.queryWinRateByPassNum(dh, passNum);
			}
		});
	}
}
