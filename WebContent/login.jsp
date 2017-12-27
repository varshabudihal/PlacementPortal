<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>Login Page</title>

<script src="js/jquery.js"></script>
<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.css" rel="stylesheet" />
<!-- Custom styles -->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-img3-body">

	<div class="container">
		<div class="login-wrap">
			<form class="login-form" action="LoginServlet" method="post">

				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>

				<div class="input-group">
					<span class="input-group-addon"><i class="icon_profile"></i></span>
					<h5><b>Enter Name</b></h5>
					<input type="email" name="emailLogin" id="emailLogin" class="form-control" placeholder="Username" required autofocus>
				</div>

				<div class="input-group">
					<span class="input-group-addon"><i class="icon_key_alt"></i></span>
					<h5><b>Enter password</b></h5>
					<input type="password" name="passwordLogin" class="form-control" placeholder="Password" required >
				</div>

				
				<div align="center">
					<h5><b> <input type="checkbox" name="AdminLogin" value="true"> Login as a Faculty member </b></h5>
				</div>

				<button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
				<a href="registration.jsp"><input type="button" value="New User" class="btn btn-info btn-lg btn-block"></a>
			</form>
		</div>
	</div>
</body>
</html>
