var util = {
	setCookie : function(e, t, o) {
		var n = new Date;
		o || (o = 30), n.setTime(n.getTime() + o*60*1000),
				document.cookie = e + "=" + escape(t) + ";expires="
						+ n.toGMTString() + ";path=/"
	},
	toDecimal2 : function(x) {
        var f = parseFloat(x);  
        if (isNaN(f)) {  
            return 0.00;  
        }  
        var f = Math.round(x*100)/100;  
        var s = f.toString();  
        var rs = s.indexOf('.');  
        if (rs < 0) {  
            rs = s.length;  
            s += '.';  
        }  
        while (s.length <= rs + 2) {  
            s += '0';  
        }  
        return s;  
    },
	getCookie : function(e) {
		var t, o = new RegExp("(^| )" + e + "=([^;]*)(;|$)");
		if (t = document.cookie.match(o)) {
			var n = unescape(t[2]);
			return n ? unescape(t[2]).replace(/^\"|\"$/g, "")
					: unescape(t[2])
		}
		return null
	},
	changeUrlArg : function(url, arg, val){
	    var pattern = arg+'=([^&]*)';
	    var replaceText = arg+'='+val;
	    return url.match(pattern) ? url.replace(eval('/('+ arg+'=)([^&]*)/gi'), replaceText) :(url.match('[\?]') ? url+'&'+replaceText : url+'?'+replaceText);
	},
	delQueStr : function(url, ref){
	    var str = "";
	    if (url.indexOf('?') != -1)
	        str = url.substr(url.indexOf('?') + 1);
	    else
	        return url;
	    var arr = "";
	    var returnurl = "";
	    var setparam = "";
	    if (str.indexOf('&') != -1) {
	        arr = str.split('&');
	        for (i in arr) {
	            if (arr[i].split('=')[0] != ref) {
	                returnurl = returnurl + arr[i].split('=')[0] + "=" + arr[i].split('=')[1] + "&";
	            }
	        }
	        return url.substr(0, url.indexOf('?')) + "?" + returnurl.substr(0, returnurl.length - 1);
	    }
	    else {
	        arr = str.split('=');
	        if (arr[0] == ref)
	            return url.substr(0, url.indexOf('?'));
	        else
	            return url;
	    }
	},
	setSessionStorage : function(e, t) {
		window.sessionStorage.setItem(e, t)
	},
	getSessionStorage : function(e) {
		return window.sessionStorage.getItem(e) || ""
	},
	
	//----JSON Object
	setJsonLocals : function (key, val) {
	    this.setLocalStorage(key, JSON.stringify(val));
	},
	getJsonLocals : function (e) {
	    var o = this.getLocalStorage(e) || "{}"
	      , n = JSON.parse(o);
	    return n;
	},
	setJsonLocalsAttr : function(key,attr,val){
		  var o = this.getLocalStorage(key) || "{}"
	      , n = JSON.parse(o);
		 n[attr] = val;
		 this.setLocalStorage(key, JSON.stringify(n));
	},
	getJsonLocalsAttr : function(key,attr){
		  var o = this.getLocalStorage(key) || "{}"
	      , n = JSON.parse(o);
		 return  n[attr];
	},
	removeLocalStorage : function(e) {
		window.sessionStorage.removeItem(e)
	},
	removeAllLocalStorage : function() {
		window.sessionStorage.clear()
	},
	setJsonArrayLocal : function(key, val) {
		var o = JSON.parse(this.getLocalStorage(key) || "[]");
		/*$.each(o, function(index, item){
		    if(val.id == item.id){
		    	o.splice(index,1);
		    	return false;
		    }
		}); */
		o.push(val);
		this.setLocalStorage(key, JSON.stringify(o));
	},
	setJsonArrayLocalOfIndex:function(key, val ,index){
		var o = JSON.parse(this.getLocalStorage(key) || "[]");
		o[index] = val;
		this.setLocalStorage(key, JSON.stringify(o));
	},
	getJsonArrayLocal : function(key, val) {
		var o = JSON.parse(this.getLocalStorage(key) || "[]");
		var i;
		$.each(o, function(index, item){
		    if(val.id == item.id){
		    	i = item;
		    	return false;
		    }
		}); 
		return i;
	},
	removeJsonArrayLocal : function(key, i) {
		var o = JSON.parse(this.getLocalStorage(key) || "[]");
		o.splice(i,1);
		/*
		$.each(o, function(index, item){
		    if(id == item.id){
		    	o.splice(index,1);    	
		    	return false;
		    }
		}); */
		this.setLocalStorage(key, JSON.stringify(o));
	},
	getJsonAllArrayLocal : function(key) {
		return JSON.parse(this.getLocalStorage(key) || "[]");
	},
	toast:function(msg) {
	    $(".pop_ts").remove();
	    var str = "<p class=\"pop_ts\">" + msg + "</p>";
	    $("body").append(str);
	    $(".pop_ts").show();
	    //2秒后清除提示
	    setTimeout(function() {
	        $(".pop_ts").hide();
	    }, 1500);
	},
	popSmall:function(msg,titCon,callback) {
		if(!callback || 'function' != typeof callback){
			callback = function(){};
		}
		
		var titCon = titCon || "知道了"; 
		$(".pop_s_box").remove();
	    var str = '<div class="pop_alert pop_s_box">'+
					'<div class="popbox">'+
						'<div class="popcon">'+
							'<div class="tit">温馨提示</div>'+
							'<div class="con">'+msg+'</div>'+
						'</div>'+
						'<div class="twobtn onebtn">'+
							'<div>'+
								'<a hre="#">'+titCon+'</a>'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</div>';
	    $("body").append(str);
	    $(".pop_s_box").show();
	    //点击按钮关闭
	    $(".onebtn a").click(function(){
	    	$(".pop_s_box").hide();
	    	callback();
	    });
	},
	showLoading:function(msg){
		 $(".loading").remove();
		 msg = msg || 'Loading...';
		 var str = '<div class="loading"><span class="img"></span><p>'+msg+'</p></div>';
		 $("body").append(str);
		 $(".loading").show();
	},
	hideLoading:function(){
		 $(".loading").hide();
	},
	ajax:function(url, data, success, complete){
		this.ajax(url, data, success, null, null, complete);
	},
	ajax:function(url, data, success, async, dataType, complete){
		if(!complete || 'function' != typeof complete){
			complete = function(){};
		}
		$.ajax({
			type:'POST',
			url:url,
			async: async || false,
			dataType: dataType || 'json',
			data:data,
			success:success,
			error:function(XMLHttpRequest, textStatus, errorThrown){
				if(textStatus == 'timeout'){
					util.popSmall('请检查手机是否联网，或者稍后重试！', '我知道了');
				}else{
					util.popSmall(textStatus, '我知道了');
				}
			},
			timeout :3000,
			complete:complete
		});
	},
	getParam:function(name){
		return window.location.search.match(new RegExp('(?:\\?|&)' + name+ '=([^&]*?)(?:&|$)', 'i')) ? RegExp.$1 : '';	
	},
	isWeiXin:function (){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	},Locate : function(callback) {
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r) {
			if (this.getStatus() == BMAP_STATUS_SUCCESS) {
				var myGeo = new BMap.Geocoder();// 获取用户的地址解析
				myGeo.getLocation(r.point, function(result) {
					var addComp = result.addressComponents;
					var address = addComp.district + addComp.street + addComp.streetNumber;
					callback(addComp.city,address,result);
				});
			}
		}, {
			enableHighAccuracy : true
		});
	}
};

var constant = {
		//localStorage key
		WX_L_PHONE_KEY:'wx_phone',
		WX_CONFIRM_CITY_KEY:'wx_confirm_city',
		WX_CONFIRM_CITY_BACKURL_KEY:'wx_confirm_city_backurl',
		WX_L_CART_KEY:'wx_cart',
		WX_L_ADDR_KEY:'wx_addr',
		WX_L_SHOW_TIME_KEY:'wx_showtime',
		WX_L_TIME_KEY:'wx_servicetime',
		WX_L_TIMESTAMP:'wx_timestamp',
		WX_L_DATA:'wx_data'
};
/**       
 * 对Date的扩展，将 Date 转化为指定格式的String       
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
 * eg:       
 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
 */ 
Date.prototype.Format=function(fmt){var o={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),"S":this.getMilliseconds()};if(/(y+)/.test(fmt)){fmt=fmt.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length))}for(var k in o){if(new RegExp("("+k+")").test(fmt)){fmt=fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(o[k]):(("00"+o[k]).substr((""+o[k]).length)))}}return fmt};Date.prototype.pattern=function(fmt){var o={"M+":this.getMonth()+1,"d+":this.getDate(),"h+":this.getHours()%12==0?12:this.getHours()%12,"H+":this.getHours(),"m+":this.getMinutes(),"s+":this.getSeconds(),"q+":Math.floor((this.getMonth()+3)/3),"S":this.getMilliseconds()};var week={"0":"/u65e5","1":"/u4e00","2":"/u4e8c","3":"/u4e09","4":"/u56db","5":"/u4e94","6":"/u516d"};if(/(y+)/.test(fmt)){fmt=fmt.replace(RegExp.$1,(this.getFullYear()+"").substr(4-RegExp.$1.length))}if(/(E+)/.test(fmt)){fmt=fmt.replace(RegExp.$1,((RegExp.$1.length>1)?(RegExp.$1.length>2?"/u661f/u671f":"/u5468"):"")+week[this.getDay()+""])}for(var k in o){if(new RegExp("("+k+")").test(fmt)){fmt=fmt.replace(RegExp.$1,(RegExp.$1.length==1)?(o[k]):(("00"+o[k]).substr((""+o[k]).length)))}}return fmt};
