package com.spring.myweb.reply.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.reply.dto.ReplyListResponseDTO;
import com.spring.myweb.reply.dto.replyRegistDTO;
import com.spring.myweb.reply.entity.Reply;
import com.spring.myweb.reply.mapper.IReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService implements IReplyService{
	
	private final IReplyMapper mapper;
	private final BCryptPasswordEncoder encoder;
	
	@Override
	public void replyRegist(replyRegistDTO dto) {
		dto.setReplyPw(encoder.encode(dto.getReplyPw())); //비밀번호 암호화
		mapper.replyRegist(dto.toEntity(dto));
		
		
	}

	@Override
	public List<ReplyListResponseDTO> getList(int bno, int pageNum) {
		
		Page page = Page.builder()
						.pageNo(pageNum)
						.amount(5)
						.build();
		//화면에서 전달된 댓글페이지 번호 
		//댓글은 한 화면에 5개씩
		
		System.out.println("44행: "+bno+pageNum+page);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("paging", page); //xml에서 사용할 "키값",value 로 맞춰놓는 것.
		map.put("boardNo", bno);
		
		System.out.println("51행: "+map);
		
		List<ReplyListResponseDTO> dtoList = new ArrayList<>();
		for(Reply reply : mapper.getList(map)) {
			System.out.println("반복문 부분: "+reply);
			dtoList.add(new ReplyListResponseDTO(reply));
		}
		
		System.out.println("58행: "+dtoList);
		
		return dtoList;
	}

	@Override
	public int getTotal(int bno) {
		return mapper.getTotal(bno);
	}

	@Override
	public String pwCheck(int rno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Reply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int rno) {
		// TODO Auto-generated method stub
		
	}
	
	
}
