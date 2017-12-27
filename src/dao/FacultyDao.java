package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.Faculty;
import util.UtilityClass;


public class FacultyDao
{

	public int registerFaculty(String fname, String mname, String lname, String password, String email, String dept, String year) throws ClassNotFoundException, SQLException
	{
		int result = 0;
		Connection conn = ConnectionManager.getConnection();
		int FacultyPrimaryKey = getNewPrimarykey();
		PreparedStatement pstmt = conn.prepareStatement("insert into Faculty(Faculty_Id,first_name,middle_name,last_name,password,email,dept,year) values(?,?,?,?,?,?,?,?,?)");

		pstmt.setInt(1, getNewPrimarykey());
		pstmt.setString(2, fname);
		pstmt.setString(3, mname);
		pstmt.setString(4, lname);
		pstmt.setString(5, password);
		pstmt.setString(6, email);
		pstmt.setString(7, dept);
		pstmt.setString(8, year);

		result = pstmt.executeUpdate();

		return result;
	}

	public int editFaculty(int facultyId, String fname, String mname, String lname, String password, String email, String dept, String year) throws ClassNotFoundException, SQLException
	{
		int result = 0;
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("update Faculty set first_name=?,middle_name=?,last_name=?,password=?,email=?,dept=?,year=? where Faculty_Id=?");


		pstmt.setString(1, fname);
		pstmt.setString(2, mname);
		pstmt.setString(3, lname);
		pstmt.setString(4, password);
		pstmt.setString(5, email);
		pstmt.setString(6, dept);
		pstmt.setString(7, year);
		pstmt.setInt(8, facultyId);

		result = pstmt.executeUpdate();

		return result;
	}

	public int isAuthenticated(String email, String password) throws SQLException, ClassNotFoundException
	{
		int isAuthenticated = -1;

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("Select Faculty_Id, email, password from Faculty");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
		{
			String registeredEmail = rs.getString("email");
			if (email.equalsIgnoreCase(registeredEmail))
			{
				if (password.equals(rs.getString("password")))
					isAuthenticated = rs.getInt("Faculty_Id");
			}
		}

		return isAuthenticated;
	}
	
	public boolean isUnique(String email) throws SQLException, ClassNotFoundException
	{
		boolean isUnique = true;

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("Select email from Faculty");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
		{
			String registeredEmail = rs.getString("email");
			if (email.equalsIgnoreCase(registeredEmail))
			{
				isUnique = false;
			}
		}
		return isUnique;
	}

	public int getNewPrimarykey() throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select count(Faculty_id) from Faculty");
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		return rs.getInt(1) + 1;
	}

	public Faculty getFaculty(int FacultyId) throws ClassNotFoundException, SQLException
	{
		Faculty facultyObj = new Faculty();

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from Faculty where Faculty_Id=?");
		pstmt.setInt(1, FacultyId);
		ResultSet rs = pstmt.executeQuery();

		rs.next();

		facultyObj.setFacultyId(rs.getInt(1));
		facultyObj.setFirstName(UtilityClass.getCapitalized(rs.getString(2)));
		facultyObj.setMiddleName(UtilityClass.getCapitalized(rs.getString(3)));
		facultyObj.setLastName(UtilityClass.getCapitalized(rs.getString(4)));

		// FacultyObj.setPassword(rs.getString(5));
		facultyObj.setEmail(rs.getString(6));
		facultyObj.setDept(rs.getString(7));
		facultyObj.setYear(rs.getString(8));
		
		return facultyObj;
	}
}
