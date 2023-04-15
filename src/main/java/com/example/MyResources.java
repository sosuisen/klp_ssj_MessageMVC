package com.example;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.glassfish.jersey.server.mvc.Viewable;

import com.example.model.MessageDTO;
import com.example.model.Messages;
import com.example.model.UserDTO;

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
	
	@GET
	@Path("login")
	public Viewable getLogin() {
		return new Viewable("/login");		
	}

	@POST
	@Path("login")
	public Viewable postLogin(@BeanParam UserDTO user) {
		if (user.getName().equals("kcg") && user.getPassword().equals("foo")) {
			// return Response.temporaryRedirect(URI.create("list")).build();
			/**
			 * リダイレクトは上のように書きたいところですが、
			 * postLoginメソッドは、Response型とViewable型という
			 * 二種類の型の戻り値を持つことはできないため、
			 * こちらもViewableを使います。
			 * リダイレクトのために、JSTLのリダイレクトタグを用います。
			 * redirect.jsp 参照。
			 */
			return new Viewable("/redirect", "list");
		}
		var error = "ユーザ名またはパスワードが異なります"; 
		return new Viewable("/login", error);
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
	
	@POST
	@Path("list")
	public Viewable postMessage(@BeanParam MessageDTO mes) {
		messages.add(mes);
		return new Viewable("/message", userName);
	}

	@GET
	@Path("clear")
	public Viewable clearMessage() {
		messages.clear();
		return new Viewable("/redirect", "list");
	}

}
