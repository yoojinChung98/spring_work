package com.spring.myweb.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {
	
	private final IWeatherMapper mapper;
	
	
	
	public void getShortTermForecast(String area1, String area2) {
		
		LocalDateTime ldt = LocalDateTime.now();
		String baseDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(ldt);
		log.info("baseDate: {}", baseDate);
		
		Map<String, String> map = mapper.getCoord(area1.trim(), area2.trim());
		log.info("좌표 결과: {}", map);
		
		
		try {
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
		    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=Gk1S164h%2FZEbryEWddqS4U6cbfWKCLpHNaaXSSUbaghWaBg4dcXEE9z6kC7cOpAsjINCKGf8RQWYjunBm1iP0A%3D%3D"); /*Service Key*/
		    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
		    urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		    urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*‘21년 6월 28일 발표*/
		    urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0200", "UTF-8")); /*06시 발표(정시단위) */
		    urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("NX")), "UTF-8")); /*예보지점의 X 좌표값*/
		    urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("NY")), "UTF-8")); /*예보지점의 Y 좌표값*/
		    
		    log.info("완성된 url: {}", urlBuilder.toString());
		    
		    URL url = new URL(urlBuilder.toString());
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Content-type", "application/json");
		    System.out.println("Response code: " + conn.getResponseCode());
		    BufferedReader rd;
		    if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		    	//200번대는 모두 정상응답임: 응답이 정상적으로 이뤄졌는지 체크하는 것 (getInputStream())
		        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    } else {
		    	//그 밖의 오류가 발생한다면 (getERrrorStream())
		        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		    }
		    StringBuilder sb = new StringBuilder();
		    String line;
		    while ((line = rd.readLine()) != null) {
		    	//line을 한줄씩 StringBuilder sb에 추가하다가 BufferedReader에 더이상 읽을 line이 없을 경우 while 종료
		        sb.append(line);
		        //System.out.println(sb.toString());   
		    }
		    
		  //StringBuilder 객체를 String으로 변환 (json-simple 이 StringBuilder는 안받음)
	        String jsonString = sb.toString(); //'문자열'로 이루어진 json데이터를 받음
	        
	        JSONParser parser = new JSONParser();
	        
	        //JSONParser에게 JSON 타입으로 파싱 시켜달라함.
	        JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
	        
	        //이제 JSON 데이터에 차근차근 접근해야 합니당!(나중엔 걍 체이닝으로 가져오셈)
	        //받은 JSON 데이터에서 "response" 라는 이름의 키에 해당하는 JSON 데이터를 가져옴
	        JSONObject response = (JSONObject) jsonObject.get("response");
	        //response 안에서 "body"라는 이름의 키에 해당하는 JSON 데이터를 가져옴
	        JSONObject body = (JSONObject) response.get("body");
	        //(body 안에서 "items"라는 이름의 키에 해당하는 JSON 데이터를 가져옴.
	        //거기서 "item"에 해당하는 JSON 데이터를 꺼내서 JSONArray 타입으로 받음!(이제 이걸 쓸거니까)
	        //item 키에 해당하는 JSON 데이터는 여러 값이기 때문에 배열의 문법을 제공하는 객체, JSONArray로 받음![0]번부터 시작
	        JSONArray itemArray = (JSONArray)((JSONObject) body.get("items")).get("item");
	        
	        //우리는 각 item 내부의 각각의 객체에 대한 반복문 선언: "category"가 "TML"/"TMN" 인 객체를 찾아낼것임!
	        for(Object obj: itemArray) {
	        	
	        	//형변환 진행
	        	JSONObject item = (JSONObject) obj;
	        	//"category"키에 해당하는 단일 값을 가져옴
	        	String category = (String) item.get("category");
	        	//"fcstValue"키에 해당하는 단일 값을 가져옴
	        	String fcstValue = (String) item.get("fcstValue");
	        	
	        	if(category.equals("TMX") || category.equals("TMN")) {
	        		log.info("category: {}, fcstValue: {}", category, fcstValue);
	        	}
	        }
		    
		    
		    rd.close();
		    conn.disconnect();
		} catch (Exception e) {
		}
	}
	
	

}
