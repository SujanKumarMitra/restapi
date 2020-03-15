package com.tutorials.restapi.web_controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import com.tutorials.restapi.model.User;

/**
 * Servlet implementation class GetAllUsers
 */
public class GetAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/restapi/webapi")
				.path("users")
				.path("get_users");
		com.tutorials.restapi.model.Client cli = (com.tutorials.restapi.model.Client) request
				.getSession()
				.getAttribute("Client");
		Response res = target
				.register(HttpAuthenticationFeature.basic(cli.getUsername(), cli.getAuthKey()))
				.request()
				.get();

		ArrayList<User> users = null;
		if(res.getStatus()==202)
		{
			users = res.readEntity(new GenericType<ArrayList<User>>() { });
		}
		request.getSession().setAttribute("All Users", users);
		response.sendRedirect("all_users.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
