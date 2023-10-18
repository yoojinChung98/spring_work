package com.spring.myweb.user.controller;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;

=======
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.user.dto.UserJoinRequestDTO;
import com.spring.myweb.user.service.UserService;
import com.spring.myweb.util.MailSenderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	//서비스 주입
	private final UserService service;
	//메일관련 서비스 주입
	private final MailSenderService mailService;
	
	//회원가입 페이지로 이동
	@GetMapping("/userJoin")
	public void userJoin() {}
	
	//아이디 중복 확인(비동기)
	/*
    @PathVariable은 URL 경로에 변수를 포함시켜 주는 방식
    null이나 공백이 들어갈 수 있는 파라미터라면 적용하지 않는 것을 추천
    파라미터 값에 .이 포함되어 있다면 .뒤의 값은 잘린다는 것을 알아두세요.
    {}안에 변수명을 지어주시고, @PathVariable 괄호 안에 영역을 지목해서
    값을 받아옵니다.
    
<<<<<<< HEAD
    ${pageContext.request.contextPath}/user/userJoin 도 받을 수 있는거 아님?
=======
    /myweb/user/userJoin 도 받을 수 있는거 아님?
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
     * Handler Mapping, Handler Adapter가 요청에 맞는 메서드를 위에서부터 순차적으로 탐색.
     * 해당 요청은 상단의 회원가입 페이지 이동이 적합해보여!
     * 오케이 얘로하자! 하고 아래는 가지도 않음
     * 
    */
	@GetMapping("/{account}")
	@ResponseBody
	public String idCheck( @PathVariable String account ) {
		System.out.println("클라이언트로부터 전달된 아이디: "+account);
		int result = service.idCheck(account);
		if(result == 1) return "duplicated";
		else return "ok";
	}
	
	//이메일 인증
	@PostMapping("/email")
	@ResponseBody
	//전달받는 값이 파라미터로 받는게 아니라서 @RequestBody가 필요한 것.
	//그러면 get 방식으로 받으면 @requestBodyrk 필요 없나?
	public String mailCheck(@RequestBody String email) {
		System.out.println("이메일 인증 요청: "+email);
		
		return mailService.joinEmail(email); //인증번호 반환받음. 그걸 return(화면단으로 보냄)

	}
	
	
	//회원가입 처리 + 회원가입이 완료되었습니다 메시지도 띄우고 싶음
	// 리다이렉트인데 같이 보내주고 싶은 값이 있는데.. 어떡하지? = RedirectAttributes 객체에 넣어가면됨.
	@PostMapping("/join")
	public String join(UserJoinRequestDTO dto, RedirectAttributes ra) {
		service.join(dto);
		/*
		redirect 상황에서 model 객체를 사용하면 데이터가 제대로 전달되지 않습니다.
		model 객체가 forward 상황에서 사용하는 request의 대체제이기 때문에
		redirect를 통해 응답이 나갔다가 재요청이 들어오는 상황에서는 데이터가 소멸합니다.
		(parameter에 붙어서(노출되어서) 전달됨.)??
		
		redirect 상황에서 일회성으로 데이터를 전송할 때 사용하느 메서드 addFlashAttribute(name, value)
		데이터가 url에 노출되지 안혹, 한 번 이용한 후에는 알아서 소멸합니다.
		 */
		ra.addFlashAttribute("msg", "joinSuccess");
		return "redirect:/user/userLogin";
	}
	
	//로그인 페이지로 이동 요청
	@GetMapping("/userLogin")
	public void login() {}
	
	//로그인 요청
	@PostMapping("/userLogin")
<<<<<<< HEAD
	public void login(String userId, String userPw, Model model) {
		System.out.println("나는 UserController의 login! ");
		model.addAttribute("result", service.login(userId, userPw));
		
		
		//어? 이러면 다시 .jsp파일로 이동하는거 아냐?
		//어 맞음, 어차피 인터셉터가 가로챌거고, 거기서 경로 다시 설정할 수 있음.
	}
	
	//마이페이지로 이동 요청
	@GetMapping("/userMypage")
	public void userMypage(HttpSession session, Model model) {
		//마이페이지는 로그인 한 사람만 이동 가능 => 세션에서 꺼내 사용할 수 있음
		String id = (String) session.getAttribute("login");
		model.addAttribute("userInfo", service.getInfo(id));
	}
=======
	public void login(String userId, Model model) {
		service.login(userId);
	}
	
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
	
	
}
