<%@page import="java.net.URLEncoder"%>
<%@page import="com.ddb.activity.base.util.newuser.AppUtil"%>
<%@page import="java.util.Map"%>
<%@page import="kkd.common.logger.LogWriter"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0,height=device-height"/>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="/favicon.ico" type="image/x-icon"/>
<title>中奖纪录</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/fontSize.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script  type="text/javascript" src="js/record.js"></script>
<script type="text/javascript">

$(function(){	
	$(".btn_pwd").click(function(){
		//$(".pwd_pop").show();
		$(".pop_gz").css({"visibility":"visible"});
	});
	$(".btn_sure").click(function(){
		//$(".pwd_pop").hide();
		$(".pop_gz").css({"visibility":"hidden"});
	});
});	
</script>
<script type="text/javascript">
$(function(){	
	function popMarginTop(){		
		$(".pop .pop_body").each(function(index, element) {
			var pHeight=$(".pop .pop_body").height();
			$(".pop .pop_body").css({"margin-top":"-"+pHeight/2+"px"});
        });
	}	
	$(".btn_pwd").click(function(){
		//$(".pwd_pop").show();
		$(".pop_gz").css({"visibility":"visible"});
		 popMarginTop()
	});
	$(".btn_sure").click(function(){
		//$(".pwd_pop").hide();
		$(".pop_gz").css({"visibility":"hidden"});
	});
})	
</script>
</head>

<body  style="background:#FFF">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rec_tab pos_top">
  <tr>
    <th>奖品</th>
    <th>中奖时间</th>
  </tr>
 </table>
<table id="tab" width="100%" border="0" cellspacing="0" cellpadding="0" class="rec_tab mar_top">  
</table>




<div class="pop pwd_pop">
	<div class="pop_body pwd_body">
    	<p id="jfbNo" class="txt_04"></p>
        <p id="jfbPwd" class="txt_04"></p>
         <p class="btn_cz">
            <a href="javascript:void(0)" class="btn_sure">确&nbsp;&nbsp;定</a>
        </p>
    </div>
</div>
<a onclick="window.location.replace('index.jsp')" class="btn_back">返回活动首页</a>
</body>
</html>
