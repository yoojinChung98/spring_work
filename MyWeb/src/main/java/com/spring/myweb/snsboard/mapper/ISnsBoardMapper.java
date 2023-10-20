package com.spring.myweb.snsboard.mapper;

import java.util.List;

import com.spring.myweb.freeboard.dto.page.Page;
import com.spring.myweb.snsboard.entity.SnsBoard;

public interface ISnsBoardMapper {
	
	//등록
	void insert(SnsBoard board);
	
	//목록
	List<SnsBoard> getList(Page page);
	
	//상세
	SnsBoard getDetail(int bno);
	
	//삭제
	void delete(int bno);

}
