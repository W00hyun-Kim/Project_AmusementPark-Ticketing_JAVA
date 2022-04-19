package Ticketing;

import java.util.*;

public class Input {
	Scanner sc = new Scanner(System.in);
	
	public int selectTicket() {
		int input;
		do {	
				System.out.println("이용권 타입을 선택하세요.");
				System.out.println("1. 종합이용권");
				System.out.println("2. 파크이용권\n>> ");
				input = sc.nextInt();
				
				if (input>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(input>2);
		return input;
	}
	
	public int selectDayNightTicket() {
		int input;
		do {
				System.out.println("권종을 선택하세요.");
				System.out.println("1. 주간권(1Day)");
				System.out.println("2. 야간권(After4)\n>> ");
				input = sc.nextInt();
								
				if (input>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(input>2);
		return input;
	}
	
	public String inputID() {
		String input;
		do {
				System.out.println("주민번호를 입력하세요.\n>> ");
				input = sc.next();
				if (input.length()!=13) {
					System.out.println("양식에 맞춰 입력해주세요. (13자리)");
				} 
		} while(input.length()!=13);
		return input;
	}	
	
	public int ticketAmount() {
		int input;
		do {
				System.out.println("티켓을 몇 장 구매하시겠습니까?(최대 10개)\n>> ");
				input = sc.nextInt();	
				System.out.println();		
				
				if (input>10) {
					System.out.println("최대 10장까지 구매할 수 있습니다.\n");
				} 
		} while(input>10);
		return input;
	}
	
	public int selectPreference_COMP() {
		int input;
		do {
				System.out.println("우대사항을 선택하세요.");
				System.out.println("1. 없음(나이 우대는 자동 처리))");
				System.out.println("2. 장애인"); 
				System.out.println("3. 국가유공자"); 
				System.out.println("4. 휴가장병"); 
				System.out.println("5. 임산부"); 
				System.out.println("6. 다둥이행복카드 소지자\n>>"); 
				input = sc.nextInt();
				System.out.println();
				
				if (input>6) {
					System.out.println("보기에서 선택해주세요.\n");			
				} 
		} while(input>6);	
		return input;
	}
	
	public int selectPreference_PARK() {
		int input;
		do {
				System.out.println("우대사항을 선택하세요.");
				System.out.println("1. 없음(나이 우대는 자동 처리))");
				System.out.println("2. 장애인"); 
				System.out.println("3. 국가유공자"); 
				System.out.println("4. 휴가장병\n>>"); 
				input = sc.nextInt();
				System.out.println();
				
				if (input>4) {
					System.out.println("보기에서 선택해주세요.\n");			
				} 
		} while(input>4);
		return input;
	}
	
	
	//Main function
	public void ticketingSystem() {
		Calculate calc = new Calculate();
		

		int isExit = 0;
		
		do {
			calc.orderItem = new OrderData();		//공간 비워주기

			isExit = calc.loop();
			Ticketing.orderList = new ArrayList<>();
		} while(isExit==1); 	
		
	}
}
