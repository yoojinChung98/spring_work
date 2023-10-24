package com.spring.myweb.snsboard.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.snsboard.dto.SnsBoardRequestDTO;
import com.spring.myweb.snsboard.dto.SnsBoardResponseDTO;
import com.spring.myweb.snsboard.service.SnsBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@RestController
@RequestMapping("/snsboard")
@RequiredArgsConstructor
@Slf4j
public class SnsBoardController {

	private final SnsBoardService service;
	
	@GetMapping("/snsList")
	public ModelAndView snsList() {
		ModelAndView mv = new ModelAndView();
		//모델에 객체를 담아서 보내고 싶을 때
		//mv.addObject("name", "value");
		mv.setViewName("snsboard/snsList");
		return mv;
	}
	
	@PostMapping()
	public String upload(SnsBoardRequestDTO dto) {
		service.insert(dto);
		return "uploadSuccess";
		
	}
	
	@GetMapping("/{page}")
	public List<SnsBoardResponseDTO> getList(@PathVariable int page) {
		//System.out.println("/snsboard/getList: GET");
		log.info("/snsboard/getList: GET");
		return service.getList(page);
	}
	
	/*
    # 게시글의 이미지 파일 전송 요청
    이 요청은 img 태그의 src 속성을 통해서 요청이 들어오고 있습니다.
    snsList.jsp 페이지가 로딩되면서, 글 목록을 가져오고 있고, JS를 이용해서 화면을 꾸밀 때
    img 태그의 src에 작성된 요청 url을 통해 자동으로 요청이 들어오게 됩니다.
    요청을 받아주는 메서드를 선언하여 입력받은 경로에 지정된 파일을 보낼 예정입니다.
    */
	@GetMapping("/display/{fileLoca}/{fileName}")
	public ResponseEntity<?> getImage(@PathVariable String fileLoca, @PathVariable String fileName) {
		//제너릭 문법 (타입이 불명확할때 제너릭 타입으로 ? 를 찍으면 어떤 타입이든 넣어서 반환할 수 있음 ResponseEntity 이기만 함!)
//		System.out.println("fileLoca : "+fileLoca);
//		System.out.println("fileName : "+fileName);
		log.info("fileLoca : "+fileLoca);
		//문자열에 {}로 자리를 만들어놓고 후속 매개변수로 그 자리를 순차적으로 채워주는 방법도 가능하다!
		log.info("fileName {}: ",fileName);
		
		File file = new File("C:/test/upload/"+ fileLoca + "/" + fileName);
		System.out.println(file.toString()); //완성된 경로.
		
		//ResponseEntity라는 객체 하나 생성
		//응답에 대한 여러가지 정보를 전달할 수 있는 객체 ResponseEntity
		//응답 내용, 응답 성공 여부, 응답에 관련된 여러 설정들을 지원
		ResponseEntity<byte[]> result = null;
		
		HttpHeaders headers = new HttpHeaders(); //응답 헤더 객체 생성.
		
		//헤더네임, 헤더밸류
		try {
            //probeContentType: 매개값으로 전달받은 파일의 타입이 무엇인지를 문자열로 반환.
            //사용자에게 보여주고자 하는 데이터가 어떤 파일인지에 따라 응답 상태 코드를 다르게 리턴하고
            //컨텐트 타입을 제공해서 화면단에서 판단할 수 있게 처리할 수 있다.
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			headers.add("value", "hello");
			
			//내가 아까 지정한 경로의 파일을 byte타입 array로 copy한 뒤 ResponseEnity에 매개값으로 준 것
			//ResponseEntity 객체에 전달하고자 하는 파일을 byte[]로 변환해서 전달. (파일의 손상을 막기 위해)
			//header 내용도 같이 포함, 응답 상태 코드를 원하는 형태로 전달이 가능
			//OK = 200 응답코드
			
			//생성자를 이용하여 ResponseEntity를 생성하는 법
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
			
		} catch (IOException e) {
			e.printStackTrace();
			//badRequest : 응답상태 400
			
			//static 메서드를 활용하여 ReesponseEntity 객체를 생성하는 법
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return result;
	}
	
	
	@GetMapping("/download/{fileLoca}/{fileName}")
	//파일도 byte[] 로 쪼개서 보내줘야하고, 어떻게 받아야하는지 ResponseHeader도 지정해줘야해서 ResponseEntity<?> 로 반환하는 것.
	public ResponseEntity<?> download(@PathVariable String fileLoca,
										@PathVariable String fileName) {
		
		File file = new File("C:/test/upload/" + fileLoca + "/" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		//다운로드를 제공할 땐 헤더가 꼭 필요함 (아까 디스플레이의 경우는 선택사항이었음)
		HttpHeaders header = new HttpHeaders();
		
		/*외우지 말고 읽어만 보기
        //응답하는 본문을 브라우저가 어떻게 표시해야 할 지 알려주는 헤더 정보를 추가합니다.
        //inline인 경우 웹 페이지 화면에 표시되고, attachment인 경우 다운로드를 제공합니다.
        
        //request객체의 getHeader("User-Agent") -> 단어를 뽑아서 확인 : 사용자가 이용하는 브라우저의 정보를 알아내는 것.
        //ie: Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko  
        //chrome: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36
    
        //파일명한글처리(Chrome browser) 크롬
        //header.add("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") );
        //파일명한글처리(Edge) 엣지 
        //header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //파일명한글처리(Trident) IE
        //Header.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " "));
		*/
		
		header.add("Content-Disposition", "attachment; filename=" + fileName); //이게 inline 속성이면 화면에 표시하겠다는 의미(inline이 디폴트값임.); 다운로드로 제공할 파일명; 그 파일명을 한글처리를 해야할 때. 
		
		try {
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	@GetMapping("/content/{bno}")
	public SnsBoardResponseDTO detail(@PathVariable int bno) {
		return service.getDetail(bno);
	}
	
	/*
	//이렇게도 가능하다!
	//단순히 값만 보낼거면 DTO 만 보내도 문제 없지만, 처리에 따른 응답상태 코드까지 함께 보내고 싶다면 ResponseEntity<>를 사용해도 무방!
	//꼭 이미지 파일을 보낼 때만 해당 객체를 사용해야 하는 것은 아님!
	@GetMapping("/content/{bno}")
	public ResponseEntity<?> getDetail(@PathVariable int bno) {
		return ResponseEntity.ok().body(service.getDetail(bno));
	}
	*/
	
	@DeleteMapping("/{bno}")
	public String delete(@PathVariable int bno, HttpSession session) {
		
		System.out.println("delete 요청 받음"+bno);

		String id = (String) session.getAttribute("login");
		SnsBoardResponseDTO dto = service.getDetail(bno);
		System.out.println("dto 받아옴: "+dto);
		
		if(id == null || !id.equals(dto.getWriter())) {
			System.out.println("조건문에 걸렸음!");
			return "noAuth";
			//return ResponseEntity.status(Httpstatus.UNAUTHORIZED).build();
		}
			service.delete(bno);
			System.out.println("delete 함수 지남");
			
			File file = new File(dto.getUploadPath() + dto.getFileLoca() +"/"+ dto.getFileName());
			System.out.println("파일 삭제로직 지나침");
			return file.delete() ? "success" : "fail" ;
			//return file.delete() ? ResponseEntity.status(HttpStatus.OK).build() :
							//ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
		
		
	//좋아요 버튼 클릭 처리
	@PostMapping("/like")
	public String likeConfirm(@RequestBody Map<String, String> params) {
		log.info("/like: POST, params: {}", params);
		
		return service.searchLike(params);
	}
	
	
	
	
}
	
	
	



