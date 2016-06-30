package com.ddb.act.red.bo;

import kkd.common.dao.dbuitl.JDBC;
import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.JDBC.MyBack;
import kkd.common.util.StringUtil;

import com.ddb.act.red.dao.ExchangeDao;
import com.ddb.act.red.vo.ExchangeVo;

public class ExchangeBo {

	ExchangeDao dao=new ExchangeDao();
	JDBC jdbc=new JDBC();
	
	/**
	 * 插入兑换信息
	 * @param ev
	 * @return
	 */
	public boolean addExInfo(final ExchangeVo ev){
		if(ev==null) return false;
		return (Boolean) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.insertExRecord(dh, ev);
			}
		});
	}
	
	/**
	 * 查询当日付费次数兑换总数
	 * @param lanId
	 * @return
	 */
	public int queryDayOfTotal(final String lanId){
		if(StringUtil.isEmpty(lanId)) return 0;
		return (Integer) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.selectDayOfTotalNum(dh, lanId);
			}
		});
	}
	
	/**
	 * 查询兑换上限
	 * @return
	 */
	public int queryMaxPayTimes(){
		return (Integer) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.selectNeedPay(dh);
			}
		});
	}
}
