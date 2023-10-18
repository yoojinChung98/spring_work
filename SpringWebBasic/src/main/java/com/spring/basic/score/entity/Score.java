package com.spring.basic.score.entity;

import com.spring.basic.score.dto.ScoreModiRequestDTO;
import com.spring.basic.score.dto.ScoreRequestDTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 Entity 클래스
 - 실제 데이터베이스에 저장된 테이블(값의 모음) 형태와 1:1로 매칭되는 클래스.(똑같이 생겨야 함)
 - DB 테이블 내에 존재하는 속성만을 필드로 가져야 합니다.
 - 상속이나 구현체여서는 안되며(자식클래스여도 안되고 인터페이스 구현도 안됨! 어디에도 영향받지 않는 순수한 객체로 놔두어야 해!!), 존재하지 않는 컬럼값(테이블값)을 가져서도 안됨 (가장 pure한 객체)
 - **절대로 요청이나 응답값을 전달하는 클래스로 사용하지 않음 (DTO의 역할)**
 */


@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Score {

	private int stuNum; //학번 : 데이터베이스에서 매겨주는 것으로 여기서 따로 가공하지는 않음.
	private String stuName; //학생 이름
	private int kor, eng, math; // 국, 영, 수 점수
	
	private int total; //총점
	private double average; //평균
	private Grade grade;
	
	
	
	public Score(ScoreRequestDTO dto) { //Score(Entity) 객체 생성자 : DTO 받아감. (얘를 service에서 생성하면서 dto를 담아줄 것.)
		this.stuName = dto.getName(); //dto에 담긴 이름(사용자가 입력)을 엔티티객체의 이름에 담겠음.
		changeScore(dto);
	}

	public void changeScore(ScoreModiRequestDTO dto) {
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); // 총점, 평균 계산
        calcGrade(); // 학점 계산
    }
	
	public void changeScore(ScoreRequestDTO dto) {
        this.kor = dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();
        calcTotalAndAvg(); // 총점, 평균 계산
        calcGrade(); // 학점 계산
    }

    private void calcGrade() { //여기서 등급이 필요한데, 안정성을 위해 eNum 사용.
        if (average >= 90) {
            this.grade = Grade.A;
        } else if (average >= 80) {
            this.grade = Grade.B;
        } else if (average >= 70) {
            this.grade = Grade.C;
        } else if (average >= 60) {
            this.grade = Grade.D;
        } else {
            this.grade = Grade.F;
        }
    }

    private void calcTotalAndAvg() {
        this.total = kor + eng + math; //모두 this. 가 생략 되어있는 것.
        this.average = total / 3.0; //여기도 this 생략
    }

	
}
