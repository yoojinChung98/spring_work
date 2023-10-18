<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 브라우저마다 기본적으로 지원되는 디자인을 초기화 시켜주는 CDN -->
    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>Insert title here</title>

<style>
        label {
            display: block;
        }

        .score-list>li {
            margin-bottom: 10px;
        }

        .score-list>li:first-child {
            font-size: 1.2em;
            color: blue;
            font-weight: 700;
            border-bottom: 1px solid skyblue;
        }

        .del-btn {
            width: 10px;
            height: 10px;
            background: red;
            color: #fff;
            border-radius: 5px;
            margin-left: 5px;
            text-decoration: none;
            font-size: 0.7em;
            padding: 6px;
        }

        .del-btn:hover {
            background: orangered;
        }

        section.score {
            padding: 200px 50px 100px;
            font-size: 1.5em;
        }

        .list-header {
            display: flex;
            justify-content: space-between;

            width: 50%;
        }
        .list-header .sort-link-group {
            display: flex;

        }
        .list-header .sort-link-group div {
            margin-right: 20px;
        }

    </style>

</head>
<body>

	<div class="wrap">
		<section class="score">
		
			<h1>시험 점수 등록</h1>
            <form action="/basic/score/register" method="POST">
                <label>
                    # 이름: <input type="text" name="name">
                </label>
                <label>
                    # 국어: <input type="text" name="kor">
                </label>
                <label>
                    # 영어: <input type="text" name="eng">
                </label>
                <label>
                    # 수학: <input type="text" name="math">
                </label>
                <label>
                    <button type="submit">확인</button>
                    <button id="go-home" type="button">홈화면으로</button>
                </label>
            </form>
            
            <hr/>

            <ul class="score-list">
                <li class="list-header">
                    <div class="count">총 학생수: ${sList.size()}명</div>
                    <div class="sort-link-group">
                        <div><a href="#">학번 순</a></div>
                        <div><a href="#">이름 순</a></div>
                        <div><a href="#">평균 순</a></div>
                    </div>
                </li>
                
               	<c:forEach var="s" items="${sList}">
	                <li>
	                    # 학번: ${s.stuNum}, 이름: <a href="/basic/score/detail?stuNum=${s.stuNum}">${s.maskingName}</a>,
                        평균: ${s.average}, 학점: ${s.grade}
	                    <a href="/basic/score/remove?stuNum=${s.stuNum}" class="del-btn">삭제</a>                	
    	            </li>
               	</c:forEach>
            </ul>
            
		</section>
	</div>

    <script>
        const $ul = document.querySelector('.score-list');

        //$ul 에 클릭 이벤트가 발생하면 하단의 화살표함수 실행 (이벤트 전파 방식)
        // $ul.addEventListener('click', function(e) {
        $ul.addEventListener('click', e => {
            
            //일일이 이벤트를 발생시키기 귀찮을 때 이벤트 전파 방식 사용
            //원하는 것은 부모가 아닌 자식을 눌렀을 때 이벤트 발생!
            //(원하는 것이 아닌 것을 눌렀을 때는 반응 안하도록 할 것임)
            if(!e.target.matches('a.del-btn')) {
                return; //이벤트 강제 종료
            }
            e.preventDefault(); // a태그의 기본 기능 정지

            
            if(confirm('정말 삭제하시겠습니까?')) {
                // 삭제 진행
                //e.target = a태그
                //location.href == sendRedirect();
                location.href = e.target.getAttribute('href');
            }else {
                return; //(false)시 삭제 취소
                
            }
        });

    </script>
</body>
</html>