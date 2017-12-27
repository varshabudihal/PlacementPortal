package servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.ProjectConstants;
import dao.StudentDao;
import pojo.Faculty;
import pojo.Student;


@WebServlet("/EditFacultyServlet")
public class EditFacultyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Faculty fac = (Faculty) request.getSession().getAttribute(ProjectConstants.facultySession);
		
		request.setAttribute("firstNameRegister", fac.getFirstName());
		request.setAttribute("middleNameRegister", fac.getMiddleName());
		request.setAttribute("lastNameRegister", fac.getLastName());
		
		request.setAttribute("emailRegister", fac.getEmail());
		request.setAttribute("departmentRegister", fac.getDept());
		request.setAttribute("yearRegister", fac.getYear());
	
		request.getRequestDispatcher("editFaculty.jsp").forward(request, response);
 	}

}
