var lsg_weixin = {
	getOpenId : function(appid,spread_id,callback) {
		var code=util.getParam('code');
		if(!code){
            //var fromurl=location.href+'#personalPage';  
			var fromurl=location.href;  
            var url='https://open.weixin.qq.com/connect/oauth2/authorize?appid='+appid+'&redirect_uri='+encodeURIComponent(fromurl)+'&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect';  
            location.href=url;  
		}else{
			util.ajax(wx_constant.WX_OPENID_URL,{"code":code,"appid":appid,"spread_id":spread_id},callback);
		}
	},
	createJsSign : function(appid,callback){
		var signUrl = encodeURIComponent(window.location.href);
		util.ajax(wx_constant.WX_JSSIGN_URL,{"signUrl":signUrl,"appid":appid},callback);
	}
};

var wx_constant = {
		//localStorage key
		LSG_USER_ID:'LSG_USER_ID',

		WX_OPENID_SERV:'WX_OPENID_SERV',
		LSG_SPREAD_ID_SERV:'LSG_SPREAD_ID_SERV',
		LSG_SPREAD_NAME_SERV:'LSG_SPREAD_NAME_SERV',

		WX_OPENID_SUBSCRIBE:'WX_OPENID_SUBSCRIBE',
		LSG_SPREAD_ID_SUBSCRIBE:'LSG_SPREAD_ID_SUBSCRIBE',
		LSG_SPREAD_NAME_SUBSCRIBE:'LSG_SPREAD_NAME_SUBSCRIBE',
		
		WX_OPENID_URL:'http://www.lsg001.com/mng/weixin/api_invoke!getOpenId.action',
		WX_JSSIGN_URL:'http://www.lsg001.com/mng/weixin/api_invoke!createWeiXinJsSign.action',
};