package com.spring.myweb.user.mapper;

import com.spring.myweb.user.entity.User;

public interface IUserMapper {
	
	//아이디 중복 확인
	//SQL 문에 COUNT(*)를 쓸거니까, 조회되면 1이 반환 될 것임.
	int idCheck(String id);
	
	//회원 가입
	void join(User user);
	
	//로그인
	//id를 통해 pw를 조회하는 메서드
	String login(String id);
	
	//회원 정보 얻어오기
	User getInfo(String id);
	
	//회원 정보 수정
	void updateUser(User user);
	
	
}
