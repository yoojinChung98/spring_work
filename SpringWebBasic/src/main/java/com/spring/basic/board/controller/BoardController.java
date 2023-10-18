package com.spring.basic.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.board.dto.BoardRequestDTO;
import com.spring.basic.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board") //슬래시 보드라는 요청(공통 url)이 들어오면 일괄적으로 다 받겠다는 아노테이션
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	
	//글 작성 화면을 열어주는 메서드
	@GetMapping("/write")
	public void write() {
		System.out.println("/board/write: GET요청");
	}
	
	//글 등록 요청 메서드
	@PostMapping("/write")
	public String regist(String writer, String title, String content) {
		System.out.println("/board/wirte: POST요청");
		service.insertArticle(writer, title, content);
		//글 등록 완료 후 /board/list 요청이 다시 들어올 수 있게끔 redirect 처리
		return "redirect:/board/list";
	}
	
	//글 목록 화면 요청
	@GetMapping("/list")
	public void boardList(Model model) {
		System.out.println("/board/list: GET요청");
		model.addAttribute("articles", service.getArticles());
	}
	
	//글 내용 상세보기 요청 처리 메서드
	@GetMapping("/content")
	public void content(int boardNo, Model model) {
		System.out.println("/board/content?boardNo= " + boardNo);
		retrieve(boardNo, model);
	}
	
	//글 수정하기 화면으로 이동 요청
	@GetMapping("/modify")
	public void modify(int boardNo, Model model) {
		System.out.println("/board/modify?boardNo= " + boardNo);
		retrieve(boardNo, model);	
	}
	
	//글 수정 처리 요청
	@PostMapping("/modify")
	public String modify(BoardRequestDTO dto) {
		service.updateArticle(dto);
		return "redirect:/board/content?boardNo="+dto.getBoardNo();
	}
	
	//삭제(삭제 클릭하면 해당 글이 삭제 될 수 있도록)
	@GetMapping("/delete")
	public String delete(int boardNo) {
		System.out.println("/board/delete:GET, boardNo: "+boardNo);
		service.deleteArticle(boardNo);
		return "redirect:/board/list";
	}
	
	
	//하나의 데이터의 모든 데이터를 받아오는 메서드
	private void retrieve(int boardNo, Model model) {
		model.addAttribute("article",service.retrieve(boardNo));
	}
	
}
