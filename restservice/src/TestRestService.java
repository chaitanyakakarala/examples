package org.mycomp.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class TestRestService {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param")String msg) {
		System.out.println(" We are hitting webservice ");
		String output = "Jersey say : " + msg;
		return Response.status(200).entity(output).build();
	}
}