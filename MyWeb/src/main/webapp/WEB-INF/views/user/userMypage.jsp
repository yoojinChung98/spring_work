<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="../include/header.jsp" %>>

	<section>
        <!--Toggleable / Dynamic Tabs긁어옴-->
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-10 col-lg-9 myInfo">
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                    <ul class="nav nav-tabs tabs-style">
                        <li class="active"><a data-toggle="tab" href="#info">내정보</a></li>
                        <li><a data-toggle="tab" href="#myBoard">내글</a></li>
                        <li><a data-toggle="tab" href="#menu2">Menu 2</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="info" class="tab-pane fade in active">
 
                            <p>*표시는 필수 입력 표시입니다</p>
                            <form>
                            <table class="table">
                                <tbody class="m-control">
                                    <tr>
                                        <td class="m-title">*ID</td>
                                        <td><input class="form-control input-sm"></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*이름</td>
                                        <td><input class="form-control input-sm"></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*비밀번호</td>
                                        <td><input class="form-control input-sm"></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*비밀번호확인</td>
                                        <td><input class="form-control input-sm"></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*E-mail</td>
                                        <td>
                                            <input class="form-control input-sm" name="userEmail1" value="${userInfo.userEmail1}">
                                            <select class="form-control input-sm sel" name="userEmail2">
                                                <option ${userInfo.userEmail2 == '@naver.com' ? 'selected' : ''}>@naver.com</option>
                                                <option ${userInfo.userEmail2 == '@daum.net' ? 'selected' : ''}>@daum.net</option>
                                                <option ${userInfo.userEmail2 == '@gmail.com' ? 'selected' : ''}>@gmail.com</option>
                                                <option ${userInfo.userEmail2 == '@hanmail.com' ? 'selected' : ''}>@hanmail.com</option>
                                                <option ${userInfo.userEmail2 == '@yahoo.co.kr' ? 'selected' : ''}>@yahoo.co.kr</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">휴대폰</td>
                                        <td>
                                            <select class="form-control input-sm sel" name="userPhone1">
                                                <option ${userInfo.userPhone1 == '010' ? 'selected' : ''}>010</option>
                                                <option ${userInfo.userPhone1 == '011' ? 'selected' : ''}>011</option>
                                                <option ${userInfo.userPhone1 == '017' ? 'selected' : ''}>017</option>
                                                <option ${userInfo.userPhone1 == '018' ? 'selected' : ''}>018</option>
                                            </select>
                                            <input class="form-control input-sm" name="userPhone2" value="${userInfo.userPhone2}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">우편번호</td>
                                        <td><input class="form-control input-sm" name="addrZipNum" value="${userInfo.addrZipNum}" readonly>
                                        	<button type="button" class="btn btn-primary" id="addBtn">주소찾기</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*주소</td>
                                        <td><input class="form-control input-sm add" name="addrBasic" value="${userInfo.addrBasic}"></td>
                                    </tr>
                                    <tr>
                                        <td class="m-title">*상세주소</td>
                                        <td><input class="form-control input-sm add" name="addrDetail" value="${userInfo.addrDetail}"></td>
                                    </tr>
                                </tbody>
                            </table>
                            </form>

                            <div class="titlefoot">
                                <button class="btn">수정</button>
                                <button class="btn">목록</button>
                            </div>
                        </div>
                        <!-- 첫번째 토글 끝 -->
                        
                        <!-- 두번째 토글 메뉴의 시작 -->
                        <div id="myBoard" class="tab-pane fade">
                            <p>*내 게시글 관리</p>
                            <form>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>번호</td>
                                        <td>제목</td>
                                        <td>작성일</td>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="b" items="${userInfo.userBoardList}">
									<tr>
                                        <td>${b.bno}</td>
                                        <td><a href="##">${b.title}</a></td>
                                        <td>${b.date}</td>
                                    </tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                            </form>
                        </div>
                        <!-- 두번째 토글 끝 -->
                        <div id="menu2" class="tab-pane fade">
                            <h3>Menu 2</h3>
                            <p>Some content in menu 2.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    
   <%@ include file="../include/footer.jsp" %>
