package com.spring.myweb.reply.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.spring.myweb.reply.entity.Reply;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
public class ReplyListResponseDTO {
	//응답에 필요한 내용들을 담아봅시다
	
	private int replyNo;
	private String replyWriter;
	private String replyText;
	private String date;
	
	public ReplyListResponseDTO(Reply reply) {
		this.replyNo = reply.getReplyNo();
		this.replyWriter = reply.getReplyWriter();
		this.replyText = reply.getReplyText();
		if(reply.getUpdateDate() == null) {
			this.date = dateToString(reply.getReplyDate());
		} else {
			this.date = dateToString(reply.getUpdateDate()) + " (수정됨)" ;
		}
	}
	
	static String dateToString(LocalDateTime regDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return dtf.format(regDate);
	}
	
}
