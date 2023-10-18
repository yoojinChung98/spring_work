package com.spring.myweb.freeboard.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter @Setter @ToString @EqualsAndHashCode
@AllArgsConstructor
@Builder
public class Page {
	//사용자가 어떤 페이지 요청?
	//항상 넘어오는 
	
	private int pageNo; //클라이언트가 보낸 페이지 번호
	private int amount; //한 페이지에 보여질 게시물 수
	
	//검색 요청에 필요한 필드를 추가
	private String keyword; //음,, 키워드는 유효성검사를 안해도 되나?
	private String condition;

	public Page() {
		this.pageNo = 1;
		this.amount = 10;
		//처음 들어왔을 때는 페이지넘버와 양을 선택할 수 없지만, 화면은 띄워야 하니까!
		//기본 생성자에 디폴트 값을 설정해놓음
	}
	
	//int(pageNo)가 받을 수 있는 페이지 수보다 큰 값을 페이지번호로 요청할 경우 거르기
	public void setPageNo(int pageNo) {
        if(pageNo < 1 || pageNo > Integer.MAX_VALUE) {
            this.pageNo = 1;
            return;
        }
        this.pageNo = pageNo;
	}
	
	//내가 지정한 한페이지에 보여줄 게시글 갯수(10,20,30)가 아닌 경우 거르기
	public void setAmount(int amount) {
        if(amount < 10 || amount > 30 || (amount % 10 != 0)) {
            this.amount = 10;
            return;
        }
        this.amount = amount;
    }
	
	//매퍼에서 그냥 pageStart라고 부르기 위해 일부러 getter의 모양과 동일하게 get-을 붙임
	public int getPageStart() {
		/*
		 하나의 페이지에 몇번부터 몇번의 게시글을 띄울지 WHERE 조건을 주기위한 변수값 설정 메서드.
		 WHERE rn > pageStart AND rn <= pageEnd;
		 pageNo:1 -> return 0;
		 page: 2 -> return 10;
		 */
		return amount * (pageNo-1);
	}
	
	//매퍼에서 편하기 지목하기 위함 get-
	public int getPageEnd() {
		return amount * pageNo; 
	}
	
}
