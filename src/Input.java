package ticketing;

import java.util.Scanner;

//�Է� ����
public class Input {
	Save save = new Save();
	Scanner sc = new Scanner(System.in);
	Ticketing ticketing = new Ticketing();
	
	void selectTicket() {
		do {	
				System.out.println("�̿�� Ÿ���� �����ϼ���.");
				System.out.println("1. �����̿��");
				System.out.println("2. ��ũ�̿��\n>> ");
				ticketing.orderItem.ticketType = sc.nextInt();
				
				if (ticketing.orderItem.ticketType>2) {
					System.out.println("���⿡�� �������ּ���.\n");
				} 
		} while(ticketing.orderItem.ticketType>2);
	}
	
	void selectDayNightTicket() {
		do {
				System.out.println("������ �����ϼ���.");
				System.out.println("1. �ְ���(1Day)");
				System.out.println("2. �߰���(After4)\n>> ");
				ticketing.orderItem.ticketDayType = sc.nextInt();
								
				if (ticketing.orderItem.ticketDayType>2) {
					System.out.println("���⿡�� �������ּ���.\n");
				} 
		} while(ticketing.orderItem.ticketDayType>2);
	}
	
	void inputID() {
		do {
				System.out.println("�ֹι�ȣ�� �Է��ϼ���.\n>> ");
				ticketing.orderItem.num = sc.next();
				if (ticketing.orderItem.num.length()!=13) {
					System.out.println("��Ŀ� ���� �Է����ּ���. (13�ڸ�)");
				} 
		} while(ticketing.orderItem.num.length()!=13);	
	}	
	
	void ticketAmount() {
		do {
				System.out.println("Ƽ���� �� �� �����Ͻðڽ��ϱ�?(�ִ� 10��)\n>> ");
				ticketing.orderItem.amount = sc.nextInt();	
				System.out.println();		
				
				if (ticketing.orderItem.amount>10) {
					System.out.println("�ִ� 10����� ������ �� �ֽ��ϴ�.\n");
				} 
		} while(ticketing.orderItem.amount>10);
	}
	
	void selectPreference_COMP() {
		do {
				System.out.println("�������� �����ϼ���.");
				System.out.println("1. ����(���� ���� �ڵ� ó��))");
				System.out.println("2. �����"); 
				System.out.println("3. ����������"); 
				System.out.println("4. �ް��庴"); 
				System.out.println("5. �ӻ��"); 
				System.out.println("6. �ٵ����ູī�� ������\n>>"); 
				ticketing.orderItem.preferenceType = sc.nextInt();
				System.out.println();
				
				if (ticketing.orderItem.preferenceType>6) {
					System.out.println("���⿡�� �������ּ���.\n");			
				} 
		} while(ticketing.orderItem.preferenceType>6);	
	}

	void selectPreference_PARK() {
		do {
				System.out.println("�������� �����ϼ���.");
				System.out.println("1. ����(���� ���� �ڵ� ó��))");
				System.out.println("2. �����"); 
				System.out.println("3. ����������"); 
				System.out.println("4. �ް��庴\n>>"); 
				ticketing.orderItem.preferenceType = sc.nextInt();
				System.out.println();
				
				if (ticketing.orderItem.preferenceType>4) {
					System.out.println("���⿡�� �������ּ���.\n");			
				} 
		} while(ticketing.orderItem.preferenceType>4);	
	}
}