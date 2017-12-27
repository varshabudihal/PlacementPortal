package dwrService;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

import constants.ProjectConstants;
import dao.EventDao;
import dao.StudentDao;
import pojo.Event;
import pojo.Student;
import util.UtilityClass;


public class StudentDWR
{
	public String activationPendingStudentGetter(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String activateStudentJSON = "";

		try
		{
			Student stud = (Student) session.getAttribute(ProjectConstants.studentSession);
			if (stud == null)
				return "";

			StudentDao sdao = new StudentDao();
			List<Student> pendingStudList = sdao.getStudentsPendingActivation(stud.getDept(), stud.getYear());

			ObjectMapper mapper = new ObjectMapper();
			activateStudentJSON = mapper.writeValueAsString(pendingStudList);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}

		System.out.println("activateStudentJSON = " + activateStudentJSON);
		return activateStudentJSON;
	}

	public void activateStudent(HttpServletRequest request, HttpServletResponse response, int studentId) throws ServletException, IOException
	{
		try
		{
			new StudentDao().activateAccount(studentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}
	}
	
	public void placeStudent(HttpServletRequest request, HttpServletResponse response, int studentId) throws ServletException, IOException
	{
		try
		{
			new StudentDao().markAsPlaced(studentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}
	}
	
	public void makeTpo(HttpServletRequest request, HttpServletResponse response, int studentId) throws ServletException, IOException
	{
		try
		{
			new StudentDao().promoteToTpo(studentId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}
	}

	public String applyForThisEvent(int eventId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("inside applyForThisEvent " + eventId);
		try
		{
			File uploadedResume;
			Student stud = (Student) session.getAttribute(ProjectConstants.studentSession);

			try
			{
				uploadedResume = UtilityClass.getUploadedResume(stud);
			}
			catch (FileNotFoundException e)
			{
				return "No saved resume found, Please upload your resume First using the above upload option";
			}

			Event e = new EventDao().getEvent(eventId);
			File appliedResumeDirectory = new File(ProjectConstants.AppliedResumeRootDirectory + "\\" + e.getWhichDept() + "\\" + UtilityClass.getCapitalized(e.getTitle()));
			if (appliedResumeDirectory.exists() == false)
				appliedResumeDirectory.mkdirs();

			FileUtils.copyFileToDirectory(uploadedResume, appliedResumeDirectory);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}

		return "Successfully applied";
	}

	public String allSelectedStudents(String dept, String year,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String selectedStudJSON = "";
		try
		{
			System.out.println("allSelectedStudents= " + dept + year);
			ArrayList<Student> selectedStudList = (ArrayList<Student>) new StudentDao().listAllStudentsForCurrentSelection(dept, year);
			ObjectMapper mapper = new ObjectMapper();
			selectedStudJSON = mapper.writeValueAsString(selectedStudList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
		}
		
		System.out.println("selectedStudJSON = " + selectedStudJSON );
		return selectedStudJSON;
	}
	
	public String verifyUniqueness(String email,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			System.out.println("emailo mila " + email);
			boolean isUnique = new  StudentDao().isUnique(email);
			System.out.println(isUnique);
			if(isUnique)
				return "true";
			else
				return "false";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("404.jsp").include(request, response);
			return "false";
		}
		
	}
}
