package com.tutorials.restapi.web_controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.tutorials.restapi.model.Error;
import com.tutorials.restapi.model.User;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setEmail(request.getParameter("email"));
		user.setName(request.getParameter("name"));
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/restapi/webapi")
				.path("users")
				.path("add_user");
		com.tutorials.restapi.model.Client cli = (com.tutorials.restapi.model.Client) request
				.getSession()
				.getAttribute("Client");
		Response res = target
				.register(HttpAuthenticationFeature.basic(cli.getUsername(), cli.getAuthKey()))
				.request()
				.post(Entity.json(user));
		if(res.getStatus()==201)
		{
			response.sendRedirect("success.html");
		}
		else if(res.getStatus()==400)
		{
			request.getSession().setAttribute("Error", new Error(400,"User Already registered"));
			response.sendRedirect("error.jsp");
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
