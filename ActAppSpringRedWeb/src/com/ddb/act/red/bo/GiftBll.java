package com.ddb.act.red.bo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import kkd.common.dao.dbuitl.JDBC;
import kkd.common.dao.dbuitl.JDBC.DbHelper;
import kkd.common.dao.dbuitl.JDBC.MyBack;
import kkd.common.logger.LogWriter;
import kkd.common.util.StringUtil;

import com.alibaba.fastjson.JSONObject;
import com.ddb.act.red.dao.GiftDao;
import com.ddb.act.red.dao.UserInfoDao;
import com.ddb.act.red.util.CodeUtil;
import com.ddb.act.red.util.ConfigUtil;
import com.ddb.act.red.util.HttpUtil;
import com.ddb.act.red.util.Md5Util;
import com.ddb.act.red.vo.Gift;
import com.ddb.act.red.vo.User;
import com.ddb.act.red.vo.UserGiftVo;
import com.ddb.act.red.vo.UserInfo;
import com.ddb.activity.base.bo.thirdInterface.ExchangeGiftInvoker;

public class GiftBll {
	GiftDao dao=new GiftDao();
	JDBC jdbc=new JDBC();
	/**
	 * 用户抽奖，得到抽中的奖品
	 * @return
	 * @throws Exception 
	 */
	public Gift getWinGift(UserInfo u,String devId) throws Exception{
		//得到有效的奖品列表
		GiftBo gbo=new GiftBo();
		List<Gift> giftList=gbo.queryGiftList();
		
		//取得礼品概率列表
		List<BigDecimal> rateList=new ArrayList<BigDecimal>();
		for(Gift gift:giftList){
			rateList.add(gift.getWinRate());
		}
		//开始根据概率取得奖品列表的奖品索引并得到奖品
		int giftIndex=com.ddb.activity.base.util.LotteryProbabilityUtil.getRandomIndex(rateList);
		Gift g=giftList.get(giftIndex);
		if(g==null){
			LogWriter.debug("-------------->抽奖异常，giftIndex:"+giftIndex+",uid:"+u.getUid()+",phone:"+u.getPhone());
			return null;
		}else{
			boolean flage=sendGift(g, u,devId);
			if(!flage){
				return null;
			}
			return g;
		}
	}
	
	public  boolean sendGift(Gift g,UserInfo u,String devId) throws Exception{
	
		//开始发送礼品
		//ExchangeGiftInvoker exchangeGift=new ExchangeGiftInvoker(ConfigUtil.getConfig(u.getAddress()));
		boolean flage=false;
		String giftName="";
		if(g.getGiftName().contains("提速时间")){
			giftName="提速时间";
			flage=getInterfaceOrder(g,u,devId);
			if(!flage)
			{LogWriter.debug("------------>奖品发送失败，调用礼品接口失败............giftName:"+giftName);
			return false;}
		}else if(g.getGiftName().contains("点券")){
			giftName="点券";
			flage=getInterfaceCount(g,u);
			if(!flage)
			{LogWriter.debug("------------>奖品发送失败，调用礼品接口失败............giftName:"+giftName);
			return false;}
		}else if(g.getGiftName().contains("冲印卡"))
		{
			flage=getCard(g,u);
			if(!flage)
			{LogWriter.debug("------------>奖品发送失败，查询礼品库为空............giftName:"+giftName);
			return false;}
		}
		else{
			giftName=g.getGiftName();
		}
		//礼品发送成功，更新用户发送状态信息,更新礼品库当前礼品数量
		LogWriter.debug("------------>奖品发送成功............");
		
		//添加一条用户数据
		UserInfoBo ubo=new UserInfoBo();
		//抽中奖用户信息入库
		u.setGiftName(g.getGiftName());
		u.setGiftType(g.getGiftType());
		u.setGiftId(g.getGiftId());
		u.setGiftName(g.getGiftName());
		u.setTransactionId(UUID.randomUUID().toString());
		u.setReceiveCoupons(g.getCoupons());
		ubo.addUserInfo(u);
		
		
		u.setGiftSendstate(1);
		//更新
		GiftBo gbo=new GiftBo();
		boolean flg=gbo.updateCurdayUsedQuantity(g);
		flage=ubo.updateSendInfo(u);
		
		if(flage && flg){
			LogWriter.debug("---------->更新用户礼品发送信息、礼品库礼品信息状态成功...........");
		}
		return true;
	}
	
	/**
	 * 调用提速时间接口发送奖品
	 */
	
	public Boolean getInterfaceOrder(Gift g,UserInfo user,String devId){
		Map<String, Object> params = new HashMap<String, Object>();
		String prodName=g.getGiftName();
		if (prodName != null && prodName.contains("小时提速时间")) {
			params.put("prodName", "红包"+prodName.split("小")[0]);
		}
		// /过期时间,当前时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar c = Calendar.getInstance();
					String nowt = sdf.format(c.getTime());
					c.add(Calendar.YEAR, 1);
					String endT = sdf.format(c.getTime());
		params.put("expirceTime", endT);
		params.put("source", "activity");
		params.put("subSource", "newYearRed");
		String tarnId = UUID.randomUUID().toString();
		params.put("tranId", UUID.randomUUID());

		String appId = CodeUtil.APPID;
		String pwd = CodeUtil.PWD;
		String token = Md5Util.md5(appId + pwd);
		String url = ConfigUtil.getConfig("ORDER_ADD_URL") + "?appId=" + appId + "&token=" + token;
		url += "&uid=" + user.getUid() + "&devId=" + devId;
		JSONObject ss = HttpUtil.post(url, params);
		if(ss!=null){
			String code = ss.getString("errCode");
			if(code.equals("SUC")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 调用点卷接口发送奖品
	 */
	
	public Boolean getInterfaceCount(Gift g,UserInfo user){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", user.getUid());
		params.put("mark", g.getCoupons());
		params.put("markSourceCode", "AppActivity");
		String tarnId = UUID.randomUUID().toString();
		params.put("transactionId",tarnId);
		String appId = CodeUtil.APPID;
		String pwd = CodeUtil.PWD;
		String token = Md5Util.md5(appId + pwd);
		String url = ConfigUtil.getConfig("MARK_ADD_URL") + "?appId=" + appId + "&token=" + token;
		JSONObject ss = HttpUtil.post(url, params);
		if(ss!=null){
			String code = ss.getString("errCode");
			if(code.equals("SUC")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 调用冲印卡奖品发送奖品
	 */
	
	public Boolean getCard(Gift g,UserInfo user){
		if(g==null) return false;
		return (Boolean) jdbc.execute(new MyBack() {
			
			@Override
			public Object exe(DbHelper dh) throws Exception {
				return dao.getCard(dh,user);
			}
		});
	}
}
