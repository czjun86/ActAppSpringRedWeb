package com.ddb.act.red.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.RowSet;

import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.SqlParameter;

import com.ddb.act.red.vo.ExchangeVo;

public class ExchangeDao {

	/**
	 * 插入兑换次数记录
	 * @param dh
	 * @param ev
	 * @return
	 * @throws SQLException
	 */
	public boolean insertExRecord(DbHelper dh,ExchangeVo ev) throws SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into Act_App_SpringRed_UserExchange(uid,phone,exchangeNum,exchangeType,exchangeTime)");
		sb.append(" values(?,?,?,?,?)");
		
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.VARCHAR, ev.getUid()));
		param.add(new SqlParameter(Types.VARCHAR, ev.getPhone()));
		param.add(new SqlParameter(Types.INTEGER, ev.getExchangeNum()));
		param.add(new SqlParameter(Types.INTEGER, ev.getExchangeType()));
		param.add(new SqlParameter(Types.TIMESTAMP, new Date()));
		
		int row=dh.executeUpdate(sb.toString(),param);
		return row > 0 ? true : false;
	}
	
	/**
	 * 查询用户当日支付兑换总次数
	 * @param dh
	 * @param lanId
	 * @return
	 * @throws SQLException 
	 */
	public int selectDayOfTotalNum(DbHelper dh,String uid) throws SQLException{
		String sql="SELECT SUM(e.exchangeNum) as total FROM Act_App_SpringRed_UserExchange e where uid =? and e.exchangeType=1 and DATE(e.exchangeTime) = CURDATE()";
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.VARCHAR, uid));
		
		RowSet rs=dh.executeQuery(sql, param);
		int exNum=0;
		if(rs.next()){
			exNum=rs.getInt("total");
		}
		return exNum;
	}
	
	/**
	 * 查询兑换上限次数
	 * @param dh
	 * @return
	 * @throws SQLException
	 */
	public int selectNeedPay(DbHelper dh) throws SQLException{
		String sql="select PayTimes from Act_App_SpringRed_Config where Id=1";
		RowSet rs=dh.executeQuery(sql, null);
		int pays=0;
		if(rs.next()){
			pays=rs.getInt("PayTimes");
		}
		return pays;
	}
}
