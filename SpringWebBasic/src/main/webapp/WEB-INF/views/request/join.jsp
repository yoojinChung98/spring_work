<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>요청 파라미터값 테스트!</h2>
        
        <form action="/basic/request/join" method="post">
            <fieldset>
                <legend>회원 가입 양식</legend>
                <p>
                    # ID: <input type="text" name="userId" size="10"> <br>
                    # PW: <input type="password" name="userPw" size="10"> <br>
                    # NAME: <input type="text" name="userName" size="10"> <br>
                    # HOBBY:
                    <input type="checkbox" name="hobby" value="soccer"> 축구 &nbsp; 
                    <input type="checkbox" name="hobby" value="read"> 독서 &nbsp; 
                    <input type="checkbox" name="hobby" value="music"> 음악감상 &nbsp;
                    <br>
                    <input type="submit" value="확인"> 
                </p>
            </fieldset>
        </form>

</body>
</html>