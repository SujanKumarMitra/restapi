package com.tutorials.restapi.database;

import java.sql.*;

public interface Properties {
	String url="jdbc:mysql://localhost:3306/rest_db";
	String username= "root";
	String password ="root";
	
	public static Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, username, password);
	}
}
