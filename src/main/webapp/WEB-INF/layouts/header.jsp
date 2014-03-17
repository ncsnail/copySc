<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div id="header" class="row">
	<div><h1>Wind-K &nbsp;<small>--Technology</small></h1></div>
	<div class="pull-right">
		<shiro:guest><a href="${ctx}/login">Sign in</a></shiro:guest>
		<shiro:user>Hi, <shiro:principal property="name"/>&nbsp;|&nbsp;<a href="${ctx}/logout">Sign out</a></shiro:user>
	</div>
</div>