package servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.ProjectConstants;
import dao.FacultyDao;
import dao.StudentDao;
import pojo.Faculty;
import pojo.Student;


@WebServlet("/FinalizeFacultyEditServlet")
public class FinalizeFacultyEditServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Faculty fac = (Faculty) request.getSession().getAttribute(ProjectConstants.facultySession);

			int facultyId = fac.getFacultyId();
			String firstName = request.getParameter("firstNameRegister");
			String middleName = request.getParameter("middleNameRegister");
			String lastName = request.getParameter("lastNameRegister");

			String password = request.getParameter("passwordRegister");
			String email = request.getParameter("emailRegister");
			String department = request.getParameter("departmentRegister");
			String year = request.getParameter("yearRegister");
			Double totalAggregate = Double.parseDouble(request.getParameter("aggregateRegister"));


			new FacultyDao().editFaculty(facultyId, firstName, middleName, lastName, password, email, department, year);
			
			response.sendRedirect("SuccessfulUserCreation.jsp");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}

	}

}
