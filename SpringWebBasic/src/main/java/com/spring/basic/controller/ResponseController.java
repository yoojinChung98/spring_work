package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.spring.basic.model.UserVO;

@Controller
@RequestMapping("/response")
public class ResponseController {

	@GetMapping("/res-ex01")
	public void resEx01(){}
	
	
	/*
	1. Model 객체를 사용하여 응답할 화면에 데이터를 전송 하기
	스프링에서 제공하는 Model 타입의 객체를 활용하여 Jsp 파일과 같은 View 템플릿으로 데이터를 전송할 수 있습니다.
	Model 객체는 기본적으로 Request 객체의 attribute로 설정되어 전송되므로,
		기존에 알고 계시던 forward 방식의 응답과 유사함.
		(그러나 따로 forward를 할 필요 없이 그냥 담아만 놓으면 됨, 스프링내부적으로 이 Model은 Request영역에 저장됨.)
	 
	@GetMapping("/test")
	public void test(int age, Model model) {
		//단순히 test.jsp로 가는것 뿐 만 아니라 전달받은 파라미터를 같이 보내고 싶다!
		//스프링에서 호출하는 것(내가 호출x)이기 때문에 어떤 객체가 필요하다고 하면 Spring이 알아서 객체를 생성해서 준대!
		model.addAttribute("age",age);
		model.addAttribute("nick", "멍멍이");
		
	}
	*/
	
	
	//2. @ ModelAttribute를 사용한 화면에 데이터 전송 처리
	// @RequestParam + model.addAttribute 처럼 동작.
	@GetMapping("/test")
	public void test(@ModelAttribute("age") int age, Model model) {
		//int age와 동일한 이름의 파라미터를 가져와서 Model 객체에 "age"란 이름으로 담았음.
		//게 아니라 int age와 동일한 이름의 파라미터를 가져워서 "age"란 이름의 Model 객체에 담은 것.
		//아니 modelAttribute라는데 모델객체에 age라는 이름의 속성이 하나 더 생기는거 아님???
		// 둘은 같은 모델이래ㅋㅋㅋㅋㅋ
		// 형태는 다르지만, 결국엔 둘다 Model 객체에 "age"란 이름으로 방금 들어온 파라미터 하나 넣고
		// addAttribute메서드를 이용하기 위해서 굳이 Model하나 생성해서(말이 생성이지 결국엔 같은 Model임) "nick"이란 이름의 '짹짹이'를 model에 넣은 것.
		model.addAttribute("nick","짹짹이");
	}
	
	@GetMapping("/test2")
	public void test2(@ModelAttribute("info") UserVO vo) {
		//info 라는 이름으로 vo를 Model에 저장할 것
		//이 경우 메서드블록에 아무것도 작성하지 않아도 무방... 이미 model 객체에 vo를 넣었고, test2.jsp로 연결될거니까.
	}
	
	
	
	//3. ModelAndView 객체를 활용한 처리
	//데이터를 view 템플릿으로 전달하는 model의 역할과, 사용자에게 응답하고자 하는 페이지를 지정하는 역할을 동시에 진행.
	@GetMapping("/test3")
	public ModelAndView test3() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("userName","김철수");
		mv.addObject("userAge", 25);
		mv.setViewName("/response/test3"); //내가 연결되고자 하는 view
		return mv;
	}
	/*
	#우리가 이렇게 작성하면 상단의 메서드와 동일하게 수행됨.
	내가 써 놓은 모델들과 문자열 반환값이 setViewName이랑 똑같이 수행돼서
	결국에는 위나 아래나 결국에 Dispatcher Servlet에는 ModelAndView 객체가 전달됨.
	@GetMapping("/test3")
	public String test3(Model model) {
		model.addAttribute("username","김철수");
		model.addAttribute("userAge", 25);
		return "response/test3";
	}
	*/
	
	
}
