package com.ddb.act.red.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kkd.common.entity.Msg;
import kkd.common.logger.LogWriter;

import com.ddb.act.red.bo.GiftBll;
import com.ddb.act.red.bo.ShareBo;
import com.ddb.act.red.bo.UserInfoBo;
import com.ddb.act.red.util.CodeUtil;
import com.ddb.act.red.vo.FingerPlayVo;
import com.ddb.act.red.vo.Gift;
import com.ddb.act.red.vo.ResponseVo;
import com.ddb.act.red.vo.UserInfo;

/**
 * Servlet implementation class FingerPlayServlet
 */
public class PlayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		String funName=request.getParameter("funName");
		String phone=request.getParameter("phone");
		String district=request.getParameter("district");
		//String lanId=request.getParameter("lanId");
		String uid=request.getParameter("uId");
		String devId=request.getParameter("devId");
		//调用接口查询用户判断是否绑定宽带
		UserInfo user=new UserInfo();//保存用户数据
		user.setUid(uid);
		
		user.setPhone(phone);
		user.setAddress(district);
		if("initUserTimes".equals(funName)){//初始查询用户剩余参与次数
			Msg<Integer> msg=initUserTimes(uid);
			out.print(msg.objectToFastJSON());
		}else if("getGift".equals(funName)){//获得奖品
			Msg<ResponseVo<Gift>> msg=getGift(user,devId);
			System.out.println(msg.objectToFastJSON());
			out.print(msg.objectToFastJSON());
		}else if("getJionGift".equals(funName)){//发送参与奖品
			Msg<Boolean> msg=getJionGift(user,devId);
			out.print(msg.objectToFastJSON());
		}
	}

	/**
	 * 获取奖品
	 * @param u
	 * @return
	 * @throws Exception
	 */
	public Msg<ResponseVo<Gift>> getGift(UserInfo u,String devId){
		Msg<ResponseVo<Gift>> resMsg=new Msg<ResponseVo<Gift>>();
		ResponseVo<Gift> rv=new ResponseVo<Gift>();
		GiftBll giftbll=new GiftBll();
		Gift g;
		try {
			UserInfoBo ubo=new UserInfoBo();
			int userTimes=ubo.queryUserTimes(u.getUid());
			if(userTimes<1){
				resMsg.setCode(CodeUtil.CODE_NO_TIMES);
				resMsg.setV(rv);
				resMsg.setMsg("次数不足");
				return resMsg;
			}
			g = giftbll.getWinGift(u,devId);
			rv.setGift(g);
//			rv.setChance(chance);
			if(g==null){
				resMsg.setCode(CodeUtil.CODE_ERROR);
				resMsg.setV(rv);
				resMsg.setMsg("获取奖品失败");
			}else{
				//查询用户是否分享过
				ShareBo bo=new ShareBo();
				boolean isShare=bo.queryIsShare(u.getUid());
				rv.setShare(isShare);
				resMsg.setCode(CodeUtil.CODE_SUCCESS);
				resMsg.setV(rv);
				resMsg.setMsg("获取奖品成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogWriter.debug("---------------->获取奖品失败，uid:"+u.getUid()+",phone:"+u.getPhone(),e);
			resMsg.setCode(CodeUtil.CODE_ERROR);
			resMsg.setV(rv);
			resMsg.setMsg("获取奖品失败");
		}
		return resMsg;
	}
	
	
	/**
	 * 初始化用户参与次数
	 * @param lanId
	 * @return
	 */
	public Msg<Integer> initUserTimes(String uid){
		Msg<Integer> msg=new Msg<Integer>();
		//查询用户剩余次数
		UserInfoBo ubo=new UserInfoBo();
		int userTimes=ubo.queryUserTimes(uid);
		if(userTimes>=0){
			msg.setCode(CodeUtil.CODE_SUCCESS);
			msg.setV(userTimes);
			msg.setMsg("查询成功");
		}else{
			msg.setCode(CodeUtil.CODE_ERROR);
			msg.setV(-1);
			msg.setMsg("查询异常");
		}
		return msg;
	}
	
	/**
	 * 发送参与奖
	 * @param u
	 * @return
	 */
	public Msg<Boolean> getJionGift(UserInfo u,String devId){
		Msg<Boolean> resMsg=new Msg<Boolean>();
		GiftBll gbiz=new GiftBll();
		Gift g=new Gift();
		g.setGiftName("1小时提速时间");
		g.setGiftType(4);
		boolean isSucc=false;
		try {
			UserInfoBo ubo=new UserInfoBo();
			int userTimes=ubo.queryUserTimes(u.getUid());
			if(userTimes>0){
				isSucc=gbiz.sendGift(g, u,devId);
			}else{
				resMsg.setCode(CodeUtil.CODE_NO_TIMES);
				resMsg.setMsg("次数不足");
				return resMsg;
			}
			
			if(isSucc==true){
				LogWriter.info("----->用户参与奖发送成功，giftName:"+g.getGiftName()+",lanId:"+u.getLanAccount()+",phone:"+u.getPhone());
				resMsg.setCode(CodeUtil.CODE_SUCCESS);
				resMsg.setMsg("发送成功");
			}else{
				LogWriter.debug("----->用户参与奖发送失败，giftName:"+g.getGiftName()+",lanId:"+u.getLanAccount()+",phone:"+u.getPhone());
				resMsg.setCode(CodeUtil.CODE_ERROR);
				resMsg.setMsg("获取奖品失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogWriter.debug("----->用户参与奖发送失败，giftName:"+g.getGiftName()+",lanId:"+u.getLanAccount()+",phone:"+u.getPhone());
			resMsg.setCode(CodeUtil.CODE_ERROR);
			resMsg.setMsg("获取奖品失败");
		}
		return resMsg;
	}
}
