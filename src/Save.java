package ticketing;

import java.util.Scanner;

//���� ����
class Save {
	Ticketing ticketing = new Ticketing();
	
	void saveArray(int ticketType, int ticketDayType, int agegroup, int amount, int resultPrice, int preferenceType) {
 					
		System.out.printf("������ %d�� �Դϴ�.\n",resultPrice);
		System.out.println("�����մϴ�.\n");
		
		ticketing.orderList.add(ticketing.orderItem);
		System.out.println("��� �߱� �Ͻðڽ��ϱ�?");		
		System.out.println("1. Ƽ�Ϲ߱�");
		System.out.println("2. ����\n>> ");
		Scanner sc = new Scanner(System.in);
		ticketing.orderItem.isExit = sc.nextInt();
		System.out.println();		
	}
}

