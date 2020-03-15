package com.tutorials.restapi.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.tutorials.restapi.model.Client;
import com.tutorials.restapi.model.User;

public class CRUD implements Properties{
	static Connection con = null;
	static PreparedStatement stmt = null;
	static ResultSet rs = null;
	public static int USER_CREATED = 1;
	public static int USER_NOT_CREATED = -1;
	public static int USER_EXISTS = 5;
	public static int USER_UPDATED = 2;
	public static int USER_NOT_UPDATED = -2;
	public static int USER_DELETED = 3;
	public static int USER_NOT_DELETED = -3;
	public static int USER_AUTHORIZED = 4;
	public static int USER_NOT_AUTHORIZED = -4;
	public static int addUser(User user) throws SQLException
	{
		try
		{
			if(user == null)
			{
				return USER_NOT_CREATED;
			}
			con = Properties.getConnection();
			stmt = con.prepareStatement("INSERT INTO users(name,email)VALUES(?,?)");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			int res = stmt.executeUpdate();
			if(res > 0)
			{
				return USER_CREATED;
			}
			return USER_NOT_CREATED;
			
		}
		catch (MySQLIntegrityConstraintViolationException e) {
			return USER_EXISTS;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			stmt.close();
			if(rs != null)
			{
				rs.close();
			}
		}
		return USER_NOT_CREATED;
	}
	public static User getUser(String email) throws SQLException
	{
		User user = null;
		try
		{
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * FROM users WHERE email = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				return user;
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			stmt.close();
			if(rs != null)
			{
				rs.close();
			}
		}
		return user;
	}
	public static ArrayList<User> getUsers() throws Exception
	{
		ArrayList<User> users = null;
		User user = null;
		try
		{
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * FROM users");
			rs = stmt.executeQuery();
			users = new ArrayList<User>();
			while(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			stmt.close();
			if(rs != null)
			{
				rs.close();
			}
		}
		return users;
	}
	public static int updateUser(User user) throws SQLException
	{
		try
		{
			if(user == null)
			{
				return USER_NOT_UPDATED;
			}
			con = Properties.getConnection();
			stmt = con.prepareStatement("UPDATE users SET name = ?,email = ? WHERE id = ?");
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setInt(3, user.getId());
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return USER_UPDATED;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			stmt.close();
			if(rs != null)
			{
				rs.close();
			}
		}
		return USER_NOT_UPDATED;
	}
	public static int deleteUser(String email) throws SQLException
	{
		try
		{
			con = Properties.getConnection();
			stmt = con.prepareStatement("DELETE from users WHERE email = ?");
			stmt.setString(1,email);
			int res = stmt.executeUpdate();
			if(res>0)
			{
				return USER_DELETED;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			con.close();
			stmt.close();
			if(rs != null)
			{
				rs.close();
			}
		}
		return USER_NOT_DELETED;
	}
	public static Client authorizeUser(String username,String key)
	{
		Client client = null;
		try {
			con = Properties.getConnection();
			stmt = con.prepareStatement("SELECT * from auth where username = ? and auth_key = ?");
			stmt.setString(1,username);
			stmt.setString(2, key);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setUsername(rs.getString("username"));
				client.setAuthKey(rs.getString("auth_key"));
				return client;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	public static int addClient(Client client) {
		try
		{
			if(client == null)
			{
				return USER_NOT_CREATED;
			}
			con = Properties.getConnection();
			stmt = con.prepareStatement("INSERT INTO auth(username,auth_key)VALUES(?,?)");
			stmt.setString(1, client.getUsername());
			stmt.setString(2, client.getAuthKey());
			int res = stmt.executeUpdate();
			if(res > 0)
			{
				return USER_CREATED;
			}
			return USER_NOT_CREATED;
			
		}
		catch (MySQLIntegrityConstraintViolationException e) {
			return USER_EXISTS;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(rs != null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return USER_NOT_CREATED;
	}
}
