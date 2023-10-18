package core.chap1;

public class AsianRestaurant implements Restaurant {

	//레스토랑은 셰프에게 의존적. 없으면 요리를 할 수가 없음.
	private Chef chef = new AsianChef();
	
	//요리 코스
	private Course course = new SushiCourse();
	
	//요리를 주문하는 기능
	public void order() {
		System.out.println("아시안 요리를 주문합니다.");
		course.combineMenu();
		chef.cook();
	}
	
}








