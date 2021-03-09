package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

// SELECT 예제
public class Program {

	public static void main(String[] args) throws ClassNotFoundException{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
		String url = "jdbc:mysql://localhost:3306/sejin?serverTimezone=UTC";
		String sql = "SELECT * FROM NOTICE";
		String sql2 = "SELECT * FROM NOTICE WHERE HIT>=10";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,"root","qwerty06100");
		st = con.createStatement();
		rs = st.executeQuery(sql2);
		
		while(rs.next()) {
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String writerId = rs.getString("WRITER_ID");
			Date regDate = rs.getDate("REGDATE");
			String content = rs.getString("CONTENT");
			int hit = rs.getInt("HIT");
			System.out.printf("id:%d, title:%s, writerId:%s, regDate:%s, content:%s, hit:%d%n",id,title,writerId,regDate,content,hit);
		}
		
		}catch(SQLException e) {
			System.out.println("SQLEXception : " + e.getMessage());
		}finally {
			try {
				con.close();
				st.close();
				rs.close();
			}catch(SQLException e){e.printStackTrace();}
		}
	}

}
