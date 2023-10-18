package com.spring.basic.board.dto;

import java.time.LocalDateTime;

import com.spring.basic.board.entity.Board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//목록화면 응답용 DTO 로 사용할 것.
@Getter @ToString @EqualsAndHashCode
//Setter를 따로 준비하지 않고 생성자 호출 시 값을 초기화 할것임.
//DTO를 만들어서 그 속성값을 꺼내쓸 계획이므로 GETTER 준비
public class BoardListResponseDTO {

	private int boardNo;
	private String writer;
	private String title;
	private LocalDateTime regDate;
	
	public BoardListResponseDTO(Board b) {
		this.boardNo = b.getBoardNo();
		this.writer = b.getWriter();
		this.title = b.getTitle();
		this.regDate = b.getRegDate();
	}
	
}
