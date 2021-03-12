package com.sejin.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sejin.app.entity.Notice;

// notice 테이블에 대한 CRUD 서비스 예제!
// 각 메서드에서 공통적인 부분은 변수로 빼내어 사용.
// 예를 들어 dbms 드라이버, 사용자 ID/패스워드 

public class NoticeService2 {
	
	private String url = "jdbc:mysql://localhost:3306/sejin?serverTimezone=UTC";
	private String user = "root";
	private String password = "qwerty06100";
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	// 데이터베이스에서 레코드를 가져오고 반환하는 메서드
	public List<Notice> getList(int page) throws SQLException, ClassNotFoundException{ 
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		int startPage = 1 + (page-1)*10 ; // 1, 11, 21, 31, ....
		int endPage = page*10; // 10, 20, 30, 40, ....
		List<Notice> list = new ArrayList<Notice>(); // Notice 객체를 담는 리스트
		
		
		String sql = "SELECT * "
				+ "FROM (SELECT @ROWNUM:=@ROWNUM+1 AS NUM, A.* "
				+ "FROM (SELECT * FROM sejin.notice ORDER BY REGDATE) A , (SELECT @ROWNUM:=0) R"
				+ ") M "
				+ "WHERE NUM BETWEEN ? AND ?;";
		
		// 쿼리가 상당히 복잡하다
		// 데이터베이스에서 뷰를 생성하여 간단히 데이터를 조회할 수 있다.
		// mysql 에서는 view 생성에서 서브쿼리를 지원하지 않는다... 아 다음에 수정하자
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,password);
		st = con.prepareStatement(sql);
		st.setInt(1,startPage);
		st.setInt(2,endPage);
		rs = st.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			String files = rs.getString("FILES");
			Notice notice = new Notice(id,title,writerId,regDate,content,hit,files);
			list.add(notice);
		}
		
		con.close();
		st.close();
		rs.close();
		
		return list;
	}

	// 데이터베이스에서 전체 레코드(게시물)의 개수를 반환하는 메서드
		public int getCount() throws ClassNotFoundException, SQLException {
			int count = 0;
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			
			String sql = "SELECT count(ID) CNT FROM sejin.notice";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			if(rs.next())
				count = rs.getInt("CNT");
			
			con.close();
			st.close();
			rs.close();
			
			return count;
		}
	
	// 데이터베이스에 새로운 레코드를 삽입하는 메서드
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement st = null;
		
		String title = notice.getTitle();
		String writerId = notice.getWriterId();
		String content = notice.getContent();
		String files = notice.getFiles();
		
		
		String sql = "INSERT INTO sejin.notice(TITLE,WRITER_ID,CONTENT,FILES) VALUES(?,?,?,?)";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,password);
		
		st = con.prepareStatement(sql); // 미리 준비된 sql을 통해서 PreparedStatement 객체를 생성
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, files);
		int rowCount = st.executeUpdate();
		
		con.close();
		st.close();
		
		return rowCount;
	}
	
	// 데이터베이스의 레코드를 수정하는 메서드
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement st = null;
		
		String title = notice.getTitle();
		String content = notice.getContent();
		String files = notice.getFiles();
		int id = notice.getId();
		
		
		String sql = "UPDATE sejin.notice SET TITLE=?,CONTENT=?,FILES=? WHERE ID=?";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,password);
		
		st = con.prepareStatement(sql); // 미리 준비된 sql을 통해서 PreparedStatement 객체를d 생성
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		int rowCount = st.executeUpdate();
		
		con.close();
		st.close();
		
		return rowCount;
	}

	// 데이터베이스의 레코드를 삭제하는 메서드
	public int delete(int id) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement st = null;
		
		
		String sql = "DELETE FROM sejin.notice WHERE ID=?";
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,password);
		st = con.prepareStatement(sql); // 미리 준비된 sql을 통해서 PreparedStatement 객체를d 생성
		st.setInt(1, id);
		int rowCount = st.executeUpdate();
		
		return rowCount;
	}

	
	
}
