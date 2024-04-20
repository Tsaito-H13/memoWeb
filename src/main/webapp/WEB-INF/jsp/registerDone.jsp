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
	<c:choose>
		<c:when test="${registerResult}">
			<p>登録完了しました</p>
			<a href="LoginServlet">▶ログインへ</a><br>
			<a href="index.jsp">▶トップへ</a><br>
		</c:when>
		<c:otherwise>
			<p>登録できませんでした</p>
			<a href="RegisterServlet">▶登録画面へ戻る</a>
		</c:otherwise>
	</c:choose>
</body>
</html>