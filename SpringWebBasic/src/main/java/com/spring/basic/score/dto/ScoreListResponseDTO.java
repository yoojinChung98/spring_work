package com.spring.basic.score.dto;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//DTO를 만든 이유 두가지 
// 1. Entity는 요청이나 응답을 담는 값으로 쓰면 안된다!
// 데이터베이스에서 조회된 값을 화면으로 응답할 때, 해당 화면이 무엇이냐에 따라
//2. 조회된 값을 가공하거나 추가하거나 제외하고 전달해야 할 필요성이 있기 때문에 (예를 들어, 개인정보 보호를 위해 이름에서 성만 *로 가림)
//그에 맞는 응답 DTO를 생성해서 전달하는 것이 각자의 역할을 침해하지 않는 설계일 것입니다. 
@Getter @ToString @EqualsAndHashCode //setter 주는게 일반적이긴 한데, 우리는 이 객체를 좀 엄격하게 이용하고 싶어서 세터 안줌.
public class ScoreListResponseDTO {

	private int stuNum;
	private String maskingName;
	private double average;
	private Grade grade;
	
	
	public ScoreListResponseDTO(Score s) {
		this.stuNum = s.getStuNum();
		this.maskingName = makeMaskingName(s.getStuName());
		this.average = s.getAverage();
		this.grade = s.getGrade();
	}
	
	// 이름을 첫 글자만 빼고 나머지를 *로 처리하기
	private String makeMaskingName(String originalName) {
		//valueOf(): 다른 타입을 문자열로 변환하는 메서드.
		maskingName = String.valueOf(originalName.charAt(0));
		for(int i=1;i<originalName.length();i++) {
			maskingName += "*";
		}
		return maskingName;
	}
	
	
}
