package com.spring.basic.board.entity;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

	private int boardNo;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime regDate;
	
//	/*
//	 (자바의) 빌더 패턴 Builder pattern
//	 - 객체를 생성하는 방법을 정의하는 내부 클래스.
//	 - 기존에는 생성자 혹은 setter 메서드를 통해 객체의 필드를 직접 초기화를 했다면,
//	  Builder 클래스를 디자인하고 활용하여 원하는 필드만 빠르게 초기화 할 수 있고,
//	   코드 가독성도 높일 수 있음.
//	 */
//	
//	//그냥 내부클래스로 함 만들어보겠음!
//	
//	//1. 중첩 static 클래스를 이용하여 기존 객체와 동일한 모양의 클래스를 하나 더 구축함.(static 클래스는 내부클래스로만 사용할 수 있음)
//	public static class BoardBuilder { //클래스명에 Builder 가 붙는 것이 관례.
//		
//		//1.5. 기존 객체와 동일한 필더를 가짐.
//		private int boardNo;
//		private String writer, title, content;
//		private LocalDateTime regDate;
//		
//		//2. 내부 클래스의 생성자를 private으로 제한해서 
//		//Board 클래스 밖에서는 호출(생성)할 수 없도록 지정.
//		private BoardBuilder(){};
//		
//		//3. 모든 필드의 setter 메서드 구축함.
//		// return type은 BoardBuilder 객체로 작성.
//		public BoardBuilder boardNo(int boardNo) {
//			this.boardNo = boardNo;
//			return this;
//		}
//		
//		public BoardBuilder writer(String writer) {
//			this.writer = writer;
//			return this;
//		}
//		
//		public BoardBuilder title(String title) {
//			this.title = title;
//			return this;
//		}
//		
//		public BoardBuilder content(String content) {
//			this.content = content;
//			return this;
//		}
//		
//		public BoardBuilder regDate(LocalDateTime regDate) {
//			this.regDate = regDate;
//			return this;
//		}
//		
//		//4. 빌더 완료 메서드 선언
//		public Board build() {
//			return new Board(boardNo, writer, title, content, regDate);
//		}
//		
//	} //end BoardBuilder class
//	
//	//5. 빌더 호출 메서드
//	//Board 가 BoardBuilder를 호출하는 메서드
//	public static BoardBuilder builder() {
//		return new BoardBuilder();
//	}
	
}
