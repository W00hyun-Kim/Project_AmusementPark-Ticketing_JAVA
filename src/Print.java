package Ticketing;
import java.util.*;

public class Print {
	Scanner sc = new Scanner(System.in);
	public void printTicket(int totalSum) {

		System.out.println("==============================================================");	
		System.out.println("================== L O T T E  W O R L D ======================");
		System.out.println("==============================================================");
		System.out.println("  이 용 권  |  권 종   |  조 건  |  개 수  |  가 격  |   할 인 적 용   ");
		System.out.println("--------------------------------------------------------------");
		
		
		for(int index=0; index < Ticketing.orderList.size(); index ++) {	
			System.out.printf(" %5s ",(Ticketing.orderList.get(index).getTicketType()==1)?"종합이용권":"파크이용권");					
			System.out.printf(" %5s ",(Ticketing.orderList.get(index).getTicketDayType()==1)?"주간권":"야간권");					
			System.out.printf("%7s ",agegroupConverter(Ticketing.orderList.get(index).getAgegroup()));		
			System.out.printf("  %5d개 ", Ticketing.orderList.get(index).getAmount());		
			System.out.printf("%9d원 ",Ticketing.orderList.get(index).getPrice());		
			System.out.printf(" %10s\n",preferenceTypeConverter(Ticketing.orderList.get(index).getPreferenceType()));
		}
		
		//ArrayList 초기화
		ArrayList<OrderData> orderList = new ArrayList<>();
		
		System.out.println("==============================================================");			
		System.out.printf("입장료 총액은 %d원입니다. \n\n", totalSum);	
		
	}

	
	public int printRepeat() {
		int input;
		do {	
				System.out.println("계속 발권하시겠습니까?");
				System.out.println("1. 티켓발권");
				System.out.println("2. 종료\n>> ");
				input = sc.nextInt();
				
				if (input>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(input>2);
		return input;			
	}	
	
	public int inputEnd() {
		int input;
		System.out.println("계속 진행(1: 새로운 주문, 2: 프로그램 종료)\n>>");
		input = sc.nextInt();
		return input;
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
