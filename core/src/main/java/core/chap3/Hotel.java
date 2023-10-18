package core.chap3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Hotel {
	
	//제어의 역전(IoC): 객체 생성의 제어권을 외부로 넘긴다.
	//의존성 주입(DI): 외부에서 생성된 객체를 주입받는 개념.
	
	//객체마다 의존성을 갖게 설계해보자! (의존성 부여)
	
	//호텔은 레스토랑 객체에 의존적.
	private Restaurant restaurant;
	
	//헤드 셰프
	private Chef headChef;
	
	//주입의 2가지 방법.
	//1. 생성자 주입
	public Hotel() {} //기본 생성자를 추가한다고 해도 에러가 안나진않음.

	public Hotel(Restaurant restaurant, Chef headChef) {
		super();
		this.restaurant = restaurant;
		this.headChef = headChef;
	}

	/*
    # @Autowired
    - 객체를 자동 주입할 때 사용하는 아노테이션입니다.
    - 스캔 명령을 통해 객체를 찾아 주입하는데, 타입 이름으로 검색합니다.
    - 타입을 찾아내지 못하면 이름(id 속성값)을 통해 검색합니다.
    - 생성자, 메서드, 필드에 적용 가능합니다.
    
    # @Qualifier("bean id")
    - Autowired를 사용할 때 동일 타입의 빈이 여러 개 있는 경우
    어떤 빈을 주입해야 하는 지 선택해 주는 추가 아노테이션 입니다.
    */
	
	//2. setter 주입
	@Autowired
	@Qualifier("asianChef")
	public void setHeadChef(Chef headChef) {
		this.headChef = headChef;
	}
	
	@Autowired
	@Qualifier("asian")//bean의 id값을 알려줘야함. id가 bean의 이름임
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	//호텔의 정보를 알려주는 기능
	public void  inform() {
		System.out.printf("우리 호텔의 레스토랑은 %s입니다. 헤드셰프는 %s입니다.\n"
				, restaurant.getClass().getSimpleName()
				, headChef.getClass().getSimpleName());//레스토랑 이름만 가져오는 메서드
		restaurant.order();
	}
	
}


















