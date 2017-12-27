package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import pojo.Student;
import util.UtilityClass;


public class StudentDao
{
	public int registerStudent(String fname, String mname, String lname, String password, String email, String dept, String year, String interest, double aggregate, boolean isTpo, boolean isActive, boolean isPlaced) throws ClassNotFoundException, SQLException
	{
		int result = 0;
		Connection conn = ConnectionManager.getConnection();
		int studentPrimaryKey = getNewPrimarykey();
		PreparedStatement pstmt = conn.prepareStatement("insert into student(Student_Id,first_name,middle_name,last_name,password,email,dept,year,interest,aggregate,is_tpo,is_active,is_placed) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

		pstmt.setInt(1, getNewPrimarykey());
		pstmt.setString(2, fname);
		pstmt.setString(3, mname);
		pstmt.setString(4, lname);
		pstmt.setString(5, password);
		pstmt.setString(6, email);
		pstmt.setString(7, dept);
		pstmt.setString(8, year);
		pstmt.setString(9, interest);
		pstmt.setDouble(10, aggregate);
		pstmt.setBoolean(11, isTpo);
		pstmt.setBoolean(12, isActive);
		pstmt.setBoolean(13, isPlaced);

		result = pstmt.executeUpdate();

		return result;
	}
	
	public int editStudent(int studentId, String fname, String mname, String lname, String password, String email, String dept, String year, String interest, double aggregate) throws ClassNotFoundException, SQLException
	{
		int result = 0;
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("update student set first_name=?,middle_name=?,last_name=?,password=?,email=?,dept=?,year=?,interest=?,aggregate=? where Student_Id=?");

		
		pstmt.setString(1, fname);
		pstmt.setString(2, mname);
		pstmt.setString(3, lname);
		pstmt.setString(4, password);
		pstmt.setString(5, email);
		pstmt.setString(6, dept);
		pstmt.setString(7, year);
		pstmt.setString(8, interest);
		pstmt.setDouble(9, aggregate);
		pstmt.setInt(10, studentId);

		result = pstmt.executeUpdate();

		return result;
	}

	public int isAuthenticated(String email, String password) throws SQLException, ClassNotFoundException
	{
		int isAuthenticated = -1;

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("Select Student_Id, email, password from student");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next())
		{
			String registeredEmail = rs.getString("email");
			if (email.equalsIgnoreCase(registeredEmail))
			{
				if (password.equals(rs.getString("password")))
					isAuthenticated = rs.getInt("Student_Id");
			}
		}

		return isAuthenticated;
	}

	public int getNewPrimarykey() throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select count(student_id) from student");
		ResultSet rs = pstmt.executeQuery();

		rs.next();
		return rs.getInt(1) + 1;
	}
	
	public boolean isUnique(String email) throws SQLException, ClassNotFoundException
	{
		boolean isUnique = true;

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("Select email from student");
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

	public Student getStudent(int studentId) throws ClassNotFoundException, SQLException
	{
		Student studentObj = new Student();

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select * from student where Student_Id=?");
		pstmt.setInt(1, studentId);
		ResultSet rs = pstmt.executeQuery();

		rs.next();

		studentObj.setStudentId(rs.getInt(1));
		studentObj.setFirstName(UtilityClass.getCapitalized(rs.getString(2)));
		studentObj.setMiddleName(UtilityClass.getCapitalized(rs.getString(3)));
		studentObj.setLastName(UtilityClass.getCapitalized(rs.getString(4)));

		//studentObj.setPassword(rs.getString(5));
		studentObj.setEmail(rs.getString(6));
		studentObj.setDept(rs.getString(7));
		studentObj.setYear(rs.getString(8));
		studentObj.setInterest(rs.getString(9));
		studentObj.setAggregate(rs.getFloat(10));

		studentObj.setTpo(rs.getBoolean(11));
		studentObj.setActive(rs.getBoolean(12));
		studentObj.setPlaced(rs.getBoolean(13));

		return studentObj;
	}

	public int activateAccount(int studentId) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("UPDATE student SET is_active=? where Student_Id=?");
		pstmt.setBoolean(1, true);
		pstmt.setInt(2, studentId);
		return pstmt.executeUpdate();
	}
	
	public int markAsPlaced(int studentId) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("UPDATE student SET is_Placed=? where Student_Id=?");
		pstmt.setBoolean(1, true);
		pstmt.setInt(2, studentId);
		return pstmt.executeUpdate();
	}
	
	public int promoteToTpo(int studentId) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("UPDATE student SET is_tpo=? where Student_Id=?");
		pstmt.setBoolean(1, true);
		pstmt.setInt(2, studentId);
		return pstmt.executeUpdate();
	}

	public List<Student> getStudentsPendingActivation(String dept, String year) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT student_Id,first_name,middle_name,last_name,email,aggregate from student where dept=? and year=? and is_active=false order by first_name");
		pstmt.setString(1, dept);
		pstmt.setString(2, year);
		ResultSet rs = pstmt.executeQuery();

		List<Student> studList = new ArrayList<Student>();


		while (rs.next())
		{
			Student studentObj = new Student();

			studentObj.setStudentId(rs.getInt(1));
			System.out.println("f name is  " +UtilityClass.getCapitalized(rs.getString(2)));
			studentObj.setFirstName(UtilityClass.getCapitalized(rs.getString(2)));
			studentObj.setMiddleName(UtilityClass.getCapitalized(rs.getString(3)));
			studentObj.setLastName(UtilityClass.getCapitalized(rs.getString(4)));
			studentObj.setEmail(rs.getString(5));
			studentObj.setAggregate(rs.getFloat(6));

			studList.add(studentObj);
		}

		return studList;
	}
	
	public List<Student> listAllStudentsForCurrentSelection(String dept, String year) throws ClassNotFoundException, SQLException
	{
		Connection conn = ConnectionManager.getConnection();
		System.out.println("listAllStudentsForCurrentSelection" + dept + year);
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from student where dept=? and year=? order by first_name");
		pstmt.setString(1, dept);
		pstmt.setString(2, year);
		ResultSet rs = pstmt.executeQuery();

		List<Student> studList = new ArrayList<Student>();


		while (rs.next())
		{
			Student studentObj = new Student();

			studentObj.setStudentId(rs.getInt(1));
			studentObj.setFirstName(UtilityClass.getCapitalized(rs.getString(2)));
			studentObj.setMiddleName(UtilityClass.getCapitalized(rs.getString(3)));
			studentObj.setLastName(UtilityClass.getCapitalized(rs.getString(4)));
			studentObj.setEmail(rs.getString(6));
			studentObj.setDept(rs.getString(7));
			studentObj.setYear(rs.getString(8));
			studentObj.setInterest(rs.getString(9));
			studentObj.setAggregate(rs.getFloat(10));
			studentObj.setTpo(rs.getBoolean(11));
			studentObj.setActive(rs.getBoolean(12));
			studentObj.setPlaced(rs.getBoolean(13));

			studList.add(studentObj);
		}

		return studList;
	}
}
