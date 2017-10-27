<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/views/include/style.jsp"%>
	<script src="${ctxStatic }/Jcrop/js/jquery.Jcrop.min.js"></script>
	<link rel="stylesheet" href="${ctxStatic }/Jcrop/css/jquery.Jcrop.min.css" type="text/css" />
	<style>
	body{background-color:#E0E0E0;}
	#btn-upload{margin-left:36px;}
	.ul-img{margin-top:10px;}
	.li-img{list-style-type:none;float:left;margin-left:10px;margin-top:10px;}
	.div-img-wrap{width:120px;height:165px;background-color:#FFFFFF;display:inline-block;cursor:pointer;position:relative;}
	.img-select{width:100px;height:100px;margin-left:10px;margin-top:10px;}
	.div-img-size,.div-img-time{font-size:5px;text-align:center;}
	.div-img-size{margin-top:10px;}
	.input-img{display:none;}
	.form-upload{margin-bottom:0;}
	.div-loading{position:absolute;top:0;left:0;width:100%;height:100%;text-align:center;background:#FFFFFF;}
	.img-loading{display:inline-block;margin-top:150px;}
	.a-delete-img{position:absolute;top:0;right:0;}
	.inuse-wrap,#btn-confirm,#btn-return{display:none;}
	#div-preview-wrap {
	  display: block;
	  position: absolute;
	  z-index: 2000;
	  top: 10px;
	  right: -280px;
	  padding: 6px;
	  border: 1px rgba(0,0,0,.4) solid;
	  background-color: white;
	  -webkit-border-radius: 6px;
	  -moz-border-radius: 6px;
	  border-radius: 6px;
	  -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	  -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	  box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	}
	#div-preview-wrap,.div-preview{
		width: 250px;
	  	height: 170px;
	  	overflow: hidden;
	}
	</style>
</head>
<body>
	<input id="btn-upload" type="button" value="上传" class="btn btn-success btn-small"/>
	<input id="btn-upload-inuse" type="button" value="上传并使用" class="btn btn-success btn-small"/>
	<input id="btn-confirm" type="button" value="确定上传" class="btn btn-success btn-small"/>
	<form id="form-upload" action="${ctx }/sys/sysCommonWebController/uploadImg" method="post" enctype="multipart/form-data" class="form-upload">
		<input id="inputName" type="hidden" name="inputName"/>
		<input id="multiSelect" type="hidden" name="multiSelect"/>
		<input id="input-file" type="file" name="img" class="input-img"/>
		<input id="inuse" type="hidden" name="inuse"/>
		<input id="jcrop-x1" type="hidden" name="x1"/>
		<input id="jcrop-y1" type="hidden" name="y1"/>
		<input id="jcrop-x2" type="hidden" name="x2"/>
		<input id="jcrop-y2" type="hidden" name="y2"/>
		<input id="jcrop-width" type="hidden" name="width"/>
		<input id="jcrop-height" type="hidden" name="height"/>
		<input id="jcrop-max-width" type="hidden" name="maxWidth"/>
		<input id="jcrop-max-height" type="hidden" name="maxHeight"/>
	</form>
	<ul class="ul-img">
		<c:forEach items="${imgList }" var="img">
			<li class="li-img">
				<div class="div-img-wrap">
					<img src="${img.filePath }" class="img-select"/>
					<div class="div-img-size">${img.fileSize }</div>
					<div class="div-img-time"><fmt:formatDate value="${img.uploadTime }" pattern="yyyy-MM-dd"/></div>
					<a href="javascript:;" class="a-delete-img">×</a>
				</div>
			</li>
		</c:forEach>
	</ul>
	<br>
	<div class="inuse-wrap">
		<div id="div-img-origin">
			<img id="img-origin" src="${ctxStatic }/Jcrop/1.jpg"/>
		</div>
		<div id="div-preview-wrap">
		<div class="div-preview">
			<img src="${ctxStatic }/Jcrop/1.jpg"/>
		</div>
	</div>
	</div>
	<div class="div-loading">
		<img class="img-loading" src="${ctx }/static/sys/img/loading-1.gif"/>
	</div>
	<script type="text/javascript">
	window.onload=function(){
		$(".div-loading").hide();
		var uploadImg = getUrlParam("uploadImg");
		if(notBlank(uploadImg)){
			var inputObj = window.opener.document.getElementById(getUrlParam("inputName"));
			inputObj.value = uploadImg;
			$(inputObj).siblings(".div-sys-img-select-append").remove();
			var appendStr = "";
			appendStr += "<div class='div-sys-img-select-append' style='position:relative;width:100px;height:100px;display:inline-block;margin-left:10px;'>";
			appendStr += "<img class='img-sys-img-select-append' src='http://localhost:8080"+uploadImg+"' style='width:100%;height:100%;'/>";
			appendStr += "<a title='"+getUrlParam("inputName")+"' class='a-sys-img-select-append' href='javascript:;' style='position:absolute;top:0;right:0;'>×</a>";
			appendStr += "</div>";
			$(inputObj).after(appendStr);
			window.close();
		}
	};
	
	var inuse = false;
	var jcrop_api,
    boundx,
    boundy,
    $preview = $("#div-preview-wrap"),
    $pimg = $(".div-preview img"),
    xsize = $preview.width(),
    ysize = $preview.height();
	$(document).ready(function(){
		$(".a-delete-img").click(function(){
			var filePath = $(this).parent().children(".img-select").attr("src");
			if(confirm('您确定要删除该图片吗？')){
				window.location.href = "${ctx }/sys/sysCommonWebController/deleteImg?filePath="+filePath+"&inputName="+getUrlParam("inputName")+"&multiSelect="+getUrlParam("multiSelect");
			}
		});
		$(".div-img-wrap").dblclick(function(){
			var path = $(this).children(".img-select").attr("src");
			var inputObj = window.opener.document.getElementById(getUrlParam("inputName"));
			var multiSelect = getUrlParam("multiSelect");
			if(multiSelect == "true"){
				var oldValue = inputObj.value;
				oldValue = oldValue.replace(path+",",'');
				newValue = oldValue+path+",";
				inputObj.value = newValue;
			}else{
				inputObj.value = path;
			}
			$(inputObj).siblings(".div-sys-img-select-append").remove();
			var pathArr = inputObj.value.split(",");
			for(var i=0; i<pathArr.length; i++){
				if(notBlank(pathArr[i])){
					var appendStr = "";
					appendStr += "<div class='div-sys-img-select-append' style='position:relative;width:100px;height:100px;display:inline-block;margin-left:10px;'>";
					appendStr += "<img class='img-sys-img-select-append' src='"+pathArr[i]+"' style='width:100%;height:100%;'/>";
					appendStr += "<a title='"+getUrlParam("inputName")+"' class='a-sys-img-select-append' href='javascript:;' style='position:absolute;top:0;right:0;'>×</a>";
					appendStr += "</div>";
					$(inputObj).after(appendStr);
				}
			}
			window.close();
		});
		$("#btn-upload").click(function(){
			inuse = false;
			return $("#input-file").click();
		});
		$("#btn-upload-inuse").click(function(){
			inuse = true;
			$("#inuse").val("1");
			return $("#input-file").click();
		});
		$("#input-file").change(function(){
			var value = $(this).val();
			if(notBlank(value)){
				var inputName = getUrlParam("inputName");
				var multiSelect = getUrlParam("multiSelect");
				if(notBlank(inputName)){
					$("#inputName").val(inputName);
				}
				if(notBlank(multiSelect)){
					$("#multiSelect").val(multiSelect);
				}
				if(!inuse){
					$("#form-upload").submit();
				}else{
					$(".ul-img").hide();
					$(".inuse-wrap").show();
					$("#btn-upload").hide();
					$("#btn-upload-inuse").hide();
					$("#btn-confirm").show();
					setImagePreview();
				}
			}
		});
		$("#btn-confirm").click(function(){
			var w = $(".jcrop-holder>div:first").width();
			var h = $(".jcrop-holder>div:first").height();
			if(w < 1  || h < 1){
				$("#jcrop-x1").val("");
				$("#jcrop-y1").val("");
				$("#jcrop-x2").val("");
				$("#jcrop-y2").val("");
				$("#jcrop-width").val("");
				$("#jcrop-height").val("");
				//var maxWidth = $(".jcrop-holder").width();
				//var maxHeight = $(".jcrop-holder").height();
			}
			$("#jcrop-max-width").val($(".jcrop-holder").width());
			$("#jcrop-max-height").val($(".jcrop-holder").height());
			$("#form-upload").submit();
		});
		
	});
	
	//图片上传预览功能
	function setImagePreview(avalue) {
		var docObj = document.getElementById("input-file");
		var imgObjPreview = document.getElementById("img-origin");
		if(docObj.files &&docObj.files[0]){
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
			$("#img-origin").attr("src",imgObjPreview.src);
			$(".div-preview img").attr("src",imgObjPreview.src);
			$(".jcrop-holder img").attr("src",imgObjPreview.src);
			$("#img-origin").Jcrop({
		      	onChange: updatePreview,
		      	onSelect: updatePreview,
		      	bgColor:'#E0E0E0',
		      	aspectRatio: xsize / ysize
	    	},function(){
		      	var bounds = this.getBounds();
		      	boundx = bounds[0];
		      	boundy = bounds[1];
		      	jcrop_api = this;
	      		$preview.appendTo(jcrop_api.ui.holder);
	    	});
		}
		return true;
	}
	// 更新截图预览
	function updatePreview(c){
		//console.log("x1:"+c.x+",y1:"+c.y+",x2:"+c.x2+",y2:"+c.y2+",width:"+c.w+",height:"+c.h);
		$("#jcrop-x1").val(c.x);
		$("#jcrop-y1").val(c.y);
		$("#jcrop-x2").val(c.x2);
		$("#jcrop-y2").val(c.y2);
		$("#jcrop-width").val(c.w);
		$("#jcrop-height").val(c.h);
  		if (parseInt(c.w) > 0){
        	var rx = xsize / c.w;
        	var ry = ysize / c.h;
       		$pimg.css({
          		width: Math.round(rx * boundx) + 'px',
          		height: Math.round(ry * boundy) + 'px',
          		marginLeft: '-' + Math.round(rx * c.x) + 'px',
          		marginTop: '-' + Math.round(ry * c.y) + 'px'
        	});
  		}
	}
	</script>
</body>
</html>