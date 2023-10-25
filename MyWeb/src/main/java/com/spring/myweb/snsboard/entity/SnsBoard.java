package com.spring.myweb.snsboard.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SnsBoard {
	
	private int bno;
	private String writer;
	private String uploadPath;
	private String fileLoca;
	private String fileName;
	private String fileRealName;
	private String content;
	private LocalDateTime regDate;
	
	//좋아요 개수가 몇 개인지를 알려주는 변수 추가(컬럼명(like_cnt)과 동일하게 카멜케이스 적용.)
	private int likeCnt;
}
