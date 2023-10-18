package com.spring.basic.score.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

//JdbcTemplate에서 SELECT 쿼리를 위한 ResultSet 사용을 편하게 하기 위한 클래스를 생성.
//(JdbcTemplate에게 조회된 내용을 어떻게 가공해야 하는지를 알려주는 클래스)
//RowMapper 인터페이스를 구현해야 함.
public class ScoreMapper implements RowMapper<Score>{

	@Override
	public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		System.out.println("mapRow 메서드 호출!");
		System.out.println("rowNum: "+ rowNum);
		
		Score score = new Score(
					rs.getInt("stuNum"),
					rs.getString("stuName"),
					rs.getInt("kor"),
					rs.getInt("eng"),
					rs.getInt("math"),
					rs.getInt("total"),
					rs.getDouble("average"),
					Grade.valueOf(rs.getString("grade"))
				);
				
		return score;
	}

}
