package com.spring.basic.score.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 DTO (Data Transfer Object): 데이터 전송(이동) 객체라는 의미
 - 계층 간 데이터 교환을 위한 객체. (controller 에서 service 에서 view....)
 - 로직을 갖고 있지 않은 순수한 데이터 객체로 활용. getter/setter 메서드만 갖는다.
 
 VO (Value Object): 좀 더 특정(순수)한 결과 값을 담는 객체.
 값 자체를 표현하기 때문에 객체의 불변성을 보장해야 하며 setter 메서드는 갖지 않는 것을 권장.
 Entity와의 공통점 : 불변의 순수한 값을 담는다.
 Entity와의 차이점 : VO는 프로그램 내부의 어디서나 사용함 / Entity는 데이터베이스의 데이터를 모두 담기 위해 사용하는 객체 (즉, 그 목적성에 차이가 있음)
 */


@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ScoreRequestDTO {

	private String name; //학생 이름
	private int kor, eng, math; //국, 영, 수 점수
	
}
