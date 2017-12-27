package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import constants.ProjectConstants;


public class ConnectionManager
{
	public static Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/placementportal", ProjectConstants.dbUserName,ProjectConstants.dbPassword );
	}
}
