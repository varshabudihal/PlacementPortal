package dwrService;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import constants.ProjectConstants;
import dao.EventDao;
import pojo.Event;
import pojo.Student;


public class EventDWR
{
	public String eligiblePlacementGetter(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String eventsJson = "";
		try
		{
			EventDao edao = new EventDao();
			Student stud = (Student) session.getAttribute(ProjectConstants.studentSession);

			String dept = stud.getDept();
			String year = stud.getYear();
			float aggregate = stud.getAggregate();

			ArrayList<Event> eventList = new ArrayList<Event>();


			System.out.println(dept);
			System.out.println(year);
			System.out.println(aggregate);

			eventList = edao.getEligiblePlacements(dept, year, aggregate);

			System.out.println(eventList.size() + eventList.toString());

			ObjectMapper mapper = new ObjectMapper();
			eventsJson = mapper.writeValueAsString(eventList);
		}
		catch (Exception e)
		{
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}

		System.out.println(eventsJson);
		return eventsJson;
	}

	public String seminarGetter(HttpSession session, HttpServletRequest request, HttpServletResponse response)
	{
		String eventsJson = "";
		try
		{
			EventDao edao = new EventDao();
			Student stud = (Student) session.getAttribute(ProjectConstants.studentSession);

			String dept = stud.getDept();
			String year = stud.getYear();
			float aggregate = stud.getAggregate();

			ArrayList<Event> eventList = new ArrayList<Event>();


			System.out.println(dept);
			System.out.println(year);
			System.out.println(aggregate);

			eventList = edao.getEligibleSeminars(dept, year, aggregate);

			System.out.println(eventList.size() + eventList.toString());

			ObjectMapper mapper = new ObjectMapper();
			eventsJson = mapper.writeValueAsString(eventList);
		}
		catch (Exception e)
		{
			request.setAttribute("errorMsg", e.getMessage());
			try
			{
				request.getRequestDispatcher("404.jsp").include(request, response);
			}
			catch (ServletException | IOException e1)
			{
				e1.printStackTrace();
			}
		}

		System.out.println(eventsJson);
		return eventsJson;
	}

	public String dreamCompanyGetter(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String eventsJson = "";
		try
		{
			EventDao edao = new EventDao();
			Student stud = (Student) session.getAttribute(ProjectConstants.studentSession);

			String dept = stud.getDept();
			String year = stud.getYear();
			float aggregate = stud.getAggregate();
			String interest = stud.getInterest();

			ArrayList<Event> eventList = new ArrayList<Event>();


			System.out.println(dept);
			System.out.println(year);
			System.out.println(aggregate);

			eventList = edao.getEligibleDreamCompany(dept, year, aggregate,interest);

			System.out.println(eventList.size() + eventList.toString());

			ObjectMapper mapper = new ObjectMapper();
			eventsJson = mapper.writeValueAsString(eventList);
		}
		catch (Exception e)
		{
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}

		System.out.println(eventsJson);
		return eventsJson;
	}
	
	public String allEventGetter(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String eventsJson = "";
		try
		{
			EventDao edao = new EventDao();
			ArrayList<Event> eventList = edao.getAllEvents();

			System.out.println(eventList.size() + eventList.toString());

			ObjectMapper mapper = new ObjectMapper();
			eventsJson = mapper.writeValueAsString(eventList);
		}
		catch (Exception e)
		{
			request.setAttribute("errorMsg", e.toString());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}

		System.out.println(eventsJson);
		return eventsJson;
	}
}
