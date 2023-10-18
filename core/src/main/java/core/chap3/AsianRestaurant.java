package core.chap3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.Setter;

@Setter //set에 넣을 조건이 없으니 롬복으로 자동완성 해준다!
public class AsianRestaurant implements Restaurant {

	//레스토랑은 셰프에게 의존적. 없으면 요리를 할 수가 없음.
	//필드에도 붙일 수 있음
	@Autowired
	@Qualifier("asianChef")
	private Chef chef;
	
	//요리 코스
	@Autowired
	@Qualifier("sushi")
	private Course course;
	
	//주입할 세터
	/*
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	*/
	
	/*
	@Autowired
	public AsianRestaurant(@Qualifier("asianChef")Chef chef, @Qualifier("sushi")Course course) {
		super();
		this.chef = chef;
		this.course = course;
	}
	*/
	
	//요리를 주문하는 기능
	public void order() {
		System.out.println("아시안 요리를 주문합니다.");
		course.combineMenu();
		chef.cook();
	}

	
}








