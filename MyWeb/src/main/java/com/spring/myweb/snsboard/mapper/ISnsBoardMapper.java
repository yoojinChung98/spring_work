package com.spring.myweb.snsboard.mapper;

import java.util.List;
import java.util.Map;

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
	
	//좋아요 탐색
	int searchLike(Map<String, String> params);
	
	//좋아요 등록
	void createLike(Map<String, String> params);
	
	//좋아요 삭제
	void deleteLike(Map<String, String> params);

}
