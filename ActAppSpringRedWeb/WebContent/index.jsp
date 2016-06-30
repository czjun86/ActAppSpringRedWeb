<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.ddb.activity.base.util.newuser.AppUtil"%>
<%@page import="kkd.common.logger.LogWriter"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0,height=device-height,target-densitydpi=device-dpi"/>
<title>纳福赢红包</title>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
<link rel="bookmark" href="/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="style/style.css">
<script src="js/jquery-1.10.1.min.js"></script>
<script src="js/fontSize.js"></script>
<script src="js/common.js"></script>
<script src="js/main.js"></script>
<script type="text/javascript">
function popMarginTop(){		
	$(".pop .pop_body").each(function(index, element) {
		var pHeight=$(this).height();
        var thisHeight=$(this).css({"margin-top":"-"+pHeight/2+"px"});
    });
}
$(function(){
	
	$(".hd_gz").click(function(){
		$(".pop_gz").css({"visibility":"visible"});
		popMarginTop();
	});
/* 	$(".btn_game").click(function(){
		$(".gamePlay").show();
		popMarginTop();
	}); */
	$(".btn_close,.btn_close_01").click(function(){
		//$(".pop").hide();
		$(".pop").css({"visibility":"hidden"});
	});
	$(".btn_sure_01").click(function(){
		//$(".pop").hide();
		$(".pop").css({"visibility":"hidden"});
	});
})
</script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?fe1fe1283a6426352766038a82caacb2";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</head>

<body>
<img src="images/pic_01.jpg" width="100%">
<img src="images/pic_02.jpg" width="100%">
<img src="images/pic_03.jpg" width="100%">
<img src="images/pic_04.jpg" width="100%">
<img src="images/pic_05.jpg" width="100%">
<img src="images/pic_06.jpg" width="100%">
<img src="images/pic_07.jpg" width="100%">
<a href="javascript:void(0)" onclick="goshare()" class="btn_share" >分享</a>
<a href="javascript:void(0)" class="hd_gz" >活动规则</a>
<div class="i_btm">
	<p class="game_num">今日剩余<span class="col_fee" id="times">0次</span>机会</p>
   <p><a href="javascript:void(0);" onclick="play()" class="btn_game"><img src="images/btn_game.png" width="100%"></a></p> 
    <!--不可点击状态-->
    <!--<p><a  class="btn_game"><img src="images/game_no.png" width="100%"></a></p>-->
    <a onclick="window.location.replace('record.jsp')" class="btn_record"><img src="images/btn_record.png" width="100%"></a>
</div>

<!--弹窗-->
<div class="pop pop_gz">
	<div class="pop_body">
        <div class="con_body">
            <img src="images/gz_t.png" width="100%">
           <a href="javascript:void(0)" class="btn_close"><img src="images/btn_close.png" width="100%"></a>
            <div class="gz_content">
                <p class="txt_01">
                参与用户：智慧沃家APP用户<br>
                活动规则：
                </p>
                <div class="gz_cont">
                    <p>1、 活动期间，用户每天有2次游戏机会；当日成功分享APP，还可额外获得1次游戏机会；</p>
                    <p>2、 游戏中累计抢到100个“倒福”图标，视为胜出，即可随机获得一个红包，打开即可100%获得奖励；</p>
                    <p>3、 获得提速时间的用户，将立即赠送到账；</p>
                    <p>4、 本活动最终解释权归智慧沃家所有；</p>
                </div>
            </div>
        </div>
    </div>
</div>

<!--判断账号是否参加过游戏-->
<div class="pop gamePlay">
	<div class="pop_body ">
        <div class="txt_pop">
        <a href="javascript:void(0)" class="btn_close_01"><img src="images/btn_close.png" width="100%"></a>
            <p class="txt_02">
                您的手机帐号今日已参与过本活动，<br>请明日再来参加。
            </p>
            <a href="javascript:void(0);" class="btn_sure_01"><img src="images/btn_sure.png" width="100%"></a>
        </div>
    </div>
</div>
</body>
</html>
