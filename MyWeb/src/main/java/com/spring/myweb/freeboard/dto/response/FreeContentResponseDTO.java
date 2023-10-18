package com.spring.myweb.freeboard.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
public class FreeContentResponseDTO {
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String date;
	
	
	public FreeContentResponseDTO(FreeBoard board) {
		super();
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.date = ckUpdate(board);
	}
	
	// 기본 reg_date/ update_Date가 null이 아니면 update_Date 값을 세팅
	public String ckUpdate(FreeBoard board) {
		if(board.getUpdateDate() == null) {
			return FreeListResponseDTO.DateToString(board.getRegDate());
		}else {
			return FreeListResponseDTO.DateToString(board.getUpdateDate())+" (수정됨)";
		}
	}
	
	//제목의 길이를 10자만... 로 표현하는 로직. 등의 가공은 DTO 에서?
	
//	//클래스 차원에서의 변환?
//	private String DateToString(LocalDateTime regDate) {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		return dtf.format(regDate);
//	}
	
	
	
	
}
