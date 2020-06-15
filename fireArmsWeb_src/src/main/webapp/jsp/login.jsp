<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<%
	String context = request.getContextPath();
%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CNSI Blockchain Interface Login</title>

<link href="<%=context%>/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=context%>/resources/bootstrap/css/login.css"
	rel="stylesheet">
</head>
<body id="login" class="text-center"
	style="background-image: url(<%=context%>/resources/images/blockchain1.jpeg);">
	<form class="form-signin" action="validate" method="post"
		autocomplete="off">
		<img class="mb-4" src="<%=context%>/resources/images/logo.png" alt=""
			style="width: 100px; margin-right: 10px;">
		<h1 class="h3 mb-3 font-weight-normal">FireArms Tracking Interface Login</h1>

		<label for="username" class="sr-only">Username</label> <input
			type="text" id="username" class="form-control" placeholder="Username"
			name="p_sUsername" required autofocus> <br> <label
			for="inputPassword" class="sr-only">Password</label> <input
			type="password" id="inputPassword" class="form-control mb-3"
			placeholder="Password" name="p_sPassword" autocomplete="new-password"
			required> <br>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
			in</button>
		<br>
		<p class="mt-5 mb-3 text-muted">&copy; UFL 2020-2021</p>
	</form>
</body>
</html>