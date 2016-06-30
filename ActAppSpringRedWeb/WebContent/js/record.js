/*var uid="500000080";
var devId="d8a752b953d5a320e974c63479243d5e6f880db0";*/


var phone="";
var pageSize=30;
var currPage=0;

var uid="",devId="";
var token=NativeObj.getToken();
var obj = eval("("+token+")");
var token_url="";

if(obj!=null){
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
	getGiftList();
});

function getGiftList(){
	PostDataJson("QueryUserGiftServlet?funName=getUserGift&"+token_url, {"uId":uid,"phone":user.phone,"district":'',
			"pageSize":pageSize,"currPage":currPage}, function(data){
		if(data.code==200){
			var gList=data.v;
			for(var i=0;i<gList.length;i++){
				var gift=gList[i];
				if(gift.giftType==2){
					$("#tab").append("<tr><td>"+gift.giftName+"</td>" +
							"<td><span id='s1' class='time'>"+formatDate(gift.giftSendTime)+"</span>" +
									"<a href='javascript:void(0)' class='btn_pwd'>查看卡密</a></td></tr>");
					$("#jfbNo").html("卡号："+gift.cardNo);
					$("#jfbPwd").html("密码："+gift.cardPwd);
				}else{
					$("#tab").append("<tr><td>"+gift.giftName+"</td>" +
							"<td><span id='s1' class='time'>"+formatDate(gift.giftSendTime)+"</span></a></td></tr>");
				}
			}
			
		}
	});
}


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
 

function formatDate(time){
	var datetime=new Date();
	datetime.setTime(time);
	var year=datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
//    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
//    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
//    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "." + month + "." + date;
}