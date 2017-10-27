<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
  	<head>
  		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    	<title>主键添加</title>
    	<%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
  	<body>
  		<ul class="nav nav-tabs sys-font">
			<li><a href="${ctx }/sys/syncFieldMappedWebController/list">主键列表</a></li>
			<li class="active"><a href="${ctx }/sys/syncFieldMappedWebController/form">主键添加</a></li>
		</ul>
		<sys:message content="${message }"/>
  		<form:form id="inputForm" modelAttribute="entity" action="/${projectName}/sys/syncFieldMappedWebController/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<div class="control-group">
				<label class="control-label">字段Id：</label>
				<div class="controls">
					<form:input path="fieldId" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">字段名称：</label>
				<div class="controls">
					<form:input path="fieldName" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">字段类型：</label>
				<div class="controls">
					<form:input path="fieldType" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">字段类型名称：</label>
				<div class="controls">
					<form:input path="fieldTypeName" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">映射值：</label>
				<div class="controls">
					<form:input path="mappedValue" htmlEscape="false" class="input-xlarge required"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">：</label>
				<div class="controls">
					<form:input path="customType" htmlEscape="false" class="input-xlarge "/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">：</label>
				<div class="controls">
					<input name="createTime" type="text" maxlength="20" class="input-medium Wdate " readOnly="true" value="<fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">：</label>
				<div class="controls">
					<input name="updateTime" type="text" maxlength="20" class="input-medium Wdate " readOnly="true" value="<fmt:formatDate value="${entity.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="btnSubmit" class="btn btn-info" type="submit" value="保 存"/>&nbsp;
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			</div>
		</form:form>
  	</body>
</html>
