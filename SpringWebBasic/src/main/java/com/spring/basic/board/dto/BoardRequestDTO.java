package com.spring.basic.board.dto;

import java.time.LocalDateTime;

import com.spring.basic.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode
@ToString @AllArgsConstructor @NoArgsConstructor
public class BoardRequestDTO {
	
	private int boardNo;
	private String writer, title, content;
	
	
	
}
