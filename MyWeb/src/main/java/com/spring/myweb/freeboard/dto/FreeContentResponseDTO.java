package com.spring.myweb.freeboard.dto;

import com.spring.myweb.freeboard.entity.FreeBoard;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter //@Setter 안 넣는 이유: 이 메서드의 값에 다른 값이 대입되는 것을 막기 위해서 사용하지 않는다.
@ToString @EqualsAndHashCode
public class FreeContentResponseDTO {
	
	private int bno; // bno를 선언해야 조회할 bno번호를 받을 수 있음
	private String title;
	private String writer;
	private String content;
	private String date; //String으로 LocalDateTime을 받아서 DTO에서 변환처리 해주는 것이 jsp파일에서 변환처리 해주는 것보다 편함
	
	public FreeContentResponseDTO(FreeBoard board) {
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		if(board.getUpdateDate() == null) {
			this.date = FreeListResponseDTO.makePrettierDateString(board.getRegDate()); // static으로 메서드 호출해서 사용!
		}else {
			this.date = FreeListResponseDTO.makePrettierDateString(board.getUpdateDate()) + "(수정됨)";
		}
	}
	
	//이렇게 생성하지 않아도 되고 FreeListResponseDTO에서 만들었던 변환메서드의 타입을 static으로 바꿔주고 여기서 호출할 수도 있음!!!!!!!!!!!!!!!!!! 데박데박
	/*
	private String makeDate(LocalDateTime date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dtf.format(date);
	}
	*/
	
	

}
