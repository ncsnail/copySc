<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>User Manager</title>
	<script>
		$(document).ready(function() {
			//mark the item in the menu
			$("#account-tab").addClass("active");
		});
	</script>
</head>

<body>
	<h1>User Managerment</h1>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<div class="row">
		<div class="offset4">
			<form class="form-search" action="#">
			 	<label>User name：</label> <input type="text" name="loginName"   class="input-small"  value="${search.loginName}"> 
			    <label>Email：</label> <input type="text" name="email" class="input-small" value="${search.email}">
			    <button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	</div>	
			
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>Username</th>
			<th>Name</th>
			<th>Email</th>
			<th>Role</th>
			<th>Status</th>
			<th>Operation</th>
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
				<a href="${ctx}/user/update/${user.id}" id="editLink-${user.loginName}">Edit</a>
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
