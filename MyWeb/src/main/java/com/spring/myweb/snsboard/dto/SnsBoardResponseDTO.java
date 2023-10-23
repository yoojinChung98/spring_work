package com.spring.myweb.snsboard.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.snsboard.entity.SnsBoard;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class SnsBoardResponseDTO {
	
	//나중에 사진을 다시 띄울 때 uploadPath, fileLoca, fileName은 필요함
	private int bno;
	private String writer;
	private String uploadPath;
	private String fileLoca;
	private String fileName; 
	private String content;
	private String regDate;
	
	public SnsBoardResponseDTO(SnsBoard board) {
		this.bno = board.getBno();
		this.writer = board.getWriter();
		this.uploadPath = board.getUploadPath();
		this.fileLoca = board.getFileLoca();
		this.fileName = board.getFileName();
		this.content = board.getContent();
		this.regDate = DateToString(board.getRegDate());
		
	}
	
	String DateToString(LocalDateTime regDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dtf.format(regDate);
	}
	
}
