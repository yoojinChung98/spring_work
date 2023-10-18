package com.spring.myweb.freeboard.dto;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FreeUpdateRequestDTO {
	
	private int bno;
	private String writer;
	private String title;
	private String content;
	
	/*
	public FreeUpdateRequestDTO(FreeBoard board) {
		
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.date = FreeListResponseDTO.makePrettierDateString(board.getUpdateDate()) + "(수정됨)";
	}
	*/

}
