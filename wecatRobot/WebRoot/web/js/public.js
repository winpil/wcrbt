/**
 * 
 * @authors Your Name (you@example.org)
 * @date    2017-04-22 14:31:45
 * @version $Id$
 */
$(function(){
	// 显示二维码
	$('.showEwmMask').click(function(){
		$('#ewmMask').show();
	});
	$('#ewmMask .closeMask').click(function(event) {
		event.stopPropagation();
		$('#ewmMask').hide();
	});

	/*详情页逻辑*/
	window.addEventListener('popstate', function(e) {
		// 当浏览器的后退和前进按钮被点击时会触发popstate事件。
		var data = document.URL;
		if (data.indexOf('#')==-1) {
			$('#currentPage').show();
	    	$('#detailsPage').hide();
	    	$('#taoToken').hide();
            $(window).scrollTop($('body').attr('data-scrollTop'));
		};
	});
	// 领优惠券
    $('#getCoupon').click(function(){
        var data = JSON.parse($('#token_goods_id').val());
        getToken(data,function(){
            $('#taoToken').show();
        });
        
    });
	
	// 展开详情页
	$('#opendetails').click(function(){
		if ($(this).hasClass('open')) {
			return;
		}
		
		$('#detailsloading').show();
		var data = JSON.parse($('#token_goods_id').val());
		getDetailImages(data.goods_id,function(response){
			var imgArr = response.data.images;
			var imgHtml = template('tpl-images', imgArr);
			$('#imagesBox').html(imgHtml);
		});
		$('#detailsloading').hide();
		$(this).addClass('open');
	});
    // 如果是详情页
    var urlData = document.URL;
    var urlNum = urlData.substr(urlData.indexOf('#')+1,urlData.indexOf('#')+4);
    if (!isNaN(urlNum)) {
        $('#currentPage').hide();
        $('#detailsPage').show();
        var id = document.URL.substr(document.URL.indexOf('#')+1)
       
        $.ajax({
            url: 'http://www.lsg001.com/mng/goods!getGoodsDetail.action',
            contentType: "application/json; charset=utf-8",
            type: 'post',
            datatype: "json",
            data:JSON.stringify({
                "id": id
            }),
            success: function(msg){
                console.log(msg)
                if (msg.status==1) {
                    $('#currentPage').hide();
                    $('#detailsPage').show();

                    var data = msg.data;
                    $('.browser').attr('href','goToBrowser.html?id='+id);
                    var timeArr = data.coupon_end_time.substr(0,10).split('-');
                    for (var i = 0; i < timeArr.length; i++) {
                        timeArr[i] = parseInt(timeArr[i]);
                    };
                    data.coupon_end_time = timeArr.join('.');
                    var html = template('tpl-details', data);
                    $('#detailsBox').html(html);
                    

                    lsg_member.wxoptions.link = "details.html?id="+id;
                    lsg_member.wxoptions.imgUrl = data.goods_img;
                    lsg_member.wxoptions.title = data.goods_title;
                    lsg_member.wxready();
                    
                    /*getDetailImages(data.goods_id,function(response){s
                        var imgArr = response.data.images;
                        var imgHtml = template('tpl-images', imgArr);
                        $('#imagesBox').html(imgHtml);
                    })*/
                }else{
                    layer.alert(msg.msg,{icon:2});
                };
            }
        });
    };
})

// 获取详情
function getDetail(id){
    var scrollTop = $(window).scrollTop();
    $('body').attr('data-scrollTop',scrollTop);
	window.location.href='#'+id;
	
    $.ajax({
        url: 'http://www.lsg001.com/mng/goods!getGoodsDetail.action',
        contentType: "application/json; charset=utf-8",
        type: 'post',
        datatype: "json",
        data:JSON.stringify({
            "id": id
        }),
        success: function(msg){
            console.log(msg)
            if (msg.status==1) {
                $(window).scrollTop(0);
            	$('#currentPage').hide();
            	$('#detailsPage').show();

                var data = msg.data;
                $('.browser').attr('href','goToBrowser.html?id='+id);
                var timeArr = data.coupon_end_time.substr(0,10).split('-');
                for (var i = 0; i < timeArr.length; i++) {
                    timeArr[i] = parseInt(timeArr[i]);
                };
                data.coupon_end_time = timeArr.join('.');
                var html = template('tpl-details', data);
                $('#detailsBox').html(html);

                lsg_member.wxoptions.link = "details.html?id="+id;
                lsg_member.wxoptions.imgUrl = data.goods_img;
                lsg_member.wxoptions.title = data.goods_title;
                lsg_member.wxready();
                
				$('#imagesBox').html('');
				$('#opendetails').removeClass('open');
				$('#detailsloading').hide();
                /*getDetailImages(data.goods_id,function(response){
                    var imgArr = response.data.images;
                    var imgHtml = template('tpl-images', imgArr);
                    $('#imagesBox').html(imgHtml);
                })*/
            }else{
                layer.alert(msg.msg,{icon:2});
            };
        }
    });
}


// 判断是否是ios
function fnCopyIOS(){
    $('#taoToken').prepend('<div class="taoTokenBg"></div>')
    $('.tokeBox').hide();
    var u = navigator.userAgent;
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1 || u.indexOf('Linux') > -1; //android终端
    if (isiOS) {
        $('#tkl').show();
        document.addEventListener("selectionchange", function (e) {
            if (window.getSelection().anchorNode.parentNode.id == 'tkl' || window.getSelection().anchorNode.id == 'tkl') {
                var key = document.getElementById('tkl');
                window.getSelection().selectAllChildren(key);
            }
        }, false);
    };
    if (isAndroid) {
        $('textarea.tokeBox').show();
        var content = $('textarea.tokeBox').val();
        $('textarea.tokeBox').on('input', function(event) {
            $(this).val(content);
        });
        // $('textarea.tokeBox').attr('readonly','readonly');
        // $('textarea.tokeBox').click(function(){
        //     this.focus();
        //     this.select();
        // });
        // document.addEventListener("selectionchange", function (e) {
        //     var key = $('textarea.tokeBox')[0];
        //     window.getSelection().selectAllChildren(key);
        // }, false);
    };
    $('#taoToken').delegate('.taoTokenBg', 'click', function(event) {
        $('#taoToken').hide();
    });
}

// 生成淘口令
function getToken(obj,callback){
    var data = obj;
    $.ajax({
        url: 'http://www.lsg001.com/mng/goods!createTaobaoPwd.action',
        contentType: "application/json; charset=utf-8",
        type: 'post',
        datatype: "json",
        data:JSON.stringify({
            "id": obj.id,
            "openid": util.getCookie(wx_constant.WX_OPENID_SERV),
            "spread_id": getUrlParam('spread_id')
            // "id": getUrlParam('id'),
            // "spread_id": "asdeoasxxae",      // 推广者userId【可选】
            // "text": "活动多多，惊喜多多"     // 推广文案【可选，不填默认文案】
        }),
        success: function(msg){
            console.log(msg)
            if (msg.status==1) {
                data.taobaoPwd = msg.data.taobaoPwd;
                var html = template('tpl-token', data);
                $('#taoToken').html(html);
				
				fnCopyIOS();
				
			    var clipboard = new Clipboard('#copyBtn');
			    clipboard.on('success', function(e) {
			        // alert(JSON.stringify(e))
			        $('#copyBtn').hide();
			        $('#successBtn').show();
			    });
			    clipboard.on('error', function(e) {
			        layer.alert('复制失败，请重新复制或手动复制',{icon:2});
			    });
                if (!!callback) {
                    callback();
                };
            }else{
                layer.alert(msg.msg,{icon:2});
            };
        }
    });
}

// 获取详情页详情图
function getDetailImages(id,callback){
    // $.ajax({
    //     url: 'http://hws.m.taobao.com/cache/mtop.wdetail.getItemDescx/4.1/',
    //     type: 'get',
    //     datatype: "jsonp",
    //     jsonp: 'callback',
    //     jsonpCallback:"nutDetail",
    //     data:{
    //         "item_num_id": id,
    //         "type": "jsonp",
    //         "_": new Date().getTime()
    //     },
    //     success: function(response, status, xhr){
    //         console.log(response)
    //     }
    // });
    $.jsonp({
        url:"http://hws.m.taobao.com/cache/mtop.wdetail.getItemDescx/4.1/",
        data:{
            "data":'{"item_num_id": '+id+'}',
            "type": "jsonp",
            "_": new Date().getTime()
        },
        async:false,
        callbackParameter: "callback",
        success: callback
   });

}

// 获取地址栏参数
function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null){
		return decodeURI(r[2]);
	};
	return null; //返回参数值
}
function getUrlHref(name){
	var reg = new RegExp("(^|#)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.hash.substr(1).match(reg);  //匹配目标参数
	if (r!=null){
		return decodeURI(r[2]);
	};
	return null; //返回参数值
}
