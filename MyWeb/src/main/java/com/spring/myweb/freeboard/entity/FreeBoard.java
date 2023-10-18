package com.spring.myweb.freeboard.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
CREATE TABLE freeboard(
    bno NUMBER PRIMARY KEY,
    title VARCHAR2(300) NOT NULL,
    writer VARCHAR2(50) NOT NULL,
    content VARCHAR2(3000),
    reg_date DATE DEFAULT sysdate,
    update_date DATE DEFAULT NULL
);

CREATE SEQUENCE freeboard_seq
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 1000
    NOCYCLE
    NOCACHE;
 */

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class FreeBoard {
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	
	
}
