package ticketing;

import java.util.*;


public class Ticketing {
	public static ArrayList<OrderData> orderList = new ArrayList<OrderData>();
	public static Input input = new Input();
	public static Cal calc = new Cal();
	public static Save save = new Save();
	public static Printing print = new Printing();
	public OrderData orderItem = null;
	

	public static void main(String[] args) {
		OrderData orderItem;
		
		while (print.newOrderCount == 1) {
			System.out.println();
			System.out.println("===================== WELCOME TO LOTTE WORLD =====================\n");
//			save.orderCount = 0;
			do {
				while (true) {					
					
					orderItem = new OrderData();
					
					input.selectTicket(); 					// 이용권 종류 선택하기
					input.selectDayNightTicket(); 			// 주간,야간권 선택하기
					
					input.inputID(); 						// 주민등록번호(13자리)입력받기
										
					int age = calc.ageCal(orderItem.num); 		// 주민번호로 나이 추출하기										
					orderItem.agegroup = calc.calAgeGroup(age); 	// 나이그룹(노인,청소년....)추출하기
					System.out.println();
					
					input.ticketAmount();					//티켓의 개수
					
					if (orderItem.ticketType == 1) {
						input.selectPreference_COMP();	 	// 종합 할인가능한 우대조건
					} else if (orderItem.ticketType == 2) {
						input.selectPreference_PARK(); 		// 파크권 구매 시 할인 가능한 우대조건
					}
										
					orderItem.price = calc.calcPriceProcess(orderItem.ticketType, orderItem.ticketDayType, orderItem.agegroup);
//					System.out.println("discount전:"+price);
					orderItem.price = calc.calDiscount(orderItem.price, orderItem.preferenceType);
//					System.out.println("discount후:"+price);
					

					orderItem.resultPrice = orderItem.price * (orderItem.amount); 
//					System.out.println("resultPrice = "+resultPrice);
					orderItem.totalPrice = orderItem.totalPrice + orderItem.resultPrice;

					
					//저장
					save.saveArray(orderItem.ticketType, orderItem.ticketDayType, orderItem.agegroup, orderItem.amount, orderItem.resultPrice, orderItem.preferenceType);
					
//					for (int i = 0; i < orderList.get(i).ticketType; i++) {
//						System.out.printf("%d번째:",i+1,orderList.get(i).ticketType);
//
//					}

					
					if (orderItem.isExit == 2) {
						System.out.println("발권을 종료합니다. 감사합니다.\n");
						break;
					}
				}
			} while (orderItem.isExit == 1);

			print.printBill(orderItem.totalPrice);

			
			if (print.newOrderCount == 2) {				
				break;
			} else if(print.newOrderCount==1) {
				orderItem.totalPrice = 0;
			}
		}
	}
}



