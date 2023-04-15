<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../app.css" rel="stylesheet">
<title>メッセージの累積</title>
</head>
<body>
	${model}さん、こんにちは！
	<form action="list" method="POST">
		投稿者名：<input type="text" name="name"> メッセージ：<input type="text"
			name="message">
		<button>送信</button>
	</form>
	<form action="clear" method="GET">
		<button>Clear</button>
	</form>
	<hr>
	<h1>メッセージ一覧</h1>
	<c:forEach var="mes" items="${messages}">
		<div>${mes.name}:${mes.message}</div>
	</c:forEach>
	<p>
		<a href="./login">ログアウト</a>
	</p>
</body>
</html>
