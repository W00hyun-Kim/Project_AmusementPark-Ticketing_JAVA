package ticketing;

import java.util.ArrayList;
import java.util.Scanner;

//프린트 구간
class Printing {
	int newOrderCount=1;
	public static Save save = new Save();
	void printBill(int totalPrice) {
		System.out.println("==============================================================");	
		System.out.println("================== L O T T E  W O R L D ======================");
		System.out.println("==============================================================");
		System.out.println("  이 용 권  |  권 종   |  조 건  |  개 수  |  가 격  |   할 인 적 용   ");
		System.out.println("--------------------------------------------------------------");
		
//		System.out.println(Main.orderList.get(0).ticketType);
//		System.out.println(Main.orderList.get(1).ticketType);
		
		
		for(int index=0; index < Ticketing.orderList.size(); index ++) {	
			System.out.printf(" %5s ",(Ticketing.orderList.get(index).ticketType==1)?"종합이용권":"파크이용권");					
			System.out.printf(" %5s ",(Ticketing.orderList.get(index).ticketDayType==1)?"주간권":"야간권");					
			System.out.printf("%7s ",agegroupConverter(Ticketing.orderList.get(index).agegroup));		
			System.out.printf("  %5d개 ", Ticketing.orderList.get(index).amount);		
			System.out.printf("%9d원 ",Ticketing.orderList.get(index).resultPrice);		
			System.out.printf(" %10s\n",preferenceTypeConverter(Ticketing.orderList.get(index).preferenceType));
		}
		System.out.println("==============================================================");			
		System.out.println("\n");
		System.out.printf("입장료 총액은 %d원입니다. \n\n", totalPrice);	
		
		
		totalPrice = 0;	
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("계속 진행(1: 새로운 주문, 2: 프로그램 종료)\n>> ");
		
		Scanner sc= new Scanner(System.in);
		newOrderCount = sc.nextInt();	
		
		System.out.println();
	}
	
	String agegroupConverter(int agegroup) {
		String age[] = {"유아","어린이","청소년","노인","성인"};	
		return age[agegroup-1];
	}
	
	String preferenceTypeConverter(int preferenceType) {
		String prefer[] = {"우대적용 없음","장애인 우대적용","국가유공자 우대적용","휴가장병 우대적용","임산부 우대적용","다자녀 우대적용"};	
		return prefer[preferenceType-1];
	}
	
}
