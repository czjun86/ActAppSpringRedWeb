package com.ddb.act.red.util;

public class DistrictCodeUtil {

	public static String districtCodeFormat(String code){
		String result="";
		if("UNICOM_CQ".equals(code)){//重庆
			result="CHONGQING";
		}else if("UNICOM_SC".equals(code)){//四川
			result="SICHUAN";
		}else if("UNICOM_FJ".equals(code)){//福建
			result="FUJIAN";
		}else if("UNICOM_TJ".equals(code)){//天津
			result="TIANJIN";
		}else if("UNICOM_NM".equals(code)){//内蒙
			result="NEIMENG";
		}else if("UNICOM_YN".equals(code)){//云南
			result="YUNNAN";
		}else if("UNICOM_JL".equals(code)){//吉林
			result="JILIN";
		}		
	 return result;
	}
}
