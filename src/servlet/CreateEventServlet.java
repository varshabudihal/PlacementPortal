package servlet;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EventDao;


@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String eventName = request.getParameter("eventName");
		String eventDescription = request.getParameter("eventDescription");
		String eventDepartment = request.getParameter("eventDepartment");
		String eventYear = request.getParameter("eventYear");
		String eventType = request.getParameter("eventType");
		String eventVenue = request.getParameter("eventVenue");
		float eventReqdPerc = Float.parseFloat(request.getParameter("eventReqdPerc"));
		String eventInterest = request.getParameter("eventInterest");
		
		try
		{
			new EventDao().createNewEvent(eventName, eventDescription, eventDepartment, eventYear, eventType, eventVenue, eventReqdPerc, eventInterest);
			request.setAttribute("eventStatus", "Event created successfully");
			request.getRequestDispatcher("profile.jsp").include(request, response);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}

	}

}
