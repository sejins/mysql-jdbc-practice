package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

//INSERT 예제
public class InsertN {

	public static void main(String[] args) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement st = null;
		
		for(int i=1; i<=10; i++) {

			String title = "타이틀"+i;
			String writerId = "아이디"+i;
			String content = "내용"+i;
			String files = "";
			
			try {
			String url = "jdbc:mysql://localhost:3306/sejin?serverTimezone=UTC";
			String sql = "INSERT INTO sejin.notice(TITLE,WRITER_ID,CONTENT,FILES) VALUES(?,?,?,?)";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,"root","qwerty06100");
			
			//st = con.createStatement();   // sql문에 ? 키워드를 사용하기 위해
			// rs = st.execute...
			
			st = con.prepareStatement(sql); // 미리 준비된 sql을 통해서 PreparedStatement 객체를 생성
			st.setString(1, title);
			st.setString(2, writerId);
			st.setString(3, content);
			st.setString(4, files);
			int rowCount = st.executeUpdate();
			
			if(rowCount==1) {
				System.out.println("삽입이 정상적으로 실행됨.");
			}
			else
				System.out.println("삽입이 정상적으로 실행되지 않음.");
			}catch(SQLException e) {
				System.out.println("SQLEXception : " + e.getMessage());
			}finally {
				try {
					con.close();
					st.close();
				}catch(SQLException e){e.printStackTrace();}
			}
		}
		
	}

}
