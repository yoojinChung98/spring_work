package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.user.entity.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserMapperTest {
	
	@Autowired
	private IUserMapper mapper;
	
	@Test
    @DisplayName("회원 가입을 진행했을 때 회원가입이 성공해야 한다.")
    void registTest() {
        //일단 id, pw, name 만 받겠음
		mapper.join(User.builder()
							.userId("Lim1234")
							.userPw("12345")
							.userName("LimByeongWook")
							.build());
    }
	/*
	 User.builder()
						.userId("seo1234")
						.userPw("12345")
						.userName("SeoGunChang")
						.userPhone1("first ph")
						.userPhone2("second phone")
						.userEmail1("firstemail")
						.userEmail2("second email")
						.addrBasic("basic address")
						.addrDetail("detail address")
						.addrZipNum("postNum")
				.build());
	 */
	
    
    @Test
    @DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴되어야 한다.")
    void checkIdTest() {
    	String id = "kim1234";
    	
        assertEquals(1, mapper.idCheck(id));
    }
    
    @Test
    @DisplayName("존재하는 회원 아이디를 입력 했을 시 그 회원의 비밀번호가 리턴되어야 한다.")
    void loginTest() {
    	String id = "kim1234";
    	
    	//assertEquals("12345", id);
    	assertNotNull(mapper.login(id));
    }
    
    @Test
    @DisplayName("존재하지 않는 회원의 아이디를 전달하면 null이 올 것이다.")
    void getInfoTest() {
        assertNull(mapper.getInfo("merong"));;
    }
    
    @Test
    @DisplayName("id를 제외한 회원의 정보를 수정할 수 있어야 한다.")
    void updateTest() {
    	
    	User user = User.builder()
    			.userId("seo1234")
    			.userPw("12345")
    			.userName("서건창")
    			.userEmail1("profSeo.gmail.com")
    			.addrBasic("키움히어로즈")
    			.addrDetail("detail")
    		.build();
    	mapper.updateUser(user);
    	
    	assertEquals(user.getUserName(), mapper.getInfo("seo1234").getUserName());
    	
    	
    }
	
	
}
