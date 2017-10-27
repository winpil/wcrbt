var ctx = null;
$(document).ready(function(){
	ctx = $("#sys-ctx").val();
	// 调整菜单模态框的宽
	$("#sysMenuModel").css("width","350px");
	// 菜单选择控件被点击，加载所有系统菜单到zTree
	$("#select-sys-menu").click(function(){
		var sysMenuNodes = [];
		var sysMenuSetting = {callback:{beforeDblClick: sysMenuTreeCallBack}};
		$.ajax( {
			url : ctx+"/ajax/menuAjaxController/findAllListTree",
			type : "post",
			dataType : "json",
			success : function(data) {
				for(var i=0;i<data.resultData.length;i++){
					var nodeOne = new Object();
					nodeOne.id = data.resultData[i].id;
					nodeOne.name = data.resultData[i].name;
					if(data.resultData[i].childList != null && typeof(data.resultData[i].childList) != "undefined" && data.resultData[i].childList.length>0){
						nodeOne.children = [];
						var menuTwoList = data.resultData[i].childList;
						for(var k=0;k<menuTwoList.length;k++){
							var nodeTwo = new Object();
							nodeTwo.id = menuTwoList[k].id;
							nodeTwo.name = menuTwoList[k].name;
							if(menuTwoList[k].childList != null && typeof(menuTwoList[k].childList) != "undefined" && menuTwoList[k].childList.length>0){
								nodeTwo.children = [];
								for(var c=0;c<menuTwoList[k].childList.length;c++){
									var nodeThree = new Object();
									nodeThree.id = menuTwoList[k].childList[c].id;
									nodeThree.name = menuTwoList[k].childList[c].name;
									nodeTwo.children.push(nodeThree);
								}
							}
							nodeOne.children.push(nodeTwo);
						}
					}
					sysMenuNodes.push(nodeOne);
				}
				$.fn.zTree.init($("#sys-menu-tree"), sysMenuSetting, sysMenuNodes);
			},error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log("error : "+ctx+"/ajax/menuAjaxController/findAllList");
            }
		});
	});
	// 表单字段校验
	$("#inputForm").validate({
		errorPlacement:function(error,element){
			if(element.parent().children(".sys-error-info").length < 1){
				element.parent().append("&nbsp;&nbsp;&nbsp;<span class='label label-important sys-error-info'>必填信息</span>");
			}
		}
	});
	// 把表单的select标签变漂亮，同时具备检索功能（jquery-select2）
	$("select").each(function(){
		$(this).select2();
	});
	// 系统图片选择器的叉叉
	$(".a-sys-img-select-append").live("click",function(){
		var inputId = $(this).attr("title");
		var imgSrc = $(this).siblings(".img-sys-img-select-append").attr("src");
		var newValue = $("#"+inputId).val().replace(imgSrc+",",'').replace(imgSrc,'');
		$("#"+inputId).val(newValue);
		$(this).parent(".div-sys-img-select-append").remove();
	});
});

// 菜单zTree双击回调函数
function sysMenuTreeCallBack(treeId, treeNode){
	if(typeof(treeNode.id) != "undefined"){
		$(".select-sys-menu-display").val(treeNode.name);
		var inputId = $(".select-sys-menu-display").attr("title");
		$("#"+inputId).val(treeNode.id);
		$('#sysMenuModel').modal('hide');
	}
}

// 去除空格
function trim(str){
	if(typeof(str) != 'undefined'){
		var reg=new RegExp(' ',"gm");
		return str.replace(reg,'');
	}
	return '';
}

// 判断非空
function notBlank(str){
	if(typeof(str) != 'undefined' && str != null){
		str = trim(str);
		if(str != '' && str != 'null'){
			return true;
		}
	}
	return false;
}

// 获取浏览器url参数
function getUrlParam(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}

// 打开系统图片选择器
function sysImgSelect(inputName,multiSelect){
	if(notBlank(inputName)){
		window.open(ctx+"/sys/sysCommonWebController/sysImgList?inputName="+inputName+"&multiSelect="+multiSelect,"win-sys-uploadImg","width=965,height=550,top=200,left=200,location=no,toolbar=no,menubar=no,status=no");
	}
}

// 根据id清空表单值
function clearInput(inputId){
	var obj = document.getElementById(inputId);
	if(obj != null ){
		obj.value = "";
	}
}

//打开系统文件选择器
function sysFileSelect(inputName){
	if(notBlank(inputName)){
		window.open(ctx+"/sys/sysCommonWebController/sysFileList?inputName="+inputName,"win-sys-uploadFile","width=965,height=550,top=200,left=200,location=no,toolbar=no,menubar=no,status=no");
	}
}

// 省份选择器ajax
function provinceChange(){
	var pid = $("#provinceId").val();
	$.ajax( {
		url : ctx+"/ajax/sysCityAjaxController/findList",
		data : {provinceId:pid},
		type : "post",
		dataType : "json",
		success : function(data) {
			var option = "<option value='' selected='selected'></option>";
			for(var i=0;i<data.resultData.length;i++){
				option += "<option value='"+data.resultData[i].id+"'>"+data.resultData[i].name+"</option>";
			}
			$("#cityId").html(option);
			$("#cityId").select2();
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("error : provinceChange");
			console.log(XMLHttpRequest+","+textStatus+","+errorThrown);
        }
	});
}

// 城市选择器ajax
function cityChange(){
	var cid = $("#cityId").val();
	$.ajax( {
		url : ctx+"/ajax/sysDistrictAjaxController/findList",
		data : {cityId:cid},
		type : "post",
		dataType : "json",
		success : function(data) {
			var option = "<option value='' selected='selected'></option>";
			for(var i=0;i<data.resultData.length;i++){
				option += "<option value='"+data.resultData[i].id+"'>"+data.resultData[i].name+"</option>";
			}
			$("#districtId").html(option);
			$("#districtId").select2();
		},error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("error : cityChange");
			console.log(XMLHttpRequest+","+textStatus+","+errorThrown);
        }
	});
}

// 系统分页组件点击事件
function pageClick(first){
	var firstInput = "<input type='hidden' name='first' value='"+first+"'/>";
	$("#searchForm").prepend(firstInput);
	$("#searchForm").submit();
}

//判断一个字符串是否为小数
function isDouble(s) {
	if(s.split(".").length != 2){
		return false;
	}
	var reg = new RegExp("\\d+\\.\\d+$|-\\d+\\.\\d+$");
	return reg.test(s);
}