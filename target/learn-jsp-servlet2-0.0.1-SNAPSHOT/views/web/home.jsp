<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1>Xin chào, đây là trang chủ của webpage</h1>
<form method="post"
	action="${pageContext.request.contextPath}/login-process">
	Name:<input type="text" name="user" /><br /> Password:<input
		type="password" name="pass"><br /> <input type="submit"
		value="submit">
</form>