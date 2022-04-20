package Ticketing;

import java.util.*;

public class Input {
	public static ArrayList<OrderData> orderList = new ArrayList<>();
	Scanner sc = new Scanner(System.in);
	
	public int selectTicket() {
		int input;
		do {	
				System.out.println("�̿�� Ÿ���� �����ϼ���.");
				System.out.println("1. �����̿��");
				System.out.print("2. ��ũ�̿��\n>> ");
				input = sc.nextInt();
				System.out.println();
				
				if (input>2) {
					System.out.println("���⿡�� �������ּ���.\n");
				} 
		} while(input>2);
		return input;
	}
	
	public int selectDayNightTicket() {
		int input;
		do {
				System.out.println("������ �����ϼ���.");
				System.out.println("1. �ְ���(1Day)");
				System.out.print("2. �߰���(After4)\n>> ");
				input = sc.nextInt();
				System.out.println();
								
				if (input>2) {
					System.out.println("���⿡�� �������ּ���.\n");
				} 
		} while(input>2);
		return input;
	}
	
	public String inputID() {
		String input;
		do {
				System.out.print("�ֹι�ȣ�� �Է��ϼ���.\n>> ");
				input = sc.next();
				System.out.println();
				if (input.length()!=13) {
					System.out.print("��Ŀ� ���� �Է����ּ���. (13�ڸ�)");
				} 
		} while(input.length()!=13);
		return input;
	}	
	
	public int ticketAmount() {
		int input;
		do {
				System.out.print("Ƽ���� �� �� �����Ͻðڽ��ϱ�?(�ִ� 10��)\n>> ");
				input = sc.nextInt();	
				System.out.println();		
				
				if (input>10) {
					System.out.println("�ִ� 10����� ������ �� �ֽ��ϴ�.\n");
				} 
		} while(input>10);
		return input;
	}
	
	public int selectPreference_COMP() {
		int input;
		do {
				System.out.println("�������� �����ϼ���.");
				System.out.println("1. ����(���� ���� �ڵ� ó��))");
				System.out.println("2. �����"); 
				System.out.println("3. ����������"); 
				System.out.println("4. �ް��庴"); 
				System.out.println("5. �ӻ��"); 
				System.out.print("6. �ٵ����ູī�� ������\n>>"); 
				input = sc.nextInt();
				System.out.println();
				
				if (input>6) {
					System.out.println("���⿡�� �������ּ���.\n");			
				} 
		} while(input>6);	
		return input;
	}
	
	public int selectPreference_PARK() {
		int input;
		do {
				System.out.println("�������� �����ϼ���.");
				System.out.println("1. ����(���� ���� �ڵ� ó��))");
				System.out.println("2. �����"); 
				System.out.println("3. ����������"); 
				System.out.print("4. �ް��庴\n>>"); 
				input = sc.nextInt();
				System.out.println();
				
				if (input>4) {
					System.out.println("���⿡�� �������ּ���.\n");			
				} 
		} while(input>4);
		return input;
	}
	
	
	//Main function
	public void ticketingSystem() {
		Calculate calc = new Calculate();
		
		int isExit = 0;		
		do {
			isExit = calc.loop();
			orderList = new ArrayList<>();
		} while(isExit==1); 			
	}
}