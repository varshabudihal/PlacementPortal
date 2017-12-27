package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import pojo.Event;
import sun.applet.Main;
import util.UtilityClass;


public class EventDao
{
	public int createNewEvent(String title, String eventDescription, String eventDepartment, String eventYear, String eventType, String eventVenue, float eventReqdPerc, String interest) throws SQLException, ClassNotFoundException
	{
		int result = 0;
		Connection conn = ConnectionManager.getConnection();
		int studentPrimaryKey = getNewPrimarykey();
		PreparedStatement pstmt = conn.prepareStatement("insert into event(event_id,title,description,which_dept,which_year,type,venue,reqd_aggregate,interest) values(?,?,?,?,?,?,?,?,?)");


		pstmt.setInt(1, getNewPrimarykey());
		pstmt.setString(2, title);
		pstmt.setString(3, StringEscapeUtils.escapeJava(eventDescription));
		pstmt.setString(4, eventDepartment);
		pstmt.setString(5, eventYear);
		pstmt.setString(6, eventType);
		pstmt.setString(7, eventVenue);
		pstmt.setFloat(8, eventReqdPerc);
		pstmt.setString(9, interest);
		
		result = pstmt.executeUpdate();

		return result;
	}

	public Event getEvent(int eventID) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select event_id,title,description,which_dept,which_year,type,venue,reqd_aggregate,interest from event where event_id=?");

		pstmt.setInt(1, eventID);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		Event e = new Event();
		e.setEventId(rs.getInt(1));
		e.setTitle(UtilityClass.getCapitalized(rs.getString(2)));
		e.setDescription(StringEscapeUtils.unescapeJava(rs.getString(3)));
		e.setWhichDept(rs.getString(4));
		e.setWhichYear(rs.getString(5));
		e.setType(rs.getString(6));
		e.setVenue(rs.getString(7));
		e.setReqdAggregate(rs.getInt(8));
		e.setInterest(rs.getString(9));

		return e;

	}

	public int getNewPrimarykey() throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select count(event_id) from event");
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		return rs.getInt(1) + 1;
	}

	public ArrayList<Event> getEligibleSeminars(String dept, String year, float aggregate) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select event_id,title,description,which_dept,which_year,type,venue,reqd_aggregate from event where type='seminar' and (which_dept=? or which_dept='all') and which_year=? and reqd_aggregate<=?");
		ArrayList<Event> eligibleEventList = new ArrayList<Event>();

		/*
		 * System.out.println("dao me aaya");
		 * 
		 * System.out.println("dept " + dept); System.out.println("year " +
		 * year); System.out.println("aggregate " + aggregate);
		 */

		pstmt.setString(1, dept);
		pstmt.setString(2, year);
		pstmt.setFloat(3, aggregate);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
		{
			Event e = new Event();

			e.setEventId(rs.getInt(1));
			e.setTitle(UtilityClass.getCapitalized(rs.getString(2)));
			e.setDescription(StringEscapeUtils.unescapeJava(rs.getString(3)));
			e.setWhichDept(rs.getString(4));
			e.setWhichYear(rs.getString(5));
			e.setType(rs.getString(6));
			e.setVenue(rs.getString(7));
			e.setReqdAggregate(rs.getInt(8));

			eligibleEventList.add(e);
		}

		System.out.println("eligibleEventList " + eligibleEventList);
		return eligibleEventList;

	}

	public ArrayList<Event> getEligiblePlacements(String dept, String year, float aggregate) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select event_id,title,description,which_dept,which_year,type,venue,reqd_aggregate from event where type='placement' and (which_dept=? or which_dept='all') and which_year=? and reqd_aggregate<=?");
		ArrayList<Event> eligibleEventList = new ArrayList<Event>();

		pstmt.setString(1, dept);
		pstmt.setString(2, year);
		pstmt.setFloat(3, aggregate);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
		{
			Event e = new Event();

			e.setEventId(rs.getInt(1));
			e.setTitle(UtilityClass.getCapitalized(rs.getString(2)));
			e.setDescription(StringEscapeUtils.unescapeJava(rs.getString(3)));
			e.setWhichDept(rs.getString(4));
			e.setWhichYear(rs.getString(5));
			e.setType(rs.getString(6));
			e.setVenue(rs.getString(7));
			e.setReqdAggregate(rs.getInt(8));

			eligibleEventList.add(e);
		}

		System.out.println("eligibleEventList " + eligibleEventList);
		return eligibleEventList;

	}


	public ArrayList<Event> getEligibleDreamCompany(String dept, String year, float aggregate, String interest) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from event where type='dream' and (which_dept=? or which_dept='all') and which_year=? and reqd_aggregate<=? and interest=?");
		ArrayList<Event> eligibleEventList = new ArrayList<Event>();

		pstmt.setString(1, dept);
		pstmt.setString(2, year);
		pstmt.setFloat(3, aggregate);
		pstmt.setString(4, interest);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
		{
			Event e = new Event();

			e.setEventId(rs.getInt(1));
			e.setTitle(UtilityClass.getCapitalized(rs.getString(2)));
			e.setDescription(StringEscapeUtils.unescapeJava(rs.getString(3)));
			e.setWhichDept(rs.getString(4));
			e.setWhichYear(rs.getString(5));
			e.setType(rs.getString(6));
			e.setVenue(rs.getString(7));
			e.setReqdAggregate(rs.getInt(8));
			e.setInterest(rs.getString(9));
			

			eligibleEventList.add(e);
		}

		System.out.println("eligibleEventList " + eligibleEventList);
		return eligibleEventList;

	}


	public ArrayList<Event> getAllEvents() throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from event");
		ArrayList<Event> eligibleEventList = new ArrayList<Event>();

		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
		{
			Event e = new Event();

			e.setEventId(rs.getInt(1));
			e.setTitle(UtilityClass.getCapitalized(rs.getString(2)));
			e.setDescription(StringEscapeUtils.unescapeJava(rs.getString(3)));
			e.setWhichDept(rs.getString(4));
			e.setWhichYear(rs.getString(5));
			e.setType(rs.getString(6));
			e.setVenue(rs.getString(7));
			e.setReqdAggregate(rs.getInt(8));
			e.setInterest(rs.getString(9));
			
			eligibleEventList.add(e);
		}

		System.out.println("entireEventList " + eligibleEventList);
		return eligibleEventList;

	}
	
	public static void main(String[] args)
	{
		try
		{
			new EventDao().getEligibleDreamCompany("Computer", "SE", 9, "java");
		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
