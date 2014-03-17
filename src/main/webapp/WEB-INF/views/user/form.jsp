<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Show Board</title>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<script>
		$(document).ready(function(){
			
			$("#account-tab").addClass("active");
			//register loginName check
			$("#inputForm").validate({
				rules:{
					loginName:{
						remote:"${ctx}/user/checkLoginName?oldLoginName=${user.loginName}"
					}
				},
				messages:{
					loginName: {
						remote: "The user name exists!"
					}
				}
			});		
		});
	</script>
</head>

<body>
	<h1>Show Board</h1>
	<form id="inputForm" action="${ctx}/user/update" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${user.id}"/>
		<fieldset>
			<legend><small>User Managerment</small></legend>
			<div id="messageBox" class="alert alert-error input-large controls" style="display:none">Error,please try a again</div>
			<div class="control-group">
				<label for="loginName" class="control-label">Username:</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" value="${user.loginName}" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="name" class="control-label">Name:</label>
				<div class="controls">
					<input type="text" id="name" name="name"  value="${user.name}" class="input-large required"/>
				</div>
			</div>
			<div class="control-group">
				<label for="plainPassword" class="control-label">Password:</label>
				<div class="controls">
					<input type="password" id="plainPassword" name="plainPassword" class="input-large" placeholder="...Leave it blank if no change"/>
				</div>
			</div>
			<div class="control-group">
				<label for="groupList" class="control-label">Role:</label>
				<div class="controls">
					
				</div>
			</div>	
			<div class="control-group">
				<label for="status" class="control-label">Status:</label>
				<div class="controls">
				</div>
			</div>
			<div class="control-group">
				<label for="email" class="control-label">Email:</label>
				<div class="controls">
				    <input type="text" id="email" name="email"  value="${user.email}" class="input-large required"/>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
				<p class="help-block">(保存后将发送JMS消息通知改动，而消息接收者将发送提醒邮件)</p>			
			</div>
		</fieldset>
	</form>
</body>
</html>
