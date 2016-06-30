package com.ddb.act.red.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kkd.common.entity.Msg;

import com.ddb.act.red.util.CodeUtil;
import com.ddb.act.red.util.ConfigUtil;
import com.ddb.activity.base.bo.common.ActivityValidDateBo;
import com.ddb.activity.base.vo.common.ActivityValidDate;

public class ValiDateActUtil extends HttpServlet{

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
		
		ActivityValidDateBo bo=new ActivityValidDateBo();
		
		Msg<Boolean> msg=new Msg<Boolean>();
		Date currentDate=new Date();
		
		ActivityValidDate avd=bo.queryValidDate(ConfigUtil.getConfig("ActName"));
		if(avd!=null && avd.getOpen()!=null	){
			if(currentDate.before(avd.getStartDate())){
				msg.setCode(CodeUtil.CODE_NOT_START);
				msg.setV(false);
				msg.setMsg("活动未开始");
			}else if(currentDate.after(avd.getEndDate())){
				msg.setCode(CodeUtil.CODE_ALREADY_END);
				msg.setV(false);
				msg.setMsg("活动已结束");
			}else{
				msg.setCode(CodeUtil.CODE_SUCCESS);
				msg.setV(true);
				msg.setMsg("验证成功");
			}
		}else{
			msg.setCode(CodeUtil.CODE_ERROR);
			msg.setV(false);
			msg.setMsg("活动异常？请重新载入。。。");
		}
		out.print(msg.objectToFastJSON());
	}
}
