package com.ddb.act.red.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.RowSet;

import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.SqlParameter;
import kkd.common.util.DateUtil;

import com.ddb.act.red.vo.UserInfo;


public class ShareDao {

	/**
	 * 根据宽手机号查询用户当日是否分享过数据
	 * @param dh
	 * @param lan
	 * @return
	 * @throws SQLException 
	 */
	public boolean countBylan(DbHelper dh,String uid) throws SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append("select count(*) as c from Act_App_SpringRed_Share");
		sb.append(" where uId=? and DATE_FORMAT(shareTime,'%Y-%m-%d')=?");
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.VARCHAR,uid));
		param.add(new SqlParameter(Types.VARCHAR,DateUtil.format(new Date(),DateUtil.DATE_PATTERN)));
		RowSet rs=dh.executeQuery(sb.toString(), param);
		int count=0;
		if(rs.next()){
			count=rs.getInt("c");
		}
		return count > 0 ? true : false;
	}
	
	/**
	 * 插入分享记录
	 * @param dh
	 * @param u
	 * @return
	 * @throws SQLException
	 */
	public boolean insertShare(DbHelper dh,UserInfo u) throws SQLException{
		StringBuilder sb=new StringBuilder();
		sb.append("insert into Act_App_SpringRed_Share");
		sb.append("(uId,phone,lanAccount,shareTime)");
		sb.append(" values(?,?,?,?)");
		List<SqlParameter> param=new ArrayList<SqlParameter>();
		param.add(new SqlParameter(Types.INTEGER, u.getUid()));
		param.add(new SqlParameter(Types.VARCHAR, u.getPhone()));
		param.add(new SqlParameter(Types.VARCHAR, u.getLanAccount()));
		param.add(new SqlParameter(Types.TIMESTAMP, new Date()));
		int row=dh.executeUpdate(sb.toString(), param);
		return row > 0 ? true : false;
	}
}
