package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Messagesクラスに@ApplicationScopedアノテーションを付けることで、
 * CDI管理Beanとして宣言しています。
 * このクラスがインスタンス化されるときのライフサイクルは
 * Application Scope（アプリが起動してから終了するまで）となります。
 */
/**
 * CDI管理Beanに名前を付ける場合、@Namedアノテーションを用います。
 * 例えば、@Named("FOO")と付けると"FOO"という名前になりますが、
 * 引数を省略すると、クラス名の先頭を小文字にした名前、
 * 下記では "messages" という名前になります。
 * 例えば、JSP内ではこの名前でインスタンスを参照することができます。
 */
@ApplicationScoped
@Named
public class Messages extends ArrayList<MessageDTO> implements Serializable {

}
