package com.sejin.app.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.sejin.app.entity.Notice;
import com.sejin.app.service.NoticeService2;

//NoticeConsole을 클래스로 만들면 이 콘솔 UI 클래스를 언제든지 재사용 할 수 있다.

public class NoticeConsole {
	
	NoticeService2 notice; // 서비스 클래스를 맴버 변수로 사용 
	
	public NoticeConsole() {
		notice = new NoticeService2();
	}
	
	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list = notice.getList();
		System.out.println("───────────────────────────────────────────");
		System.out.printf("<공지사항> 총 %d 게시글%n",list.size());
		System.out.println("───────────────────────────────────────────");
		
		for(Notice n : list)
			System.out.printf("%d. %s / %s / %s%n",n.getId(),n.getTitle(),n.getWriterId(),n.getRegDate());
		
		System.out.println("───────────────────────────────────────────");
		System.out.printf("%d/%d page%n",1,3);
		System.out.println();
	}
	
	public int inputNoticeMenu(){
		Scanner scan = new Scanner(System.in);
		System.out.print("1.상세조회 / 2.이전 / 3.다음 / 4.글쓰기 / 5.종료 >> ");
		String menu_ = scan.next();
		int menu = Integer.parseInt(menu_);
		return menu;
		
	}
}
