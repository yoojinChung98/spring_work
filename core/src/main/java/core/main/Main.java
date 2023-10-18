package core.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import core.chap2.Hotel;

public class Main {
	
	public static void main(String[] args) {
		
		/*
		Hotel hotel = new Hotel();
		hotel.inform();
		*/
		
		//xml파일의 bean 안에 등록한 객체를 사용해보자!
		//빈 등록에 사용된 xml의 설정을 가져오는 객체를 생성. (classpath == src/main/resources)
		GenericXmlApplicationContext ct =
				new GenericXmlApplicationContext("classpath:hotel-config.xml");//매개값으로 등록한 bean이 있는 경로를 전달!

		//Hotel타입의 id가 hotel1인 애를 데리고 와라!
		Hotel hotel1 = ct.getBean("hotel1", Hotel.class);//setter메서드를 주입한 방법
		hotel1.inform();
		
		System.out.println("=======================================");
		
		Hotel hotel2 = ct.getBean("hotel2", Hotel.class);//생성자를 주입한 방법
		hotel2.inform();
	}

}
