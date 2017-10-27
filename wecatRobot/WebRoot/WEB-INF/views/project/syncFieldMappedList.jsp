<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<title>主键列表</title>
	    <%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
	<body>
    	<ul class="nav nav-tabs sys-font">
			<li class="active"><a href="${ctx }/sys/syncFieldMappedWebController/list">主键列表</a></li>
			<li><a href="${ctx }/sys/syncFieldMappedWebController/form">主键添加</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="syncFieldMapped" action="${ctx }/sys/syncFieldMappedWebController/list" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<li><label>字段Id：</label>
					<form:input path="fieldId" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>字段名称：</label>
					<form:input path="fieldName" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>字段类型：</label>
					<form:input path="fieldType" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>字段类型名称：</label>
					<form:input path="fieldTypeName" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>映射值：</label>
					<form:input path="mappedValue" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>：</label>
					<form:input path="customType" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>：</label>
					<form:input path="createTime" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>：</label>
					<form:input path="updateTime" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>创建时间：</label>
					<input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncFieldMapped.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
					<input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncFieldMapped.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</li>
				<li><label>更新时间：</label>
					<input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncFieldMapped.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
					<input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncFieldMapped.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
				<li class="clear"></li>
			</ul>
		</form:form>
		<sys:message content="${message }"/>
    	<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
		<thead class="sys-font">
			<tr>
				<th>字段Id</th>
				<th>字段名称</th>
				<th>字段类型</th>
				<th>字段类型名称</th>
				<th>映射值</th>
				<th></th>
				<th></th>
				<th></th>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity">
			<tr>
				<td>${entity.fieldId }</td>
				<td>${entity.fieldName }</td>
				<td>${entity.fieldType }</td>
				<td>${entity.fieldTypeName }</td>
				<td>${entity.mappedValue }</td>
				<td>${entity.customType }</td>
				<td><fmt:formatDate value="${entity.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${entity.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
    				<a href="${ctx }/sys/syncFieldMappedWebController/form?id=${entity.id }">修改</a>
					<a href="${ctx }/sys/syncFieldMappedWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<sys:page page="${page }"/>
  	</body>
</html>
