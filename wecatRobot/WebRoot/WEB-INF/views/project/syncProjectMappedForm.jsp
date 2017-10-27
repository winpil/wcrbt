<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="${ctx }/sys/syncProjectMappedWebController/list">列表</a></li>
			<li class="active"><a href="${ctx }/sys/syncProjectMappedWebController/form">添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/${projectName}/sys/syncProjectMappedWebController/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<form:hidden path="projectName"/>
			<div class="control-group">
				<label class="control-label">项目：</label>
				<div class="controls">
					<c:choose>
						<c:when test="${empty entity.id}">
							<form:select path="projectId" class="input-medium required">
								<form:option value="" label=""/>
								<c:forEach items="${projects }" var="project">
									<form:option value="${project.id }" label="${project.name }"/>
								</c:forEach>
							</form:select>
						</c:when>
						<c:otherwise>
							<form:select path="projectId" class="input-medium required"  disabled="true">
								<form:option value="" label=""/>
								<c:forEach items="${projects }" var="project">
									<form:option value="${project.id }" label="${project.name }"/>
								</c:forEach>
							</form:select>
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
