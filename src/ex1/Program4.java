package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

// DELETE 예제
public class Program4 {

	public static void main(String[] args) throws ClassNotFoundException{
		Connection con = null;
		PreparedStatement st = null;
		
		int id = 6; // 쿼리문의 조건식에 사용할 컬럼
		
		
		try {
		String url = "jdbc:mysql://localhost:3306/sejin?serverTimezone=UTC";
		String sql = "DELETE FROM sejin.notice WHERE ID=?";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url,"root","qwerty06100");
		
		
		st = con.prepareStatement(sql); // 미리 준비된 sql을 통해서 PreparedStatement 객체를d 생성
		st.setInt(1, id);
		int rowCount = st.executeUpdate();
		
		System.out.println("st.executeUpdate() : " + rowCount);
		
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
