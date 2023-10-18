<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>게시글 등록</h2>
	<!-- 
	 Form 태그의 action을 생략하면, 이 페이지를 열기위해 들어왔던 마지막 요청(get)을 재활용함.
	 (마지막요청: /board/write: GET , 생성될요청: /board/write: POST)
	 -->
    <form method="post">
        <p>
            # 작성자: <input type="text" name="writer"> <br>
            # 제목: <input type="text" name="title"> <br>
            # 내용: <textarea rows="3" name="content"></textarea> <br>
            <input type="submit" value="등록"> 
        </p>
    </form>	

</body>
</html>