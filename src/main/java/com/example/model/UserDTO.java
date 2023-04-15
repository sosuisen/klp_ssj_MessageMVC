package com.example.model;

import javax.ws.rs.FormParam;

@lombok.Data
public class UserDTO {
	@FormParam("name")
	private String name;
	@FormParam("password")
	private String password;
}
