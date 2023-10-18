<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1 class="result-title">
	
	</h1>


	<p>
		
	<%-- 
		<c:choose>
			<c:when test="${result.equals('success')}">
				로그인이 성공적으로 이루어졌습니다.
			</c:when>
			<c:when test="${result.equals('f-id')}">
				아이디가 잘못 입력되었습니다.<br/>
				<a href="/basic/hw/s-login-form">다시 입력하기</a>
			</c:when>
			<c:when test="${result.equals('f-pw')}">
				비밀번호가 잘못 입력되었습니다.<br/>
				<a href="/basic/hw/s-login-form">다시 입력하기</a>
			</c:when>
		</c:choose>
	--%>
	
	<%--
	 	<c:if test="${result eq 'f-id'}">아이디가 존재하지 않습니다.</c:if>
		<c:if test="${result == 'f-pw'}">비밀번호가 틀렸습니다.</c:if>
		<c:if test="${result == 'success'}">로그인 성공</c:if>
		<%-- 포워딩 된 시점에서 모든 값들은 다 html에서 사용될 수 있도록 가공이 된 상태. -->
	--%>
	
	
	</p>


	<script>
	
	/*
	 브라우저가 HTML을 해석하고, css를 해석하고, javascript 코드를 실행해서 화면에 표현하는 과정을 렌더링 이라고 합니다.
	 지금 우리가 작성하는 파일 형태 -> jsp -> 서버 내에서 클래스로 변환 -> 응답은 HTML
	 즉, 서버 내에서 jsp파일이 class로 변환하는 과정에서 작성한 el 문법은 자바 코드로 변환 -> 값을 표현 -> 응답은 HTML로 나옴(표현).
	 
	 EL 표현식이 서버에서 먼저 평가되고 그 결과를 클라이언트에게 전달하여 브라우저로 표현하기 때문에 script에서도 EL 표현이 가능
	 javaScript에서 EL 표현식을 작성할 때는 문자열로 감싸주세요. (안그러면 자바스크립트가 지들 문법인 줄 알고 저걸 해석하고 있거든...)
	 그냥 문자열로 묶어서 자바스크립트는 무시하고 넘어갈 수 있도록 하고, 나중에 class 파일로 변환되는 과정에서 EL 로써 해석될 수 있도록 하는 것!
	 
	 즉, 모델에 담아서 전달한 파라미터를 EL표현식을 통해 javaScript에서도 사용할 수 있다는 말!
	 */
		const result = '${result}';
		//console.log('result: '+result);
		const $h1 = document.querySelector('.result-title');
		switch(result){
		
		case 'f-id':
			$h1.textContent = '아이디가 존재하지 않습니다.';
			break;
		
		case 'f-pw':
			$h1.textContent = '비밀번호가 틀렸습니다.';
			break;
			
		case 'success':
			$h1.textContent = '로그인 성공!';
			break;
		}
	</script>

</body>
</html>