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
	<h1>メモウェブログイン</h1>
	<c:choose>
		<c:when test="${result}">
			<p>ようこそ<c:out value="${loginUser.userId}" />さん</p>
			<a href="Main">▶ホームへ</a>
		</c:when>
		<c:otherwise>
			<p>ログインできませんでした</p><br>
			<a href="LoginServlet">▶ログイン画面へ戻る</a><br>
			<a href="index.jsp">▶トップへ</a><br>
		</c:otherwise>
	</c:choose>
</body>
</html>