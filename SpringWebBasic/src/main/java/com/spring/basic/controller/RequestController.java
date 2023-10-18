package com.spring.basic.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.basic.model.UserVO;

//자동으로 스프링 컨테이너에 해당 클래스의 빈을 등록하는 아노테이션
//빈을 등록해 놔야(객체가 생성되어 있어야) HandlerMapping이 이 클래스의 객체를 검색할 수 있음

@Controller//("wanted_beanId") //디폴트는 클래스이름
@RequestMapping("/request") //컨트롤러 자체에 공통된 URI 맵핑
public class RequestController {

//	빈 등록이 어느 시점에 생성되는지 확인하기 위한 출력문
//	public RequestController() {
//		System.out.println("RequestCon 생성됨!");
//	}
	
	@RequestMapping("/test")
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴!");
		return "test";
	}
	
	/* 만약 사용자가 /request/req 요청을 보내왔을 때, view 폴더 아래의
	 * request 폴더 안에 존재하는 req-ex01.jsp파일을 열도록 메서드 구성*/
	@RequestMapping("/req")
	public String req() {
		System.out.println("/request/req 요청이 들어옴!");
		return "request/req-ex01";
	}
	
	
	
	
	//요청 URI 주소가 같더라도, 전송 방식에 따라 맵핑을 다르게 하기 때문에
	//같은 주소를 사용하는 것이 가능합니다. (GET -> 화면처리, POST -> 입력값 처리)
	
	@RequestMapping(value="/basic01", method= RequestMethod.GET)
//	@GetMapping("/request/basic01") // spring 4버전부터 사용 가능
	public String basicGet() {
		System.out.println("/basic01 요청이 들어옴!: GET 방식");
		return "/request/req-ex01";
	}
	
	@RequestMapping(value="/basic01", method= RequestMethod.POST)
//	@PostMapping("/request/basic01")
	public String basicPost() {
		System.out.println("/basic01 요청이 들어옴!: POST 방식");
		return "/request/req-ex01";
	}

	//////////////////////////////////////////////

	//컨트롤러 내의 메서드 타입을 void 로 선언하면
	//요청이 들어온 URL 값을 뷰 리졸버에게 전달합니다.
	@GetMapping("/join")
	public void register() {
		System.out.println("/request/join: GET");
	}
	//만약 컨트롤러 내에 리턴타입이 void ? url을 보고 리턴할 곳을 지정한다?
	//url을 파일 경로로 삼겠다는 의미. ( 앞에 슬래시 빼고 request/join 을 파일경로로 잡을 것임)
	//그럼 결국 접두어/ request/join  접미어 파일이 완성된 url이 될 것.
	
	
	/*
	# 스프링에서 요청과 함께 전달된 데이터를 처리하는 방식
	
	1. 전통적인 jsp/servlet 방식의 파라미터 읽기 처리 방법
	-HttpServletRequest 객체를 활용 (우리가 jsp에서 사용하던 방식) -> 스프링에선 잘 사용하지 않음

	@PostMapping("/join")
	public void register(HttpServletRequest request) {
		System.out.println("/request/join:POST");
		
		System.out.println("ID: "+request.getParameter("userId"));
		System.out.println("PW: "+request.getParameter("userPw"));
		System.out.println("HOBBY: "+Arrays.toString(request.getParameterValues("hobby")));
	}
	*/
	
	/*
	2. @RequestParam 아노테이션을 이용한 요청 파라미터 처리.
		@RequestParam("파라미터 변수명") 값을 받아서 처리할 변수
		파라미터 변수명과 값을 받을 변수명을 동일하게 작성하면  @RequestParam 생략 가능.

//	//스프링 근본방식

	@PostMapping("/join")
	public void register(
			String userId,
			@RequestParam("userPw") String userPw,
			@RequestParam(value="hobby", required=false,
						defaultValue = "no hobby person") List<String> hobby
	){
		System.out.println("ID: "+ userId);
		System.out.println("PW: "+ userPw);
		System.out.println("hobby: "+ hobby);
	}
	
	//RequestParam은 무조건 값을 하나라도 받아야 하는 아노테이션이므로
	//값이 오지 않으면 요청을 거부해버림. 400에러 발생.
	//required 는 체크박스를 선택하지 않아도 에러가 뜨지 않고 요청을 받을 수 있게 해줌
	// 이 경우, null을 받고 싶지 않으면, 아무것도 선택되지 않았을 때 디폴트값을 설정할 수 있음 (defaultValue)
	//String 같은 경우는 입력하지 않으면 null이 온다기보다는 ""로 입력이 되더라!!! 그래서 에러가 안뜨더라!!
	*/
	
	
	/*
	3. 커맨드 객체를 활용한 파라미터 처리
	- 파라미터 데이터와 연동되는 "값을 표현하는 객체" VO 클래스가 필요합니다.
	- VO 클래스의 필드는 파라미터 변수명과 동일하게 작성합니다. (setter 메서드를 호출)

	# 커맨드 객체: 사용자의 입력을 담기 위해 설계되고, 특정 검증 로직이나 비즈니스 로직을 수행할 수 있음.
		(VO 보다는 역할이 좀더 많고, 특정 목적을 가진 객체)(비슷해보이지만 목적에 따라 부르는 호칭이 조금 다른 것)
	# VO: 데이터베이스와 값을 주고받을 때 정말 값을 담기만 할 목적으로 사용하는 객체.

	커맨드 객체로 파라미터를 처리할 때 VO 객체를 많이 활용하는 것 뿐!
	커맨드 객체는 따로 자동주입을 하지 않아도 Spring이 스스로 생성해서 주입해준다고 합니다!
	*/
	@PostMapping("/join")
	public void register(UserVO vo) {
		System.out.println(vo);
	}
	
	
	
	
	

	
	
	
	
	
}












