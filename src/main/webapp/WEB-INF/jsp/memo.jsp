<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css">
	<title>メモウェブ</title>
</head>
<body>
	<h1>メモ一覧</h1>
	<ul>
	<c:forEach var="memo" items="${memoList}">
		<li class="title" onclick="openModal('${memo.memoId}')"><c:out value="${memo.title}" /></li><br>
		<div id="modal-${memo.memoId}" class="modal">
			<div class="modal-content">
				<span class="close" onclick="closeModal('${memo.memoId}')">&times;</span>
				<div>作成日：<c:out value="${memo.createDate}" /></div>
				<div>更新日：<c:out value="${memo.modifiedDate}" /></div>
				<div>タイトル：<c:out value="${memo.title}" /></div>
				<div>内容：<c:out value="${memo.memo}" /></div>
				<form action="MemoEditServlet" method="get">
					<input type="hidden" name="memoId" value="${memo.memoId}">
					<input type="submit" value="編集">
				</form>
				<form action="MemoDeleteServlet" method="post">
					<input type="hidden" name="memoId" value="${memo.memoId}">
					<input type="hidden" name="deleteMethod" value="logical">
					<input type="submit" value="削除">
				</form>
			</div>
		</div>
	</c:forEach>
	</ul>
	<a href="Main">ホームへ戻る</a><br>
		<script src="${pageContext.request.contextPath}/js/modal.js"></script>
</body>
</html>