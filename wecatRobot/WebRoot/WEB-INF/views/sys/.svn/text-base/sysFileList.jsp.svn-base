<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/WEB-INF/views/include/style.jsp"%>
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
	</style>
</head>
<body>
	<input id="btn-upload" type="button" value="上传" class="btn btn-success btn-small"/>
	<form id="form-upload" action="${ctx }/sys/sysCommonWebController/uploadFile" method="post" enctype="multipart/form-data" class="form-upload">
		<input id="inputName" type="hidden" name="inputName"/>
		<input id="input-file" type="file" name="file" class="input-img"/>
	</form>
	<ul class="ul-img">
		<c:forEach items="${fileList }" var="f">
			<li class="li-img">
				<div class="div-img-wrap">
					<img title="${f.filePath }" alt="${f.fileName }" src="${ctxStatic }/sys/img/sys_file_logo.png" class="img-select"/>
					<div class="div-img-size">${f.fileSize }</div>
					<div class="div-img-time">${f.fileName }</div>
					<a href="javascript:;" class="a-delete-img">×</a>
				</div>
			</li>
		</c:forEach>
	</ul>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(".a-delete-img").click(function(){
			var filePath = $(this).parent().children(".img-select").attr("alt");
			if(confirm('您确定要删除该文件吗？')){
				window.location.href = "${ctx }/sys/sysCommonWebController/deleteFile?filePath="+filePath+"&inputName="+getUrlParam("inputName");
			}
		});
		$(".div-img-wrap").dblclick(function(){
			var path = $(this).children(".img-select").attr("title");
			var inputObj = window.opener.document.getElementById(getUrlParam("inputName"));
			inputObj.value = path;
			window.close();
		});
		$("#btn-upload").click(function(){
			return $("#input-file").click();
		});
		$("#input-file").change(function(){
			var value = $(this).val();
			if(notBlank(value)){
				var inputName = getUrlParam("inputName");
				if(notBlank(inputName)){
					$("#inputName").val(inputName);
				}
				$("#form-upload").submit();
			}
		});
	});
	</script>
</body>
</html>