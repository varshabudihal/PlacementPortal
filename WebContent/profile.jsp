<!DOCTYPE html>
<%@ page import="util.UtilityClass,constants.ProjectConstants,pojo.Student"%>
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

<% if( session.getAttribute(ProjectConstants.studentSession) == null )
	{
		request.setAttribute("errorMsg", "Session expried, Please login again");
		request.getRequestDispatcher("404.jsp").forward(request, response);
	}
%>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
%>


<script type="text/javascript">
$(document).ready(function() 
{
	loadEvents();
	getPendingStudents();	
	$("#messageBox").delay(3000).fadeOut("slow");
	
	dwr.engine.setErrorHandler(handler);
});
</script>


<script type="text/javascript">


function loadEvents()
{
	eventDWR.seminarGetter
	(
			function(data) 
			{
				var eventJSON = jQuery.parseJSON(data);
			
				for (i = 0; i <= eventJSON.length; i++) 
				{
					$("#accordionSeminar").append(

							'<div class="panel panel-default ">' + 
                            	'<div class="panel-heading ">' +
                                	'<h4 class="panel-title">' +
                                    	'<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionSeminar" href="#a' +eventJSON[i].eventId+ '">' + '<b>' +eventJSON[i].title+'</b>' + '</a>' + 
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
	
	eventDWR.eligiblePlacementGetter
	(
			function(data) 
			{
				var eventJSON = jQuery.parseJSON(data);
			
				for (i = 0; i <= eventJSON.length; i++) 
				{
					$("#accordionPlacement").append(

							'<div class="panel panel-default ">' + 
                            	'<div class="panel-heading ">' +
                                	'<h4 class="panel-title">' +
                                    	'<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionPlacement" href="#b' +eventJSON[i].eventId+ '">' + '<b>' +eventJSON[i].title+'</b>' + '</a>' + 
                                    		'<button class="btn btn-success" id="applyButton' +eventJSON[i].eventId+ '" onclick="applyForThis(' + eventJSON[i].eventId + ',this.id)" style="float: right;"><span class="icon_lightbulb_alt"></span> Apply for the Drive </button>' +
                                	'</h4>' +
                                '</div>' +
                           		 
	                            '<div id="b' +eventJSON[i].eventId+ '" class="panel-collapse collapse ">' +
	                                '<div class="panel-body">' + 
	                                	eventJSON[i].description + '<hr>' +
	                                	eventJSON[i].venue +
	                                '</div>' +
	                            '</div>' +
	                        '</div>' );
					
				}
			}
	);
	
	eventDWR.dreamCompanyGetter
	(
			function(data) 
			{
				var eventJSON = jQuery.parseJSON(data);
				for (i = 0; i <= eventJSON.length; i++) 
				{
					$("#accordionDreamCompany").append(

							'<div class="panel panel-default ">' + 
                            	'<div class="panel-heading ">' +
                                	'<h4 class="panel-title">' +
                                    	'<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordionDreamCompany" href="#c' +eventJSON[i].eventId+ '">' + '<b>' +eventJSON[i].title+'</b>' + '</a>' + 
                                    		'<button class="btn btn-success" id="applyButton' +eventJSON[i].eventId+ '" onclick="applyForThis(' + eventJSON[i].eventId + ',this.id)" style="float: right;"><span class="icon_lightbulb_alt"></span> Apply for the Drive </button>' +
                                	'</h4>' +
                                '</div>' +
                           		 
	                            '<div id="c' +eventJSON[i].eventId+ '" class="panel-collapse collapse ">' +
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

function getPendingStudents()
{
	studentDWR.activationPendingStudentGetter
	(
			function(data) 
			{
				var studentJSON = jQuery.parseJSON(data);
				$("#activateStudentsData").empty();
				if(studentJSON.length != 0)
				{
					for (i = 0; i <= studentJSON.length; i++) 
					{
						$("#activateStudentsData").append(
								'<tr>' +
								'<td>' +studentJSON[i].firstName+ '</td>' +
								'<td>' +studentJSON[i].middleName+ '</td>' +
								'<td>' +studentJSON[i].lastName+ '</td>' +
								'<td>' +studentJSON[i].email+ '</td>' +
								'<td>' +studentJSON[i].aggregate+ '</td>' +
								'<td><button class="btn btn-warning" onclick="approveStudent(' + studentJSON[i].studentId + ')" name="' + studentJSON[i].studentId + '"><i class="icon_check_alt2"></i></td>' +
								'</tr>');
					}
				}
				else
				{
					$("#activateStudentsData").append("No Student needs activation");
				}
			}
			
			
	);	
}

function approveStudent(i)
{
	studentDWR.activateStudent(i , 
			function(data) 
			{
				getPendingStudents();
			}
		)	
}

function applyForThis(i,button)
{
	studentDWR.applyForThisEvent(i , button,
			function(data) 
			{
				$("#applyButton"+i).removeClass('btn btn-success').addClass('btn btn-danger').text(data).blur();
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
									<c:choose>
										<c:when test="${stud.isTpo() == true}">
											<img class="profile-ava" alt="" src="img/committee.png">
										</c:when>
									<c:otherwise>
											<img class="profile-ava" alt="" src="img/student.png">
									</c:otherwise>
									</c:choose>
						</span> <span class="username">${stud.getFirstName()}</span> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu extended logout">
							<div class="log-arrow-up"></div>
							<li><a href="logout"><i class="icon_key_alt"></i> Log Out</a></li>
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
							 		<div class="follow-ava">
											<c:choose>
												<c:when test="${stud.isTpo() == true}">
													<img class="profile-ava" alt="" src="img/committee.png">
												</c:when>
											<c:otherwise>
													<img class="profile-ava" alt="" src="img/student.png">
											</c:otherwise>
											</c:choose>
									</div>
									<c:choose>
									<c:when test="${stud.isTpo() == true}">
										<h6> TPO Committee Member - ${stud.getDept()} <i>${stud.getYear()}</i></h6>
									</c:when>
										
									<c:otherwise>
										<h6> Student - ${stud.getDept()} <i>${stud.getYear()}</i></h6>
									</c:otherwise>
									</c:choose>
									
								</div>
								<div class="col-lg-4 col-sm-4 follow-info">
									<p>Hello ${stud.getFirstName()}</p>

									<h6>
										<span><i class="icon_calendar"></i> ${UtilityClass.getTime()}</span>
									</h6>
								</div>

								<div class="col-lg-4 col-sm-4 follow-info">
									<form action="UploadServlet" method="post" enctype="multipart/form-data">
										${UtilityClass.getFileStatus(stud)} 
										<p><label class="btn btn-primary"> Select Resume 
											<input type="file" class="inputfile" name="uploadResume"/ required> 
										</label>
										
										<label class="btn btn-danger"> Upload  <span class="icon_cloud-upload_alt"></span>
											<input type="submit" class="inputfile"/> 
										</label>
									</form>
									
									<div id="messageBox">
	                                  ${uploadStatus} ${eventStatus}
	                                  
	                              	</div>      
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
										<c:if test="${stud.isTpo() == true}">
											<li><a data-toggle="tab" href="#activateStudents"> <i class="icon-user"></i> Activate Students</a></li>
											<li><a data-toggle="tab" href="#createEvent"> <i class="icon-user"></i> Create Event</a></li>
										</c:if>
									<!-- <li class=""><a data-toggle="tab" href="#edit-profile"> <i class="icon-envelope"></i> Edit Profile</a></li> -->
								</ul>
							</header>
							<div class="panel-body">
								<div class="tab-content">
								
<!------------------------------------------------------------------------------------------------------------ seminars  ------------------------------------------------------------------------------------------------------>
									<div id="seminars" class="tab-pane active container-fluid" style="white-space: pre-wrap">
										<div class="panel-group m-bot20 " id="accordionSeminar" >
													 
										</div>	
									</div>

<!------------------------------------------------------------------------------------------------------------ placements  ------------------------------------------------------------------------------------------------------>
									<div id="placement" class="tab-pane container-fluid" style="white-space: pre-wrap">
									
									<c:choose>
										<c:when test="${stud.isPlaced() == false}">
											<div class="panel-group m-bot20 " id="accordionPlacement" >
													 
											</div>
										</c:when>
									<c:otherwise>
											<div class="panel-group m-bot20 "> Congrats !!! You are Placed. From now on you can only apply to Dream Companies</div>
									</c:otherwise>
									</c:choose>
									
									
											
									</div>
<!------------------------------------------------------------------------------------------------------------ Dream  ------------------------------------------------------------------------------------------------------>
									<div id="dreamCompany" class="tab-pane container-fluid" style="white-space: pre-wrap">
										<div class="panel-group m-bot20 " id="accordionDreamCompany" >
													 
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
															<span>First Name </span>: ${stud.getFirstName()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Middle Name </span>: ${stud.getMiddleName()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Last Name </span>: ${stud.getLastName()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Email </span>: ${stud.getEmail()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Department</span>: ${stud.getDept()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Year </span>: ${stud.getYear()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Area of Interest </span>: ${stud.getInterest()}
														</p>
													</div>
													<div class="bio-row">
														<p>
															<span>Aggregate</span>: ${stud.getAggregate()}
														</p>
													</div>
												</div>
												<form action="EditStudentServlet" >
												<button style="float: right;" class="btn btn-success">Edit profile</button>
												</form>
											</div>
										</section>
										<section>
										
											<div class="row"></div>
										</section>
									</div>
									
<!------------------------------------------------------------------------------------------------------------activate profile ------------------------------------------------------------------------------------------------------>									

									<div  id="activateStudents" class="tab-pane panel-body bio-graph-info">
										
										<table style="width: 100%;">
											<thead>
												<th>First name</th>
												<th>Middle name</th>
												<th>Last name</th>
												<th>Email</th>
												<th>Aggregate</th>
												<th>Approve Student</th>
											</thead>
											<tbody id="activateStudentsData">
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
															<option value="Dream">Dream Company</option>
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
