<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>User manager</title>
	<script>
		$(document).ready(function() {
			//选中左侧菜单
			$("#account-tab").addClass("active");
		});
	</script>
</head>

<body>
	<h1>帐号管理</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<div class="row">
		<div class="offset4">
			<form class="form-search" action="#">
			 	<label>登录名：</label> <input type="text" name="loginName"   class="input-small"  value="${search.loginName}"> 
			    <label>邮件名：</label> <input type="text" name="email" class="input-small" value="${search.email}">
			    <button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	</div>	
			
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>登录名</th>
			<th>姓名</th>
			<th>电邮</th>
			<th>角色</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${users.content}" var="user">
			<tr>
				<td>${user.loginName}&nbsp;</td>
				<td>${user.name}&nbsp;</td>
				<td>${user.email}&nbsp;</td>
				<td>${user.roleNames}&nbsp;</td>
				<td>${allStatus[user.status]}&nbsp;</td>
				<td>
				<a href="${ctx}/user/update/${user.id}" id="editLink-${user.loginName}">修改</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>	
		<c:if test="${not empty users}">
		<tags:page page="${users}" paginationSize="5"/>
		</c:if>	
	</table>
</body>
</html>
