package com.ddb.act.red.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kkd.common.entity.Msg;
import kkd.common.logger.LogWriter;

import com.ddb.act.red.bo.ExchangeBo;
import com.ddb.act.red.bo.ShareBo;
import com.ddb.act.red.util.CodeUtil;
import com.ddb.act.red.vo.ExchangeVo;
import com.ddb.act.red.vo.UserInfo;

/**
 * Servlet implementation class ShareServlet
 */
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Pragma", "No-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addDateHeader("Expires", 0);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=response.getWriter();
		Msg<Boolean> msg=new Msg<Boolean>();
		
		String uId=request.getParameter("uId");
		String devId=request.getParameter("devId");
		//String lanId=request.getParameter("lanId");
		String phone=request.getParameter("phone");
		String district=request.getParameter("district");
		UserInfo user=new UserInfo();//保存用户数据
		user.setUid(uId);
		user.setPhone(phone);
		user.setAddress(district);


		//查询用户是否分享过
		ShareBo bo=new ShareBo();
		boolean exSucc=false;
		boolean flage=bo.queryIsShare(uId);
		if(flage==false){//当日未分享过，添加兑换机会
			ExchangeBo exbo= new ExchangeBo();
			ExchangeVo ev=new ExchangeVo();
			ev.setUid(uId);
			ev.setPhone(phone);
			ev.setExchangeNum(1);
			ev.setExchangeType(0);//设置兑换类型：分享免费
			exSucc=exbo.addExInfo(ev);
			if(exSucc==false){
				LogWriter.debug("----------------->添加赠送挑战机会记录失败,lanId:"+user.getLanAccount()+",phone:"+user.getPhone());
			}else{
				LogWriter.debug("----------------->添加赠送挑战机会记录成功,lanId:"+user.getLanAccount()+",phone:"+user.getPhone());
			}
		}
		//添加分享记录
		boolean shSucc=bo.addShareInfo(user);
		if(shSucc==true){
			msg.setCode(CodeUtil.CODE_SUCCESS);
			msg.setV(true);
			msg.setMsg("分享成功");
		}else{
			msg.setCode(CodeUtil.CODE_ERROR);
			msg.setV(false);
			msg.setMsg("分享成功");

		}
		out.print(msg.objectToFastJSON());
	}
}
