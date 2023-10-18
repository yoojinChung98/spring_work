package com.spring.basic.score.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // 빈 등록 -> Service가 의존하는 객체니까
public class ScoreRepositoryImpl implements IScoreRepository {



	//key: 학번, value: 성적 정보를 담은 객체
	private static final Map<Integer, Score> SCORE_MAP;//Map객체를 상수 선언!, 초기화를 시켜야함!
	//List로 선언했으면 반복문 이용해서 다 비교해서 꺼내줬어야 하니까 먼저 Map으로 선언해주고 나중에 List로 전환시켜준다!

	//학번에 사용할 일련번호
	private static int sequence;

	//static이기 때문에 그냥 생성자를 생성할 수 없음 -> 정적초기화
	static {
		SCORE_MAP = new HashMap<>();
		Score stu1 = new Score(new ScoreRequestDTO("뽀로로", 100, 34, 91));
		Score stu2 = new Score(new ScoreRequestDTO("춘식이", 77, 99, 87));
		Score stu3 = new Score(new ScoreRequestDTO("대길이", 98, 66, 85));

		//실제 DB가 아니여서 수동으로 번호 주입
		stu1.setStuNum(++sequence);
		stu2.setStuNum(++sequence);
		stu3.setStuNum(++sequence);

		//실제 DB가 아니여서 수동으로 번호 주입2
		SCORE_MAP.put(stu1.getStuNum(), stu1);
		SCORE_MAP.put(stu2.getStuNum(), stu2);
		SCORE_MAP.put(stu3.getStuNum(), stu3);
	}

	@Override
	public List<Score> findAll() {
		// SCORE_MAP은 번호가 key, 객체가 value로 이루어져 있음.
		// 객체들만 전부 뽑아서 List로 만들 것이기 때문에(key값은 필요 없으니까)
		// SCORE_MAP에서 value들만 전부 뽑아낸 뒤, ArrayList의 생성자의 매개값으로 전달해서
		// List로 포장!
		List<Score> scoreList = new ArrayList<Score>(SCORE_MAP.values());
		//values(): colletion타입의 값을 줌. collection에는 List와 set이 있음
		return scoreList;//service한테 리턴
	}

	@Override
	//Service가 Repository에게 전달해서 DB에 넣자고 했으니까 이 메서드를 service에서 사용할 수 있게 해줘야한다.
	public void save(Score score) {

		score.setStuNum(++sequence);
		SCORE_MAP.put(score.getStuNum(), score);// DB역할을 하는 SCORE_MAP에 일련번호와 score를 넣음.

	}
	
	public void resave(Score score, int stuNum) {

		SCORE_MAP.put(stuNum, score);// DB역할을 하는 SCORE_MAP에 일련번호와 score를 넣음.

	}

	@Override
	public Score findByStuNum(int stuNum) {
		return SCORE_MAP.get(stuNum); // 따로 변수 선언할 필요X
	}

	@Override
	public void deleteByStuNum(int stuNum) {
		SCORE_MAP.remove(stuNum);
	}

	//수정한 정보로 교체해주는 메서드
	@Override
	public void modify(Score modScore) {
		SCORE_MAP.put(modScore.getStuNum(), modScore);
	}


	@Override
	public void modify(int stuNum, Score newScore) {
		// TODO Auto-generated method stub
		
	}

}































