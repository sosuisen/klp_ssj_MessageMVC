package com.example.model;

import javax.ws.rs.FormParam;

@lombok.Data
public class MessageDTO {
	@FormParam("message")
	private String message;
	@FormParam("name")
	private String name;
}
/*
 * @BeanParam を使わない場合
public class MessageBean {
	private String message;
	private String name;
}
*/