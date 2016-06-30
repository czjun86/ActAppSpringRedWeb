package com.ddb.act.red.util;


/***
 * czj
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
public static String  url;
public static Map<String,String>  params;
	public static JSONObject post(String ur,Map<String,Object> params){
		 StringBuffer sb=new StringBuffer();
		try {
			//String json = JSONObject.toJSONBytes(params);
			String urlPath = new String(ur); 
		    //建立连接
		    URL url=new URL(urlPath);
		    HttpURLConnection httpConn=(HttpURLConnection)url.openConnection();
		    //设置参数
		    httpConn.setDoOutput(true);   //需要输出
		    httpConn.setDoInput(true);   //需要输入
		    httpConn.setUseCaches(false);  //不允许缓存
		    httpConn.setRequestMethod("POST");   //设置POST方式连接
		    //设置请求属性
		    httpConn.setRequestProperty("Accept", "application/json");
		    httpConn.setRequestProperty("Content-Type", "application/json");
		    httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
		    httpConn.setRequestProperty("Charset", "UTF-8");
		    //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
		    httpConn.connect();
		    //建立输入流，向指向的URL传入参数
		    DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
		    dos.write(JSONObject.toJSONBytes(params));
		    dos.flush();
		    dos.close();
		    //获得响应状态
		    int resultCode=httpConn.getResponseCode();
		    if(HttpURLConnection.HTTP_OK==resultCode){
		      String readLine=new String();
		      BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
		      while((readLine=responseReader.readLine())!=null){
		        sb.append(readLine).append("\n");
		      }
		      responseReader.close();
		    } 
		} catch (Exception e) {
			 System.out.println(e);
		}
		JSONObject jsb=	JSONObject.parseObject(sb.toString());
		
		return jsb;
	}
}
