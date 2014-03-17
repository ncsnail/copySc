<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException "%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Sign in</title>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<h2>Please sign in</h2>
	<form id="loginForm" action="${ctx}/login" method="post">
		<%
			String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			if(error != null){
		%>
				<div class="alert alert-error controls input-large">
					<button class="close" data-dismiss="alert">×</button>
		<%
				if(error.contains("DisabledAccountException")){
					out.print("Disabled user.");
				}
				else{
					out.print("Incorrect username or password.");
				}
		%>		
				</div>
		<%
			}
		%>
		<div class="control-group">
			<label for="username" class="control-label">Username:</label>
			<div class="controls">
				<input type="text" id="username" name="username" value="${username}" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">password:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<label class="checkbox inline" for="rememberMe"> <input type="checkbox" id="rememberMe" name="rememberMe"/>Remember me</label>
				<input id="submit_btn" class="btn" type="submit" value="Sign in"/>
				<p class="help-block">(Administrator：<b>admin/admin</b>, User：<b>user/user</b>)</p>
			</div>
		</div>
	</form>
	
	<script>
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
