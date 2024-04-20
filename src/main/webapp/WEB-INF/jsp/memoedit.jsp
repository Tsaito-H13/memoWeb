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
	<h1>メモ編集</h1>
	<c:forEach var="errorMessage" items="${errorMessage}">
    	<p style="color:red" class="error">${errorMessage}</p>
	</c:forEach>
	<p>
	 	<form action="MemoEditServlet" method="post">
  			<input type="text" name="title" size="50"/>
  			<br/>
  			<textarea rows="5" cols="80" name="memo"></textarea>
  			<br/>
  			<input type="hidden" name="memoId" value="${memoId}">
  			<input type="submit" value="編集">
 		</form>
 	</p>
 	<a href="MemoServlet">メモ一覧へ</a>
</body>
</html>