package com.ddb.act.red.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kkd.common.entity.Msg;
import kkd.common.logger.LogWriter;

import com.ddb.act.red.bo.UserInfoBo;
import com.ddb.act.red.util.CodeUtil;
import com.ddb.act.red.util.ConfigUtil;
import com.ddb.act.red.vo.UserGiftVo;
import com.ddb.activity.base.bo.thirdInterface.ExchangeGiftInvoker;
import com.ddb.activity.base.vo.thirdInterface.Gift;
import com.ddb.activity.base.vo.thirdInterface.GiftResponseMsg;
import com.mysql.jdbc.log.Log;

/**
 * Servlet implementation class QueryUserGiftServlet
 */
public class QueryUserGiftServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		String funName=request.getParameter("funName");
		//String lanId=request.getParameter("lanId");
		String district=request.getParameter("district");
		String uId=request.getParameter("uId");
		String pageSize=request.getParameter("pageSize");
		String currPage=request.getParameter("currPage");
		int size=Integer.parseInt(pageSize);
		int page=Integer.parseInt(currPage);
		if("getLbGift".equals(funName)){
			Msg<List<UserGiftVo>> msg=getLbGift();
			out.print(msg.objectToFastJSON());
		}else if("getUserGift".equals(funName)){
//修改用户奖品列表查询方式
			Msg<List<UserGiftVo>> msg=getUserGift(district,uId,size,page);
			out.print(msg.objectToFastJSON());
		}
	}

	/**
	 * 查询用户奖品列表
	 * @param lanId
	 * @return
	 */
	public Msg<List<UserGiftVo>> getUserGift(String district,String uid,int pageSize,int currPage){
		Msg<List<UserGiftVo>> msg=new Msg<List<UserGiftVo>>();
		UserInfoBo ubo=new UserInfoBo();
		//查询用户奖品列表
		List<UserGiftVo> list=ubo.queryUserGift(uid, pageSize, currPage);
//		String url=ConfigUtil.getConfig(district+".HisGift");//从配置文件获取提速系统接口地址
//		LogWriter.info("礼品管理查询历史地址：" + url);
//		ExchangeGiftInvoker exchangeGift=new ExchangeGiftInvoker(district,url);
//		GiftResponseMsg grm;
//		List<Gift> list=null;
		
//			grm = exchangeGift.getHisGiftList(uid, pageSize, currPage);
//			list= grm.getList();
			if(list!=null && list.size()>0){
				msg.setCode(CodeUtil.CODE_SUCCESS);
				msg.setV(list);
				msg.setMsg("奖品查询成功");
				LogWriter.info("奖品查询成功，uid=" + uid);
			}else{//查询礼品为空
				msg.setCode(CodeUtil.CODE_ERROR);
				msg.setV(list);
				msg.setMsg("未查询到奖品");
				LogWriter.info("未查询到奖品，uid=" + uid);
			}

		return msg;
	}
	
	/**
	 * 查询轮播奖品列表
	 * @return
	 */
	public Msg<List<UserGiftVo>> getLbGift(){
		Msg<List<UserGiftVo>> msg=new Msg<List<UserGiftVo>>();
		UserInfoBo ubo=new UserInfoBo();
		//查询用户奖品列表
		List<UserGiftVo> list=ubo.queryLbUserGift();
		
		if(list!=null && list.size()>0){
			msg.setCode(CodeUtil.CODE_SUCCESS);
			msg.setV(list);
			msg.setMsg("奖品查询成功");
		}else{//查询礼品为空
			msg.setCode(CodeUtil.CODE_ERROR);
			msg.setV(list);
			msg.setMsg("未查询到奖品");
		}
		return msg;
	}
}