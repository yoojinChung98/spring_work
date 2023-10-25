package com.spring.myweb.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Controller()
@RestController
@RequestMapping("/rest")
@Slf4j
@RequiredArgsConstructor
public class RestControllerTest {
	
	private final WeatherService service;
	
	@GetMapping("/view")
	public String viewPage(){
		return "test/test1";
	}
	
	@GetMapping("/api-view")
	public ModelAndView apiViewPage() {
		return new ModelAndView("test/api-view");
	}
	
	@GetMapping("/api-req/{area1}/{area2}")
	public void apiRequest(@PathVariable String area1, @PathVariable String area2) {
		log.info("/api-req: GET, area1: {}, area2: {}", area1, area2);
		
		service.getShortTermForecast(area1, area2);
	}
	/*
	 @ RequestBody
	 - 클라이언트 쪽에서 전송하는 JSON 데이터를
	 서버에서 사용하는 자바 언어에 맞게 변환하여 받을 수 있는 아노테이션
	 
	 @ResponseBody
	 - 메서드가 리턴하는 데이터를 viewResolver에게 전달하지 않고,
	 클라이언트에게 해당 데이터를 바로 응답하게 합니다.
	 비동기 통신에서 주로 많이 사용합니다.
	 
	 @RestController 아노테이션을 통해 빈으로 등록한 컨트롤러는
	 모든 메서드가 리턴 값을 클라이언트로 직접 리턴합니다. (viewResolver를 사용하지 않습니다.)
	 REST 방식의 통신 전용 컨트롤러로 빈을 등록하는 것!!!
	 */
	
	@PostMapping("/object")
	@ResponseBody
	public Person object(@RequestBody Person p) {
		System.out.println("비동기 방식의 요청 들어옴! >0<");
		System.out.println(p.toString());
		
		p.setName("변경된 이름임");
		p.setAge(100);
		
		
		return p;
	}
	
	/////////////
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello world!";
	}
	
	@GetMapping("/hobby")
	@ResponseBody
	public List<String> hobby() {
		return Arrays.asList("축구", "영화감상", "수영" );
	}
	
	@GetMapping("/study")
	@ResponseBody
	public Map<String, Object> study() {
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "java");
		subject.put("jsp", "java server pages");
		subject.put("spring", "spring framework");
		
		return subject;
	}
	
	
}
