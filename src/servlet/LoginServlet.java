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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String email = request.getParameter("emailLogin");
			String loginPassword = request.getParameter("passwordLogin");
			boolean isFaculty = Boolean.parseBoolean(request.getParameter("AdminLogin"));

			if (isFaculty)
			{
				FacultyDao fdao = new FacultyDao();
				int facultydId = fdao.isAuthenticated(email, loginPassword);

				if (facultydId != -1)
				{
					Faculty fac = fdao.getFaculty(facultydId);
					request.getSession().setAttribute(ProjectConstants.facultySession, fac);
					response.sendRedirect("profileFaculty.jsp");
				}
				else
				{
					request.setAttribute("errorMsg", "Incorrect login credentials");
					request.getRequestDispatcher("404.jsp").include(request, response);
				}
			}


			/*++++++++++++++++++++++++++++++++++ This is for students* ++++++++++++++++++++++++++++++ */
			
			else
			{
				StudentDao sdao = new StudentDao();
				int StudId = sdao.isAuthenticated(email, loginPassword);

				if (StudId != -1)
				{
					Student stud = sdao.getStudent(StudId);
					System.out.println(stud.isActive());

					if (stud.isActive() == false)
					{
						System.out.println("inside false");
						request.setAttribute("errorMsg", "Sorry, please contact your Student TPO member and get your profile approved");
						request.getRequestDispatcher("404.jsp").include(request, response);
					}
					else
					{
						request.getSession().setAttribute(ProjectConstants.studentSession, stud);
						request.getSession().setAttribute("studName", stud.getFirstName());
						response.sendRedirect("profile.jsp");
					}
				}
				else
				{
					request.setAttribute("errorMsg", "Incorrect login credentials");
					request.getRequestDispatcher("404.jsp").include(request, response);
				}
			}

		}

		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}
	}

}
