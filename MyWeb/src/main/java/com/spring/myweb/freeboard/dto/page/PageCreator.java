package com.spring.myweb.freeboard.dto.page;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
public class PageCreator {

	//한 화면에 배치할 버튼의 개수
	private static final int BUTTON_NUM = 5;
	
	//화면 랜더링 시 페이지의 시작값과 끝값
	private int begin, end;
	
	//이전, 다음 버튼 활성화 여부
	private boolean prev, next; 
	
	//현재 요청 페이지 정보 (사용자가 선택한 값을 받는 것이므로 외부에서 값을 받아와야 함?)
	private Page page;
	
	//총 게시물 수(DB에서 받아와야 함)
	private int articleTotalCount;
	
	//페이징 알고리즘을 수행하기 위해 외부로부터 필요한 데이터를 전달받음
	public PageCreator(Page page, int articleTotalCount) {
		this.page = page;
		this.articleTotalCount = articleTotalCount ;
		calcDataOfPage(); //전달 완료 후 알고리즘 수행
	}
	
	//각 버튼에 페이징버튼을 어떻게 보여줄 것인지/ 각 버튼은 어떤 정보를 갖고있는지를 값을 세팅하는 메서드?인듯?
	private void calcDataOfPage() {
		//end 세팅
		this.end = (int) (Math.ceil( page.getPageNo() / (double) BUTTON_NUM ) * BUTTON_NUM);
		//내가 3버튼을 눌렀어 / 그러면 1 2 3 4 5 가 떠야함.
		
		//begin 세팅
		this.begin = end - BUTTON_NUM + 1;
		
		//prev 활성화?
		prev = begin > 1;
		//prev = (begin == 1 ? false : true);
		
		//next 활성화 (게시물 135개. 한페이지 10개 요청페이지 14 마지막버튼-> false 13 -> true
		this.next = articleTotalCount <= (end * page.getAmount()) ? false : true;
		//next = ( articleTotalCount/page.getAmount() +1 ) < end ? false : true;
		
		if(!this.next) {
			this.end = (int) Math.ceil(articleTotalCount / (double) page.getAmount());
//			if(this.end == 0) {
//				this.end = 1;
//			}
			//아니면 alert("조회 결과가 없습니다.")하고 모든 리스트를 띄우느 방법도 있다고 쌤이 말씀해주심.
		}
	}
	
	
	
}
