package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

	//커피 주문 화면을 열어주는 메서드
	@GetMapping("/order")
	public String coffeeOrder() {
		System.out.println("/coffee/order: GET 요청 발생!");
		return "/response/coffee-form";
	}
	
	//주문하기 버튼을 누르면 들어오는 요청을 받는 메서드
	//이벤트가 발생하지 않으면 값이 input태그로 넘어가지 않으므로 다음과 같이 설정.
	//value="price"는 변수이름이 같으므로 생략
	@PostMapping("/result")
	public String coffeeResult(String menu,
								@RequestParam(defaultValue="3000") int price,
								Model model) {
		System.out.println("menu: "+menu);
		System.out.println("price: "+price);
		
		model.addAttribute("menu", menu);
		model.addAttribute("p", price);
		
		return "response/coffee-result";
		
	}
}
