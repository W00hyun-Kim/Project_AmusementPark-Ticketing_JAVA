package ticketing;

import java.util.ArrayList;
import java.util.Scanner;

//����Ʈ ����
class Printing {
	int newOrderCount=1;
	public static Save save = new Save();
	void printBill(int totalPrice) {
		System.out.println("==============================================================");	
		System.out.println("================== L O T T E  W O R L D ======================");
		System.out.println("==============================================================");
		System.out.println("  �� �� ��  |  �� ��   |  �� ��  |  �� ��  |  �� ��  |   �� �� �� ��   ");
		System.out.println("--------------------------------------------------------------");
		
//		System.out.println(Main.orderList.get(0).ticketType);
//		System.out.println(Main.orderList.get(1).ticketType);
		
		
		for(int index=0; index < Ticketing.orderList.size(); index ++) {	
			System.out.printf(" %5s ",(Ticketing.orderList.get(index).ticketType==1)?"�����̿��":"��ũ�̿��");					
			System.out.printf(" %5s ",(Ticketing.orderList.get(index).ticketDayType==1)?"�ְ���":"�߰���");					
			System.out.printf("%7s ",agegroupConverter(Ticketing.orderList.get(index).agegroup));		
			System.out.printf("  %5d�� ", Ticketing.orderList.get(index).amount);		
			System.out.printf("%9d�� ",Ticketing.orderList.get(index).resultPrice);		
			System.out.printf(" %10s\n",preferenceTypeConverter(Ticketing.orderList.get(index).preferenceType));
		}
		System.out.println("==============================================================");			
		System.out.println("\n");
		System.out.printf("����� �Ѿ��� %d���Դϴ�. \n\n", totalPrice);	
		
		
		totalPrice = 0;	
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����)\n>> ");
		
		Scanner sc= new Scanner(System.in);
		newOrderCount = sc.nextInt();	
		
		System.out.println();
	}
	
	String agegroupConverter(int agegroup) {
		String age[] = {"����","���","û�ҳ�","����","����"};	
		return age[agegroup-1];
	}
	
	String preferenceTypeConverter(int preferenceType) {
		String prefer[] = {"������� ����","����� �������","���������� �������","�ް��庴 �������","�ӻ�� �������","���ڳ� �������"};	
		return prefer[preferenceType-1];
	}
	
}
