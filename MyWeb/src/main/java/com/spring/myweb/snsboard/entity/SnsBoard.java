package com.spring.myweb.snsboard.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class SnsBoard {
	
	private int bno;
	private String writer;
	private String uploadPath;
	private String fileLoca;
	private String fileName;
	private String fileRealName;
	private String content;
	private LocalDateTime regDate;
}
