<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Register</title>

<!-- Custom fonts for this template-->
<link href="/resources/static/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/resources/static/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
							</div>
							<c:if test="${message!=null }">
								<div class="form-group">
									<label class="form-check-label" for="inlineRadio1">
										${message }
									</label>
								</div>
							</c:if>
							<form action="/register" class="user" method="POST">
								<div class="form-group">
									<input required name="phone" type="number"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Phone Number">
								</div>
								<div class="form-group">
									<input required name="email" type="email"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Email">
								</div>
								<div class="form-group">
									<input required name="fullname" type="text"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Full Name">
								</div>
								<div class="form-group">
									<input required name="password" type="password"
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Password">
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="gender" id="inlineRadio1" value="male" required>
									<label class="form-check-label" for="inlineRadio1">Male</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio"
										name="gender" id="inlineRadio2" value="female" required>
									<label class="form-check-label" for="inlineRadio2">Female</label>
								</div>
								<button type="submit" class="btn btn-primary btn-user btn-block">
									Register Account</button>
								<hr>
								<!-- <a href="" class="btn btn-google btn-user btn-block"> <i
									class="fab fa-google fa-fw"></i> Register with Google
								</a> <a href="" class="btn btn-facebook btn-user btn-block"> <i
									class="fab fa-facebook-f fa-fw"></i> Register with Facebook
								</a> -->
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="forgot-password.html">Forgot
									Password?</a>
							</div>
							<div class="text-center">
								<a class="small" href="/login">Already have an account?
									Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="/resources/static/vendor/jquery/jquery.min.js"></script>
	<script
		src="/resources/static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="/resources/static/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/resources/static/js/sb-admin-2.min.js"></script>

</body>

</html>
