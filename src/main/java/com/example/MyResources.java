package com.example;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import com.example.model.MessageDTO;
import com.example.model.Messages;

@Path("/")
public class MyResources {
	@Inject
	private Messages messages;

	private String userName = "KCG";

	@GET
	@Path("")
	public Viewable home() {
		/**
		 * Viewableの第1引数はテンプレート（.jspファイル）名。
		 * 
		 * MyApplication.java の
		 * JspMvcFeature.TEMPLATE_BASE_PATH
		 * で指定した場所からのパスを書きます。
		 * 
		 * index.jsp の拡張子は省略して index と書けます。
		 * JAX-RS に限らず、フレームワークではこの手の省略がよく見られます。
		 */
		return new Viewable("/index");
	}

	/*
	 * Viewable を用いたViewの呼び出し
	 */
	@GET
	@Path("list")
	public Viewable getMessage() {
		/**
		 *  Viewableの第1引数はテンプレート（.jspファイル）名。
		 *  第2引数はテンプレートへ渡すオブジェクト。
		 *  テンプレート側では model という変数でオブジェクトを受け取れます。
		 */
		return new Viewable("/message", userName);
	}

	/**
	 * Viewableの代わりに@Templateを用いたViewの呼び出し
	 * この場合、name = "テンプレート名"
	 * メソッドの戻り値は、テンプレートに渡すオブジェクトとその型を指定してください。
	 * 下記では、String型のuserNameオブジェクトをテンプレート（.jspファイル）へ渡します。
	 */
	/*
	@GET
	@Template(name = "/message")
	public String getMessage() {
		return userName;
	}
	*/

	/*
	 * @BeanParamを使わない場合
	 */
	@POST
	@Path("list")
	public Viewable postMessage(
			@FormParam("name") String name,
			@FormParam("message") String message) {
		var mes = new MessageDTO();
		mes.setMessage(message);
		mes.setName(name);
		messages.add(mes);
		return new Viewable("/message", userName);
	}

	/**
	 * @BeanParam を使用する場合
	 */
	/*
	@POST
	@Path("list")
	public Viewable postMessage2(@BeanParam MessageDTO mes) {
		messages.add(mes);
		return new Viewable("/message", userName);
	}
	*/

	@GET
	@Path("clear")
	public Response clearMessage() {
		messages.clear();
		// リダイレクト
		return Response.seeOther(URI.create("list")).build();
	}

}
