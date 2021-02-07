<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Home of Admin page</title>
</head>
<body>
	<h1>Xin chào, đây là trang chủ của trang admin</h1>
	<a href="<c:url value='/admin-new?page=1&nItemPerPage=2&sortName=id&sortBy=desc&type=list' />"> <i
		class="menu-icon fa fa-caret-right"></i> DS bài viết
	</a>
	<b class="arrow"></b>
</body>
</html>