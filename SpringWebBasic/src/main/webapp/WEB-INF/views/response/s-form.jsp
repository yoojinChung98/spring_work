<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
		label {
			display: block;
		}
	</style>

</head>
<body>

<h1>로그인하기~</h1>

    <form action="/basic/hw/s-login-check" method="post">
        <label>
            # 아이디 : <input type="text" name="id">
        </label>
        <label>
            # 비밀번호 : <input type="password" name="pw">
        </label>
        <label>
            <button type="submit">로그인</button>
        </label>
    </form>

</body>
</html>