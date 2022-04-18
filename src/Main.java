package ticketing;

import java.util.*;

public class Main {
	public static InputSelect input = new InputSelect();
	public static Calc calc = new Calc();
	public static Save save = new Save();
	public static Print print = new Print();

	public static int orderList[][] = new int[100][6];
	public static int ticketType,ticketDayType,preferenceType,amount,price,resultPrice;
	public static int totalPrice = 0;
	public static int position = 0;

	public static void main(String[] args) {

		while (save.newOrderCount == 1) {
			System.out.println();
			System.out.println("===================== WELCOME TO LOTTE WORLD =====================\n");
			save.orderCount = 0;
			do {
				while (true) {
					input.selectTicket(); 					// �̿�� ���� �����ϱ�
					input.selectDayNightTicket(); 			// �ְ�,�߰��� �����ϱ�
					
					input.inputID(); 						// �ֹε�Ϲ�ȣ(13�ڸ�)�Է¹ޱ�
										
					int age = calc.ageCal(input.num); 		// �ֹι�ȣ�� ���� �����ϱ�										
					int agegroup = calc.calAgeGroup(age); 	// ���̱׷�(����,û�ҳ�....)�����ϱ�
					System.out.println();
					
					input.ticketAmount();					//Ƽ���� ����
					
					if (input.ticketType == 1) {
						input.selectPreference_COMP();	 	// ���� ���ΰ����� �������
					} else if (input.ticketType == 2) {
						input.selectPreference_PARK(); 		// ��ũ�� ���� �� ���� ������ �������
					}
										
					price = calc.calcPriceProcess(input.ticketType, input.ticketDayType, agegroup);
//					System.out.println("discount��:"+price);
					price = calc.calDiscount(price, input.preferenceType);
//					System.out.println("discount��:"+price);
					

					resultPrice = price * (input.amount); 
//					System.out.println("resultPrice = "+resultPrice);
					totalPrice = totalPrice + resultPrice;

					save.saveArray(input.ticketType, input.ticketDayType, agegroup, input.amount, resultPrice, input.preferenceType);
					if (save.isExit == 2) {
						System.out.println("�߱��� �����մϴ�. �����մϴ�.\n");
						break;
					}
				}
			} while (save.isExit == 1);

			print.printBill(totalPrice);
			if (save.newOrderCount == 2) {
				break;
			} else if(save.newOrderCount==1) {
				totalPrice = 0;
			}
		}

	}

}

//�Է� ����
class InputSelect {
	public static int ticketType, ticketDayType, intNum, amount, preferenceType;
	public static String num;
	
	void selectTicket() {
		do {	
				System.out.println("�̿�� Ÿ���� �����ϼ���.");
				System.out.println("1. �����̿��");
				System.out.println("2. ��ũ�̿��\n>> ");
				Scanner sc = new Scanner(System.in);
				ticketType = sc.nextInt();
				
				if (ticketType>2) {
					System.out.println("���⿡�� �������ּ���.\n");
				} 
		} while(ticketType>2);
	}
	
	void selectDayNightTicket() {
		do {
				System.out.println("������ �����ϼ���.");
				System.out.println("1. �ְ���(1Day)");
				System.out.println("2. �߰���(After4)\n>> ");
				Scanner sc = new Scanner(System.in);
				ticketDayType = sc.nextInt();
								
				if (ticketDayType>2) {
					System.out.println("���⿡�� �������ּ���.\n");
				} 
		} while(ticketDayType>2);
	}
	
	void inputID() {
		do {
				System.out.println("�ֹι�ȣ�� �Է��ϼ���.\n>> ");
				Scanner sc = new Scanner(System.in);
				num = sc.next();
				if (num.length()!=13) {
					System.out.println("��Ŀ� ���� �Է����ּ���. (13�ڸ�)");
				} 
		} while(num.length()!=13);	
	}	
	
	void ticketAmount() {
		do {
				System.out.println("Ƽ���� �� �� �����Ͻðڽ��ϱ�?(�ִ� 10��)\n>> ");
				Scanner sc = new Scanner(System.in);
				amount = sc.nextInt();	
				System.out.println();		
				
				if (amount>10) {
					System.out.println("�ִ� 10����� ������ �� �ֽ��ϴ�.\n");
				} 
		} while(amount>10);
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
				Scanner sc = new Scanner(System.in);
				preferenceType = sc.nextInt();
				System.out.println();
				
				if (preferenceType>6) {
					System.out.println("���⿡�� �������ּ���.\n");			
				} 
		} while(preferenceType>6);	
	}

	void selectPreference_PARK() {
		do {
				System.out.println("�������� �����ϼ���.");
				System.out.println("1. ����(���� ���� �ڵ� ó��))");
				System.out.println("2. �����"); 
				System.out.println("3. ����������"); 
				System.out.println("4. �ް��庴\n>>"); 
				Scanner sc = new Scanner(System.in);
				preferenceType = sc.nextInt();
				System.out.println();
				
				if (preferenceType>4) {
					System.out.println("���⿡�� �������ּ���.\n");			
				} 
		} while(preferenceType>4);	
	}
}


//��� ����
class Calc {
	public static final double DISABLE_DISCOUNT_RATE = 0.5, SOLDIER_DISCOUNT_RATE = 0.51,
			MULTICHILD_DISCOUNT_RATE = 0.7, MERIT_DISCOUNT_RATE = 0.5,
			PREGNANT_DISCOUNT_RATE = 0.5;
	public static final int MAX_BABY = 2, MIN_KIDS = 3, MIN_TEEN = 13, MIN_ADULT = 19, MAX_KIDS = 12, MAX_TEEN = 18,
			MAX_ADULT = 64, MIN_OLD = 65;
	
	int calcPriceProcess(int ticketType, int ticketDayType, int agegroup) {
		//group1=baby, 2=kids, 3=teen, 4=old, 5=adult
		int price = 0;
		int compDayPrice[] = {15000,47000,54000,47000,62000}; 
		int compNightPrice[] = {15000,36000,43000,36000,50000};
		int parkDayPrice[]={15000,46000,52000,46000,59000};
		int parkNightPrice[]={15000,35000,41000,35000,47000};

		if(ticketType==1) {
			switch(ticketDayType) {
				case 1 : 
					price = compDayPrice[agegroup-1];
					break;
				
				case 2 : {
					price = compNightPrice[agegroup-1];
					break;
				}
			}
		}	
		if(ticketType==2) {
			switch(ticketDayType) {
				case 1 : {
					price = parkDayPrice[agegroup-1];
					break;
				}
				case 2 : {
					price = compNightPrice[agegroup-1];
					break;
				}
			}
		}
		return price;
	}
	
	int calDiscount(int price, int preferenceType) {
		if(preferenceType==1) {	//���� 
			price = price;
		} else if(preferenceType==2) {	//����� 
			price = (int) (price*DISABLE_DISCOUNT_RATE);
		} else if(preferenceType==3) {	//���������� 
			price =  (int) (price*MERIT_DISCOUNT_RATE);
		} else if(preferenceType==4) {	//���ڳ�
			price =  (int) (price*MULTICHILD_DISCOUNT_RATE);						
		} else if(preferenceType==5) {	//�ӻ�� 
			price = (int) (price*PREGNANT_DISCOUNT_RATE);
		} else if(preferenceType==6) {	//�ް��庴 
			price = (int) (price*SOLDIER_DISCOUNT_RATE); 
		}
							
		return price;
	}
	
	public static int ageCal(String idNumber) {
		Calendar today = Calendar.getInstance();
		int yearToday = Calendar.getInstance().get(Calendar.YEAR);
		int monthToday = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int dateToday = Calendar.getInstance().get(Calendar.DATE);

		int yearIndex, year, month, day, man;
		int NEW_GENERATION = 2000;
		int OLD_GENERATION = 1900;

		yearIndex = Integer.parseInt(idNumber.substring(0, 2));
		month = Integer.parseInt(idNumber.substring(2, 4));
		day = Integer.parseInt(idNumber.substring(4, 6));

		if (yearIndex <= 22) {
			year = NEW_GENERATION + yearIndex;
		} else {
			year = OLD_GENERATION + yearIndex;
		}

		int age = yearToday - year + 1;

		if (month > monthToday) {
			man = age - 2;
		} else if (month == monthToday) {
			if (day > dateToday) {
				man = age - 2;
			} else {
				man = age - 1;
			}
		} else {
			man = age - 1;
		}
		return man;
	}
	
	public static int calAgeGroup(int age) {
		int group = 0;
		if (age < MAX_BABY) {
			group = 1;
		} else if (age >= MIN_KIDS && age <= MAX_KIDS) {
			group = 2;
		} else if (age >= MIN_TEEN && age <= MAX_TEEN) {
			group = 3;
		} else if (age >= MIN_OLD) {
			group = 4;
		} else if (age >= MIN_ADULT && age <= MAX_ADULT) {
			group = 5;
		}
		return group;
	}

}


//���� ����
class Save {
	public static int orderList[][] = new int[100][6];
	public static int orderCount = 0, isExit=0;
	public static int newOrderCount = 1;
	void saveArray(int ticketType, int ticketDayType, int agegroup, int amount, int resultPrice, int preferenceType) {
		 
		orderList[orderCount][0] = ticketType;
		orderList[orderCount][1] = ticketDayType;
		orderList[orderCount][2] = agegroup;
		orderList[orderCount][3] = amount;
		orderList[orderCount][4] = resultPrice;
		orderList[orderCount][5] = preferenceType;
		orderCount++;
		
		System.out.printf("������ %d�� �Դϴ�.\n",resultPrice);
		System.out.println("�����մϴ�.\n");
						
		System.out.println("��� �߱� �Ͻðڽ��ϱ�?");		
		System.out.println("1. Ƽ�Ϲ߱�");
		System.out.println("2. ����\n>> ");
		Scanner sc = new Scanner(System.in);
		isExit = sc.nextInt();
		System.out.println();		
	}
}

//����Ʈ ����
class Print {
	public static Save save = new Save();	
	void printBill(int totalPrice) {
		System.out.println("==============================================================\n");	
		System.out.println("================== L O T T E  W O R L D ======================\n");
		System.out.println("==============================================================\n");
		System.out.println("  �� �� ��  |  �� ��   |  �� ��  |  �� ��  |  �� ��  |   �� �� �� ��   \n");
		System.out.println("--------------------------------------------------------------\n");
		
		for(int index=0; index < save.orderCount; index ++) {	
			System.out.printf(" %5s ",(save.orderList[index][0]==1)?"�����̿��":"��ũ�̿��");					
			System.out.printf(" %5s ",(save.orderList[index][1]==1)?"�ְ���":"�߰���");					
			System.out.printf("%7s ",agegroupConverter(save.orderList[index][2]));		
			System.out.printf("  %5d�� ", save.orderList[index][3]);		
			System.out.printf("%9d�� ",save.orderList[index][4]);		
			System.out.printf(" %10s\n",preferenceTypeConverter(save.orderList[index][5]));
		}
		
		System.out.println("\n");
		System.out.printf("����� �Ѿ��� %d���Դϴ�. \n\n", totalPrice);	
		totalPrice = 0;	
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����)\n>> ");
		
		Scanner sc= new Scanner(System.in);
		save.newOrderCount = sc.nextInt();	
		
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

