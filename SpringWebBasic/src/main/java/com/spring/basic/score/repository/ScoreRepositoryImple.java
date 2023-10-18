package com.spring.basic.score.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;

//@Component -> 3계층(Controller, Service, Repository) 이외의 객체를 빈으로 등록할 때 사용
@Repository // Service가 save라는 메서드를 부르기 위해서는 Repository 객체도 생성이 되어있어야 하므로 빈등록.
// 즉, Service가 의존하는 객체니까 빈등록이 되어있어야 함. 스프링 프레임워크에서는 오브젝트 생성과 주입을 모두 스프링에게 맡기니까!!
public class ScoreRepositoryImple implements IScoreRepository {

	//DB가 없으니까... DB역할을 할 뭔가를 만드는 것
	//key: 학번, value: 성적 정보를 담은 객체
	private static final Map<Integer, Score> SCORE_MAP;
	
	//나중에 DB에서 알아서 매겨줍니다... 오늘만 하는 것...
	//학번에 사용할 일련번호
	private static int sequence;
	
	//DB에 넣어놓을 더미 데이터... 나중엔 안합니다.
	//static을 초기화 하는 방법 : 선언할때 동시에 / 정적초기화자 static{}
	static {
		SCORE_MAP = new HashMap<>();
		Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 34, 91));
		Score stu2 = new Score(new ScoreRequestDTO("춘식이", 77, 99, 87));
		Score stu3 = new Score(new ScoreRequestDTO("대길이", 98, 96, 85));
		
		stu1.setStuNum(++sequence);
		stu2.setStuNum(++sequence);
		stu3.setStuNum(++sequence);
		
		SCORE_MAP.put(stu1.getStuNum(), stu1);
		SCORE_MAP.put(stu2.getStuNum(), stu2);
		SCORE_MAP.put(stu3.getStuNum(), stu3);
	}
	
	
	@Override
	public List<Score> findAll() {
		// SCORE_MAP은 번호가 key, 객체(Score Entity)가 value로 이루어져있음.
		// 객체들만 전부 뽑아서 List로 만들 것이기 때문에
		// Score_MAP에서 .values 로 value 들로만 전부 뽑아낸 뒤, ArrayList의 생성자의 매개값으로 전달하여 ArrayList 생성
		// List로 포장
		List<Score> scoreList = new ArrayList<Score>(SCORE_MAP.values());
		return scoreList;
	}

	@Override
	public void save(Score score) {
		score.setStuNum(++sequence);
		SCORE_MAP.put(score.getStuNum(), score);
	}

	@Override
	public Score findByStuNum(int stuNum) {
		return SCORE_MAP.get(stuNum);
	}
	
	@Override
<<<<<<< HEAD
	public void deleteByStuNum(int stuNum) {
=======
	public void deleteBystuNum(int stuNum) {
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
		SCORE_MAP.remove(stuNum);
	}
	
	//레파지토리: stuNum으로 score 찾아서 entity 갈아끼우고 새로갈아끼운 값 return
	@Override
	public void modify(int stuNum, Score newScore) {	
		SCORE_MAP.put(stuNum, newScore);
	}
	
	//6. 쌤 메서드
	@Override
	public void modify(Score modScore) {
		SCORE_MAP.put(modScore.getStuNum(), modScore);
	}

}
