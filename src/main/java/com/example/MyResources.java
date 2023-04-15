package com.example;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

import com.example.model.MessageDTO;
import com.example.model.Messages;

@ApplicationScoped
@Path("/")
public class MyResources {
	// データの保存先を適切なパスへ変更してください。
	private String saveFilePath = "c:\\pleiades-ssj2023\\data.json";

	/**
	 * MyResourcesがインスタンス化された後、
	 * この@Injectアノテーションは、
	 * Messages.javaで宣言されたCDI管理Beanである
	 * Messagesクラスを自動的にインスタンス化して、messagesへインジェクションします。
	 */
	@Inject
	private Messages messages;

	/**
	 * 上のインスタンスが生成された直後に実行する処理です。
	 * MessagesクラスはApplication　Scopeであるため、
	 * 生成されるのはアプリが起動したときです。
	 * saveFilePathからJSON文字列を読み込んで、
	 * Messagesオブジェクトへ変換し、messagesへセットしています。
	 */
	@PostConstruct
	public void prepare() {
		Jsonb jsonb = JsonbBuilder.create();
		try {
			String json = Files.readString(java.nio.file.Path.of(saveFilePath));
			messages.addAll(jsonb.fromJson(json, Messages.class));
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	/**
	 * 上のインスタンスが破壊される直前に実行する処理です。
	 * MessagesクラスはApplication　Scopeであるため、
	 * 破壊されるのはアプリが終了するときです。
	 * messagesの内容をJSON文字列へ変換して、saveFilePathへ保存しています。
	 */
	@PreDestroy
	public void after() {
		Jsonb jsonb = JsonbBuilder.create();
		String json = jsonb.toJson(messages);
		try {
			Files.writeString(java.nio.file.Path.of(saveFilePath), json);
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	private String userName = "KCG";

	/**
	 * @Path("")は、
	 * http://localhost:8080/MessageMVC/message
	 * または
	 * http://localhost:8080/MessageMVC/message/
	 * にマッチします。
	 * 今回の index.jsp　は、
	 * http://localhost:8080/MessageMVC/message/
	 * を開かないと、リンクが正しく動作しないので注意。
	 */
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
	public Response postLogin() {
		return Response.seeOther(URI.create("list")).build();
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
	public Response clearMessage() {
		messages.clear();
		// リダイレクト
		return Response.seeOther(URI.create("list")).build();
	}

}
