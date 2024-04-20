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
	<h1>会員登録</h1>
	<c:forEach var="errorMessage" items="${errorMessage}">
    	<p style="color:red" class="error">${errorMessage}</p>
	</c:forEach>
	<form action="RegisterServlet" method="post">
		ユーザーID：<input type="text" name="userId"><br>
		パスワード：<input type="password" name="pass"><br>
		名前：<input type="text" name="name"><br>
		<input type="submit" value="登録">
	</form><br>
	<a href="index.jsp">▶トップへ</a>
</body>
</html>