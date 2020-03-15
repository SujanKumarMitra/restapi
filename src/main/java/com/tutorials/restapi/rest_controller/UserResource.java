package com.tutorials.restapi.rest_controller;



import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.core.util.Base64;
import com.tutorials.restapi.database.CRUD;
import com.tutorials.restapi.model.Client;
import com.tutorials.restapi.model.User;

@Path("users")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserResource {
	@GET
	@Path("get_users")
	public Response getUsers(@HeaderParam("Authorization") String key) {
		try {
			System.out.println("getUsers called");
			String credentials[] = new String(Base64.base64Decode(key.substring("Basic ".length()))).split(":");
			Client client = CRUD.authorizeUser(credentials[0], credentials[1]);
			if(client == null)
			{
				return Response.status(403).build();
			}
			ArrayList<User> users = CRUD.getUsers();
			if(users != null)
			{
				GenericEntity<ArrayList<User>> entity = new GenericEntity<ArrayList<User>>(users) {};
				return Response.accepted().entity(entity).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.noContent().build();
		
	}
	@Path("add_user")
	@POST
	public Response addUser(User user, @Context UriInfo uri,@HeaderParam("Authorization") String key)
	{
		System.out.println("adduser called");
		try
		{
			String credentials[] = new String(Base64.base64Decode(key.substring("Basic ".length()))).split(":");
			Client client = CRUD.authorizeUser(credentials[0], credentials[1]);
			if(client == null)
			{
				return Response.status(404).build();
			}
			int res = CRUD.addUser(user);
			if(res == CRUD.USER_CREATED)
			{
				return Response.created(new URI(uri.getAbsolutePathBuilder().path(user.getEmail()).toString())).build();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	@Path("get_user/{email}")
	@GET
	public Response getUser(@PathParam("email") String email,@HeaderParam("Authorization") String key)
	{
		try
		{
			String credentials[] = new String(Base64.base64Decode(key.substring("Basic ".length()))).split(":");
			Client client = CRUD.authorizeUser(credentials[0], credentials[1]);
			if(client == null)
			{
				return Response.status(404).build();
			}
			System.out.println("getUser called");
			User user = CRUD.getUser(email);
			if(user == null)
			{
				return Response.status(Status.NOT_FOUND).build();
			}
			return Response.status(Status.FOUND).entity(user).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Path("update_user")
	@PUT
	public Response updateUser(User user,@HeaderParam("Authorization") String key)
	{
		try
		{
			String credentials[] = new String(Base64.base64Decode(key.substring("Basic ".length()))).split(":");
			Client client = CRUD.authorizeUser(credentials[0], credentials[1]);
			if(client == null)
			{
				return Response.status(404).build();
			}
			System.out.println("updateUser called");
			int res = CRUD.updateUser(user);
			if(res == CRUD.USER_UPDATED)
			{
				return Response.status(200).entity(user).build();
			}
			return Response.status(404).build();
		}
		catch (Exception e) {
			return null;
		}
	}
	@Path("delete_user/{email}")
	@DELETE
	public Response deleteUser(@PathParam("email") String email,@HeaderParam("Authorization") String key)
	{
		try
		{
			System.out.println("deleteUser called.");
			int res = CRUD.deleteUser(email);
			if(res==CRUD.USER_DELETED)
			{
				return Response.ok().build();
			}
			return Response.status(404).build();
			
		}
		catch (Exception e) {
			return Response.status(404).build();
		}
	}
	
}
