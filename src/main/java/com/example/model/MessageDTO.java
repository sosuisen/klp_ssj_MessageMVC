package com.example.model;

import javax.ws.rs.FormParam;

@lombok.Data
public class MessageDTO {
	@FormParam("message")
	private String message;
	@FormParam("name")
	private String name;
}

