<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>

	<div id="login-row"
		class="row justify-content-center align-items-center">
		<div id="login-column" class="col-md-6">
			<div id="login-box" class="col-md-12">
				<form id="login-form" class="form"
					action="<c:url value='/dang-nhap' />" method="post">
					<h3 class="text-center text-info">Login</h3>
					<div class="form-group">
						<label for="username" class="text-info">Username:</label><br>
						<input type="text" name="userName" id="username"
							class="form-control">
					</div>
					<div class="form-group">
						<label for="password" class="text-info">Password:</label><br>
						<input type="password" name="password" id="password"
							class="form-control"> <input type="hidden" id="action"
							name="action" value="login"> <input type="hidden"
							id="status" name="status" value="1">

					</div>
					<div class="form-group">
						<input type="submit" name="submit" class="btn btn-info btn-md"
							value="Submit">
					</div>
				</form>
			</div>
			<c:if test="${not empty message}">
				<div class="alert alert-${alert}">${message}</div>
			</c:if>

		</div>
	</div>

</body>
</html>