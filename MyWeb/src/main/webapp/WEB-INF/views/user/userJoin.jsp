
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-9 col-sm-12 join-form">
                    <div class="titlebox">
                       	 회원가입
                    </div>
<<<<<<< HEAD
                    <form action="${pageContext.request.contextPath}/user/join" method="post" name="joinForm">
=======
                    <form action="/myweb/user/join" method="post" name="joinForm">
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
                        <div class="form-group"><!--사용자클래스선언-->
                            <label for="id">아이디</label>
                            <div class="input-group"><!--input2탭의 input-addon을 가져온다 -->
                                <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디를 (영문포함 4~12자 이상)">
                                <div class="input-group-addon">
                                    <button type="button" class="btn btn-primary" id="idCheckBtn">아이디중복체크</button>
                                </div>
                            </div>
                            <span id="msgId"></span><!--자바스크립트에서 추가-->
                        </div>
                        <div class="form-group"><!--기본 폼그룹을 가져온다-->
                            <label for="password">비밀번호</label>
                            <input type="password" class="form-control" id="userPw" name="userPw" placeholder="비밀번호 (영 대/소문자, 숫자 조합 8~16자 이상)">
                            <span id="msgPw"></span><!--자바스크립트에서 추가-->
                        </div>
                        <div class="form-group">
                            <label for="password-confrim">비밀번호 확인</label>
                            <input type="password" class="form-control" id="pwConfirm" placeholder="비밀번호를 확인해주세요.">
                             <span id="msgPw-c"></span><!--자바스크립트에서 추가-->
                        </div>
                        <div class="form-group">
                            <label for="name">이름</label>
                            <input type="text" class="form-control" id="userName" name="userName" placeholder="이름을 입력하세요.">
                        </div>
                        <!--input2탭의 input-addon을 가져온다 -->
                        <div class="form-group">
                            <label for="hp">휴대폰번호</label>
                            <div class="input-group">
                                <select name="userPhone1" class="form-control phone1" id="userPhone1">
                                    <option>010</option>
                                    <option>011</option>
                                    <option>017</option>
                                    <option>018</option>
                                </select>
                                <input type="text" name="userPhone2" class="form-control phone2" id="userPhone2" placeholder="휴대폰번호를 입력하세요.">
                            </div>
                        </div>
                        <div class="form-group email-form">
                            <label for="email">이메일</label><br>
                            <div class="input-group">
                                <input type="text" name="userEmail1" class="form-control" id="userEmail1" placeholder="이메일">
                                <select name="userEmail2" class="form-control" id="userEmail2">
                                    <option>@naver.com</option>
                                    <option>@daum.net</option>
                                    <option>@gmail.com</option>
                                    <option>@hanmail.com</option>
                                    <option>@yahoo.co.kr</option>
                                </select>
                                <div class="input-group-addon">
                                    <button type="button" class="btn btn-primary" id="mail-check-btn">이메일 인증</button>
                                </div>
                            </div>
                        </div>


                        <div class="mail-check-box">
                            <input type="text" class="form-control mail-check-input" placeholder="인증번호 6자리를 입력하세요."
                                maxlength="6" disabled="disabled">
                            <span id="mail-check-warn"></span>
                        </div>

                        <!--readonly 속성 추가시 자동으로 블락-->
                        <div class="form-group">
                            <label for="addr-num">주소</label>
                            <div class="input-group">
                                <input type="text" name="addrZipNum" class="form-control" id="addrZipNum" placeholder="우편번호" readonly>
                                <div class="input-group-addon">
                                    <button type="button" class="btn btn-primary" onclick="searchAddress()">주소찾기</button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="addrBasic" class="form-control" id="addrBasic" placeholder="기본주소" readonly>
                        </div>
                        <div class="form-group">
                            <input type="text" name="addrDetail" class="form-control" id="addrDetail" placeholder="상세주소">
                        </div>

                        <!--button탭에 들어가서 버튼종류를 확인한다-->
                        <div class="form-group">
                            <button type="button" id="joinBtn" class="btn btn-lg btn-success btn-block">회원가입</button>
                        </div>

                        <div class="form-group">
                            <button type="button" class="btn btn-lg btn-info btn-block">로그인</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>


    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        let code = ''; //이메일 전송 인증번호 저장을 위한 변수. const로 선언 시 값이 변하지 않음. 
        let idFlag, pwFlag; // 정규표현식 유효성 검사 여부 판단을 위한 변수

       document.getElementById('idCheckBtn').onclick = function() {
            const userId = document.getElementById('userId').value;
            if(userId === '') {
                alert('아이디를 입력해주세요.');
                return;
            }

            if(!idFlag) {
                //아이디 규칙이 어긋나는 경우라서 중복검사를 할 필요가 없음
                alert('유효하지 않은 아이디입니다.');
                return;
            }


            /*
            //아이디 중복확인 비동기 요청 준비
            const xhr = new XMLHttpRequest();

            

            //이번 경우에는 뭐 더 필요한 데이터가 없어보이므로 그냥 이대로 요청 보내겠음
            xhr.send();

            xhr.onload = function() {
                console.log(xhr.status);
                console.log(xhr.response);
            }

            //얘는 순서가 보장이 안돼,,,,
            */



            /*
            # fetch API: 자바스크립트에서 제공하는 비동기 통신 함수
            - Promise 객체를 자동으로 리턴하여 손쉽게 통신의 응답데이터를 소비할 수 있게 해줍니다.
            (Promise: 비동기 통신의 결과 및 통신의 순서를 보장하는 객체)
            - fetch 함수가 리턴하는 Promise 객체는 단순히 응답 JSON 데이터가 아닌 전체적이고, 포괄적인 응답 정보를 가지고 있습니다.
            -따라서, 서버가 응답한 여러 정보 중 JSON 데이터만 소비하려면 json()이라는 메서드를 사용합니다.
            - 서버가 전달한 데이터가 단순 문자열 데이터라면 text() 메서드를 사용.
            */
            

            /*
            //동일한 내용을 fetch 와 promise를 이용하여 재작성 해보겠음!

            //fetch('url', {요청 관련 정보를 담은 객체(GET 방식에서는 따로 전달 안함)} )
            //fetch를 호출한 곳에 Promise 객체가 반환됨.

            // Promise 객체의 상태가 요청 성공일 시 데이터 후속 처리 진행. (실패시 catch라는 메서드가 실행됨)//서버 요청 정보 설정 (url에 동사적인 느낌을 주면 안되고, 무슨 일을 하려고 하는지 드러나게 적는것은 권장하지 않음.)
            //여기서는 jsp파일이라서 EL태그로 먼저 인식하므로 값이 잘 전달되지 않음. 따라서 문자열을 의미하기 위해 백슬래쉬를 붙임.
<<<<<<< HEAD
            xhr.open('GET', 백틱기호${pageContext.request.contextPath}/user/백슬래시기호달러기호{userId}백틱기호);
=======
            xhr.open('GET', 백틱기호/myweb/user/백슬래시기호달러기호{userId}백틱기호);
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
            //.then(응답,,콜백,,함수,,,?)
            //.then(function(res))
            .then(res => {
                //fetch 함수를 통해 비동기 통신이 실행되고,
                //요청이 완료된 후 then()의 매개값으로 응답에 관련된 함수를 콜백 방식으로 전달합니다.
                //fucntion(res) -> 화살표 함수로 간단히 표현함. (res를 매개값으로 받는 함수를 ㅓ,,,뭐ㅓ,,,?)

                //함수의 매개변수를 선언하면 해당 매개변수로 응답에 관련된 전반적인 정보를 가진 응답정보가 전달됩니다.

                console.log(res);
                console.log('서버가 전달한 데이터: ', res.text());

                return res.text(); //서버가 전달한 데이터를 다시 또다른 Promise 형태로 리턴.

            })
            //위에 배치된 then() 함수가 먼저 실행될 것을 강제.
            //그 이후에 나중에 배치된 then()이 실행되게끔 메서드 체이닝 방식으로 작성.
            //통신이 "성공" 완료가 아니고 "성공" 했다는 것을 먼저 보장받은 후, 서버의 데이터르 꺼내는 콜백함수를 실행
            .then(data => {
                console.log('data: ', data);
            })
            .catch(error => {
                console.log('error: ', error);
            });
            */

            


            //비동기 요청을 fetch()로 보내고 결과를 확인하기.
            //화살표 함수내의 코드가 한줄이고 그것이 return 값이라면 괄호와 return 생략가능.
            //요청 완료 후 응답 정보에서 텍스트 데이터가 담긴 Promise 반환. 
<<<<<<< HEAD
            fetch('${pageContext.request.contextPath}/user/'+userId)  //매개값이 2개 ((resolve(얘가 콜백함수의 기능을 함.))
=======
            fetch('/myweb/user/'+userId)  //매개값이 2개 ((resolve(얘가 콜백함수의 기능을 함.))
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
            .then(res => res.text() )  //텍스트 데이터만 담긴 Promise 객체로부터 텍스트 값만 추출하여 반환 한걸 왜 또 Promise 로 반환함? 그럼 뭐한거임?
            .then(data => {  //그 값을 data로 받아옴?
                if(data === 'ok') {
                    //더 이상 아이디를 작성할 수 없도록 막아주겠다.
                    document.getElementById('userId').setAttribute('readonly', true);

                    //더 이상 버튼을 누를 수 없도록 버튼 비활성화
                    document.getElementById('idCheckBtn').setAttribute('disabled', true);

                    //메세지 남기기
                    document.getElementById('msgId').innerText = '사용 가능한 아이디 입니다.';

                } else {
                    document.getElementById('msgId').innerText = '중복된 아이디 입니다.';
                    document.getElementById('userId').value = ''; //입력칸 비우기
                    document.getElementById('userId').focus(); //입력칸에 포커싱.
                }
            }); 
       }//아이디 중복확인 끝.
       






       //인증번호 이메일 전송
       document.getElementById('mail-check-btn').onclick = function() {
            //사용자의 이메일 가져오기
            const email = document.getElementById('userEmail1').value + document.getElementById('userEmail2').value;
            console.log('완성된 email: ', email);

<<<<<<< HEAD
            fetch('${pageContext.request.contextPath}/user/email', {
=======
            fetch('/myweb/user/email', {
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
                method: 'post',
                headers: {
                    'Content-Type': 'text/plain'
                    //만약 전달할 데이터가 json 형태라면 'application/json'
                }, //headers는 객체형태로 보냄
                body: email //전달하고자 하는 데이터
            })
            .then(res => res.text())
            .then(data => {
                console.log('인증번호: ', data);

                //비활성화된 인증번호 입력창을 활성화
                document.querySelector('.mail-check-input').disabled = false;
                code = data; //서버가 전달한 인증번호를 전역변수에 저장.
                alert('인증번호가 전송되었습니다. 확인 후 입력란에 정확히 입력하세요.');
            })
            .catch(error => {
                console.log(error);
                alert('알 수 없는 문제가 발생했습니다. 관리자에게 문의하세요!');
            }); //비동기 끝.

       };//이메일 인증 버튼 클릭 이벤트 끝
       




       //인증번호 검증
       //blur -> focus 가 벗어나는 경우 발생
       document.querySelector('.mail-check-input').onblur = function(e) {
            console.log('blur 이벤트 발생 확인!');

            const inputCode = e.target.value; //사용자가 입력한 인증번호
            const $resultMsg = document.getElementById('mail-check-warn'); //span태그
            console.log('사용자가 입력한 값: ', inputCode);

            if(inputCode === code) {
                $resultMsg.textContent = '인증번호가 일치합니다.';
                $resultMsg.style.color = 'green';
                //이메일 인증을 더이상 못하게 버튼 비활성화.
                document.getElementById('mail-check-btn').disabled=true;
                document.getElementById('userEmail1').setAttribute('readonly', true);
                document.getElementById('userEmail2').setAttribute('readonly', true);

                e.target.style.display = 'none'; //인증번호 입력창 숨기기
                
                //select 태그에서 초기값을 사용자가 선택한 값으로 무조건 설정하는 방법
                //(select에서 readonly 대용으로 사용)
                //항상 2개 같이 쓰셔야 합니다.
                const $email2 = document.getElementById('userEmail2');
                
                $email2.setAttribute('onFocus', 'this.initialSelect = this.selectedIndex');
                $email2.setAttribute('onChange', 'this.selectedIndex = this.initialSelect');
                //onChange : 변화감지 이벤트, onFocus: 포커싱 감지 이벤트
                //사용자가 select의 옵션을 처음 선택했을 때의 값을 기억했다가
                //option 변경 시도를 할 때마다 초기값으로 강제로 변경해서
                //option이 마치 변하지 않는 것처럼 처리
            
            } else {
                $resultMsg.textContent = '인증번호를 다시 확인해주세요.';
                $resultMsg.style.color = 'red';
                e.target.focus();
            }
            

       } //이메일 인증번호 검증 끝.
       
       





       //다음 주소 api 사용해보기 (상단의 script src 추가해놓아야 함!)
       function searchAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색'결과' 항목을 클릭했을때 실행할 코드를 작성하는 부분. (결과값이 data에 들어감.)

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소('R' 이건 카카오애들이 정한 것.)를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('addrZipNum').value = data.zonecode;
                document.getElementById("addrBasic").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addrDetail").focus();
            }
        }).open();
    } //주소찾기 api 끝.








    //폼 데이터 검증 (회원 가입 버튼 눌렀을 시)
    document.getElementById('joinBtn').onclick = function() {
        if(idFlag && pwFlag) { //id pw 가 유효한지 체크
            
            //아이디 중복체크 여부 체크 (중복체크 성공 -> input태그가 readonly로 변했었지)
            //getAttribute('속성값명') -> 속성값의 value 리턴
            if(!document.getElementById('userId').getAttribute('readonly')){
                alert('아이디 중복체크는 필수입니다.');
                return;
            }

            //이름 검즘: 값을 넣었는지 여부
            if(document.getElementById('userName').value === '') {
                alert('이름은 필수값입니다.');
                return;
            }

            //이메일 인증 검증: 인증버튼 diabled 여부로 체크
            if(!document.getElementById('mail-check-btn').disabled) {
                alert('이메일 인증을 완료해 주세요.');
                return;
            }

            if(confirm('회원가입을 진행합니다.')) {
                document.joinForm.submit();
            } else return;

        } else {
            alert('아이디나 비밀번호의 입력값을 다시 한 번 확인하세요!');
            return;
        }

    }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
/*
자바스크립트의 정규 표현식 (각 언어마다 유사)
정규표현식: 문자열 내의 특정 문자 조합을 찾기 위한 패턴.
특정 규칙이 있는 문자열 집합을 대상으로 규칙을 직접 지정하여 탐색할 수 있게 해주는 메타 문자.
메타문자: 일반 텍스트가 아닌 특정 목적을 위해 사용하는 문자.
/^ : 시작
[범위]
{최소개수, 최대개수}
*/
       
       /*아이디 형식 검사 스크립트*/
        var id = document.getElementById("userId");
        id.onkeyup = function() { //onkeyup: 키보드를 눌렀다가 뗄 때 이벤트 발생
            /*자바스크립트의 정규표현식 입니다*/
            /*test메서드를 통해 비교하며, 매칭되면 true, 아니면 false반*/
            var regex = /^[A-Za-z0-9+]{4,12}$/; 
            //test('테스트할_문자열') : 정규표현식의 규칙에 어긋나지 않는 문자 : true/ 어긋나는 문자: false 반환
            if(regex.test(document.getElementById("userId").value )) { 
                document.getElementById("userId").style.borderColor = "green";
                document.getElementById("msgId").innerHTML = "아이디 중복 체크는 필수입니다";
                idFlag = true;

            } else {
                document.getElementById("userId").style.borderColor = "red";
                document.getElementById("msgId").innerHTML = "부적합한 아이디입니다.";
                idFlag = false;
            }
        }
        /*비밀번호 형식 검사 스크립트*/
        var pw = document.getElementById("userPw");
        pw.onkeyup = function(){
            var regex = /^[A-Za-z0-9+]{8,16}$/;
             if(regex.test(document.getElementById("userPw").value )) {
                document.getElementById("userPw").style.borderColor = "green";
                document.getElementById("msgPw").innerHTML = "사용가능합니다";
                pwFlag = true;
            } else {
                document.getElementById("userPw").style.borderColor = "red";
                document.getElementById("msgPw").innerHTML = "";
                pwFlag = false;
            }
        }
        /*비밀번호 확인검사*/
        //keyup 이 발생할 때마다 pwFlag 값을 갱신할 것. (비밀번호 확인하고 또 바꿀 수 도 있으므로.)
        var pwConfirm = document.getElementById("pwConfirm");
        pwConfirm.onkeyup = function() {
            var regex = /^[A-Za-z0-9+]{8,16}$/;
            if(document.getElementById("pwConfirm").value == document.getElementById("userPw").value ) {
                document.getElementById("pwConfirm").style.borderColor = "green";
                document.getElementById("msgPw-c").innerHTML = "비밀번호가 일치합니다";
                pwFlag = true;
            } else {
                document.getElementById("pwConfirm").style.borderColor = "red";
                document.getElementById("msgPw-c").innerHTML = "비밀번호 확인란을 확인하세요";
                pwFlag = false;
            }
        }        
    </script>
    
    
     <%@ include file="../include/footer.jsp" %>
