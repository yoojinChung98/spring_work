package com.spring.myweb.reply.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myweb.reply.dto.ReplyListResponseDTO;
import com.spring.myweb.reply.dto.ReplyUpdateRequestDTO;
import com.spring.myweb.reply.dto.replyRequestDTO;
import com.spring.myweb.reply.service.IReplyService;

import lombok.RequiredArgsConstructor;

//@Controller
@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
	
	private final IReplyService service;
	
	//댓글 등록 요청(POST)
	@PostMapping()
	@ResponseBody
	public String replyRegist(@RequestBody replyRequestDTO dto) {
		System.out.println("댓글 등록 요청 들어옴" + dto);
		service.replyRegist(dto);
		return "regSuccess";

	}
	
	//목록요청
	@GetMapping("/list/{bno}/{pageNum}")
	public Map<String, Object> getList(@PathVariable int bno, @PathVariable int pageNum) {
		
		/*
		1. 화면단에서 getList 메서드가 글 번호, 페이지 번호를 url을 통해 전달합니다.
		2. Mapper 인터페이스에게 복수의 값을 전달하기 위해 Map을 쓸지, @Param을 쓸 지 결정.
		3. ReplyMapper.xml에 sql문을 페이징 쿼리로 작성.
		4. 클라이언트 측으로 DB에서 조회한 댓글 목록을 보낼 때, 페이징을 위한 댓글의 총 개수도 함께 보내줘야 함.
			근데 우리는 return을 한 개만 쓸 수 있으니까, 복수 개의 값을 리턴하기 위해, 리턴 타입을 Map/새로 디자인한 객체로 줄지 결정해야 함
			(댓글 목록 리스트와 전체 댓글 개수를 함께 전달할 예정) -> 일회성으로 쓸거니까 Map으로 클라이언트에게 전달할것임!
		 */
		
		
		System.out.println("/list/"+bno+"/"+pageNum);
		List<ReplyListResponseDTO> list = service.getList(bno, pageNum); //댓글 목록
		int total = service.getTotal(bno); //게시글의 댓글의 총개수

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);

		
		return map;
	}
	
	//댓글 수정 요청
	@PutMapping("/{rno}")
	public String update(@PathVariable int rno, @RequestBody ReplyUpdateRequestDTO dto) {
		dto.setReplyNo(rno);
		return service.update(dto);
	}
	
	//댓글 삭제 요청
	@DeleteMapping("/{rno}")
	public String delete(@PathVariable int rno, @RequestBody String replyPw) {
		return service.delete(rno, replyPw);
	}
	
	

}
