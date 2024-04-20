<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>メモウェブ</title>
</head>
<body>
	<h1>登録確認</h1>
	<p>下記のユーザーを登録します</p>
	<p>
		ユーザーID：<c:out value="${registerAccount.userId}" /><br>
		名前：<c:out value="${registerAccount.name}" /><br>
	</p>
	<a href="RegisterServlet?action=done">▶登録</a><br>
	<a href="RegisterServlet">▶戻る</a><br>
</body>
</html>