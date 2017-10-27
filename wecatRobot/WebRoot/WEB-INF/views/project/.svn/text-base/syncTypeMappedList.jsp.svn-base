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
			<li class="active"><a href="${ctx }/sys/syncTypeMappedWebController/list">列表</a></li>
			<li><a href="${ctx }/sys/syncTypeMappedWebController/form">添加</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="syncTypeMapped" action="${ctx }/sys/syncTypeMappedWebController/list" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
				<li><label>项目：</label>
					<form:select path="projectId" class="input-medium required" id="projectId">
								<form:option value="" label="请选择..."/>
								<c:forEach items="${projects }" var="project">
									<form:option value="${project.projectId }" label="${project.projectName }"/>
								</c:forEach>
					</form:select>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-info" type="submit" value="查询"/></li>
				<li class="clear"></li>
			</ul>
		</form:form>
		<sys:message content="${message }"/>
    	<table id="contentTable" class="table table-hover table-striped table-bordered table-condensed">
		<thead class="sys-font">
			<tr>
				<th>项目</th>
				<th>类型名称</th>
				<th>映射值(参数值)</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity">
			<tr>
				<td>${entity.projectName }</td>
				<td>${entity.typeName }</td>
				<td>${entity.mappedValue }</td>
				<td><fmt:formatDate value="${entity.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${entity.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
    				<a href="${ctx }/sys/syncTypeMappedWebController/form?id=${entity.id }">修改</a>
					<a href="${ctx }/sys/syncTypeMappedWebController/delete?id=${entity.id }" onclick="return confirm('您确定要删除该项吗？')">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<sys:page page="${page }"/>
  	</body>
</html>
