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
					
					input.selectTicket(); 					// �̿�� ���� �����ϱ�
					input.selectDayNightTicket(); 			// �ְ�,�߰��� �����ϱ�
					
					input.inputID(); 						// �ֹε�Ϲ�ȣ(13�ڸ�)�Է¹ޱ�
										
					int age = calc.ageCal(orderItem.num); 		// �ֹι�ȣ�� ���� �����ϱ�										
					orderItem.agegroup = calc.calAgeGroup(age); 	// ���̱׷�(����,û�ҳ�....)�����ϱ�
					System.out.println();
					
					input.ticketAmount();					//Ƽ���� ����
					
					if (orderItem.ticketType == 1) {
						input.selectPreference_COMP();	 	// ���� ���ΰ����� �������
					} else if (orderItem.ticketType == 2) {
						input.selectPreference_PARK(); 		// ��ũ�� ���� �� ���� ������ �������
					}
										
					orderItem.price = calc.calcPriceProcess(orderItem.ticketType, orderItem.ticketDayType, orderItem.agegroup);
//					System.out.println("discount��:"+price);
					orderItem.price = calc.calDiscount(orderItem.price, orderItem.preferenceType);
//					System.out.println("discount��:"+price);
					

					orderItem.resultPrice = orderItem.price * (orderItem.amount); 
//					System.out.println("resultPrice = "+resultPrice);
					orderItem.totalPrice = orderItem.totalPrice + orderItem.resultPrice;

					
					//����
					save.saveArray(orderItem.ticketType, orderItem.ticketDayType, orderItem.agegroup, orderItem.amount, orderItem.resultPrice, orderItem.preferenceType);
					
//					for (int i = 0; i < orderList.get(i).ticketType; i++) {
//						System.out.printf("%d��°:",i+1,orderList.get(i).ticketType);
//
//					}

					
					if (orderItem.isExit == 2) {
						System.out.println("�߱��� �����մϴ�. �����մϴ�.\n");
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



