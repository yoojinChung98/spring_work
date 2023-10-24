<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>

	<style type="text/css">
		section {
			margin-top: 70px;
		}
		/*부트스트랩 li의 drophover클래스 호버시에 드롭다운 적용코드*/
		ul.nav li.drophover:hover>ul.dropdown-menu {
			display: block;
			margin: 0;
		}
		.aside-inner {
			position: fixed;
			top: 70px;
			width: 180px;
		}
		.section-inner {
			border-right: 1px solid #ddd;
			border-left: 1px solid #ddd;
			background-color: white;
		}
		.section-inner p,
		.aside-inner p {
			margin: 0;
		}
		.title-inner {
			position: relative;
			padding: 15px 0;
		}
		.title-inner .profile {
			position: absolute;
			/*부모기준으로 위치지정 릴레이티브*/
			top: 15px;
			left: 0;
		}
		.title-inner .title {
			padding-left: 50px;
		}
		/*내용*/
		.content-inner {
			padding: 10px 0;
		}
		/* 이미지영역  */
		.image-inner img {
			width: 100%;
		}
		/*좋아요*/
		.like-inner {
			padding: 10px 0;
			border-bottom: 1px solid #ddd;
			border-top: 1px solid #ddd;
			margin-top: 10px;
		}
		.like-inner img {
			width: 20px;
			height: 20px;
		}
		.link-inner {
			overflow: hidden;
			padding: 10px 0;
		}
		.link-inner a {
			float: left;
			width: 33.3333%;
			text-align: center;
			text-decoration: none;
			color: #333333;
		}
		.link-inner i {
			margin: 0 5px;
		}
		/*767미만 사이즈에서 해당 css가 적용됨*/
		/*xs가 767사이즈   */
		@media (max-width :767px) {
			aside {
				display: none;
			}
		}
		/* 파일업로드 버튼 바꾸기 */
		.filebox label {
			display: inline-block;
			padding: 6px 10px;
			color: #fff;
			font-size: inherit;
			line-height: normal;
			vertical-align: middle;
			background-color: #5cb85c;
			cursor: pointer;
			border: 1px solid #4cae4c;
			border-radius: none;
			-webkit-transition: background-color 0.2s;
			transition: background-color 0.2s;
		}
		.filebox label:hover {
			background-color: #6ed36e;
		}
		.filebox label:active {
			background-color: #367c36;
		}
		.filebox input[type="file"] {
			position: absolute;
			width: 1px;
			height: 1px;
			padding: 0;
			margin: -1px;
			overflow: hidden;
			clip: rect(0, 0, 0, 0);
			border: 0;
		}
		/* sns파일 업로드시 미리보기  */
		.fileDiv {
			height: 100px;
			width: 200px;
			display: none;
			margin-bottom: 10px;
		}
		.fileDiv img {
			width: 100%;
			height: 100%;
		}
		/* 모달창 조절 */
		.modal-body {
			padding: 0px;
		}
		.modal-content>.row {
			margin: 0px;
		}
		.modal-body>.modal-img {
			padding: 0px;
		}
		.modal-body>.modal-con {
			padding: 15px;
		}
		.modal-inner {
			position: relative;
		}
		.modal-inner .profile {
			position: absolute;
			top: 0px;
			left: 0px;
		}
		.modal-inner .title {
			padding-left: 50px;
		}
		.modal-inner p {
			margin: 0px;
		}
	</style>

</head>

<body>
	<%@ include file="../include/header.jsp" %>
	<section>
		<div class="container">
			<div class="row">
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">
							<p>
								<img src="${pageContext.request.contextPath}/img/profile.png"> 홍길동
							</p>
							<ul>
								<li>사이드메뉴1</li>
								<li>사이드메뉴2</li>
								<li>사이드메뉴3</li>
							</ul>
						</div>
						<div class="menu2">
							<p>둘러보기</p>
							<ul>
								<li>링크1</li>
								<li>링크2</li>
								<li>링크3</li>
								<li>링크4</li>
								<li>링크5</li>
							</ul>
						</div>
					</div>
				</aside>
				<div class="col-xs-12 col-sm-8 section-inner">
					<h4>게시물 만들기</h4>
					<!-- 파일 업로드 폼입니다 -->
					<div class="fileDiv">
						<img id="fileImg" src="${pageContext.request.contextPath}/img/img_ready.png">
					</div>
					<div class="reply-content">
						<textarea class="form-control" rows="3" name="content" id="content"
							placeholder="무슨 생각을 하고 계신가요?"></textarea>
						<div class="reply-group">
							<div class="filebox pull-left">
								<label for="file">이미지업로드</label>
								<input type="file" name="file" id="file">
							</div>
							<button type="button" class="right btn btn-info" id="uploadBtn">등록하기</button>
						</div>
					</div>
					<!-- 파일 업로드 폼 끝 -->


					<div id="contentDiv">

						<!-- 비동기 방식으로 서버와 통신을 진행한 후
							목록을 만들어서 붙일 예정. -->

					</div>
				</div>
				<!--우측 어사이드-->
				<aside class="col-sm-2">
					<div class="aside-inner">
						<div class="menu1">
							<p>목록</p>
							<ul>
								<li>목록1</li>
								<li>목록2</li>
								<li>목록3</li>
								<li>목록4</li>
								<li>목록5</li>
							</ul>
						</div>
					</div>
				</aside>
			</div>
		</div>
	</section>
	<%@ include file="../include/footer.jsp" %>


	<!-- 모달 -->
	<div class="modal fade" id="snsModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body row">
					<div class="modal-img col-sm-8 col-xs-6">
						<img src="${pageContext.request.contextPath}/img/img_ready.png" id="snsImg" width="100%">
					</div>
					<div class="modal-con col-sm-4 col-xs-6">
						<div class="modal-inner">
							<div class="profile">
								<img src="${pageContext.request.contextPath}/img/profile.png">
							</div>
							<div class="title">
								<p id="snsWriter">테스트</p>
								<small id="snsRegdate">21시간전</small>
							</div>
							<div class="content-inner">
								<p id="snsContent">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam
									vulputate elit libero, quis mattis enim tincidunt non. Mauris consequat ante vel
									urna posuere consequat. </p>
							</div>
							<div class="link-inner">
								<a href="##"><i class="glyphicon glyphicon-thumbs-up"></i>좋아요</a>
								<a href="##"><i class="glyphicon glyphicon-comment"></i>댓글달기</a>
								<a href="##"><i class="glyphicon glyphicon-share-alt"></i>공유하기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>

		//등록하기 버튼 클릭 이벤트
		document.getElementById('uploadBtn').onclick = () => {
			regist();
		}

		//등록을 담당하는 함수
		function regist() {

			//세션에서 현재 로그인 중인 사용자의 정보(아이디)를 얻어오자.
			const userId = '${sessionScope.login}';
			if(userId === '') { //세션 데이터가 null -> 로그인 X
				alert('로그인이 필요한 서비스 입니다.');
				return;
			}

			//자바스크립트로 첨부한 파일 확장자 체크 (이미지만 허용함.)
			let file = document.getElementById('file').value;
			console.log(file);
			// .을 제거한 확장자만 얻어낸 후 그것을 소문자로 일괄 변경.
			file = file.slice(file.indexOf('.')+1).toLowerCase(); //.slice() : 자바의 substring 같은애! (범위추출)

			if(file != 'jpg' && file != 'png' && file != 'jpeg' && file !== 'bmp') {
				alert('이미지 파일(jpb, png, jpeg, bmp)만 등록이 가능합니다.');
				return; 
			}

			/*
			비동기 방식 요청에서 Form을 생성해서 보내주는 방법.
			-> FormData 객체를 활용합니다.
			무조건 이 방식이 옳은 것은 아닙니다. FormData는 비동기 방식이 꼭 필요한 경우에만 사용함.
			비동기가 필요하지 않다면 form 태그를 이용해서 전송하는 방식이 더 간편하고 더 자주 사용됨
			*/

			//자바스크립트 빌트인 객체. 즉, 내장객체
			const formData = new FormData();
			const $data = document.getElementById('file'); //이미지 첨부 input

			
			console.log('data: ', $data);


			//한 화면에 파일 업로드 버튼이 여러 개일 경우 요소의 인덱스를 지목해서 가져올 수 있음.
            //우리는 파일 첨부 버튼이 하나고, id를 지목해서 가져오기 때문에 인덱스를 쓸 필요는 없습니다.
			//console.log('data[0]', $data[0]);


			/*
			//만약 우리가 여러 파일을 하나의 태그로 받을 수 있도록 multiple을 제공했다면
            //files -> FileList에 여러 파일의 정보가 들어오게 됩니다.
            //FileList는 유사 배열이기 때문에 인덱스를 이용해서 여러 파일 중 하나를 지목할 수 있습니다.
            //우리는 지금 multiple이 없기 때문에 [0]이라고 작성하면 파일 정보를 얻을 수 있습니다.
			*/
			console.log($data.files); //파일 태그에 담긴 파일 정보를 확인하는 프로퍼티. (파일의 정보가 유사배열로 담겨서 옴.)
			//console.log($data.files[0]); //사용자가첨부한 최종 파일의 정보.


			//FormData 객체에 사용자가 업로드한 파일의 정보가 들어있는 객체를 전달.
			//append('파라미터변수명(name)', '값');
			formData.append('file', $data.files[0]); //파일 정보 추가
			formData.append('content', document.getElementById('content').value); //textArea의 글내용 추가
			formData.append('writer', userId); //작성자 추가

			//응답 데이터는 text로 전달됩니다. (uploadSuccess)
			//file input 내용을 비우고, 글영역 비우고, class 이름이 fileDive(미리보기영역) 감추기. -> display 속성을 none으로

			//formdata 객체를 보낼 때는 따로 headers 설정을 진행하지 않음!(formData 객체 자체가 이런 정보들을 가지고 있음)
			fetch('${pageContext.request.contextPath}/snsboard', {
				method : 'post',
				body : formData
			})
			.then(res => res.text())
			.then(data => {
				console.log(data);

				document.getElementById('file').value = ''; //file input 비우기
				document.getElementById('content').value = ''; //글영역 비우기
				document.querySelector('.fileDiv').style.display = 'none'; //미리보기 감추기
				
				//글 목록 함수 호출
				getList(1,true);
			});
		}

		//글 목록 함수 선언.
		let str = '';
		let page = 1;
		let isFinish = false; //글목록 함수가 종료됐는지에 대한 변수(브라우저 로딩이 완료되었는지 안전장치 하나 걸어두는 것.)
		let reqStatus = false; //더 불러올 list가 있는지를 담는 변수

		const $contentDiv = document.getElementById('contentDiv');

		getList(1,true);

		//페이지번호와 리셋여부를 매개변수로 받을 것.
		function getList(page, reset) {
			str = '';
			isFinish = false;
			console.log('page: ', page);
			console.log('reset: ', reset);

			fetch('${pageContext.request.contextPath}/snsboard/' + page)
			.then(res => res.json())
			.then(list => {
				console.log(list);
				console.log(list.length);
				if(list.length <= 0) {
					isFinish = true;
					reqStatus = true;
					return;
				}

				if(reset) {
					while($contentDiv.firstChild) {
						$contentDiv.firstChild.remove();
					}
					page = 1;
				}

				for(board of list) {
					str += 
					`<div class="title-inner">
                            <!--제목영역-->
                            <div class="profile">
                                <img src="${pageContext.request.contextPath}/img/profile.png">
                            </div>
                            <div class="title">
                                <p>` + board.writer + `</p>
                                <small>` + board.regDate + `</small> &nbsp;&nbsp;
                                <a id="download" href="${pageContext.request.contextPath}/snsboard/download/` + board.fileLoca + `/` + board.fileName + `">이미지 다운로드</a>
							</div>
                        </div>
                        <div class="content-inner">
                            <!--내용영역-->
                            <p>` + board.content + `</p>
                        </div>
                        <div class="image-inner">
                            <!-- 이미지영역 -->
                            <a href="` + board.bno + `">
                                <img data-bno="`+ board.bno+`" src="${pageContext.request.contextPath}/snsboard/display/` + board.fileLoca + `/` + board.fileName + `">
							</a>
                        </div>
                        <div class="like-inner">
                            <!--좋아요-->
                            <img src="${pageContext.request.contextPath}/img/icon.jpg"> <span>522</span>
                        </div>
                        <div class="link-inner">
                            <a id="likeBtn" href="` + board.bno + `"><img src="${pageContext.request.contextPath}/img/like1.png" width="20px" height="20px"/> 좋아요</a>
                            <a data-bno="` + board.bno + `" id="comment" href="` + board.bno + `"><i class="glyphicon glyphicon-comment"></i>댓글달기</a>
                            <a id="delBtn" href="` + board.bno + `"><i class="glyphicon glyphicon-remove"></i>삭제하기</a>
                        </div>`;
				} //for 종료

				if(!reset) {
					$contentDiv.insertAdjacentHTML('beforeend', str);
				} else {
					$contentDiv.insertAdjacentHTML('afterbegin', str);
				}

				isFinish = true;

			}); //end fetch
		} //end getList()



		$contentDiv.addEventListener('click', e => {
			e.preventDefault(); //a태그 고유기능 막기

			if(!e.target.matches('.image-inner img')
				&& !e.target.matches('.title #download')
				&& !e.target.matches('.link-inner #comment')
				&& !e.target.matches('.link-inner #delBtn')
			) {
				console.log('이벤트 대상이 아닌 태그!');
				return;
			}

			//다운로드 처리
			if(e.target.matches('.title #download')) {
				if(confirm('다운로드를 진행하시겠습니까?')){
					//a태그가 원래 가지고 있던 요청을 그대로 가져와서 보내기
					location.href = e.target.getAttribute('href');
				}
				return;
			
			}

			//삭제 처리
			if(e.target.matches('.link-inner #delBtn')) {
				//비동기 방식으로 삭제를 진행해 주세요. (삭제 버튼은 여러 개 입니다!)
				//서버쪽에서 권한을 확인 해 주세요. (작성자와 로그인 중인 사용자의 id를 비교해서 일치하는지의 여부)
				//일치하지 않는다면 문자열 "noAuth" 리턴, 삭제 완료하면 "success" 리턴
				//url: /snsboard/글번호 method: DELETE
				const bno = e.target.getAttribute('href');
				console.log('글번호: ', bno);
				fetch('${requestContext.request.contextPath}/snsboard/'+bno, {
					method : 'delete'
				})
				.then(res => res.text())
				.then(data => {
					if(data === "noAuth") alert('권한이 없습니다.');
					else if(data === 'fail') alert('관리자에게 문의하세요.');
					else {
						alert('게시물이 정상적으로 삭제되었습니다.');
						getList(1,true); // 삭제가 반영된 새로운 글 목록 보여주기
					}
				});

				/*
				.then(res => {
					console.log(res.status); //400, 200, 401 이런 것이 뜰 것!
					if(res.stauts === 400) {
						alert('궏한이 없습니다.');
					} else if (res.status === 500) {
						alert('관리자에게 문의하세요.');
					} else {
						alert('게시물이 정상적으로 삭제되었습니다.');
						getLsit(1,true);
					}
				})
				*/

				return;
			}



			//글 번호 얻기
			const bno = e.target.dataset.bno;
			console.log('bno: ', bno);

			
			
			//fetch함수를 사용하여 글 상세 보기 요청을 비동기 식으로 요청하세요.
			fetch('${pageContext.request.contextPath}/snsboard/content/'+bno)
			.then(res => res.json())
			.then(data => {
				//console.log(data);

				if(data.bno == null) {
					alert('요청 중 문제가 발생하여 글을 불러오지 못했습니다.');
					return;
				}

				const imgSrc = '${requestContext.request.contextPath}/snsboard/display/'+data.fileLoca+'/'+data.fileName;
				document.querySelector('.modal-img img').setAttribute('src', imgSrc);
				document.querySelector('.modal-inner #snsWriter').textContent = data.writer ;
				document.querySelector('.modal-inner #snsRegdate').textContent = data.regDate;
				document.querySelector('.modal-inner #snsContent').textContent = data.content;
			});
            // url: /snsboard/content/글번호 -> GET
            //전달 받은 글 내용을 미리 준비한 모달창에 뿌릴 겁니다.(모달 위에 있어요.)
            //값을 제 위치에 배치하시고 모달을 열어 주세요. 
            //(부트스트랩 모달이기 때문에 jQuery로 열어주세요.)
			$('#snsModal').modal('show');
		});



		/*
        쓰로틀링 - 지정한 일정한 시간 간격 내에서 지정 함수가 한 번만 실행되도록 보장함.

        쓰로틀링은 사용자가 이벤트를 몇번이나 발생시키든, 일정한 간격으로
        한 번만 실행하도록 하는 기법.
        마우그 움직임, 스크롤 이벤트 같은 짧은 주기로 자주 발생하는 경우에 사용하는 기법 (lodash 라이브러리를 이용해 구현)
		_.throttle((콜백)함수, 밀리초)
        */
		//handleScroll은 함수임!! 얘 함수야!!!
		const handleScroll = _.throttle(() => {
			console.log('throttle actiavate!');

			const scrollPosition = window.pageYOffset;
			//body 태그 지목방법
			const height = document.body.offsetHeight;
			const windowHeight = window.innerHeight;

			if(isFinish) {
				//공식, 비율 설정은 변경 가능: 스크롤이 90% 지점에 닿으면!
				if(scrollPosition + windowHeight >= height * 0.9) {
					console.log('next page call!');
					console.log('getList전 페이지: ',page);
					getList(++page, false);
					console.log('getList 후 페이지: ',page);
				}
			}

		}, 1000);


		//브라우저 창에서 스크롤이 발생할 때마다 이벤트 발생.
		window.addEventListener('scroll', () => {
			if(!reqStatus) handleScroll();
		});



		//좋아요 기능 구현
		$contentDiv.addEventListener('click', (e) => {
			e.preventDefault();
			if(!e.target.matches('#likeBtn')) return;
			console.log('좋아요 버튼이 클릭됨');

			const id = '${login}'; //현재 로그인 중인 사용자의 아이디
			if(id === '') {// 로그인을 하지 않은 사람은 좋아요 버튼을 누를 수 없음
				alert('로그인이 필요합니다.');
				return;
			}
			const bno = e.target.getAttribute('href'); //좋아요를 누른 글 번호

			fetch('${pageContext.request.contextPath}/snsboard/like', {
				method : 'post',
				headers : {
					'Content-Type' : 'application/json'
				},
				body : JSON.stringify({
					'userId' : id,
					'bno' : bno,

				})
			})
			.then(res => res.text())
			.then(result => {
				console.log('result: ', result);

				if(result === 'like') {
					//하트 이미지를 색칠된 이미지로 바꿔줄 것임.
					e.target.firstElementChild.setAttribute('src','${pageContext.request.contextPath}/img/like2.png');
					e.target.style.color="blue";
					const $cnt = e.target.parentNode.previousElementSibling.children[1];
					$cnt.textContent = Number($cnt.textContent) + 1;
				} else {
					e.target.firstElementChild.setAttribute('src','${pageContext.request.contextPath}/img/like1.png');
					e.target.style.color="black";
					const $cnt = e.target.parentNode.previousElementSibling.children[1];
					$cnt.textContent = Number($cnt.textContent) - 1;
				}
			});
		});









		//자바 스크립트 파일 미리보기 기능
        function readURL(input) {
            if (input.files && input.files[0]) {

                var reader = new FileReader(); //비동기처리를 위한 파읽을 읽는 자바스크립트 객체
                //readAsDataURL 메서드는 컨텐츠를 특정 Blob 이나 File에서 읽어 오는 역할 (MDN참조)
                reader.readAsDataURL(input.files[0]);
                //파일업로드시 화면에 숨겨져있는 클래스fileDiv를 보이게한다
                //$(".fileDiv").css("display", "block");
                document.querySelector('.fileDiv').style.display = 'block';

                reader.onload = function (event) { //읽기 동작이 성공적으로 완료 되었을 때 실행되는 익명함수
                    //$('#fileImg').attr("src", event.target.result);
                    document.getElementById('fileImg').setAttribute('src', event.target.result);

                    console.log(event.target) //event.target은 이벤트로 선택된 요소를 의미
                }
            }
        }

        document.getElementById('file').onchange = function () {
            readURL(this); //this는 #file자신 태그를 의미

        };

	</script>