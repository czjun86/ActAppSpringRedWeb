package com.ddb.act.red.web.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kkd.common.entity.Msg;

import com.ddb.act.red.bo.QueryLanUserBo;
import com.ddb.act.red.util.CodeUtil;
import com.ddb.act.red.util.ConfigUtil;
import com.ddb.act.red.vo.User;
import com.ddb.activity.base.bo.common.ActivityValidDateBo;
import com.ddb.activity.base.vo.common.ActivityValidDate;

public class ValiBindActUtil extends HttpServlet{

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
		String uid=request.getParameter("uId");
		PrintWriter out=response.getWriter();
		Msg<User> msg=new Msg<User>();
		QueryLanUserBo bo=new QueryLanUserBo();
		User us=bo.getUser(uid);
		if(us!=null&&us.getPhone()!=null){
				msg.setCode(CodeUtil.CODE_SUCCESS);
				msg.setV(us);
				msg.setMsg("验证成功");
		}else{
			msg.setCode(CodeUtil.CODE_ERROR);
			msg.setV(null);
			msg.setMsg("用户手机号不能为空");
		}
		out.print(msg.objectToFastJSON());
	}
}
