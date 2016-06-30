package com.ddb.act.red.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.RowSet;

import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.SqlParameter;

import com.ddb.act.red.vo.UserGiftVo;
import com.ddb.act.red.vo.UserInfo;

public class UserInfoDao {

	
	/**
	 * 查询用户每日剩余参与次数
	 * @param dh
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	public int countUserTimes(DbHelper dh,String uid) throws SQLException{
		String sql="call proc_Act_App_Red_UserTimes(?);";
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.VARCHAR, uid));
		RowSet rs=dh.executeQuery(sql, param);
		int chance=0;
		if(rs.next()){
			chance=Integer.parseInt(rs.getString("chance"));
		}
		return chance;
	}
	
	/**
	 * 查询兑换所需花费
	 * @param dh
	 * @return
	 * @throws SQLException
	 */
	public int selectNeedPay(DbHelper dh) throws SQLException{
		String sql="select PayTimesCoupons from Act_App_SpringRed_Config where Id=1";
		RowSet rs=dh.executeQuery(sql, null);
		int pays=0;
		if(rs.next()){
			pays=rs.getInt("PayTimesCoupons");
		}
		return pays;
	}
	
	/**
	 * 插入用户数据
	 * @param dh
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean insertUserInfo(DbHelper dh,UserInfo user) throws SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into Act_App_SpringRed_UserInfo");
		sb.append(" (Uid,LanAccount,Phone,Address,GiftId,UseCoupons,ReceiveCoupons,ActTime,PassNum,GiftSendState,transactionId,GiftName,GiftType)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.INTEGER, user.getUid()));
		param.add(new SqlParameter(Types.VARCHAR, user.getLanAccount()));
		param.add(new SqlParameter(Types.VARCHAR,user.getPhone()));
		param.add(new SqlParameter(Types.VARCHAR, user.getAddress()));
		param.add(new SqlParameter(Types.INTEGER, user.getGiftId()));
		param.add(new SqlParameter(Types.INTEGER, user.getUseCoupons()));
		param.add(new SqlParameter(Types.INTEGER, user.getReceiveCoupons()));
		param.add(new SqlParameter(Types.TIMESTAMP, new Date()));
		param.add(new SqlParameter(Types.INTEGER, user.getPassNum()));
		param.add(new SqlParameter(Types.INTEGER, user.getGiftSendstate()));
		param.add(new SqlParameter(Types.VARCHAR, user.getTransactionId()));
		param.add(new SqlParameter(Types.VARCHAR, user.getGiftName()));
		param.add(new SqlParameter(Types.INTEGER, user.getGiftType()));
		int row=dh.executeUpdate(sb.toString(), param);
		return row > 0 ? true : false;
	}
	
	/**
	 * 更新发送状态
	 * @param dh
	 * @param u
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSendInfo(DbHelper dh,UserInfo u) throws SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append("update Act_App_SpringRed_UserInfo");
		sb.append(" set ReceiveCoupons=?,GiftSendState=?,GiftSendTime=?,cardNo=?,cardPwd=?");
		sb.append(" where transactionId=?");
		
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.INTEGER, u.getReceiveCoupons()));
		param.add(new SqlParameter(Types.INTEGER,u.getGiftSendstate()));
		param.add(new SqlParameter(Types.TIMESTAMP, new Date()));
		param.add(new SqlParameter(Types.VARCHAR, u.getCardNo()));
		param.add(new SqlParameter(Types.VARCHAR, u.getCardPwd()));
		param.add(new SqlParameter(Types.VARCHAR, u.getTransactionId()));
		int row=dh.executeUpdate(sb.toString(), param);
		return row > 0 ? true :false;
	}
	
	/**
	 *  查询奖品列表
	 * @param dh
	 * @param lan
	 * @return
	 * @throws SQLException
	 */
	public List<UserGiftVo> queryUserGift(DbHelper dh,String uid,int pageSize,int currPage) throws SQLException{
		List<UserGiftVo> list=new ArrayList<UserGiftVo>();
		UserGiftVo uv=null;
		StringBuilder sb =new StringBuilder();
		sb.append("SELECT u.Uid,u.GiftId,u.GiftSendTime,u.GiftName,u.GiftType ,u.cardNo,u.cardPwd FROM Act_App_SpringRed_UserInfo u "
				+ "  where u.Uid=? order by u.GiftSendTime desc limit ?,?");
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.VARCHAR, uid));
		param.add(new SqlParameter(Types.INTEGER, currPage));
		param.add(new SqlParameter(Types.INTEGER, pageSize));
		RowSet rs=dh.executeQuery(sb.toString(), param);
		while(rs.next()){
			uv=new UserGiftVo();
			uv.setUid(rs.getInt("Uid"));
			uv.setGiftId(rs.getInt("GiftId"));
			uv.setGiftName(rs.getString("GiftName"));
			uv.setGiftSendTime(rs.getDate("GiftSendTime"));
			uv.setGiftType(rs.getInt("GiftType"));
			uv.setCardNo(rs.getString("cardNo"));
			uv.setCardPwd(rs.getString("cardPwd"));
			list.add(uv);
		}
		return list;
	}
	
	/**
	 * 查询指定轮播10条最新记录
	 * @param dh
	 * @param lanId
	 * @return
	 * @throws SQLException
	 */
	public List<UserGiftVo> queryLbUserGift(DbHelper dh) throws SQLException{
		List<UserGiftVo> list=new ArrayList<UserGiftVo>();
		UserGiftVo uv=null;
		StringBuilder sb =new StringBuilder();
		sb.append("SELECT u.Uid,u.Phone,u.GiftId,u.GiftSendTime,g.GiftName,g.GiftType FROM Act_App_SpringRed_UserInfo u LEFT JOIN Act_App_SpringRed_Gift g"
				+ " on u.GiftId=g.GiftId order by u.GiftSendTime desc LIMIT 0,10");
//		List<SqlParameter> param=new ArrayList<SqlParameter>();
//		param.add(new SqlParameter(Types.VARCHAR, lanId));
		RowSet rs=dh.executeQuery(sb.toString(), null);
		while(rs.next()){
			uv=new UserGiftVo();
			uv.setUid(rs.getInt("Uid"));
			String phone=rs.getString("Phone");
			String enPhone=phone.replace(phone.substring(3, 8), "****");
			uv.setPhone(enPhone);
			uv.setGiftId(rs.getInt("GiftId"));
			uv.setGiftName(rs.getString("GiftName"));
			uv.setGiftSendTime(rs.getDate("GiftSendTime"));
			uv.setGiftType(rs.getInt("GiftType"));
			list.add(uv);
		}
		return list;
	}
	
	/**
	 * 根据关卡数查询胜率
	 * @param dh
	 * @param passNum
	 * @return
	 * @throws SQLException
	 */
	public double queryWinRateByPassNum(DbHelper dh,int passNum) throws SQLException{
		String sql="select WinRate from Act_App_FingerPlay_PassConfig where PassNum=?";
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.INTEGER, passNum));
		RowSet rs=dh.executeQuery(sql, param);
		double rate=0;
		if(rs.next()){
			rate=rs.getDouble("WinRate");
		}
		return rate;
	}
}