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
	<h1>ログイン</h1>
	<c:forEach var="errorMessage" items="${errorMessage}">
    	<p style="color:red" class="error">${errorMessage}</p>
	</c:forEach>
	<form action="LoginServlet" method="post">
		ユーザーID：<input type="text" name="userId"><br>
		パスワード：<input type="password" name="pass"><br>
		<input type="submit" value="ログイン">
	</form>
	<a href="index.jsp">▶トップへ</a>
</body>
</html>