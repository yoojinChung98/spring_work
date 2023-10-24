package com.spring.myweb.snsboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
--좋아요 테이블
CREATE TABLE sns_like(
    lno NUMBER PRIMARY KEY,
    user_id VARCHAR2(50) NOT NULL,
    bno NUMBER NOT NULL
);

--ON DELETE CASCADE
ALTER TABLE sns_like ADD FOREIGN KEY(bno) REFERENCES snsboard(bno) ON DELETE CASCADE;
 */

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SnsLike {

	private int lno;
	private String userId;
	private int bno;
	
}
