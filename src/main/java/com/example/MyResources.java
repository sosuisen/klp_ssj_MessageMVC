package com.example;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import com.example.model.MessageBean;
import com.example.model.Messages;

@Path("")
public class MyResources {
	@Inject
	private Messages messages;
	
	@GET
	@Path("/")
	public Viewable home() {
		return new Viewable("/index.jsp");
	}

	@GET
	@Path("/list")
	public Viewable getMessage() {
		return new Viewable("/message.jsp");
	}

	@POST
	@Path("/list")
	public Viewable postMessage(
			@FormParam("name") String name,
			@FormParam("message") String message) {
		messages.add(new MessageBean(name, message));
		return new Viewable("/message.jsp");
	}

	@GET
	@Path("/clear")
	public Response clearMessage() {
		messages.clear();
		// リダイレクト
		return Response.temporaryRedirect(URI.create("list")).build();
	}

}
