package com.spring.basic.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.score.dto.ScoreListResponseDTO;
import com.spring.basic.score.dto.ScoreModiRequestDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.service.ScoreService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/score")
@RequiredArgsConstructor //: final 필드가 존재한다면 그것은(꼭 필요로하는 값은) 초기화 해주는 생성자.
							//+ 해당 필드에 @Autowired를 붙여줌.
public class ScoreController {
	
	
	private final ScoreService service;
	// 만약에 클래스의 생성자가 단 1개라면 자동으로 @Autowired를 작성해줌 (빈에 등록 된 객체를 자동으로 주입해줌)
	// 근데 나는 service 를 빈에 등록한 기억이 없는걸...? 아
	// ScoreSerivce 에서 @Service 로 빈등록 했네...?^^
	 

//	@Autowired
//	public ScoreController(ScoreService scoreService) {
//		this.service = scoreService;
//	}
	
	
	//1. 성적 등록 화면 띄우기 + 정보 목록 조회
	@GetMapping("/list")
	public String list(Model model) { //모델에 담아서 view에 구현할거니까.
		List<ScoreListResponseDTO> dtoList = service.getList();
		model.addAttribute("sList", dtoList);
		return "score/score-list";
	}
	
	//2. 성적 정보 등록 처리 요청.
	@PostMapping("/register")
	public String register(ScoreRequestDTO dto) {
		//단순 입력데이터 읽기
		System.out.println("score/register: POST! - " + dto);
		
		//서비스에게 성적정보 등록 + 합계평균 계산 일 시키기
		service.insertScore(dto);
		
		/*
		 등록 요청이 완료되었다면, 목록을 불러오는 로직을 여기다 작성하는 것이 아닌,
		 갱신된 목록을 불러오는 요청이 다시 들어올 수 있도록 유도를 하자 -> sendRedirect() HttpServletResponse객체 필요
		 
		 스프링에서는 "redirect:URL" 을 작성하면 내가 지정한 URL로 자동 재요청이 일어나면서
		 미리 준비해 둔 로직을 수행할 수 있음.
		 점수 등록 완료 -> 목록을 달라는 요청으로 유도 -> 목록 응답.
		 */
		
		//스프링 자체에서 리다이렉트 응답을 보내줄 수 있는 방법: 경로 앞에 redirect: 입력
		return "redirect:/score/list";
	}
	
	//3. 특정 학생의 성적 정보 상세 조회 요청
	@GetMapping("/detail")
	public String detail(int stuNum, Model model) {
		System.out.println("/score/detail: GET!");
//		Score score = service.retrieve(stuNum);
//		model.addAttribute("s", score);
		retrieve(stuNum, model);
		
		return "score/score-detail";
	}
	
	//4. 성적 정보 삭제 요청
	@GetMapping("/remove")
	public String remove(int stuNum) {
		System.out.println("/score/remove: GET!");
		
		service.delete(stuNum);
		return "redirect:/score/list";
	}
	
	//5. 수정 페이지로 이동 요청
	@GetMapping("/modify")
	public String modify(int stuNum, Model model) {
		retrieve(stuNum, model);
		return "score/score-modify";
	}
	
	/*
	//6. 수정된 내용을 실제로 데이터베이스에서 교체하는 명령
	//서비스한테 숫자랑 지금 변경된 과목값 DTO에 싸서 넘길까?oo (새로 갈아껴진 entity model에 담아서 detail.jsp로 return)
	//서비스: 해당 dto로 entity 하나 만들기. stuNum이랑 entity 넘기기 (새로 갈아껴진 entity return)
	//레파지토리: stuNum으로 score 찾아서 entity 갈아끼우고 새로갈아끼운 값 return
	@PostMapping("/modify")
	public String modify(int stuNum, ScoreModiRequestDTO dto, Model model) {
		Score score = service.replaceModify(stuNum, dto);
		model.addAttribute("s",score);
		return "score/score-detail";
	}
	*/
	
	//6. 쌤버전
	@PostMapping("/modify")
	public String modify(int stuNum, ScoreRequestDTO dto) {
		System.out.println("/score/modify: POST!");
		
		service.modify(stuNum, dto);
		return "redirect:/score/detail?stuNum="+stuNum;
	}
	
	
	
	// 3, 5 번에 중복되던 로직(stuNum에 해당하는 score를 가져오는)을 메서드화 시켜 편리성 증진
	private void retrieve(int stuNum, Model model) {
		Score score = service.retrieve(stuNum);
		model.addAttribute("s", score);
		
	}
	
	
	
	
	
	
	
	
}
