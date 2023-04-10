package com.example.model;

/*
 * @BeanParam を使わない場合
 */
@lombok.Data
public class MessageDTO {
	private String message;
	private String name;
}

/**
 * @BeanParam を使用する場合
 */
/*
@lombok.Data
public class MessageDTO {
	@FormParam("message")
	private String message;
	@FormParam("name")
	private String name;
}
*/
