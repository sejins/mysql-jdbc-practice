package com.sejin.app.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.sejin.app.entity.Notice;
import com.sejin.app.service.NoticeService2;

//NoticeConsole을 클래스로 만들면 이 콘솔 UI 클래스를 언제든지 재사용 할 수 있다.

public class NoticeConsole {
	
	NoticeService2 notice; // 서비스 클래스를 맴버 변수로 사용 
	private int page; // 화면에 출력할 페이지에 대한 멤버 변수
	private String searchField; // 검색 범주로 사용할 멤버 변수
	private String searchWord; // 검색 키워드로 사용할 멤버 변수
	
	
	public NoticeConsole() {
		notice = new NoticeService2();
		page = 1; // 페이지의 초기화 값은 1
		searchWord = "";
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		 
		List<Notice> list = notice.getList(page);
		int count = notice.getCount(); // 전체 notice 게시물의 개수
		int lastPage = (count%10==0) ? count/10 : count/10+1 ; // notice 게시판의 페이지 개수
		
		System.out.println("───────────────────────────────────────────");
		System.out.printf("<공지사항> 총 %d개 게시글%n",count);
		System.out.println("───────────────────────────────────────────");
		
		for(Notice n : list)
			System.out.printf("%d. %s / %s / %s%n",n.getId(),n.getTitle(),n.getWriterId(),n.getRegDate());
		
		System.out.println("───────────────────────────────────────────");
		System.out.printf("%d/%d page%n",page,lastPage);
		System.out.println();
	}
	
	public int inputNoticeMenu(){
		Scanner scan = new Scanner(System.in);
		System.out.print("1.상세조회 / 2.이전 / 3.다음 / 4.글쓰기 / 5.검색 / 6.종료 >> ");
		String menu_ = scan.next();
		int menu = Integer.parseInt(menu_);
		return menu;
		
	}

	public void prevPage() {
		if(page==1) {
			System.out.println();
			System.out.println("=======================");
			System.out.println("현재 가장 최근 페이지입니다.");
			System.out.println("=======================");
			System.out.println();
		}
		else
			page--;
	}

	public void nextPage() throws ClassNotFoundException, SQLException {
		int count = notice.getCount(); // 전체 notice 게시물의 개수
		int lastPage = (count%10==0) ? count/10 : count/10+1 ; // notice 게시판의 페이지 개수
		// count 와 lastPage 를 멤버 변수가 아닌 print 메서드에서 지역적으로 선언하였기 때문에 다시 한번 호출하여 사용하는 것이 필요함 
		if(page==lastPage) {
			System.out.println();
			System.out.println("=======================");
			System.out.println("현재 마지막 페이지입니다.");
			System.out.println("=======================");
			System.out.println();
		}
		else
			page++;
	}

	public void inputSearchWord() { 
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("검색 범주(title/content/writerId)중 하나를 입력하세요. >>  ");
		searchField = scan.nextLine(); // 검색 키워드를 사용해서 결국 데이터베이스에서 리스트를 받아오기 위해서는 print 메서드를 사용해야하기 때문에 멤버 변수로 설정을 해준다.
		System.out.print("검색어 >>  ");
		searchWord = scan.nextLine();
	}
}
