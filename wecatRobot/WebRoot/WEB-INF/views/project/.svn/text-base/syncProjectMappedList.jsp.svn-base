<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<title>列表</title>
	    <%@ include file="/WEB-INF/views/include/style.jsp"%>
  	</head>
	<body>
    	<ul class="nav nav-tabs sys-font">
			<li class="active"><a href="${ctx }/sys/syncProjectMappedWebController/list">列表</a></li>
			<li><a href="${ctx }/sys/syncProjectMappedWebController/form">添加</a></li>
		</ul>
		<%-- <form:form id="searchForm" modelAttribute="syncProjectMapped" action="${ctx }/sys/syncProjectMappedWebController/list" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<li><label>jira项目ID：</label>
					<form:input path="projectId" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>jira项目名称：</label>
					<form:input path="projectName" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>映射值：</label>
					<form:input path="mappedValue" htmlEscape="false" maxlength="255" class="input-medium"/>
				</li>
				<li><label>创建时间：</label>
					<input name="createDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncProjectMapped.createDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
					<input name="createDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncProjectMapped.createDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</li>
				<li><label>更新时间：</label>
					<input name="updateDateStart" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncProjectMapped.updateDateStart}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
					<input name="updateDateEnd" type="text" readonly="true" maxlength="20" class="input-medium Wdate" value="<fmt:formatDate value="${syncProjectMapped.updateDateEnd}" pattern="yyyy-MM-dd HH:mm:ss"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
				<li class="clear"></li>
			</ul>
		</form:form> --%>
		<sys:message content="${message }"/>
    	<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
		<thead class="sys-font">
			<tr>
				<th>jira项目ID</th>
				<th>jira项目名称</th>
				<th>映射值(参数值)</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity">
			<tr>
				<td>${entity.projectId }</td>
				<td>${entity.projectName }</td>
				<td>${entity.mappedValue }</td>
				<td><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
    				<a href="${ctx }/sys/syncProjectMappedWebController/form?id=${entity.id }">修改</a>
					<a href="${ctx }/sys/syncProjectMappedWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<sys:page page="${page }"/>
  	</body>
</html>
