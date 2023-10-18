package core.person;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 모든 파라미터 생성자
@EqualsAndHashCode
//@Data -> 위의 annotation을 다 생성해줌. 근데 실무에서는 사용하지 않음! 왜: 의도하지 않는 정보를 노출시킬 수도 있고 등등의 세세한ㅇ 기타설정이 불가함.
public class Person {
	
	private String nickname;
	private String address;
	private int age;
	
	
	
}
