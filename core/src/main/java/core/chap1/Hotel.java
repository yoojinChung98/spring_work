package core.chap1;

public class Hotel {
	
	//객체마다 의존성을 갖게 설계해보자! (의존성 부여)
	
	//호텔은 레스토랑 객체에 의존적.
	private Restaurant restaurant = new WesternRestaurant();
	
	//헤드 셰프
	private Chef headChef = new FrenchChef();
	
	//호텔의 정보를 알려주는 기능
	public void  inform() {
		System.out.printf("우리 호텔의 레스토랑은 %s입니다. 헤드셰프는 %s입니다.\n"
				, restaurant.getClass().getSimpleName()
				, headChef.getClass().getSimpleName());//레스토랑 이름만 가져오는 메서드
		restaurant.order();
	}
	
}


















