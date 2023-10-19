<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
    <%@ include file="../include/header.jsp" %>
    
    
    <section>
        <div class="container-fluid">
            <div class="row">
                <!--lg에서 9그리드, xs에서 전체그리드-->   
                <div class="col-lg-9 col-xs-12 board-table">
                    <div class="titlebox">
                        <p>자유게시판</p>
                    </div>
                    <hr>
                    
                    <!--form select를 가져온다 -->
            <form action="${pageContext.request.contextPath}/freeboard/freeList">
		    		<div class="search-wrap">
                       <button type="submit" class="btn btn-info search-btn">검색</button>
                       <input type="text" name="keyword" class="form-control search-input" value="${pc.page.keyword}">
                       <select name="condition" class="form-control search-select">
                            <option value="title" ${pc.page.condition== 'title' ? 'selected' : ''}>제목</option>
                            <option value="content" ${pc.page.condition== 'content' ? 'selected' : ''}>내용</option>
                            <option value="writer" ${pc.page.condition== 'writer' ? 'selected' : ''}>작성자</option>
                            <option value="titleContent" ${pc.page.condition== 'titleContent' ? 'selected' : ''}>제목+내용</option>
                       </select>
                    </div>
		    </form>
                   
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th class="board-title">제목</th>
                                <th>작성자</th>
                                <th>등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="vo" items="${boardList}">
	                            <tr>
	                                <td>${vo.bno}</td>
	                                <td><a href="${pageContext.request.contextPath}/freeboard/content?bno=${vo.bno}&pageNo=${pc.page.pageNo}&amount=${pc.page.amount}&keyword=${pc.page.keyword}&condition=${pc.page.condition}">${vo.title}</a></td>
	                                <td>${vo.writer}</td>
	                                <td>${vo.date}</td>
	                            </tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>


                    <!--페이지 네이션([이전]페이지버튼들[다음] 을 모두 합쳐서 페이지네이션이라고 부름)을 가져옴-->
		    <form action="${pageContext.request.contextPath}/freeboard/freeList" name="pageForm">
                    <div class="text-center">
                    <hr>
                    <ul id="pagination" class="pagination pagination-sm">
                        <c:if test="${pc.prev}">
                            <li><a href="#" data-pagenum="${pc.begin-1}">이전</a></li>
                        </c:if>

                        <c:forEach var="num" begin="${pc.begin}" end="${pc.end}">

                            <li class="${pc.page.pageNo == num ? 'active' : ''}" ><a href="#" data-pagenum="${num}">${num}</a></li>
                            
                        </c:forEach>
                        
                        <c:if test="${pc.next}">
                            <li><a href="#" data-pagenum="${pc.end+1}">다음</a></li>
                        </c:if>
                    </ul>
                    <button type="button" class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/freeboard/freeRegist'">글쓰기</button>
                    </div>

                    <input type="hidden" name="pageNo" value="${pc.page.pageNo}"/>
                    <input type="hidden" name="amount" value="${pc.page.amount}"/>
                    <input type="hidden" name="keyword" value="${pc.page.keyword}"/>
                    <input type="hidden" name="condition" value="${pc.page.condition}"/>
		    </form>

                </div>
            </div>
        </div>
	</section>
	
	<%@ include file="../include/footer.jsp" %>

    <script>
        //브라우저 창이 로딩이 완료된 후에 실행할 것을 보장하는 이벤트
        window.onload = function() {
            //이벤트 를 부모에 걸어서 편하게 갑시다 (각 버튼마다 이벤트를 걸기 보다는)
            //이벤트 전파 방식을 사용하겠음

            //사용자가 페이지 관련 버튼을 클릭했을 때 (이전, 다음, 1, 2, 3 ...)
            //a태그의 href에다가 각각 다른 url을 작성해서 요청을 보내기가 귀찮다.
            //클릭한 버튼이 무엇인지를 확인해서 그 버튼에 맞는 페이지 정보를 자바스크립트로 끌고와서 요청을 보내주겠다.
            //e = 이벤트객체임(부모태그에 건 이벤트가 어디에 걸린건지 체크해서 걍 이벤트 종료할지 결정)
            document.getElementById('pagination').addEventListener('click', e => {
                if(!e.target.matches('a')){
                    return;
                }// click 이벤트가 발생한 태그가 a태그가 아니라면 이벤트 종료

                e.preventDefault(); //a태그의 고유 기능 중지

                //현재 이벤트가 발생한 요소 (버튼)의 data-pagenum의 값을 얻어서 변수에 저장
                //data- 으로 시작하는 속성값을 dataset 프로퍼티로 쉽게 끌고 올 수 있음 (모든 태그에 적용 가능)
                const value = e.target.dataset.pagenum;

                //페이지 버튼들을 감싸고 있는 form 태그를 지목하여 그 안에 숨겨져 있는 input 태그의 value에
                //위에서 얻은 data-pagenum의 값을 삽입한 후에 submit
                document.pageForm.pageNo.value = value;
                document.pageForm.submit();

            }); 

            const msg = '${msg}';
            if(msg === 'searchFail') {
                alert('검색 결과가 없습니다.')
            }



        }

    </script>



