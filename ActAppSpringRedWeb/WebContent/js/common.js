


/**

 * 异步提交返回XML
 * 
 * @param url
 *            提交路径
 * @param data
 *            参数
 * @param callback
 *            回调函数
 */
function PostDataXml(url, data, callback) {
	$.ajax({
		async : false,
		"url" : url,
		"global" : false,
		"type" : "POST",
		"dataType" : "xml",
		"timeout" : "60000",
		"data" : data,
		"success" : callback,
		"error" : function(err, msg, code) {
			return false;
		}
	});
}
function PostDataXml(url, data, callback,async) {
	$.ajax({
		async : false,
		"url" : url,
		"global" : false,
		"type" : "POST",
		"dataType" : "xml",
		"timeout" : "60000",
		"data" : data,
		"success" : callback,
		"error" : function(err, msg, code) {
			return false;
		}
	});
}



function PostDataJson(url, data, callback,async){
	data.format = "json";
	if(!async){
		async = false;
	}
	$.ajax({
		async : async,
		"url" : url,
		"global" : false,
		"type" : "POST",
		"dataType" : "json",
		"timeout" : "600000",
		"data" : data,
		"traditional":true,
		"success" : callback,
		"error" : function(err, msg, code) {
			return false;
		}
	});
}


//添加收藏夹
function add_favorite(a, title, url) {
	url = url || a.href;
	title = title || a.title;
	try{ // IE
		window.external.addFavorite(url, title);
	} catch(e) {
		try{ // Firefox
			window.sidebar.addPanel(title, url, "");
		} catch(e) {
			if (/Opera/.test(window.navigator.userAgent)) { // Opera
				a.rel = "sidebar";
				a.href = url;
				return true;
			}
			alert('加入收藏失败，请使用 Ctrl+D 进行添加');
		}
	}
	return false;
}
function exceptionOpenRedirect() {
	if (window === window.top)
		location.href = "http://www.kkdian.com";
}

function topOpenURL() {
	if (window != window.top) {
		top.location.href = location.href;
	}
}

/**
 * 验证权限异步请求
 * @param url
 */
function authUser(url,callback){
	PostDataJson(url,{"dialog":true},callback);
}
