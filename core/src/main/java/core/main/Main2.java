package core.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import core.chap3.Hotel;

public class Main2 {

	public static void main(String[] args) {

		GenericXmlApplicationContext ct = 
				new GenericXmlApplicationContext("classpath:auto-config.xml");
		
		Hotel hotel1 = ct.getBean("hotel1", Hotel.class);
		hotel1.inform();
	}

}
