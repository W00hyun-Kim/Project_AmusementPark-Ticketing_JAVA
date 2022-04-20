package Ticketing;

import java.util.Calendar;

public class Calculate {
	
	//When you input IDnum(String) -> return age(int)
	public int CalAge(String num) {
		Calendar today = Calendar.getInstance();
		int yearToday = Calendar.getInstance().get(Calendar.YEAR);
		int monthToday = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int dateToday = Calendar.getInstance().get(Calendar.DATE);

		int yearIndex, year, month, day, man;
		int NEW_GENERATION = 2000;
		int OLD_GENERATION = 1900;

		yearIndex = Integer.parseInt(num.substring(0, 2));
		month = Integer.parseInt(num.substring(2, 4));
		day = Integer.parseInt(num.substring(4, 6));

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
	
	//Age group : 0=baby, 1=kids, 2=Teen, 3=Old, 4=Adult
	public int calAgeGroup(int age) {
		int group = 0;	//Baby
		if (age < StaticValue.MAX_BABY) {
			group = 1;	//Kids
		} else if (age >= StaticValue.MIN_KIDS && age <= StaticValue.MAX_KIDS) {
			group = 2;	//Teen
		} else if (age >= StaticValue.MIN_TEEN && age <= StaticValue.MAX_TEEN) {
			group = 3;	//Old
		} else if (age >= StaticValue.MIN_OLD) {
			group = 4;	//Adult
		} else if (age >= StaticValue.MIN_ADULT && age <= StaticValue.MAX_ADULT) {
			group = 5;
		}
		return group;
	}	
	
	public int calcTicketPrice(OrderData orderItem) {
		//group1=baby, 2=kids, 3=teen, 4=old, 5=adult
		int price = 0;
		int compDayPrice[] = {StaticValue.BABY_ALL_DAY_PRICE,StaticValue.CHILD_ALL_DAY_PRICE,StaticValue.TEEN_ALL_DAY_PRICE,StaticValue.CHILD_ALL_DAY_PRICE,StaticValue.ADULT_ALL_DAY_PRICE}; 
		int compNightPrice[] = {StaticValue.BABY_ALL_AFTER4_PRICE,StaticValue.CHILD_ALL_AFTER4_PRICE,StaticValue.TEEN_ALL_AFTER4_PRICE,StaticValue.CHILD_ALL_AFTER4_PRICE,StaticValue.ADULT_ALL_AFTER4_PRICE};
		int parkDayPrice[]={StaticValue.BABY_PARK_DAY_PRICE,StaticValue.CHILD_PARK_DAY_PRICE,StaticValue.TEEN_PARK_DAY_PRICE,StaticValue.CHILD_PARK_DAY_PRICE,StaticValue.ADULT_PARK_DAY_PRICE};
		int parkNightPrice[]={StaticValue.BABY_PARK_AFTER4_PRICE,StaticValue.CHILD_PARK_AFTER4_PRICE,StaticValue.TEEN_PARK_AFTER4_PRICE,StaticValue.CHILD_PARK_AFTER4_PRICE,StaticValue.ADULT_PARK_AFTER4_PRICE};

		if(orderItem.getTicketType()==1) {
			switch(orderItem.getTicketDayType()) {
				case 1 : 
					price = compDayPrice[orderItem.getAgegroup()-1];
					break;				
				case 2 : {
					price = compNightPrice[orderItem.getAgegroup()-1];
					break;
				}
			}
		}	
		if(orderItem.getTicketType()==2) {
			switch(orderItem.getTicketDayType()) {
				case 1 : {
					price = parkDayPrice[orderItem.getAgegroup()-1];
					break;
				}
				case 2 : {
					price = parkNightPrice[orderItem.getAgegroup()-1];
					break;
				}
			}
		}
		return price;
	}
	
	public int calDiscount(int price, int preferenceType) {
		if(preferenceType==1) {	//없음 
			price = price;
		} else if(preferenceType==2) {	//장애인 
			price = (int) (price*StaticValue.DISABLE_DISCOUNT_RATE);
		} else if(preferenceType==3) {	//국가유공자 
			price =  (int) (price*StaticValue.MERIT_DISCOUNT_RATE);
		} else if(preferenceType==4) {	//다자녀
			price =  (int) (price*StaticValue.MULTICHILD_DISCOUNT_RATE);						
		} else if(preferenceType==5) {	//임산부 
			price = (int) (price*StaticValue.PREGNANT_DISCOUNT_RATE);
		} else if(preferenceType==6) {	//휴가장병 
			price = (int) (price*StaticValue.SOLDIER_DISCOUNT_RATE); 
		}							
		return price;
	}
	
	
	//main에서 반복루프돌던 함수 
//	public int loop() { 
//		Input inputData = new Input();
//		Print printUi = new Print();
//		Calculate calc = new Calculate();
//		
//		int totalSum = 0;
//		while(true) {
//			//Make
//			OrderData orderItem = new OrderData();
//			
//			orderItem.setTicketType(inputData.selectTicket());
//			orderItem.setTicketDayType(inputData.selectDayNightTicket());
//			orderItem.setNum(inputData.inputID());
//			orderItem.setAmount(inputData.ticketAmount());
//			if(orderItem.getTicketType()==1) { 
//				orderItem.setPreferenceType(inputData.selectPreference_COMP());
//			} else {
//				orderItem.setPreferenceType(inputData.selectPreference_PARK());
//			}
//			orderItem.setAge(calc.CalAge(orderItem.getNum()));
//			orderItem.setAgegroup(calc.calAgeGroup(orderItem.getAge()));
//			//Price before discount
//			int price = calc.calcTicketPrice(orderItem);	
//			//set the discount Price
//			orderItem.setPrice(calc.calDiscount(price, orderItem.getPreferenceType()));	
//			totalSum += orderItem.getPrice()*orderItem.getAmount();
//			//TotalSum = discount price * amount
//			orderItem.setTotalSum(totalSum);
//			
//			int tmp = printUi.printRepeat();
//			Input.orderList.add(orderItem);
//			if(tmp==2) break;			
//		}
//		printUi.printTicket(totalSum);				
//		return printUi.inputEnd();		
//	}
}