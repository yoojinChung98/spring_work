package com.spring.myweb.freeboard;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.freeboard.entity.FreeBoard;
import com.spring.myweb.freeboard.mapper.IFreeBoardMapper;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(SpringExtension.class) //테스트 환경을 만들어주는 Junit5 객체를 로딩하는 것(spirngExtension이라는 클래스 강제 깨움)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//여기에 Mybatis 설정이 있으므로 해당 파일을 불러와야함.
public class FreeBoardMapperTest {
	
	@Autowired
	private IFreeBoardMapper mapper;
	
	//단위 테스트 (unit test) - 가장 작은 단위의 테스트 (기능별 테스트 => 메서드별 테스트)
	
	//테스트 시나리오 작성
	//- 단언(Assertion) 기법 사용할 것.
	
	@Test //이 아노테이션을 달아야 이 메서드만 실행할 수 있음?
	@DisplayName("Mapper 계층의 regist를 호출하면서"
			+ "FreeBoard를 전달하면 데이터가 INSERT 될 것이다.") //이 메서드를 통해 달성하고자 하는 목표를 세우는 것(선택사항)
	void registTest() {
		// 테스트는 given - when - then 패턴을 따름 (권장사항)
		
		//given: 테스트를 위해 주어질 데이터 세팅 (실제로는 parameter 값이 넘어온 상황) //지금은 생략됨
		for(int i=1; i<=300; i++) {
			//when: 테스트 실제 상황 세팅
			mapper.regist(FreeBoard.builder()
									.title("테스트 제목"+i)
									.writer("abc1234")
									.content("테스트 내용입니다."+i)
									.build());;
		}
		
//		mapper.regist(FreeBoard.builder()
//				.title("메롱메롱")
//				.writer("abc1234")
//				.content("테스트 중!")
//				.build());;
//		//then: 테스트의 결과를 확인. //지금은 생략됨(getList 아직 구현 안함)
	}
	
	@Test
	@DisplayName("조회 시 전체 글 목록이 올 것이고, 조회된 글의 개수는 10개일 것이다.")
	void getListTest() {
		
		//when
		List<FreeBoard> list = mapper.getList(Page.builder()
													.pageNo(4)
													.amount(10)
													.build());
		for(FreeBoard board : list) {
			System.out.println(board);
		}
		System.out.println("조회된 글의 개수: "+list.size());
		
		//then
		//첫번째와 두번째 매개변수가 일치하는지 체크해서 에러가 없어도 테스트 결과를 성공/실패 띄워줌.
		Assertions.assertEquals(list.size(), 10);	
	}
	
	@Test
	@DisplayName("글 번호가 11번인 글을 조회하면 글쓴이는 'abc1234'일 것이고, 글 제목은'메롱메롱'일 것이다.")
	void getContentTest() {
		
		//given
		int bno = 11;
		
		//when
		FreeBoard board = mapper.getContent(bno);
		
		//then
		assertEquals(board.getWriter(), "abc1234");
		assertTrue(board.getTitle().equals("메롱메롱"));	
	}
	
	//글 번호가 1번인 글의 제목과 내용을 수정 후 다시 조회했을 때,
	//수정한 제목과 내용으로 바뀌었음을 단언하세요.
	@Test
	@DisplayName("글번호가 1인 글의 제목과 내용 수정 후 재 조회 시, 수정한 제목과 내용으로 바뀜.")
	void updateTest() {
		
		//given
		int bno = 1;
		FreeBoard board = FreeBoard.builder()
					.bno(1).title("수정된제목").content("수정된내용")
					.build();
		
		//when
		mapper.update(board);
		FreeBoard nBoard = mapper.getContent(bno);
		
		//then
		assertEquals(nBoard.getTitle(), board.getTitle());
		//=> assertEquals(mapper.getContent(board.getBno()).getTitle())
		
		assertTrue(nBoard.getContent().equals(board.getContent()));
		
		
		
	}
	
	//글 번호가 2번인 글을 삭제한 후에 전체 목록을 조회했을 때,
	//글의 개수는 11개일 것이고
	//2번 글을 조회했을 때 null이 리턴됨을 단언하세요 -> assertNull(객체)
	@Test
	@DisplayName("글번호가 2번인 글을 삭제 -> 글의 개수가 11개일 것. 2번 글을 조회=Null")
	void deleteTest() {
		
		//given
		int bno=2;
		
		//when
		mapper.delete(bno);
		
		//then
		//assertEquals(mapper.getList().size(), 11);
		assertNull(mapper.getContent(bno));
		
	}
}
