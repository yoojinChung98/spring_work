<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>게시글 목록</h2>
	<table boarder="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>비고</th>
		</tr>
		<c:forEach var="article" items="${articles}">
			<tr>
				<td>${article.boardNo}</td>
				<td>
					<a href="/basic/board/content?boardNo=${article.boardNo}">${article.title}</a>
				</td>
				<td>${article.writer}</td>
				<td>
					<fmt:parseDate value="${article.regDate}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
                    <fmt:formatDate value="${parsedDateTime}" pattern="yyyy년 MM월 dd일 HH시 mm분" />
				</td>
				<td><a href="/basic/board/delete?boardNo=${article.boardNo}">[삭제]</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/basic/board/write">게시물 작성하기</a>

</body>
</html>