package com.tutorials.restapi.web_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.restapi.database.CRUD;
import com.tutorials.restapi.model.Error;
import com.tutorials.restapi.model.Client;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username =  request.getParameter("username");
		String password = request.getParameter("password");
		Client client = CRUD.authorizeUser(username, password);
		if(client == null)
		{
			request.getSession().setAttribute("Error", new Error(302,"User Not Found"));
			response.sendRedirect("error.jsp");
		}
		else
		{
			request.getSession().setAttribute("Client", client);
			response.sendRedirect("menu.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
