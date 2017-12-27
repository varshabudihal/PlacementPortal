<!DOCTYPE html>
<%@ page import="util.UtilityClass,constants.ProjectConstants,pojo.Student,constants.ProjectConstants,pojo.Faculty"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="404.jsp" %>  
 
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>Dashboard</title>

<script src="js/jquery.js"></script>
<script type='text/javascript' src='/PlacementPortal/dwr/engine.js'></script>
<script type='text/javascript' src='/PlacementPortal/dwr/interface/eventDWR.js'></script>
<script type='text/javascript' src='/PlacementPortal/dwr/interface/studentDWR.js'></script>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />


<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
%>


<script type="text/javascript">
$(document).ready(function() 
{
	
	loadEvents();
	
	$("#studentDepartment").change(refresh);

	$("#studentYear").change(refresh);

});
</script>


<script type="text/javascript">


function refresh()
{
	var dept = $( "#studentDepartment" ).val();
	var year = $( "#studentYear" ).val();
	getSelectedStudents(dept,year);
}

function loadEvents()
{
	eventDWR.allEventGetter
	(
			function(data) 
			{
				var eventJSON = jQuery.parseJSON(data);
			
				for (i = 0; i <= eventJSON.length; i++) 
				{
					$("#"+eventJSON[i].type+"Type").append(

							'<div class="panel panel-default ">' + 
                            	'<div class="panel-heading ">' +
                                	'<h4 class="panel-title">' +
                                    	'<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionSeminar" href="#a' +eventJSON[i].eventId+ '">' + '<b>' +eventJSON[i].title+'</b>' + '</a>' + '<b style="float:right">' +eventJSON[i].whichDept+ " " +eventJSON[i].whichYear+'</b>' +
                                	'</h4>' +
                                '</div>' +
                           		 
	                            '<div id="a' +eventJSON[i].eventId+ '" class="panel-collapse collapse ">' +
	                                '<div class="panel-body">' +
	                                	eventJSON[i].description + '<hr>' +
	                                	eventJSON[i].venue +
	                                '</div>' +
	                            '</div>' +
	                        '</div>' );
				}
			}
	);
	
	
}

function getSelectedStudents(dept,year)
{
	studentDWR.allSelectedStudents
	(		
			dept,year,
			function(data) 
			{
				var studentJSON = jQuery.parseJSON(data);
				$("#manageStudentsData").empty();
				if(studentJSON.length != 0)
				{
					for (i = 0; i <= studentJSON.length; i++) 
					{
						$("#manageStudentsData").append('<tr>');
						$("#manageStudentsData").append(
								'<td>' +studentJSON[i].firstName+ '</td>' +
								'<td>' +studentJSON[i].middleName+ '</td>' +
								'<td>' +studentJSON[i].lastName+ '</td>' +
								'<td>' +studentJSON[i].email+ '</td>' +
								'<td>' +studentJSON[i].aggregate+ '</td>' )
								

								if(studentJSON[i].active == false)
								{
									$("#manageStudentsData").append('<td><button class="btn btn-warning" onclick="approveStudent(' + studentJSON[i].studentId + ')" name="' + studentJSON[i].studentId + '"><i class="icon_check_alt2"></i></td>');
								}
								else
								{
									$("#manageStudentsData").append('<td><i class="">Active</i></td>');
								}
								
								
								if(studentJSON[i].tpo == false)
								{
									$("#manageStudentsData").append('<td><button class="btn btn-warning" onclick="markAsTpo(' + studentJSON[i].studentId + ')" name="' + studentJSON[i].studentId + '"><i class="icon_check_alt2"></i></td>');
								}
								else
								{
									$("#manageStudentsData").append('<td><i class="">TPO Member</i></td>');
								}
								
								
								if(studentJSON[i].placed == false)
								{
									$("#manageStudentsData").append('<td><button class="btn btn-warning" onclick="markAsPlaced(' + studentJSON[i].studentId + ')" name="' + studentJSON[i].studentId + '"><i class="icon_check_alt2"></i></td>');
								}
								else
								{
									$("#manageStudentsData").append('<td><i class="">Placed</i></td>');
								}
								
								$("#manageStudentsData").append('</tr>');
					}
				}
			}
			
			
	);	
}

function approveStudent(i)
{
	studentDWR.activateStudent(i , 
			function(data) 
			{
				refresh();
			}
		)	
}

function markAsTpo(i)
{
	studentDWR.makeTpo(i , 
			function(data) 
			{
				refresh();
			}
		)	
}

function markAsPlaced(i)
{
	studentDWR.placeStudent(i , 
			function(data) 
			{
				refresh();
			}
		)	
}


</script>

</head>

<body style="background-color:black">
<div id="myDiv"></div>
	<!-- container section start -->
	<section id="container" class="sidebar-closed">
		<!--header start-->
		<header class="header dark-bg">
			<!--logo start-->
			<a class="logo">iT <span class="lite">&P</span></a>
			<!--logo end-->
			
			

			<div class="top-nav notification-row">
				<!-- notificatoin dropdown start-->
				<ul class="nav pull-right top-menu">



					<!-- user login dropdown start-->
					<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span class="profile-ava"> 
									
											<img class="profile-ava" alt="" src="img/faculty.png">
										
						</span> <span class="username">${fac.getFirstName()}</span> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<div class="log-arrow-up"></div>
							<li><a href="login.jsp"><i class="icon_key_alt"></i> Log Out</a></li>
						</ul></li>
					<!-- user login dropdown end -->
				</ul>
				<!-- notificatoin dropdown end-->
			</div>
		</header>
		<!--header end-->

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header" style="color:white">
							<i class="fa fa-user-md"></i> HomePage
						</h3>

					</div>
				</div>
				<div class="row">
					<!-- profile-widget -->
					<div class="col-lg-12">
						<div class="profile-widget profile-widget-info">
							<div class="panel-body">
								<div class="col-lg-2 col-sm-2">
							 		<div class="follow-ava"> <img class="profile-ava" alt="" src="img/faculty.png"> </div>
									<h6> Faculty Member - ${fac.getDept()} <i>${fac.getYear()}</i></h6>
								</div>
								<div class="col-lg-4 col-sm-4 follow-info">
									<p>Hello ${fac.getFirstName()}</p>
									<h6> <span><i class="icon_calendar"></i> ${UtilityClass.getTime()}</span> </h6>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- page start-->
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading tab-bg-info">
								<ul class="nav nav-tabs">
									<li class="active"><a data-toggle="tab" href="#seminars"> <i class="icon-home"></i> Upcoming Events</a></li>
									<li><a data-toggle="tab" href="#placement"> <i class="icon-home"></i> Placement Drives</a></li>
									<li><a data-toggle="tab" href="#dreamCompany"> <i class="icon-home"></i> Dream Companies</a></li>
									<li><a data-toggle="tab" href="#profile"> <i class="icon-user"></i> My Profile</a></li>
									<li><a data-toggle="tab" href="#manageStudents"> <i class="icon-user"></i> Manage Students</a></li>
									<li><a data-toggle="tab" href="#createEvent"> <i class="icon-user"></i> Create Event</a></li>
								</ul>
							</header>
							<div class="panel-body">
								<div class="tab-content">
								
<!------------------------------------------------------------------------------------------------------------ seminars  ------------------------------------------------------------------------------------------------------>
									<div id="seminars" class="tab-pane active container-fluid" style="white-space: pre-wrap">
										<div class="panel-group m-bot20 " id="SeminarType" >
													 
										</div>	
									</div>

<!------------------------------------------------------------------------------------------------------------ placements  ------------------------------------------------------------------------------------------------------>
									<div id="placement" class="tab-pane container-fluid" style="white-space: pre-wrap">
										<div class="panel-group m-bot20 " id="PlacementType" >
													 
										</div>	
									</div>
<!------------------------------------------------------------------------------------------------------------ Dream  ------------------------------------------------------------------------------------------------------>
									<div id="dreamCompany" class="tab-pane container-fluid" style="white-space: pre-wrap">
										<div class="panel-group m-bot20 " id="DreamType" >
													 
										</div>	
									</div>						
									

<!------------------------------------------------------------------------------------------------------------ Profile ------------------------------------------------------------------------------------------------------>
									<div id="profile" class="tab-pane">
										<section class="panel">

											<div class="panel-body bio-graph-info">
												<h1>Bio Graph</h1>
												<div class="row">
													<div class="bio-row">
														<p>
															<span>First Name </span>: ${fac.getFirstName()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Middle Name </span>: ${fac.getMiddleName()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Last Name </span>: ${fac.getLastName()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Email </span>: ${fac.getEmail()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Department</span>: ${fac.getDept()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Year </span>: ${fac.getYear()}
														</p>
													</div>
												</div>
												<form action="EditFacultyServlet" >
												<button style="float: right;" class="btn btn-success">Edit profile</button>
												</form>
											</div>
										</section>
										<section>
										
											<div class="row"></div>
										</section>
									</div>										
<!------------------------------------------------------------------------------------------------------------activate profile ------------------------------------------------------------------------------------------------------>									

									<div  id="manageStudents" class="tab-pane panel-body bio-graph-info">
										
												<div class="form-group">
                                                      <div class="col-lg-6">
                                                      Select Department
														<select name="studentDepartment" id="studentDepartment" class="form-control s-bot15">
															<option selected value="Computer">Computer</option>
															<option value="IT">IT</option>
															<option value="ETRX">ETRX</option>
															<option value="EXTC">EXTC</option>
														</select>
														</div>
                                                
                                                      <div class="col-lg-6">
                                                      Select year
														<select name="studentYear" id="studentYear" id="eventYear" class="form-control s-bot15">
															<option selected value="FE">F.E</option>
															<option value="SE">S.E</option>
															<option value="TE">T.E</option>
															<option value="BE">B.E</option>
														</select>
													  </div>
                                                  </div>
                                                  
                                                  
										
										<table style="width: 100%;">
											<thead>
												<th>First name</th>
												<th>Middle name</th>
												<th>Last name</th>
												<th>Email</th>
												<th>Aggregate</th>
												<th>Approve Student</th>
												<th>Promote to TPO member</th>
												<th>Mark Student as Placed</th>
											</thead>
											<tbody id="manageStudentsData">
											</tbody>
										</table>
									
									</div>
									
<!-------------------------------------------------------------------------------------------------------------- Create Event ----------------------------------------------------------------------------------------------------->
                                  <div id="createEvent" class="tab-pane">
                                    <section class="panel">                                          
                                          <div class="panel-body bio-graph-info">
                                              <h1> Event Creation</h1>
                                              <form class="form-horizontal" role="form" action="CreateEventServlet" method="post">
                                                                                                
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label"><b>Company Name</b></label>
                                                      <div class="col-lg-6">
                                                          <input type="text" class="form-control" id="eventName" name="eventName" placeholder=" " required>
                                                      </div>
                                                  </div>
                                                  
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label"><b>Event Description</b></label>
                                                      <div class="col-lg-6">
                                                          <textarea class="form-control" cols="30" rows="5" name="eventDescription" id="eventDescription" required></textarea>
                                                      </div>
                                                  </div>
                                                  
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label"> <b>Select Department</b></label>
                                                      <div class="col-lg-6">
														<select name="eventDepartment" class="form-control s-bot15">
															<option value="ALL">All Branches</option>
															<option value="Computer">Computer</option>
															<option value="IT">IT</option>
															<option value="ETRX">ETRX</option>
															<option value="EXTC">EXTC</option>
														</select>
														</div>
                                                  </div>
                                                  
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label"> <b>Select Year</b></label>
                                                      <div class="col-lg-6">
														<select name="eventYear" id="eventYear" class="form-control s-bot15">
															<option value="FE">F.E</option>
															<option value="SE">S.E</option>
															<option value="TE">T.E</option>
															<option value="BE">B.E</option>
														</select>
													  </div>
                                                  </div>
                                                  
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label"> <b>Select Interest</b></label>
                                                      <div class="col-lg-6">
														<select name="eventInterest" id="eventInterest" class="form-control s-bot15">
															<option value="Java">Java</option>
																<option value="Database">Database</option>
																<option value="DotNet">DotNet</option>
																<option value="Web">Web development</option>
																<option value="Telecom">Telecom</option>
																<option value="Embedded">Embedded Systems</option>
																<option value="Sales">Sales & Marketting</option>
														</select>
													  </div>
                                                  </div>
                                                  
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label"> <b>Event Type</b></label>
                                                      <div class="col-lg-6">
														<select name="eventType" id="eventType" class="form-control s-bot15">
															<option value="Seminar">Seminar</option>
															<option value="Placement">Placement Drive</option>
															<option value="cream">Dream Company</option>
														</select>
													  </div>
                                                  </div>
                                                  
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label"><b>Venue Details</b></label>
                                                      <div class="col-lg-6">
                                                          <textarea class="form-control" cols="30" rows="5" name="eventVenue" id="eventVenue" required></textarea>
                                                      </div>
                                                  </div>
					
                                                  <div class="form-group">
                                                      <label class="col-lg-2 control-label">
	                                                      <b>Required Aggregate </b> <i style="display: block;"> Enter 0 for no criteria</i> 
                                                      </label>
                                                      <div class="col-lg-6">
                                                          <input type="number" step="0.01" min="0" max="99" class="form-control" id="eventReqdPerc" name="eventReqdPerc" placeholder="Dont enter percentage symbol" required>
                                                      </div>
                                                  </div>

                                                  <div class="form-group">
                                                      <div class="col-lg-offset-2 col-lg-10">
                                                          <button type="submit" class="btn btn-primary">Create Event</button>
                                                      </div>
                                                  </div>
                                              </form>
                                          </div>
                                      </section>
                                  </div>
                                  
<!-------------------------------------------------------------------------------------------------------------- End of tabs ----------------------------------------------------------------------------------------------------->
                                  
								</div>
							</div>
						</section>
					</div>
				</div>

				<!-- page end-->
			</section>
		</section>
		<!--main content end-->
	</section>
	<!-- container section end -->
	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- jquery knob -->
	<script src="assets/jquery-knob/js/jquery.knob.js"></script>
	<!--custome script for all page-->
	<script src="js/scripts.js"></script>

	<script>
		//knob
		$(".knob").knob();
	</script>
</body>
</html>
