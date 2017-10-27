$(function(){
	lsg_member.init();
});
var lsg_member = {
	appid : "wx0e365a52112a1464",
	shareImgUrl:"",
	_initOpenId:function(){
		var spread_id = util.getParam('spread_id');
		var openid = util.getSessionStorage(wx_constant.WX_OPENID_SERV);
		if(openid && openid!=''){
			// 显示推广人
			var spread_name = util.getCookie(wx_constant.LSG_SPREAD_NAME_SERV);
			if(spread_name){
				$(document).attr("title",spread_name);
			}
			return;
		}
		lsg_weixin.getOpenId(this.appid,spread_id,function(result){
			if(result.status==1){
				// 显示推广人
				if(result.data.spread_name){
					$(document).attr("title",result.data.spread_name);
				}
				util.setSessionStorage(wx_constant.WX_OPENID_SERV,result.data.openid)
				util.setCookie(wx_constant.WX_OPENID_SERV,result.data.openid,24*60);
				util.setCookie(wx_constant.LSG_USER_ID,result.data.user_id,24*60);
				util.setCookie(wx_constant.LSG_SPREAD_ID_SERV,result.data.spread_id,24*60);
				util.setCookie(wx_constant.LSG_SPREAD_NAME_SERV,result.data.spread_name,24*60);
			}else{
				// TODO 自行修改错误提示，result.msg 
				alert(result.msg);
			}
		});
	},
    wxoptions : {
            imgUrl : "img/erweima_dyh.jpg",
            link : "",
            desc : null,
            title :null,
            appId : this.appid
       },
	wxready:function(){
        wx.ready(function () {
            wx.checkJsApi({
                jsApiList: [
                    'hideAllNonBaseMenuItem','showMenuItems','onMenuShareTimeline','onMenuShareAppMessage'
                ]
            });
            wx.hideAllNonBaseMenuItem();    // 隐藏所有非基本菜单项
            wx.showMenuItems({
                menuList: ['menuItem:share:appMessage','menuItem:share:timeline','menuItem:favorite'] // 要显示的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
            });
            wx.onMenuShareTimeline({title: lsg_member.wxoptions.title,link:  lsg_member.wxoptions.link,imgUrl:lsg_member.wxoptions.imgUrl});
            wx.onMenuShareAppMessage({title: lsg_member.wxoptions.title,desc: lsg_member.wxoptions.desc,link: lsg_member.wxoptions.link,imgUrl: lsg_member.wxoptions.imgUrl,type: 'link',dataUrl: ''});
        });
        
        wx.error(function(res){
        	alert(res.errMsg);
        });
        
	   },
	_initShare : function(){
		var openidJoin = util.getSessionStorage(wx_constant.WX_OPENID_SERV);
		if(!openidJoin || openidJoin==''){
			return;
		}
		
		var signUrl = encodeURIComponent(window.location.href);
		//var signUrl = encodeURIComponent(window.location.host + "/wxweb/index.html");
		if(this.wxoptions.link==""){
	        var link = util.changeUrlArg(window.location.href,"spread_id",util.getCookie(wx_constant.LSG_USER_ID));
	        link = util.delQueStr(link, "code");
	        link = util.delQueStr(link, "state");
	        lsg_member.wxoptions.link = link;
		}else{
			//signUrl = encodeURIComponent(this.wxoptions.link);
		}
		
		var weixinSignMap = null;
		util.ajax(wx_constant.WX_JSSIGN_URL,{"signUrl":signUrl,"appid":this.appid}, function(data){weixinSignMap = data;});
        //微信设置
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: weixinSignMap.appId, // 必填，公众号的唯一标识
            timestamp:weixinSignMap.timestamp,  // 必填，生成签名的时间戳
            nonceStr: weixinSignMap.nonceStr,  // 必填，生成签名的随机串
            signature: weixinSignMap.signature, // 必填，签名，见附录1
            jsApiList: ['hideAllNonBaseMenuItem','onMenuShareTimeline','onMenuShareAppMessage','showMenuItems'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        
        this.wxready();
	},
	_init : function() {
		//if(util.isWeiXin()){
			//this._initOpenId();
			this._initShare();
		//}
	},
	init : function(){
		this._init();
	}
}
