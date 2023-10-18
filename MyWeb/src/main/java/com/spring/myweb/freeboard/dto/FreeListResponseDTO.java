package com.spring.myweb.freeboard.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

// 클라이언트 측으로 게시글 목록을 줄 때의 스펙 정의.
@Getter
@ToString @EqualsAndHashCode
public class FreeListResponseDTO {
	
	private int bno;
	private String title; 
	private String writer;
	private String date;
	
	
	public FreeListResponseDTO(FreeBoard board) {
		super();
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.date = makePrettierDateString(board.getRegDate());
		// ㄴ> 이렇게 작성해주기 위해서 메서드를 생성한 것임.
		// ㄴ> 이러면 .jsp에서 따로 변환하고 입력하고 그런 짓 안해도 된다.
	}
	
	
	// 원래 LocalDateTime타입인데 String으로 작성.
	// 이 메서드를 통해 LocalDateTime -> String 으로 변환+포맷 해서 만들어준다!
	static String makePrettierDateString(LocalDateTime regDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dtf.format(regDate);
	}
	
}
