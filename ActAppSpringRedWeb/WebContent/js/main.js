
/*var uid="500000080";
var devId="d8a752b953d5a320e974c63479243d5e6f880db0";
var token_url="abcdefg";*/

var phone="";
var userTimes=0;//剩余次数

var uid="",devId="";
var token=NativeObj.getToken();
var obj = eval("("+token+")");
var token_url="";
if(obj){
	 uid=obj.uid;
	 devId=obj.devId;
	 token_url="uid="+uid+"&token="+obj.token;
}
if(uid==""||uid==0){
	NativeObj.open("login")//登录
}
var user=null;
    queryBind();
	$(function (){	
		initGameNum();
	});

/**
 * 验证是否绑定并获取用户信息*/
 
function queryBind(){
	PostDataJson("ValiBindActUtil?"+token_url, {"uId":uid,"devId":devId}, function(data){
		if(data.code==200){
			user=data.v;
			phone=user.phone;
		}
		
	},false);
}

function initGameNum(){
	PostDataJson("PlayServlet?funName=initUserTimes&"+token_url, {"uId":uid,"phone":phone,"district":""}, function(data){
		if(data.code==200){
			userTimes=data.v;
			$("#times").text(data.v+"次");
		}
	});
};


function play(){
	PostDataJson("ValiDateActUtil?"+token_url, {}, function(data){
		if(data.code==200){
			if(userTimes==0){
				$(".gamePlay").css({"visibility":"visible"});
				popMarginTop();
			}else{
			window.location.replace("game/index.html?district=''&phone="+phone+"&"+token_url);
			}
		}else{
			return;
		}
	});
};

function addShareInfo(){
	PostDataJson("ShareServlet?"+token_url, {"uId":uid,"phone":phone,"district":""},function(data){
		if(data.code==200){//分享成功，得到挑战次数
			initGameNum();
		}else{
			return;
		}
	});
}

function share(){
	NativeObj.share("{\"title\":\"智慧沃家\",\"content\":\"智慧沃家，联通用户可免费提速至100M,你也来试试吧\",\"url\":\"http://www.cqwin.com/tisuapp/m/ip.htm\"}");
	
};

function goshare(){
	share();
	addShareInfo();
}