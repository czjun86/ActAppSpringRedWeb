package com.ddb.act.red.bo;



import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ddb.act.red.util.ConfigUtil;
import com.ddb.act.red.util.HttpUtil;
import com.ddb.act.red.vo.User;

public class QueryLanUserBo {
	
	public User getUser(String uid){
		JSONObject jobj=this.getInterfasce(uid);
		String code=jobj.getString("errCode");
		User user=null;
		if(code.equals("SUC")){
			user=jobj.getJSONObject("data").getObject("userInfo", User.class);
		}
		
		return user;
		
	}
 /**
  * 取得接口返回数据
  */

  public JSONObject getInterfasce(String uid){
	//调用接口
	    Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("number", uid);
		params2.put("queryType", "uid");
		params2.put("hasLanInfo", "1");
		String url2 = ConfigUtil.getConfig("USER_URL");
		JSONObject ss2 = HttpUtil.post(url2, params2);
		return ss2;
  }
}
