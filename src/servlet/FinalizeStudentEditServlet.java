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


@WebServlet("/FinalizeStudentEditServlet")
public class FinalizeStudentEditServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			Student s = (Student) request.getSession().getAttribute(ProjectConstants.studentSession);

			int studentID = s.getStudentId();
			String firstName = request.getParameter("firstNameRegister");
			String middleName = request.getParameter("middleNameRegister");
			String lastName = request.getParameter("lastNameRegister");

			String password = request.getParameter("passwordRegister");
			String email = request.getParameter("emailRegister");
			String department = request.getParameter("departmentRegister");
			String year = request.getParameter("yearRegister");
			String interest = request.getParameter("interestRegister");
			Double totalAggregate = Double.parseDouble(request.getParameter("aggregateRegister"));


			
			new StudentDao().editStudent(studentID, firstName, middleName, lastName, password, email, department, year, interest, totalAggregate);
			
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
