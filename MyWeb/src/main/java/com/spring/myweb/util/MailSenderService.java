package com.spring.myweb.util;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailSenderService {

	//email-config에 등록한 빈 주입 (해당 인터페이스를 구현하는 빈은 email-config에서 등록한 빈 밖에 없음)
	private final JavaMailSenderImpl mailSender;
	
	//난수 발생(이메일 확인을 위한)
	private int makeRandomNumber() {
		//난수의 범위: 111111 ~ 999999
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("인증번호: " + checkNum);
		return checkNum;
	}
	
	
	//가입할 회원에게 전송할 이메일 양식 준비
	public String joinEmail(String email) { //컨트롤러에게 전달한 인증번호를 문자열의 형태로 보내겠음
		
		//인증번호 생성
		int authNum = makeRandomNumber();
		
		//인증번호를 발신할 이메일(개발자의 이메일이게찌) (email-config에서 설정한 발송용 이메일 주소 입력)
		String setFrom = "esmeraldachung@naver.com";
		
		//인증번호를 수신 받을 이메일 (가입하고자 하는 사람의 이메일)
		String toMail = email;
		
		//이메일 제목
		String title = "MyWeb 회원 가입 인증 이메일 입니다.";
		
		//이메일 본문 (HTML(+CSS) 태그로 구성)
		String content = "홈페이지 가입을 신청해 주셔서 감사합니다." + "<br><br>" +
							"인증 번호는 <strong>"+ authNum +"</strong> 입니다." + "<br>" +
							"상단의 인증 번호를 인증번호 확인란에 기입해 주세요.";
		
		//메일발송 메서드 호출
		//mailSend(setFrom, toMail, title, content);
		
		return Integer.toString(authNum);
	}

	
	
	//이메일 발송 메서드
	private void mailSend(String setFrom, String toMail, String title, String content) {
		
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
			//기타 설정들을 담당할 MimeMessageHelper 객체를 생성.
			//생성자의 매개값으로는 MimeMessage 객체, boolean, 문자 인코딩 설정
			//true 매개값을 전달하면 MultiPart 형식의 메세지 전달이 가능. (첨부 파일)
			//MultiPart : 첨부파일이 있다면(true), 첨부파일 없이 단순 텍스트라면 false()
			
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true); // true: html 형식으로 전송, false(디폴트): 단순 텍스트 전달
			
			//메일 전송
			mailSender.send(message);
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
