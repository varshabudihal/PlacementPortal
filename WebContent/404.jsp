<!DOCTYPE html>
<%@ page isErrorPage="true" %> 
<html lang="en">
<head>
    <meta charset="utf-8">

    <title> Error </title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <link href="css/elegant-icons-style.css" rel="stylesheet" />
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet" />


</head>

  <body  style="background-image: url(img\\blurBG.jpg)">
   <div class="page-404">
    <p class="text-404">404</p>

    <h2 style="color:white">Aww Snap!</h2>
    <p><h4><b style="color:white"> ${errorMsg} <%=exception%> </b></h4><br>
    <a class="btn btn-primary" href="login.jsp">Back to Login</a></p>
    
  </div>
  </body>
</html>
