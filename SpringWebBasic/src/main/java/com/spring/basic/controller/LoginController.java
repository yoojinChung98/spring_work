package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.basic.model.HwUserVO;


@Controller
@RequestMapping("/hw")
public class LoginController {
	
	/*
    1번요청: 로그인 폼 화면 열어주기
    - 요청 URL : /hw/s-login-form : GET
    - 포워딩 jsp파일 경로:  /WEB-INF/views/response/s-form.jsp
    - html form: 아이디랑 비번을 입력받으세요.
	*/
	@GetMapping("/s-login-form")
	public String login() {
		return "response/s-form";
	}
	


	/*
    2번요청: 로그인 검증하기
    - 로그인 검증: 아이디를 grape111이라고 쓰고 비번을 ggg9999 라고 쓰면 성공
    - 요청 URL : /hw/s-login-check : POST
    - 포워딩 jsp파일 경로:  /WEB-INF/views/response/s-result.jsp
    - jsp에게 전달할 데이터: 로그인 성공여부, 아이디 없는경우, 비번 틀린경우
    - 로그인 여부를 "success", "f-pw", "f-id" 문자열로 전송.
	 */

	
//	@PostMapping("/s-login-check")
//	public String login(@ModelAttribute("newUser") HwUserVO vo, Model model) {
//		String result = "success";
//		String id = vo.getId();
//		String pw = vo.getPw();
//		
//		if(id == null || !id.equals("grape111")) {
//			result = "fail-id";
//		} else if(pw == null || !pw.equals("ggg9999")){
//			result = "fail-pw";
//		}
//		
//		model.addAttribute("result",result);
//		return "response/s-result";
//	}

	
	
	@PostMapping("/s-login-check")
	public String login(String id, String pw, Model model) {
		
		String result = "success";
		
		if(!id.equals("grape111")) {
			result = "f-id";
		} else if(!pw.equals("ggg9999")){
			result = "f-pw";
		}
		
//		if(id.equals("grape111")) {
//			if(pw.equals("ggg9999")) {
//				result="sucess";
//			} else {
//				result = "f-pw";
//			}
//		} else {
//			result = "f-id";
//		}
		
		model.addAttribute("result",result);
		
		return "response/s-result";	
	}
    
    
    //s-result.jsp에서 전송된 로그인 여부를 확인해서 적절한 화면을 응답.

	

}
