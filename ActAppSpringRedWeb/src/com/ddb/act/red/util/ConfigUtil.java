package com.ddb.act.red.util;

import ddb.webconf.resource.conf.IConfigure;

public class ConfigUtil {
	
	public static IConfigure configure;
	
	public static String getConfig(String key){
		String result="";
		result=configure.getValue(key);
		return result;
	}
}
