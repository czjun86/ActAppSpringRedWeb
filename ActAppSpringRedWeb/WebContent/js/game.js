
function getGift(){
	PostDataJson("../PlayServlet?funName=getGift&"+token_url, {"uId":uid,"phone":phone}, function(data){
		if(data.code==200){
			$(".red_txt").html("恭喜您获<span class='col_f30'>"+data.v.gift.giftName+"</span>"+
                    "<a href='javascript:void(0);' onclick='backIndexPgae()' class='btn_sure_01'><img src='../images/btn_sure.png' width='100%'></a>");
		}else if(data.code==3){
			$(".red_txt").html("打开红包失败，游戏次数不足！");
		}else{
			$(".red_txt").html("打开红包失败，请重试！"+
            "<a href='javascript:void(0);' onclick='backIndexPgae()'  class='btn_sure_01'><img src='../images/btn_sure.png' width='100%'></a>");
		}
	});
};

function getJionGift(){
	PostDataJson("../PlayServlet?funName=getJionGift&"+token_url, {"uId":uid,"phone":phone},function(data){
		if(data.code==3)
			$("#GameScoreLayer-bast").html("打开红包失败，游戏次数不足！");
	});
}