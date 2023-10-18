<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="../include/header.jsp" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>수정하기</p>
                        </div>
                        
<<<<<<< HEAD
                        <form action="${pageContext.request.contextPath}/freeboard/modify" method="post" name="updateForm">
=======
                        <form action="/myweb/freeboard/modify" method="post" name="updateForm">
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
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
                                <input class="form-control" name='title' value="${article.title}">
                            </div>
                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content'>${article.content}</textarea>
                            </div>

                            <button id="list-btn" type="button" class="btn btn-dark">목록</button>    
                            <button id="update-btn" type="button" class="btn btn-primary">변경</button>
                            <button id="del-btn" type="button" class="btn btn-info">삭제</button>
                    </form>
                                    
                </div>
            </div>
        </div>
        </section>
      
      <%@ include file="../include/footer.jsp" %>

        <script>
            //목록 이동 처리
            document.getElementById('list-btn').onclick = function() {
<<<<<<< HEAD
                location.href = '${pageContext.request.contextPath}/freeboard/freeList';
=======
                location.href = '/myweb/freeboard/freeList';
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
            }

            //form 태그는 메서드 없이 form 태그의 name으로 요소를 바로 취득할 수 있음
            const $form = document.updateForm;
            //수정 버튼 이벤트 (유효성검사 포함.)
            document.getElementById('update-btn').onclick = function() {
                //form 내부의 요소를 지목하 땐 name 속성으로 바로 지목이 가능합니다.(다른 요소들은 태그의 요소도 따로 취득을 해야 사용 가능)
                if($form.title.value === ''){
                    alert('제목은 필수 항목입니다.');
                    return; //이벤트 종료. title은 NotNull 임.
                } else if($form.content.value === ''){
                    alert('내용은 필수 항목입니다.');
                    return; //이벤트종료. content는 NotNull
                }

                //문제가 없다면 form을 submit. submit()은 submit하는 자바스크립트의 메서드.
                $form.submit();
            }

            //삭제 요청 처리 (화살표함수 써봄)
            document.getElementById('del-btn').onclick = () => {
                if(confirm('정말 삭제하시겠습니까?')) {//confirm (확인 + 취소취소 버튼이 있는 알림!)
<<<<<<< HEAD
                    $form.setAttribute('action', '${pageContext.request.contextPath}/freeboard/delete');
=======
                    $form.setAttribute('action', '/myweb/freeboard/delete');
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
                    //setAttribute가 누구메서드임? 폼의 메서드임?
                    $form.submit();
                }
            }


        </script>