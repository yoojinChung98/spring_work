<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 파일 업로드는 기본적으로  post 방식 전송을 진행함.
		enctype(인코딩 타입)을 "multipart/form-data"로 반드시 지정. -->
	<form action="${pageContext.request.contextPath}/fileupload/upload_ok" method="post" enctype="multipart/form-data">
		파일 선택: <input type="file" name="file"/> <br/>
		<input type="submit" value="전송"/>
	</form>
	<hr/>
	
	
	
	
	<form action="${pageContext.request.contextPath}/fileupload/upload_ok2" method="post" enctype="multipart/form-data">
		파일 선택: <input type="file" name="files" multiple="multiple"> <br>
		<input type="submit" value="전송">
	</form>
	<hr/>
	
	
	<form action="${pageContext.request.contextPath}/fileupload/upload_ok3" method="post" enctype="multipart/form-data">
		파일 선택: <input type="file" name="file"/> <br/>
		파일 선택: <input type="file" name="file"/> <br/>
		파일 선택: <input type="file" name="file"/> <br/>
		<input type="submit" value="전송"/>
	</form>
	
</body>
</html>