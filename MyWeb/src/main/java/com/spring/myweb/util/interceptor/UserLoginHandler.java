package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
=======

>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//인터셉터로 사용할 클래스는 HandlerInterceptor 인터페이스를 구현함.
public class UserLoginHandler implements HandlerInterceptor {

<<<<<<< HEAD
	//preHandle은 컨트롤러로 요청이 들어가기 전 처리해야 할 로직을 작성
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("나는 preHandle!");
=======
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
<<<<<<< HEAD
	//postHandle은 컨트롤러에서 나갈 때 공통 처리해야 할 내용을 작성
	// userLogin 이라는 요청이 컨트롤러에서 마무리 된 후, viewResolver로 전달이 되기 전
	// 로그인 성공 or 실패 여부에 따라 처리할 로직을 작성할 계획 입니다.
=======
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

<<<<<<< HEAD
		System.out.println("나는 postHandle! 로그인 인터셉트는 로그인 검증을 할거야! ");
		
		//1. 요청된 방식이 무엇인지를 알아야함 그리고 거를것 (지정한 요청url에 항상 반응할 것이기 때문)
		//request에게 물어볼거얌 (request.getMethod()) : GET POST 등을 문자열로 반환함
		System.out.println("요청 방식: " + request.getMethod());
		
		if(request.getMethod().equals("POST")) { //요청방식 POST -> 로그인 요청 이라면
			ModelMap map = modelAndView.getModelMap(); //모델 객체 꺼내기
			String result = (String) map.get("result");
			
			if(result != null) { //로그인 성공
				System.out.println("로그인 성공 로직이 동작!");
				//로그인 성공한 회원에게는 세션 데이터를 생성해서 로그인 유지를 하게 해 줌.
				HttpSession session = request.getSession();
				session.setAttribute("login", result);
				
				//로그인 성공 후, 메인페이지로 이동시킴
				response.sendRedirect(request.getContextPath() + "/"); //homecontroller가 동작하며 home.jsp로 보내줌
				
			} else { //로그인 실패
				modelAndView.addObject("msg", "loginFail");
				//이러고 아까 controller에서 설정한 userLogin.jsp 로 "msg"라는 데이터를 가지고 가던길 갈 것.
			}
		}
		
		
		
=======
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

<<<<<<< HEAD
		
		
		
		
=======
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
	}
	
	
	
}
