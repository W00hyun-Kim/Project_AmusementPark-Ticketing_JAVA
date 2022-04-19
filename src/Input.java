package ticketing;

import java.util.Scanner;

//입력 구간
public class Input {
	Save save = new Save();
	Scanner sc = new Scanner(System.in);
	Ticketing ticketing = new Ticketing();
	
	void selectTicket() {
		do {	
				System.out.println("이용권 타입을 선택하세요.");
				System.out.println("1. 종합이용권");
				System.out.println("2. 파크이용권\n>> ");
				ticketing.orderItem.ticketType = sc.nextInt();
				
				if (ticketing.orderItem.ticketType>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(ticketing.orderItem.ticketType>2);
	}
	
	void selectDayNightTicket() {
		do {
				System.out.println("권종을 선택하세요.");
				System.out.println("1. 주간권(1Day)");
				System.out.println("2. 야간권(After4)\n>> ");
				ticketing.orderItem.ticketDayType = sc.nextInt();
								
				if (ticketing.orderItem.ticketDayType>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(ticketing.orderItem.ticketDayType>2);
	}
	
	void inputID() {
		do {
				System.out.println("주민번호를 입력하세요.\n>> ");
				ticketing.orderItem.num = sc.next();
				if (ticketing.orderItem.num.length()!=13) {
					System.out.println("양식에 맞춰 입력해주세요. (13자리)");
				} 
		} while(ticketing.orderItem.num.length()!=13);	
	}	
	
	void ticketAmount() {
		do {
				System.out.println("티켓을 몇 장 구매하시겠습니까?(최대 10개)\n>> ");
				ticketing.orderItem.amount = sc.nextInt();	
				System.out.println();		
				
				if (ticketing.orderItem.amount>10) {
					System.out.println("최대 10장까지 구매할 수 있습니다.\n");
				} 
		} while(ticketing.orderItem.amount>10);
	}
	
	void selectPreference_COMP() {
		do {
				System.out.println("우대사항을 선택하세요.");
				System.out.println("1. 없음(나이 우대는 자동 처리))");
				System.out.println("2. 장애인"); 
				System.out.println("3. 국가유공자"); 
				System.out.println("4. 휴가장병"); 
				System.out.println("5. 임산부"); 
				System.out.println("6. 다둥이행복카드 소지자\n>>"); 
				ticketing.orderItem.preferenceType = sc.nextInt();
				System.out.println();
				
				if (ticketing.orderItem.preferenceType>6) {
					System.out.println("보기에서 선택해주세요.\n");			
				} 
		} while(ticketing.orderItem.preferenceType>6);	
	}

	void selectPreference_PARK() {
		do {
				System.out.println("우대사항을 선택하세요.");
				System.out.println("1. 없음(나이 우대는 자동 처리))");
				System.out.println("2. 장애인"); 
				System.out.println("3. 국가유공자"); 
				System.out.println("4. 휴가장병\n>>"); 
				ticketing.orderItem.preferenceType = sc.nextInt();
				System.out.println();
				
				if (ticketing.orderItem.preferenceType>4) {
					System.out.println("보기에서 선택해주세요.\n");			
				} 
		} while(ticketing.orderItem.preferenceType>4);	
	}
}