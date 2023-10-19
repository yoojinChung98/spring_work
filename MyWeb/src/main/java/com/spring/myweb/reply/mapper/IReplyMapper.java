package com.spring.myweb.reply.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.reply.entity.Reply;

public interface IReplyMapper {
	
	//댓글 등록
	void replyRegist(Reply reply);
	
	//목록 요청(댓글에도 페이지가 있잖아)
	//List<Reply> getList(int bno, Page page);
	List<Reply> getList( Map<String, Object> data);
	
	/*
	-MyBatis로 DB연동을 진행할 때, 
	
	  1. @Param(mapper.xml_에서_지목할_이름)을 이용해서 이름을 붙여주는 방법.
	   - 참고로 여기서는 괄호 생략 불가능.
	  List<Reply> getList(@Param("bno")int bno, @Param("page")Page page);
	  
	  2. Map으로 포장해서 보내는 방법
	  - 서비스에서 값을 보낼 때 Map으로 보내는 거임
	  (서비스부분 설멍_
	  List<Reply> getList( Map<String, Object> data);
	  
	  3. 클래스를 디자인해서 객체 하나만 매개값으로 보내는 방법
	  
	 */
	
	
	
	//댓글 개수 (페이징, PageCreator는 사용하지 않습니다.)
	int getTotal(int bno);
	
	//비밀번호 확인
	String pwCheck(int rno);
	
	//댓글 수정
	void update(Reply reply);
	
	//댓글 삭제
	void delete(int rno);
}