package ex1;

import java.sql.SQLException;

import com.sejin.app.ui.NoticeConsole;

public class Program5 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException { // top-dowm 방식으로 설계함 -> 먼저 실행 프로그램을(Program5)에서 먼저 틀을 구현해놓고, 그다음 NoticeConsol 클래스 내부를 구현해나가는 방식!
		NoticeConsole console = new NoticeConsole();
		
		EXIT:
		while(true){
			console.printNoticeList(); // notice의 항목(리스트)를 출력하는 메서드
			int menu=99;
			
			try {
				menu = console.inputNoticeMenu(); // 메뉴를 선택하는 메서드
			}catch(NumberFormatException e) {}
			
			
			switch(menu){
				case 1: // 상세조회
					break;
				case 2: // 이전
					break;
				case 3: // 다음
					break;
				case 4: // 글쓰기
					break;
				case 5: // 종료
					System.out.println("exit.");
					break EXIT;
				default:
					System.out.println("올바른 메뉴 번호를 입력하시오.");
					break;
			}
		}
		
	}

}
