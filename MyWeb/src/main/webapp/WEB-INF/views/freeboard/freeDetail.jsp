<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>상세보기</p>
                        </div>
                        
                        <form action="${pageContext.request.contextPath}/freeboard/modPage" method="post">
                            <div>
                                <label>DATE</label>
                                <p>${article.date}</p>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name='bno' value="${article.bno}" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='writer' value="${article.writer}" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='title' value="${article.title}" readonly>
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content' readonly>${article.content}</textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">변경</button>
                            <button type="button" class="btn btn-dark" onclick="location.href='${pageContext.request.contextPath}/freeboard/freeList?pageNo=${p.pageNo}&amount=${p.amount}&keyword=${p.keyword}&condition=${p.condition}'">목록</button>
                    </form>
                </div>
            </div>
        </div>
        </section>
        
        <section style="margin-top: 80px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-9 write-wrap">
                        <form class="reply-wrap">
                            <div class="reply-image">
                                <img src="${pageContext.request.contextPath}/img/profile.png">
                            </div>
                            <!--form-control은 부트스트랩의 클래스입니다-->



	                    <div class="reply-content">
	                        <textarea class="form-control" rows="3" id="reply"></textarea>
	                        <div class="reply-group">
	                              <div class="reply-input">
	                              <input type="text" class="form-control" id="replyId" placeholder="이름">
	                              <input type="password" class="form-control" id="replyPw" placeholder="비밀번호">
	                              </div>
	                              
	                              <button type="button" id="replyRegist" class="right btn btn-info">등록하기</button>
	                        </div>
	
	                    </div>
                        </form>



                        <!--여기에접근 반복-->
                        <div id="replyList">
                        <!--자바스크립트로 반복문을 이용하여 댓글의 개수만큼 반복표현
                        <div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='${pageContext.request.contextPath}/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class='left'>honggildong</strong> 
                                    <small class='left'>2019/12/10</small>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                </div>
                                <p class='clearfix'>여기는 댓글영역</p>
                            </div>
                        </div>
                        -->
                        </div>

                        <button type="button" class="form-control" id="moreList" style="display: none;">더보기(페이징)</button>

                    </div>
                </div>
            </div>
        </section>
        
	<!-- 모달 -->
	<div class="modal fade" id="replyModal" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
					<h4 class="modal-title">댓글수정</h4>
				</div>
				<div class="modal-body">
					<!-- 수정폼 id값을 확인하세요-->
					<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
						    <input type="hidden" id="modalRno">
							<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
					</div>
					</div>
					<!-- 수정폼끝 -->
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="../include/footer.jsp" %>

    <script>
        window.onload = function() { //없어도 이벤트가 발생하긴 함. 내용물이 모두 렌더링 된 후에 다음 함수들이 작동할 수 있도록! 좀더 안전한 코드를 작성하기 위해 작성한 행임.

            document.getElementById('replyRegist').onclick = () => {
                console.log('댓글 등록 이벤트가 발생함!');

                const bno = '${article.bno}'; //현재 게시글 번호도 모내야 한다.
                const reply = document.getElementById('reply').value;
                const replyId = document.getElementById('replyId').value;
                const replyPw = document.getElementById('replyPw').value;

                //간단한 유효성 검사 : 일단 공백만 체크해봄
                if(reply === '' || replyId === '' || replyPw === '') {
                    alert('이름, 비밀번호, 내용은 필수값 입니다.');
                    return;
                }

                //비동기방식으로 데이터를 전송할 것
                //요청으로 전송할 데이터를 객체화 할 것
                const reqObj = {
                    method: 'post',
                    headers: {
                        'Content-Type' : 'application/json'
                    },
                    body: JSON.stringify({
                        'bno' : bno,
                        'replyText' : reply,
                        'replyId' : replyId,
                        'replyPw' : replyPw
                    })
                };

                fetch('${pageContext.request.contextPath}/reply', reqObj)
                .then(res => res.text())
                .then(data =>{
                    console.log('통신 성공!: ', data);
                    //등록 성공 했다면, 다음 등록을 위해 입력창을 비워주자!
                    document.getElementById('reply').value='';
                    document.getElementById('replyId').value='';
                    document.getElementById('replyPw').value='';

                    //등록 완료 후 댓글 목록 함수를 재호출해서 비동기식으로 목록 갱신&표현
                    getList(1,true);
                });

            }//댓글 등록 이벤트 끝

            
            //더보기 버튼 클릭 이벤트
            document.getElementById('moreList').onclick = () => {
                getList(++page, false);
                // 더보기 = 기존+a => 누적해야 하므로 초기화하면 안됨=false
            }


            let strAdd = ''; //화면에 그려넣을 태그를 문자열의 형태로 추가할 변수
            const $replyList = document.getElementById('replyList');
            let page = 1; //전역의 의미로 사용할 현재 페이지 번호;


            //게시글 상세보기 화면에 처음 진입했을 시 댓글 리스트를 기본으로 1페이지를 불러오자
            getList(1,true);
            //댓글 목록을 가져올 함수 (몇번 댓글 페이지의 댓글목록인지를 매개변수로 받아야 함)
            //getList의 매개값으로 무엇을 줄 것인가?
            //요청된 페이지 번호와, 화면을 리셋할 것인지의 여부를 bool 타입의 reset이라는 변수로 받겠음
            //비동기 방식이기 때문에 페이지가 그대로 계속 머물면서 댓글이 밑에 쌓입니다.
            //때에 따라서는 댓글을 계속 누적시키는 것이 아닌, 화면을 초기화하고 새롭게 보여줘야 할 때가 있다.
            //reset -> true: 페이지를 리셋해서 새롭게 그려내기, rest -> false: 누적해서 쌓기.
            function getList(pageNum,reset) {
                console.log('getList() 호출됨!');
                strAdd = ''; //새로운 내용을 작성하기 위해 변수 비우기

                const bno = '${article.bno}'; //현재 게시글 번호
                
                //get방식으로 댓글 목록을 요청 (비동기)
                fetch('${pageContext.request.contextPath}/reply/list/'+ bno + '/' + pageNum )
                .then(res => res.json()) //Promise에 있는 JSON객체 줘.
                .then(data => {
                    console.log('서버가 전달한 MAP: ', data);

                    let total = data.total; //총 댓글 수
                    let replyList = data.list; //댓글 리스트

                    //응답 데이터의 길이가 0과 같거나 더 작으면 함수를 종료
                    if(replyList.length <= 0) return;

                    if(reset) {
                        while($replyList.firstChild) {//자바스크립트에서는 조건식에 null이 오면 false에 옴 0, null, '', undefiended가 false로 인식됨.
                            $replyList.firstChild.remove();
                        }
                        page = 1; //전역변수로 관리되던 page를 1로 초기화 시켜줌.
                    }
                    
                    //더보기 버튼 배치 판단
                    //현재 페이지 번호 * 이번 요청으로 받은 댓글 수 > 전체 댓글수 => 더보기 없어도 됨
                    console.log('현재 페이지: ', page);
                    if(total <= page * 5) {
                        document.getElementById('moreList').style.display = 'none';
                    } else {
                        document.getElementById('moreList').style.display = 'block';
                    }

                    //replyList의 개수만큼 태그를 문자열 형태로 직접 그림.
                    //중간중간 들어가야 할 글쓴이, 날짜, 댓글 내용은 목록에서 꺼내서 표현할것임.
                    for(let i=0; i<replyList.length; i++){
                        strAdd += `<div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='${pageContext.request.contextPath}/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class='left'>`+ replyList[i].replyWriter+`</strong> 
                                    <small class='left'>`+ parseTime(replyList[i].date) +`</small>
                                    <a href='`+ replyList[i].replyNo +`' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                    <a href='` + replyList[i].replyNo +`' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                </div>
                                <p class='clearfix'>`+ replyList[i].replyText +`</p>
                            </div>
                        </div>`
                    }

                    //id 가 replyList라는 div 영역에 문자열 형식으로 모든 댓글을 추가
                    //insertAdjacentHTML : 인접한 곳에 HTML을 삽입하는 함
                    //(position문자열, 문자열형태의 HTML)
                    if(!reset) {
                        $replyList.insertAdjacentHTML('beforeend', strAdd);
                    } else {
                        $replyList.insertAdjacentHTML('afterbegin', strAdd);
                    }

                })
                
            }// end getList()



            //수정, 삭제
            /*
            document.querySelector('.replyModify').onclick = function(e) {
                e.preventDefault();
                console.log('수정 버튼 클릭 이벤트 발생!');
            }
            
            해당 블록의 코드는 정상작동하지 않음....
            이벤트가 제대로 걸리지 않은 것!

            - .replyModify라는 요소는 화면에 실제 존재하는 요소가 아니라 비동기 통신을 통해 생성되는 요소임(onload 된 뒤, getList()라는 함수 속에서 fetch로 댓글목록을 받아옴)
            그러다보니 이벤트가 등록되는 시점보다 fetch함수의 실행이 먼저 끝날 것이라는 보장이 없기 때문에!
            해당 방식으로는 이벤트 등록이 불가능함!
            이벤트 객체 같은 애들? = 가상도우미: 처음부터 랜더링 되는 애들이 아님
            처음부터 렌더링 되는 애들: 직접 바디에 적은 애들.

            태그가 완성되기 전에 이벤트를 등록하려는 것?

            fetch 함수는 비동기 방식이므로 순서를 보장할 수 없음 (지금 댓글목록 HTML 태그의 삽입을 끝냈는지 알 수 없음)
            그 상황 속에서 상단의 코드에서는 아직 완성됐는지 알 수 없는 것에 이벤트를 등록하려고 하고 있음 (여기서 문제발생! 존재의 유무를 알 수 없는 태그에 이벤트를 제대로 걸 수 있을 리 없음)

            그래서 원래 존재하는 'replyList' 태그에 이벤트를 걸어서 자식요소(getList()로 추가할 태그)에 간접적으로 이벤트를 전파하는 방식을 사용해야 함.
            이미 실제로 존재하는 #replyList에 이벤트를 등록하고, 이벤트를 자식에게 위임하여 사용하는 addEventListenner를 통해 처리해야 함.
            */
           document.getElementById('replyList').addEventListener('click', e => {
                e.preventDefault();
                
                //1. 이벤트가 발생한 target이 원하는 항목(여기선 a태그)이 아니면 이벤트 강제 종료.
                if(!e.target.matches('a')) return;

                //2. a태그가 두 개 (수정, 삭제)이므로 어떤 링크인지를 확인.
                //댓글이 여러 개 -> 수정과 삭제가 발생하는 댓글이 몇번인지도 확인해야 함
                const rno = e.target.getAttribute('href');
                console.log('댓글번호', rno);
                //모달 내부에 숨겨진 input 태그에 댓글 번호를 달아주자
                document.getElementById('modalRno').value = rno;

                //댓글 내용도 가져와서 모달에 뿌려주자
                const content = e.target.parentNode.nextElementSibling.textContent;
                console.log('댓글 내용: ', content);

                //3. 모달 창 하나를 이용해서 상황에 맞게 수정/ 삭제 모달을 제공
                //조건문 이용 (수정 / 삭제에 따라 모달 디자인을 조정)
                //이벤트객체가, 가지고있는모든클래스리스트(Arrray 형식으로 받아옴)가, 포함하고있니('replyModify')? true=수정버튼 false=삭제버튼
                if(e.target.classList.contains('replyModify')) {
                    //수정버튼을 눌렀으므로 수정 모달 형식으로 꾸미겠음
                    document.querySelector('.modal-title').textContent = '댓글 수정';
                    document.getElementById('modalReply').style.display = 'inline';
                    document.getElementById('modalReply').value = content;
                    document.getElementById('modalModBtn').style.display = 'inline';
                    document.getElementById('modalDelBtn').style.display = 'none';

                    //어쩔 수 없이 제이쿼리를 이용하여 bootstrap 모달을 여는 방법 ('show'): 열기 / ('hide'): 닫기
                    $('#replyModal').modal('show');
                } else {
                    document.querySelector('.modal-title').textContent = '댓글 삭제';
                    document.getElementById('modalReply').style.display = 'none';
                    document.getElementById('modalModBtn').style.display = 'none';
                    document.getElementById('modalDelBtn').style.display = 'inline';
                    $('#replyModal').modal('show');
                }
           }); //수정 or 삭제 버튼 클릭 이벤트 끝.


           //수정 처리 함수. (수정 모달을 열어서 수정내용을 작성한 후 수정 버튼을 클릭했을 때)
           document.getElementById('modalModBtn').onclick = () => {

                const reply = document.getElementById('modalReply').value;
                const rno = document.getElementById('modalRno').value;
                const replyPw = document.getElementById('modalPw').value;

                if(reply == '' || replyPw == ''){
                    alert('내용과 비밀번호를 확인하세요!');
                    return;
                }

                //요청과 요청에 관련된 정보 객체
                const reqObj = {
                    method : 'put',
                    headers : {
                        'Content-Type' : 'application/json'
                    },
                    body : JSON.stringify({
                        'replyText' : reply,
                        'replyPw' : replyPw
                    })
                };

                fetch('${pageContext.request.contextPath}/reply/'+rno, reqObj)
                .then(res => res.text())
                .then(data => {
                    if(data === 'pwFail') {
                        alert('비밀번호가 일치하지 않습니다.');
                        document.getElementById('modalPw').value = '';
                        document.getElementById('modalPw').focus();
                    } else {
                        alert('정상 처리 되었습니다.');
                        
                        //수정완료! 이후, 다른 처리를 할 수 있으므로 모달의 값을 미리 비워둘 것임
                        document.getElementById('modalReply').value = '';
                        document.getElementById('modalPw').value = '';

                        //제이쿼리 문법으로 bootstrap 모달 닫아주기
                        $('#replyModal').modal('hide');
                        getList(1, true); //수정된 댓글을 반영해 보여주기 위한 댓글목록 재요청
                    }
                    
                });
           } //end update event

           //삭제 이벤트
           document.getElementById('modalDelBtn').onclick = () => {
            /*
            1. 모달창에 rno값, replyPw 값을 얻습니다.

            2. fetch 함수를 이용해서 DELETE 방식으로 reply/{rno} 요청

            3. 서버에서는 요청을 받아서 비밀번호를 확인하고, 비밀번호가 맞으면
             삭제를 진행하시면 됩니다.

            4. 만약 비밀번호가 틀렸다면, 문자열을 반환해서
            '비밀번호가 틀렸습니다.' 경고창을 띄우세요.

            삭제 완료되면 모달 닫고 목록 요청 다시 보내세요. (reset의 여부를 잘 판단)
            */

            //보낼값 세팅
            const rno = document.getElementById('modalRno').value;
            const replyPw = document.getElementById('modalPw').value;

            if(replyPw === '') {
                alert('비밀번호를 확인하세요!');
                return;
            }

            fetch('${pageContext.request.contextPath}/reply/'+rno, {
                method : 'delete',
                headers : {
                    'Content-Type' : 'application/json'
                },
                body : replyPw
            })
            .then(res => res.text())
            .then(data => {
                console.log('fetch후 data: ',data);
                if(data === 'delSuccess') {
                    alert('댓글이 삭제되었습니다.');
                    document.getElementById('modalPw').value = '';
                    $('#replyModal').modal('hide');
                    getList(1, true);
                } else {
                    alert('비밀번호가 틀렸습니다.');
                    document.getElementById('modalPw').value = '';
                    document.getElementById('modalPw').focus();
                }
            })


           } //end delete event










            //댓글 날짜 변환 함수
            function parseTime(regDate) {
                //원하는 날짜로 객체를 생성
                const now = new Date(); //현재 날짜
                const regTime = new Date(regDate);

                //댓글 등록일로부터 시간이 얼마나 지났는지 확인
                //getTime() : 1970년 1월 1일 자정을 기준으로 현재까지의 시간을 '밀리초'로 리턴.
                const gap = now.getTime() - regTime.getTime();

                let time;
                if(gap < 60*60*24*1000){ // *60-> 분단위 / *60-> 시단위 / *24-> 일단위
                    if(gap < 60*60*1000) {// *60-> 분단위 / *60-> 시단위 
                        //즉, 한시간 내에 작성된 글은 방금전 이라는 문자가 뜰 것
                        time = '방금 전'; 
                    } else {
                        time = parseInt(gap / (1000*60*60)) + '시간 전';
                    }
                } else if (gap < 60*60*24*30*1000) {//하루가 지났고, 한달이 지나기 전
                    time = parseInt(gap / (1000 * 60^60*24)) + '일 전';
                } else {
                    time = `\${regTime.getFullYear()}년 \${regTime.getMonth()+1}월 \${regTime.getDate()}일`;
                }

                if(regDate.includes('(수정됨)')) return time + ' (수정됨)';
                return time;
            }

        } // end  window.onload 

    </script>
