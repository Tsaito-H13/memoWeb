<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/hamburger.css">
	<title>メモウェブ</title>
</head>
<body>
	<h1>メモウェブHOME</h1>
	<div id="nav-wrapper" class="nav-wrapper">
	<div class="hamburger" id="js-hamburger">
		<span class="hamburger__line hamburger__line--1"></span>
	    <span class="hamburger__line hamburger__line--2"></span>
	    <span class="hamburger__line hamburger__line--3"></span>
	</div>
	<nav class="sp-nav">
		<ul>
	    	<li><a href="MemoServlet">メモ一覧へ</a></li>
	      	<li><a href="MemoGarbageServlet">削除したメモ一覧</a></li>
	      	<li><a href="LogoutServlet">ログアウト</a></li>
	     	<li><a href="SecessionServlet">退会</a></li>
	    </ul>
	</nav>
	 	<div class="black-bg" id="js-black-bg"></div>
	</div>
	<p>
		<c:out value="${loginUser.userId}" />さん、こんにちは
	</p>
  	<c:forEach var="errorMessage" items="${errorMessage}">
 		<p style="color:red" class="error">${errorMessage}</p>
	</c:forEach>
    <form action="Main" method="post">
      	<input type="text" name="title" size="50"/>
      	<br/>
      	<textarea rows="5" cols="80" name="memo"></textarea>
      	<br/>
      	<input type="submit" value="メモ" />
    </form>
	<script src="${pageContext.request.contextPath}/js/hamburger.js"></script>
</body>
</html>