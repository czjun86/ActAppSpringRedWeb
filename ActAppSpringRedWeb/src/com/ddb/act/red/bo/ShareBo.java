package com.ddb.act.red.bo;

import kkd.common.dao.dbuitl.JDBC;
import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.JDBC.MyBack;
import kkd.common.util.StringUtil;

import com.ddb.act.red.dao.ShareDao;
import com.ddb.act.red.vo.UserInfo;

public class ShareBo {

	ShareDao dao=new ShareDao();
	JDBC jdbc=new JDBC();
	
	/**
	 * 查询是否分享
	 * @param lanId
	 * @return
	 */
	public boolean queryIsShare(final String uid){
		if(StringUtil.isEmpty(uid)) return false;
		return (Boolean) jdbc.execute(new MyBack() {
			
			@Override
			public Boolean exe(DbHelper dh) throws Exception {
				return dao.countBylan(dh, uid);
			}
		});
	}
	
	/**
	 * 添加分享信息
	 * @param u
	 * @return
	 */
	public boolean addShareInfo(final UserInfo u){
		if(u==null) return false;
		return (Boolean) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.insertShare(dh, u);
			}
		});
	}
}
