<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    <style>
        li {
            list-style: none;
            margin: 0;
            padding: 0;
            font-size: 1.4em;
        }

        section.score-main {
            width: 30%;
            margin: 0 auto 150px;
            padding: 20px;
            border: 2px solid orange;
            border-radius: 10px;
            box-shadow: 2px 2px 5px orangered;
            transform: translateY(200px);
        }

        a {
            display: block;
            width: fit-content;
            text-decoration: none;
            border-radius: 5px;
            border: 1px solid white;
            padding: 5px;
            margin-right: 10px;
        }

        a.list-btn {
            background: rgb(83, 189, 83);
            color: white;
            box-shadow: 1px 1px 2px rgb(146, 228, 146);
        }
        a.mod-btn {
            background: rgb(228, 248, 49);
            color: #333;
            box-shadow: 1px 1px 2px rgb(250, 240, 105);
            border-radius: 5px;
        }
    </style>

</head>
<body>

	<c:choose>
	<c:when test="${s eq null}">
		<h1>존재하지 않는 학생이거나, 잘못된 접근입니다.</h1>
		<a href="/basic/score/list" class="list-btn">목록</a>
	</c:when>
	
	<c:otherwise>
	    <div class="wrap">
	        <section class="score-main">
	            <h1>${s.stuName}님 성적 정보</h1>
	            <ul>
	                <li># 국어: ${s.kor}점</li>
	                <li># 영어: ${s.eng}점</li>
	                <li># 수학: ${s.math}점</li>
	                <li># 총점: ${s.total}점</li>
	                <li># 평균: ${s.average}점</li>
	                <li># 학점: ${s.grade}</li>
	            </ul>
	            <div class="btn-group">
	                <a href="/basic/score/list" class="list-btn">목록</a>
	                <a href="/basic/score/modify?stuNum=${s.stuNum}" class="mod-btn">수정</a>
	            </div>
	        </section>
	    </div>
	</c:otherwise>
	</c:choose>

</body>
</html>