<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Model 객체에 대해 알아보기</h2>
	
	<a href="/basic/response/test?age=30">테스트1 페이지로~</a>
	
	<hr/>
	
	<form action="/basic/response/test2">
		<input type="text" name="userId"> <br/>
		<input type="text" name="userName"> <br/>
		<input type="submit" value="확인"> <br/>
	</form>
	
	<hr/>
	
	<a href="/basic/response/test3">테스트3 페이지로~</a>

</body>
</html>