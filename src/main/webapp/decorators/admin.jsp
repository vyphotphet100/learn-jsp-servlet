<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><dec:title default="Dashboard - Ace Admin" /></title>

<link rel="stylesheet"
	href="<c:url value='/template/admin/css/bootstrap.min.css' />" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/font-awesome.min.css' />" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/fonts.googleapis.com.css' />" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/ace.min.css' />"
	class="ace-main-stylesheet" id="main-ace-style" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/ace-skins.min.css' />" />
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/ace-rtl.min.css' />" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript"
	src="<c:url value='/template/admin/js/jquery-2.1.4.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/template/admin/js/ace-extra.min.js' />"></script>
<script type="text/javascript"
	src="<c:url value='/template/admin/js/admin.js' />"></script>


<script type="text/javascript"
	src="<c:url value='/template/admin/js/jquery-2.1.4.min.js' />"></script>
<script type="text/javascript">
	if ('ontouchstart' in document.documentElement)
		document
				.write("<script src='/template/admin/js/jquery.mobile.custom.min.js'>"
						+ "<"+"/script>");
</script>
<script src="<c:url value='/template/admin/js/bootstrap.min.js' />"></script>
<script
	src="<c:url value='/template/admin/js/jquery-ui.custom.min.js' />"></script>
<script
	src="<c:url value='/template/admin/js/jquery.ui.touch-punch.min.js' />"></script>
<script
	src="<c:url value='/template/admin/js/jquery.easypiechart.min.js' />"></script>
<script
	src="<c:url value='/template/admin/js/jquery.sparkline.index.min.js' />"></script>
<script src="<c:url value='/template/admin/js/jquery.flot.min.js' />"></script>
<script
	src="<c:url value='/template/admin/js/jquery.flot.pie.min.js' />"></script>
<script
	src="<c:url value='/template/admin/js/jquery.flot.resize.min.js' />"></script>

<script src="<c:url value='/template/admin/js/ace-elements.min.js' />"></script>
<script src="<c:url value='/template/admin/js/ace.min.js' />"></script>
<script
	src="<c:url value='https://code.jquery.com/jquery-3.3.1.min.js' />"></script>
</head>


<body class="no-skin">
	<!-- header -->
	<%@include file="/common/admin/header.jsp"%>
	<!-- header -->

	<div class="container">
		<dec:body />
	</div>

	<!-- footer -->
	<%@include file="/common/admin/footer.jsp"%>
	<!-- footer -->

	<script type="text/javascript"
		src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>

</body>
</html>