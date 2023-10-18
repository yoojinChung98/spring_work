<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

    이름: <input type="text" name="name"/> <br/>

    나이: <input type="text" name="age"/> <br/>

    취미:
    <input type="checkbox" name="hobby" value="soccer"> 축구
    <input type="checkbox" name="hobby" value="music"> 음악감상
    <input type="checkbox" name="hobby" value="game"> 게임

    <button type="button" id="send">요청 보내기!</button>


    <script>
        const $sendBtn = document.getElementById('send');
        $sendBtn.onclick = function(e) {
            const name = document.querySelector('input[name=name]').value;
            const age = document.querySelector('input[name=age]').value;
            const $hobby = document.querySelectorAll('input[name=hobby]');

            const arr = []; //checkbox에서 체크가 된 요소를 넣기 위한 배열
            
            
            // querySelectorAll 의 리턴값은 NodeList라는 유사 배열 형태
            //배열메서드를 쓰고싶어서 hobby태그의 value를 실제 배열로 바꾸는 작업. [...유사배열].forEach(변수 => ) 로 간편하게 변환 가능!
            //반복문 한바퀴당 유사배열의 하나의 값이 변수에 할당됨. 향상포문과 비슷하게 작동
            [...$hobby].forEach($check => {
                if($check.checked) {
                    arr.push($check.value);
                }
            });

            console.log(name);
            console.log(age);
            console.log(arr);

            
            //# http 요청을 서버로 보내는 방법
            //1. XMLHttpRequest 객체를 생성.
            const xhr = new XMLHttpRequest();
            
            /*
            2. http 요청 설정 (요청방식, 요청URL) 세팅
            - 요청방식 (REST 방식엔 규약이 있다,,? 이해 모담)(요청 방식만 보고도 어떤 요청인지 얼추 알 수 있음)
            a. GET - 조회
            b. POST - 등록
            c. PUT - 수정
            d. DELETE - 삭제
            */
<<<<<<< HEAD
            xhr.open('POST', '${pageContext.request.contextPath}/rest/object');
=======
            xhr.open('POST', '/myweb/rest/object');
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b

            //3. 서버로 전송할 데이터를 제작합니다.
            //제작하는 데이터의 형식은 JSON 형태여야 합니다.
            //요청을 보내는쪽(js)과 요청을 받는쪽(java)의 언어가 다르므로 이 사이에서 
            //규정된 표기법의 규약을 지켜서 작성을 해야 서로 데이터를 주고 받을 수 있음.
            //JSON 표기법: Java Script Object Notation
            const data = { //지금 괄호를 열어서 객체를 생성하는거래
                //'property' : value
                'name' : name,
                'age' : age,
                'hobby' : arr
            } //이게 바로 자바스크립트의 객체! (JSON 아님)

            // js 객체 -> JSON으로 변경 메서드: JSON.stringify(arg)
            const sendData = JSON.stringify(data);

            //전송할 데이터의 형태가 어떠한지를 요청 헤더에 지정.
            //(지금 내가 요청과 함께 보내는 content의 type은 json 타입이라고 명세(알리는))하는 것.)
            xhr.setRequestHeader('content-type', 'application/json');

            //4. 서버에 요청 전송
            xhr.send(sendData);

            //5. 응답된 정보 확인 (send 뒤에 onload로 받겠음.)
            //응답이 오면 onload가 되는 것임()
            xhr.onload = function() {
                //응답 코드 (응답상태 코드로 함 잘 돌아갔는지 확인해보겠음)
                console.log(xhr.status);

                //응답 데이터 확인
                console.log(xhr.response);

            }
            
        }

    </script>
    
</body>
</html>




