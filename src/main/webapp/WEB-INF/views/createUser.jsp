<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
</head>
<body>
	<div class="container-fluid">
		<form action="/admin/product/editPro" method="POST"
			enctype="multipart/form-data">
			<form:form enctype="multipart/form-data"
				action="/admin/user/createUser" modelAttribute="user" method="post">
				<form:input type="hidden" path="username" />

				<div class="form-group">
					<label for="username">User Name</label>
					<form:input type="text" class="form-control" path="username" />
				</div>

				<div class="form-group">
					<label for=password>Password</label>
					<form:input type="text" class="form-control" path="password" />
				</div>

				<div class="form-group">
					<label for="email">Email</label>
					<form:input type="email" class="form-control" path="email" />
				</div>

				<div class="form-group">
					<label for="phone">Phone</label>
					<form:input type="text" class="form-control" path="phone" />
				</div>

				<div class="form-group">
					<label for="gender">Gender</label>
					<form:input type="text" class="form-control" path="gender" />
				</div>

				<div class="form-group">
					<label for="balance">Balance</label>
					<form:input type="text" class="form-control" path="balance" />
				</div>



				<div class="form-group">
					<label for="address">Address</label>
					<form:input type="text" class="form-control" path="address" />
				</div>
				<button type="submit" class="btn btn-primary">Finish</button>	
				<a href="/admin/user" class="btn btn-danger">Cancel</a>
			</form:form>
		</form>
	</div>

</body>