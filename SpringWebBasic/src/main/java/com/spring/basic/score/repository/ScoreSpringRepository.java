package com.spring.basic.score.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

import lombok.RequiredArgsConstructor;

@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements IScoreRepository {

	
    //내부(중첩) 클래스 (inner class)
    //두 클래스가 굉장히 긴밀한 관계가 있을 때 주로 선언.
    //해당 클래스 안에서만 사용할 클래스를 굳이 새 파일로 선언하지 않고 만들 수 있습니다.
	class ScoreMapper implements RowMapper<Score>{
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
	
	
	private final JdbcTemplate template;
	
	@Override
	public List<Score> findAll() {
		String sql = "SELECT * FROM score";
		
		//template.query(sql, 물음표가 있다면 물음표 채우기, RowMapper구현객체 )
		return template.query(sql, new ScoreMapper());
		
	}

	@Override
	public void save(Score score) {
		String sql = "INSERT INTO score VALUES(score_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, score.getStuName(), score.getKor(), score.getEng(), score.getMath(),
						score.getTotal(), score.getAverage(), String.valueOf(score.getGrade()));

	}

	@Override
	public Score findByStuNum(int stuNum) {
		String sql = "SELECT * FROM score WHERE stuNum = ?";
		try {
			//queryForObject는 조회 결과가 없을 시 예외가 발생합니다. (EmptyResultDataAccessException)
			return template.queryForObject(sql, new ScoreMapper(), stuNum);
		} catch (Exception e) {
			// 조회 결과가 없다면 catch문 실행
			return null;
		}
	}

	@Override
<<<<<<< HEAD
	public void deleteByStuNum(int stuNum) {
=======
	public void deleteBystuNum(int stuNum) {
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
		String sql = "DELETE FROM score WHERE stuNum = "+stuNum;
		template.update(sql);
	}

	
	@Override
	public void modify(int stuNum, Score newScore) {
		// TODO Auto-generated method stub
	}
	

	@Override
	public void modify(Score modScore) {
		//수정해온 score 에 맞춰 dbms 수정
		String sql = "UPDATE score SET kor=?, eng=?, math=?, total=?, average=?, grade=? WHERE stuNum=?";
		template.update(sql, modScore.getKor(), modScore.getEng(), modScore.getMath(),
				modScore.getTotal(), modScore.getAverage(), String.valueOf(modScore.getGrade()),
				modScore.getStuNum());
	}

}
