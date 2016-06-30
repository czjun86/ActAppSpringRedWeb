package com.ddb.act.red.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.SqlParameter;

import com.ddb.act.red.vo.Gift;
import com.ddb.act.red.vo.UserGiftVo;
import com.ddb.act.red.vo.UserInfo;


public class GiftDao {

	/**
	 * 查询有效的奖品列表
	 * @param dh
	 * @return
	 * @throws Exception
	 */
	public List<Gift> queryGiftList(DbHelper dh) throws Exception{
		List<Gift> list=new ArrayList<Gift>();
		Gift gift=null;
		StringBuilder sb=new StringBuilder();
		sb.append("select * from Act_App_SpringRed_Gift");
		sb.append(" where EverydayNumLimit !=-1 AND CurdayUsedQuantity < EverydayNumLimit");
		sb.append(" union");
		sb.append(" select * from Act_App_SpringRed_Gift");
		sb.append(" where EverydayNumLimit=-1");
		RowSet rs=dh.executeQuery(sb.toString(), null);
		while(rs.next()){
			gift=new Gift();
			gift.setGiftId(rs.getInt("GiftId"));
			gift.setGiftName(rs.getString("GiftName"));
			gift.setGiftType(rs.getInt("GiftType"));
			gift.setWinRate(rs.getBigDecimal("WinRate"));
			gift.setEveryDayNumLimit(rs.getInt("EverydayNumLimit"));
			gift.setCurDAyUsedQuantity(rs.getInt("CurdayUsedQuantity"));
			gift.setQuantity(rs.getInt("Quantity"));
			gift.setUsedQuantity(rs.getInt("UsedQuantity"));
			gift.setCoupons(rs.getInt("Coupons"));
			list.add(gift);
		}
		return list;
	}
	
	/**
	 * 更新已抽取奖品的每日被抽中数量(当前数量+1)
	 * @param dh
	 * @param giftId
	 * @return
	 * @throws SQLException 
	 */
	public boolean updateCurdayUsedQuantity(DbHelper dh,Gift gift) throws SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append("update Act_App_SpringRed_Gift");
		sb.append(" set CurdayUsedQuantity=?");
		sb.append(" where GiftId=?");
		
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.INTEGER, gift.getCurDAyUsedQuantity()+1));
		param.add(new SqlParameter(Types.INTEGER, gift.getGiftId()));
		int row=dh.executeUpdate(sb.toString(), param);
		return row > 0 ? true : false;
	}
	
	/**
	 * 查询获得一个冲印卷并修改状态
	 */
	
	public Boolean getCard(DbHelper dh,UserInfo user)throws SQLException{
		UserGiftVo vo=null;
		String sql="select id cardNo,cardPwd from Act_App_SpringRed_CARD where state=0 limit 1";
		String sql2="update Act_App_SpringRed_CARD set state=1 where id=?";
		RowSet rs=dh.executeQuery(sql, null);
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		
		String id=null;
		while(rs.next()){
			vo=new UserGiftVo();
			vo.setCardNo(rs.getString("cardNo"));
			vo.setCardPwd(rs.getString("cardPwd"));
			id=rs.getString("id");
			vo.setGiftName("68元数码冲印卡");
			vo.setGiftType(2);
		}
		int row=0;
		if(id!=null){
			param.add(new SqlParameter(Types.VARCHAR, id));
			row=dh.executeUpdate(sql2, param);
			user.setCardNo(vo.getCardNo());
			user.setCardPwd(vo.getCardPwd());
		}
		return row>0? true : false;
		
	}
}
