package ticketing;

import java.util.Scanner;

//저장 구간
class Save {
	Ticketing ticketing = new Ticketing();
	
	void saveArray(int ticketType, int ticketDayType, int agegroup, int amount, int resultPrice, int preferenceType) {
 					
		System.out.printf("가격은 %d원 입니다.\n",resultPrice);
		System.out.println("감사합니다.\n");
		
		ticketing.orderList.add(ticketing.orderItem);
		System.out.println("계속 발권 하시겠습니까?");		
		System.out.println("1. 티켓발권");
		System.out.println("2. 종료\n>> ");
		Scanner sc = new Scanner(System.in);
		ticketing.orderItem.isExit = sc.nextInt();
		System.out.println();		
	}
}

