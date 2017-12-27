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
import pojo.Student;


@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Student stud = (Student) request.getSession().getAttribute(ProjectConstants.studentSession);
		
		request.setAttribute("firstNameRegister", stud.getFirstName());
		request.setAttribute("middleNameRegister", stud.getMiddleName());
		request.setAttribute("lastNameRegister", stud.getLastName());
		
		request.setAttribute("emailRegister", stud.getEmail());
		request.setAttribute("departmentRegister", stud.getDept());
		request.setAttribute("yearRegister", stud.getYear());
		request.setAttribute("interestRegister", stud.getInterest());
		request.setAttribute("aggregateRegister", stud.getAggregate());
	
		request.getRequestDispatcher("editStudent.jsp").forward(request, response);
 	}

}
