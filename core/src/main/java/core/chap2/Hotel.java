package core.chap2;

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

	
	//2. setter 주입
	public void setHeadChef(Chef headChef) {
		this.headChef = headChef;
	}
	
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


















