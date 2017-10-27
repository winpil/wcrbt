<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
    	<script type="text/javascript">
    	$(function(){ 
    		$("#projectId").change(function(){
    		  var projectId=$("#projectId").val();
    		  $.post("../../api/jira/getType",{"projectId":projectId},function(result){
    			  if(result.resultMessage){
    				  var data = result.resultData;
    				  $("#typeSelect option").remove();
    				  for(var i=0;i<data.length;i++){
    					  $("#typeSelect").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    				  }
    			  }
    		  });
    		});
    		$("#typeSelect").change(function(){
      		  $("#typeName").val($("#typeSelect").find("option:selected").text());
      		});
    	}); 
    	
  		</script>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="${ctx }/sys/syncTypeMappedWebController/list">列表</a></li>
			<li class="active"><a href="${ctx }/sys/syncTypeMappedWebController/form">添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/${projectName}/sys/syncTypeMappedWebController/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<c:choose>
						<c:when test="${empty entity.id}">
							<form:hidden path="typeName" id="typeName"/>
						</c:when>
						<c:otherwise>
							<form:hidden path="typeId"/>
							<form:hidden path="projectId"/>
						</c:otherwise>
					</c:choose>
			<div class="control-group">
				<label class="control-label">项目：</label>
				<div class="controls">
					<c:choose>
						<c:when test="${empty entity.id}">
							<form:select path="projectId" class="input-medium required" id="projectId">
								<form:option value="" label=""/>
								<c:forEach items="${projects }" var="project">
									<form:option value="${project.projectId }" label="${project.projectName }"/>
								</c:forEach>
							</form:select>
						</c:when>
						<c:otherwise>
							<form:select path="projectId" class="input-medium required" disabled="true">
								<form:option value="" label=""/>
								<c:forEach items="${projects }" var="project">
									<form:option value="${project.projectId }" label="${project.projectName }"/>
								</c:forEach>
							</form:select>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">问题类型：</label>
				<div class="controls">
					<c:choose>
						<c:when test="${empty entity.id}">
							<form:select path="typeId" class="input-medium required" id="typeSelect">
								<form:option value="" label=""/>
							</form:select>
						</c:when>
						<c:otherwise>
							<form:input path="typeName" htmlEscape="false" class="input-xlarge required" disabled="true"/>
						</c:otherwise>
					</c:choose>
				</div>
				
			</div>
			<div class="control-group">
				<label class="control-label">映射值(参数值)：</label>
				<div class="controls">
					<form:input path="mappedValue" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
