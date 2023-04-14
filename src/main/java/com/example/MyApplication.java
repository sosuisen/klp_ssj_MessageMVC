package com.example;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

@ApplicationPath("message")
public class MyApplication extends ResourceConfig {
	public MyApplication() {
		/**
		 * Jersey（JAX-RS仕様の実装の１つ）は、クライアントからのリクエストに対してリソースを返します。
		 * リソースを表現するクラス（例えばMyResource）がどのパッケージに属しているのかを
		 * package()で指定してください。システムは指定パッケージのクラスからリソースを探します。
		 */
		packages("com.example");
		
		/**
		 * Jersey MVCの設定
		 * 以降は、JAX-RSをベースにMVCパターンのWebアプリを実現する
		 * Jersey独自のMVC拡張のための設定です。
		 * 現在では、Jersey MVCを参考にして作られた
		 * Jakarta MVCというJakarta EE標準仕様がありますが、
		 * まだ情報が少ないのでJersey MVCを用いています。
		 */
		
		/**
		 * Viewの設定
		 */
		/**
		 * ViewとしてJSPを利用するためのクラスをregisterで登録します。
		 * 
		 * 他のテンプレートエンジンも指定することができ、
		 * 標準では、Mustache、Freemarker、JSPの3つに対応しています。
		 * Thymeleafを使いたい場合は「Thymeleaf JAX-RS」で検索
		 */
		register(JspMvcFeature.class);
		
		// JSPで書かれたテンプレートが保存されているパスを指定
		property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/templates");
		
		/**
		 * 参考資料
		 * JavaによるRESTfulシステム構築
		 * https://www.oreilly.co.jp/books/9784873114675/
		 * 
		 * Jersey 公式ユーザガイド（英語）
		 * https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/index.html
		 */
	}
}
