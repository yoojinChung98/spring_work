package com.spring.myweb.user.service;

<<<<<<< HEAD
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.myweb.user.dto.UserInfoResponseDTO;
=======
import org.springframework.stereotype.Service;

>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
import com.spring.myweb.user.dto.UserJoinRequestDTO;
import com.spring.myweb.user.entity.User;
import com.spring.myweb.user.mapper.IUserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final IUserMapper mapper;
<<<<<<< HEAD
	private final BCryptPasswordEncoder encoder;
=======
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b

	public int idCheck(String account) {
		return mapper.idCheck(account);
		
	}

	public void join(UserJoinRequestDTO dto) {
<<<<<<< HEAD
		
		//회원 비밀번호 암호화 인코딩
		System.out.println("암호화 하기 전 비밀번호: " + dto.getUserPw());
		
		//비밀번호를 암호화해서 dto에 다시 저장하기
		String securePw = encoder.encode(dto.getUserPw());
		System.out.println("암호화 후 비밀번호: " + securePw);
		dto.setUserPw(securePw);
		
		
		
=======
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
		//dto를 entity로 변환
		User user = User.builder()
				.userId(dto.getUserId())
				.userPw(dto.getUserPw())
				.userName(dto.getUserName())
				.userPhone1(dto.getUserPhone1())
				.userPhone2(dto.getUserPhone2())
				.userEmail1(dto.getUserEmail1())
				.userEmail2(dto.getUserEmail2())
				.addrBasic(dto.getAddrBasic())
				.addrDetail(dto.getAddrDetail())
				.addrZipNum(dto.getAddrZipNum())
				.build();
		mapper.join(user);
	}

<<<<<<< HEAD
	public String login(String userId, String userPw) {
		String dbPw = mapper.login(userId);
		if(dbPw != null) { //조회가 됐다면
			
			//날것의 비밀번호와 암호화된 비밀번호의 일치 여부를 알려주는 matches()
			if(encoder.matches(userPw, dbPw)) {
				return userId;
			}
		}
		return null;
	}//아이디가 맞고, 비밀번호까지 맞아야 userId가 반환됨

	
	
	
	public UserInfoResponseDTO getInfo(String id) {
		User user = mapper.getInfo(id);
		return UserInfoResponseDTO.toDTO(user);
	} 
=======
	public void login(String userId) {
		mapper.login(userId);
		
	}
>>>>>>> 03aa3e370222594d6281070ca8190a48c48e102b
	
	

}
