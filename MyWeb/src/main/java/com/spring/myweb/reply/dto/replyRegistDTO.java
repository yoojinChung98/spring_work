package com.spring.myweb.reply.dto;

import com.spring.myweb.reply.entity.Reply;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class replyRegistDTO {
	
	private int bno;
	private String replyText;
	private String replyId;
	private String replyPw;

	public Reply toEntity(replyRegistDTO dto) {
		return Reply.builder()
		.bno(bno)
		.replyText(this.replyText)
		.replyWriter(dto.getReplyId())
		.replyPw(dto.getReplyPw())
		.build();
	}
	
}
