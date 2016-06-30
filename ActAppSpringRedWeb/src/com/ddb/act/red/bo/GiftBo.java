package com.ddb.act.red.bo;

import java.util.List;

import kkd.common.dao.dbuitl.JDBC;
import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.JDBC.MyBack;

import com.ddb.act.red.dao.GiftDao;
import com.ddb.act.red.vo.Gift;

public class GiftBo {

	GiftDao dao= new GiftDao();
	JDBC jdbc=new JDBC();
	
	/**
	 * 查询奖品列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gift> queryGiftList(){
		return (List<Gift>) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.queryGiftList(dh);
			}
		});
	} 
	
	/**
	 * 更新已抽取奖品的每日被抽中数量(当前数量+1)
	 * @param g
	 * @return
	 */
	public boolean updateCurdayUsedQuantity(final Gift g){
		if(g==null)return false;
		return (Boolean) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.updateCurdayUsedQuantity(dh, g);
			}
		});
	}
}
