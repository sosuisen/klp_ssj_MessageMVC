package com.example;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

@ApplicationPath("message")
public class MyApplication extends ResourceConfig {
	public MyApplication() {
		// Java EE環境下では @Path で注釈されたクラスをリソースクラス、
		// @Provider で注釈されたクラスをプロバイダーと認識して自動で登録してくれるため、
		packages("com.example"); //のようにリソースのパッケージの指定は不要
		register(JspMvcFeature.class);
		property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/templates");
	}
}
