package com.spring.basic.score.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.basic.score.dto.ScoreListResponseDTO;
import com.spring.basic.score.dto.ScoreModiRequestDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;
import com.spring.basic.score.entity.Score;
import com.spring.basic.score.repository.IScoreMapper;
import com.spring.basic.score.repository.IScoreRepository;
import com.spring.basic.score.repository.ScoreRepositoryImple;

import lombok.RequiredArgsConstructor;

//컨트롤러와 레파지토리 사이에 배치되어 기타 비즈니스 로직 처리
//ex) 값을 가공, 예외 처리, dto 변환, 트랜잭션 처리 등등...

@Service //빈 등록.
@RequiredArgsConstructor
public class ScoreService {

	private final IScoreMapper scoreRepository;
	
//	@Autowired
//	public ScoreService(@Qualifier("spring")IScoreRepository scoreRepository) {
//		this.scoreRepository = scoreRepository;
//	}
	
	
	//등록의 중간처리
	//컨트롤러가 매개변수로 dto(사용자가 입력한 값)을 보내줌
	//하지만, 온전한 학생의 정보를 가지는 객체 -> Score(Entity 객체)
	//내가 Entity를 만들어서 넘겨야겠다.^^;;
	public void insertScore(ScoreRequestDTO dto) {
		Score score = new Score(dto);
		
		//Entity를 완성했으니, Repository에게 전달해서 DB에 넣자.
		scoreRepository.save(score);
	}


	/*
	 컨트롤러는 나에게 데이터베이스를 통해 전체 성적 정보 리스트를 가져오라고 시켰음.
	 근데 Repository는 학생 정보가 모두 포함된 리스트를 줬네?
	 음 성적 정보 리스트에 필요한 정보는 특정하니까... 해당 정보만 갖춘 DTO로 변경해서 주자!
	 */
	public List<ScoreListResponseDTO> getList() {
		
		List<ScoreListResponseDTO> dtoList = new ArrayList<>();
		
		List<Score> scoreList = scoreRepository.findAll(); //원본 entity 리스트 받아옴
		for(Score s: scoreList) { //List니까 그 속의 Entity들을 하나씩 DTO 생성해서 List에 하나씩 담아야지
			//Entity -> DTO 로 변환 과정
			ScoreListResponseDTO dto = new ScoreListResponseDTO(s);
			dtoList.add(dto); //변환한 DTO를 예쁘게 List에 포장해놓기
		}
		return dtoList;
		
	}


	//학생 점수 개별 조회
	public Score retrieve(int stuNum) {
		// 응답하는 화면에 맞는 DTO 를 선언해서 주어야 하는 것이 원칙.
		// 딱 필요한 만큼의 데이터만 추리고 필요시 가공될 수 있는 DTO 를 설계하여 리턴 하는 것이 맞음.
		// 그런데 이런 경우, 모든 데이터를 수정없이 사용할 것이므로 원칙에 어긋나지만 왕왕 Entity를 리턴하기도 함.
		return scoreRepository.findByStuNum(stuNum);
		//학생의 정보를 빠짐없이 수정없이 전부 다 보여줄 것.(원래는 안되지만, 걍 엔티티 리턴할 것임)	
	}


	public void delete(int stuNum) {
		scoreRepository.deleteBystuNum(stuNum);
		
		
	}

	//6. 에 사용되는 메서드(내가 만든거)
	//서비스: 해당 dto로 entity 하나 만들기. stuNum이랑 entity 넘기기 (새로 갈아껴진 entity return)
	public Score replaceModify(int stuNum, ScoreModiRequestDTO dto) {
		Score s = scoreRepository.findByStuNum(stuNum);
		s.changeScore(dto);
		scoreRepository.modify(stuNum, s);
		return s;
	}

	//6. 에 해당하는 메서드(쌤)
	public void modify(int stuNum, ScoreRequestDTO dto) {
		Score score = scoreRepository.findByStuNum(stuNum);
//		dto.setName(score.getStuName()); //dto에 name 은 지금 null 이었음.
//		Score modScore = new Score(dto); //dto는 stuNum 정보 없음
//		modScore.setStuNum(stuNum);
		
		//새로운 스코어를 만들지 않고 그냥 메서드 이용해서 값만 바꿔도 됨.
		score.changeScore(dto);
		
		scoreRepository.modify(score);
		
		
	}
	
	
}
