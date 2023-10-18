package com.spring.basic.score.repository;
import java.util.List;
import com.spring.basic.score.entity.Score;

public interface IScoreMapper {

	

		//성적 정보 전체 조회.
		List<Score> findAll();
		
		//성적 정보 등록
		void save(Score score);
		
		//성적 정보 개별 조회
		Score findByStuNum(int stuNum);

		//성적 정보 삭제
		void deleteBystuNum(int stuNum);
		
		//성적 정보 교체(6.내꺼)
		void modify(int stuNum, Score newScore);

		//성적 정보 수정(6.쌤꺼)
		void modify(Score modScore);
		
	
	
	
	
	
	
}
