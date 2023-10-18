package com.spring.basic.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserVO {
	
	//커맨드 객체? VO? 를 생성할 땐 반드시 변수명을 파라미터이름과 동일하게 맞춰줘야 합니다!
	//왜냐면 나중에 setter 메서드를 부를 때 이 이름들을 기준으로 부를 것이기 때문.
	// DS가 /request/join 요청(post)을 받고 RequestController 의 register() 메서드로감
	//해당 메서드는 UserVO 객체를 파라미터로 요구하므로 DS가 여기 클래스로 방문함
	// 해당 필드 이름과 파라미트이름이 같으면 하나씩 초기화해줌 (setter를 호출해서)
	// 그리고 그걸 register()에게 인자로 던져주는 것!
	private String userId;
	private String userPw;
	private String userName;
	private List<String> hobby;

}
