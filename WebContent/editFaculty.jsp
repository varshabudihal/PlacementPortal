<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>Edit profile</title>

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
			<form class="login-form" action="FinalizeFacultyEditServlet" method="post">

				<p class="login-img">
					<i class="icon_lock_alt"></i>
				</p>

				${firstNameRegister}

				<div class="input-group">
					<span class="input-group-addon"> <i class="icon_profile"></i></span>
					<h5><b> First name</b></h5>
					<input type="text" value="${firstNameRegister}" name="firstNameRegister" class="form-control" placeholder="First name" autofocus required>
				</div>

				<div class="input-group">
					<span class="input-group-addon"> <i class="icon_profile"></i></span>
					<h5><b>Middle name</b></h5>
					<input type="text" value="${middleNameRegister}" name="middleNameRegister" class="form-control" placeholder="Middle name" required>
				</div>

				<div class="input-group">
					<span class="input-group-addon"> <i class="icon_profile"></i></span>
					<h5><b>Last name</b></h5>
					<input type="text" value="${lastNameRegister}" name="lastNameRegister" class="form-control" placeholder="Last name" required>
				</div>
				
				<div class="input-group">
					<span class="input-group-addon"> <i class="icon_profile"></i></span>
					<h5><b>Email</b></h5>
					<input type="email" value="${emailRegister}" name="emailRegister" class="form-control" placeholder="Email Id" required>
				</div>

				<div class="input-group">
					<span class="input-group-addon"> <i class="icon_key_alt"></i></span>
					<h5><b>Password</b></h5>
					<input type="password" name="passwordRegister" class="form-control" placeholder="Password" required>
				</div>


				<div class="input-group">
					Select Department
					<select name="departmentRegister" class="form-control s-bot15">
						<option value="Computer">Computer</option>
						<option value="IT">IT</option>
						<option value="ETRX">ETRX</option>
						<option value="EXTC">EXTC</option>
					</select>

					Select Year
					<select name="yearRegister" class="form-control s-bot15">
						<option value="FE">F.E</option>
						<option value="SE">S.E</option>
						<option value="TE">T.E</option>
						<option value="BE">B.E</option>
					</select>
				</div>
				
				<button class="btn btn-primary btn-lg btn-block" type="submit">Register</button>
			</form>
		</div>


	</div>


</body>
</html>
