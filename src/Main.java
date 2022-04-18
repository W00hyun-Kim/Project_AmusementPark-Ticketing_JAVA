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
					input.selectTicket(); 					// 이용권 종류 선택하기
					input.selectDayNightTicket(); 			// 주간,야간권 선택하기
					
					input.inputID(); 						// 주민등록번호(13자리)입력받기
										
					int age = calc.ageCal(input.num); 		// 주민번호로 나이 추출하기										
					int agegroup = calc.calAgeGroup(age); 	// 나이그룹(노인,청소년....)추출하기
					System.out.println();
					
					input.ticketAmount();					//티켓의 개수
					
					if (input.ticketType == 1) {
						input.selectPreference_COMP();	 	// 종합 할인가능한 우대조건
					} else if (input.ticketType == 2) {
						input.selectPreference_PARK(); 		// 파크권 구매 시 할인 가능한 우대조건
					}
										
					price = calc.calcPriceProcess(input.ticketType, input.ticketDayType, agegroup);
//					System.out.println("discount전:"+price);
					price = calc.calDiscount(price, input.preferenceType);
//					System.out.println("discount후:"+price);
					

					resultPrice = price * (input.amount); 
//					System.out.println("resultPrice = "+resultPrice);
					totalPrice = totalPrice + resultPrice;

					save.saveArray(input.ticketType, input.ticketDayType, agegroup, input.amount, resultPrice, input.preferenceType);
					if (save.isExit == 2) {
						System.out.println("발권을 종료합니다. 감사합니다.\n");
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

//입력 구간
class InputSelect {
	public static int ticketType, ticketDayType, intNum, amount, preferenceType;
	public static String num;
	
	void selectTicket() {
		do {	
				System.out.println("이용권 타입을 선택하세요.");
				System.out.println("1. 종합이용권");
				System.out.println("2. 파크이용권\n>> ");
				Scanner sc = new Scanner(System.in);
				ticketType = sc.nextInt();
				
				if (ticketType>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(ticketType>2);
	}
	
	void selectDayNightTicket() {
		do {
				System.out.println("권종을 선택하세요.");
				System.out.println("1. 주간권(1Day)");
				System.out.println("2. 야간권(After4)\n>> ");
				Scanner sc = new Scanner(System.in);
				ticketDayType = sc.nextInt();
								
				if (ticketDayType>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(ticketDayType>2);
	}
	
	void inputID() {
		do {
				System.out.println("주민번호를 입력하세요.\n>> ");
				Scanner sc = new Scanner(System.in);
				num = sc.next();
				if (num.length()!=13) {
					System.out.println("양식에 맞춰 입력해주세요. (13자리)");
				} 
		} while(num.length()!=13);	
	}	
	
	void ticketAmount() {
		do {
				System.out.println("티켓을 몇 장 구매하시겠습니까?(최대 10개)\n>> ");
				Scanner sc = new Scanner(System.in);
				amount = sc.nextInt();	
				System.out.println();		
				
				if (amount>10) {
					System.out.println("최대 10장까지 구매할 수 있습니다.\n");
				} 
		} while(amount>10);
	}
	
	void selectPreference_COMP() {
		do {
				System.out.println("우대사항을 선택하세요.");
				System.out.println("1. 없음(나이 우대는 자동 처리))");
				System.out.println("2. 장애인"); 
				System.out.println("3. 국가유공자"); 
				System.out.println("4. 휴가장병"); 
				System.out.println("5. 임산부"); 
				System.out.println("6. 다둥이행복카드 소지자\n>>"); 
				Scanner sc = new Scanner(System.in);
				preferenceType = sc.nextInt();
				System.out.println();
				
				if (preferenceType>6) {
					System.out.println("보기에서 선택해주세요.\n");			
				} 
		} while(preferenceType>6);	
	}

	void selectPreference_PARK() {
		do {
				System.out.println("우대사항을 선택하세요.");
				System.out.println("1. 없음(나이 우대는 자동 처리))");
				System.out.println("2. 장애인"); 
				System.out.println("3. 국가유공자"); 
				System.out.println("4. 휴가장병\n>>"); 
				Scanner sc = new Scanner(System.in);
				preferenceType = sc.nextInt();
				System.out.println();
				
				if (preferenceType>4) {
					System.out.println("보기에서 선택해주세요.\n");			
				} 
		} while(preferenceType>4);	
	}
}


//계산 구간
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
		if(preferenceType==1) {	//없음 
			price = price;
		} else if(preferenceType==2) {	//장애인 
			price = (int) (price*DISABLE_DISCOUNT_RATE);
		} else if(preferenceType==3) {	//국가유공자 
			price =  (int) (price*MERIT_DISCOUNT_RATE);
		} else if(preferenceType==4) {	//다자녀
			price =  (int) (price*MULTICHILD_DISCOUNT_RATE);						
		} else if(preferenceType==5) {	//임산부 
			price = (int) (price*PREGNANT_DISCOUNT_RATE);
		} else if(preferenceType==6) {	//휴가장병 
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


//저장 구간
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
		
		System.out.printf("가격은 %d원 입니다.\n",resultPrice);
		System.out.println("감사합니다.\n");
						
		System.out.println("계속 발권 하시겠습니까?");		
		System.out.println("1. 티켓발권");
		System.out.println("2. 종료\n>> ");
		Scanner sc = new Scanner(System.in);
		isExit = sc.nextInt();
		System.out.println();		
	}
}

//프린트 구간
class Print {
	public static Save save = new Save();	
	void printBill(int totalPrice) {
		System.out.println("==============================================================\n");	
		System.out.println("================== L O T T E  W O R L D ======================\n");
		System.out.println("==============================================================\n");
		System.out.println("  이 용 권  |  권 종   |  조 건  |  개 수  |  가 격  |   할 인 적 용   \n");
		System.out.println("--------------------------------------------------------------\n");
		
		for(int index=0; index < save.orderCount; index ++) {	
			System.out.printf(" %5s ",(save.orderList[index][0]==1)?"종합이용권":"파크이용권");					
			System.out.printf(" %5s ",(save.orderList[index][1]==1)?"주간권":"야간권");					
			System.out.printf("%7s ",agegroupConverter(save.orderList[index][2]));		
			System.out.printf("  %5d개 ", save.orderList[index][3]);		
			System.out.printf("%9d원 ",save.orderList[index][4]);		
			System.out.printf(" %10s\n",preferenceTypeConverter(save.orderList[index][5]));
		}
		
		System.out.println("\n");
		System.out.printf("입장료 총액은 %d원입니다. \n\n", totalPrice);	
		totalPrice = 0;	
		System.out.println("-----------------------------------------------------------------\n");
		System.out.println("계속 진행(1: 새로운 주문, 2: 프로그램 종료)\n>> ");
		
		Scanner sc= new Scanner(System.in);
		save.newOrderCount = sc.nextInt();	
		
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

