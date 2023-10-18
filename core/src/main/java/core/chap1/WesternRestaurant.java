package core.chap1;

public class WesternRestaurant implements Restaurant {

	//레스토랑은 셰프에게 의존적. 없으면 요리를 할 수가 없음.
	private Chef chef = new FrenchChef();
	
	//요리 코스
	private Course course = new FrenchCourse();
	
	//요리를 주문하는 기능
	public void order() {
		System.out.println("서양 요리를 주문합니다.");
		course.combineMenu();
		chef.cook();
	}
	
}








