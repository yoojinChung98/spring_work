package com.spring.basic.score.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.basic.score.entity.Grade;
import com.spring.basic.score.entity.Score;

@Repository("jdbc")
public class ScoreJdbcRepository implements IScoreRepository {
	
	//데이터베이스 접근에 필요한 정보들을 변수화 시킨 것.
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //데이터베이스 주소@오라클DBMS주소://
	private String username = "hr"; //데이터베이스 접근을 위한 계정명
	private String password = "hr"; //비밀번호
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//데이터베이스 연동을 전담하는 객체는 무분별한 객체 생성을 막기 위해 싱글톤 디자인 패턴으로 구축해야함
	//스프링 프레임워크는 컨테이너 내의 빈 객체들을 싱글톤으로 유지해주므로 내가 따로 신경쓸 필요가 없음.
	
	
	public ScoreJdbcRepository() {
		try {
			//객체가 생성될 때(생성자니까) 오라클 데이터베이스 커넥터 드라이버를 강제 구동해서 연동 준비.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Oracle Driver 는 오라클이 라이브러리 형식으로 제공.
		//Class.forName()=>직접 객체를 생성하지 않고 해당 객체를 클래스 이름으로 불러서 강제로 구동하는 메서드
		//커넥터/.forName()의 인자값은 패키지정보와 클래스임.
	}
	
	
	
	
	
	@Override
	public List<Score> findAll() {
		
		//조회된 정보를 모두 담아서 한번에 리턴하기 위한 리스트.
		List<Score> scoreList = new ArrayList<Score>();
		
		
		String sql = "SELECT * FROM score ORDER BY stuNum DESC";
		try {
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			//SELECT문은 실행한 뒤, 받아올 것이 있으므로 executequery로 해야함 / 리턴타입: ResultSet. 
			//실행하고자 하는 sql이 SELECT인 경우에는 INSERT, UPDATE, DELETE와는 다른 메서드를 호출함
			// 메서드의 실행 결과 = sql의 '조회 결과'를 들고있는 객체인 ResultSet이 리턴됨.
			rs = pstmt.executeQuery();
			
			//조회할 데이터(한 행)가 있는지부터 물어보고
			//해당 데이터(행 하나)를 다 담은 뒤 데이터(행이) 더 있는지 물어보고.. 반복
			//즉, true가 온다는 것은 조회할 다음 행이 타겟팅이 되었다는 뜻
			
			while(rs.next()) { //rs.next() 는 boolean 타입. 조회된 행이 있으면 true/ 없으면 false
				//타겟으로 잡힌 행의 데이터를 얻어옴
				Score s = new Score(//rs.get얻고자하는_데이터타입("테이블의_컬럼명")
							rs.getInt("stuNum"),
							rs.getString("stuName"),
							rs.getInt("kor"),
							rs.getInt("eng"),
							rs.getInt("math"),
							rs.getInt("total"),
							rs.getDouble("average"),
							Grade.valueOf(rs.getString("grade"))
						);
				scoreList.add(s);			
			}//end while
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return scoreList;
	}
	
	

	@Override
	public void save(Score score) {
		try {
			//접속하고 실행하는 애를 쓸 것임
			//1. sql을 문자열로 준비해 주세요.
			// 변수 도는 객체에 들어이는 값으로 sql을 완성해야 한다면, 해당 자리에 ?를 찍어주세요
			String sql = "INSERT INTO score VALUES(score_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			
			//2. 데이터베이스에 접속을 담당하는 Connection 객체를 메서드를 통해 받아옵니다.
			// 데이터베이스 접속을 위해 접속 정보를 함께 전달합니다.
			//오라클 드라이버가 활성화 되면 사용할 수 있는 객체 Drivermanager. Return 타입은 Connection 이라는 객체
			// 이 connection이라는 객체가 DBMS에 우리 대신 접속할 것임. (접속만 하는 애임)
			conn = DriverManager.getConnection(url, username, password);
			
			//3. 이제 접속을 완료했으니, sql을 실행할 수 있는 PreparedStatemnet를 받아옵시다.
			// 직접 생성하지 않고, 메서드를 통해 받아옵니다.
			//PreparedStatment 객체를 반환
			// 얘를 통해서 전달한 sql문을 실행할 수 있음.
			pstmt = conn.prepareStatement(sql);
			
			//4. 근데 sql이 아직 완성되지 않았기 때문에 ?를 채워서 SQL을 완성 시킴
			// sql을 pstmt에게 전달했기 때문에, pstmt를 이용하여 ?를 채울것임
			// 값의 타입에 따라 불리는 메서드가 조금씩 차이가 있음.
			pstmt.setString(1, score.getStuName());
			pstmt.setInt(2, score.getKor());
			pstmt.setInt(3, score.getEng());
			pstmt.setInt(4, score.getMath());
			pstmt.setInt(5, score.getTotal());
			pstmt.setDouble(6, score.getAverage());
			pstmt.setString(7, String.valueOf(score.getGrade()));
			
			//5. sql 을 다 완성했으니, pstmt에게 sql을 실행하라는 명령을 내림
			//sql 실행 명령 = executeUpdate()
			//리턴타입 int -> 실행성공=1 실행실패=0
			int rn = pstmt.executeUpdate();
			/*
			if(rn==1) {
				System.out.println("INSERT 성공!");
			}else{
				System.out.println("INSERT 실패!");
			}
			 */
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//6. 객체 사용 후 반납!
			//try문 안에서 모두 선언해서 fianlly 부분에서 찾을 수 가 없음..
			//그래서 try문 외부에서 선언하고 try문에서 초기화 하겠음
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		

	}

	@Override
	public Score findByStuNum(int stuNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBystuNum(int stuNum) {
		//일단 stuNum 매개변수로 받음
		String sql = "DELETE FROM score WHERE stuNUM = ? ";
		//String sql = "DELETE FROM score WHERE stuNUM ="+stuNum;
		
		try {
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stuNum);
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void modify(int stuNum, Score newScore) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modify(Score modScore) {
		// TODO Auto-generated method stub

	}

}
