package ticketing;

import java.util.Calendar;

//계산 구간
class Cal {
int calcPriceProcess(int ticketType, int ticketDayType, int agegroup) {
	//group1=baby, 2=kids, 3=teen, 4=old, 5=adult
	int priceBeforeDC = 0;
	int compDayPrice[] = {StaticValue.BABY_ALL_DAY_PRICE,StaticValue.CHILD_ALL_DAY_PRICE,StaticValue.TEEN_ALL_DAY_PRICE,StaticValue.CHILD_ALL_DAY_PRICE,StaticValue.ADULT_ALL_DAY_PRICE}; 
	int compNightPrice[] = {StaticValue.BABY_ALL_AFTER4_PRICE,StaticValue.CHILD_ALL_AFTER4_PRICE,StaticValue.TEEN_ALL_AFTER4_PRICE,StaticValue.CHILD_ALL_AFTER4_PRICE,StaticValue.ADULT_ALL_AFTER4_PRICE};
	int parkDayPrice[]={StaticValue.BABY_PARK_DAY_PRICE,StaticValue.CHILD_PARK_DAY_PRICE,StaticValue.TEEN_PARK_DAY_PRICE,StaticValue.CHILD_PARK_DAY_PRICE,StaticValue.ADULT_PARK_DAY_PRICE};
	int parkNightPrice[]={StaticValue.BABY_PARK_AFTER4_PRICE,StaticValue.CHILD_PARK_AFTER4_PRICE,StaticValue.TEEN_PARK_AFTER4_PRICE,StaticValue.CHILD_PARK_AFTER4_PRICE,StaticValue.ADULT_PARK_AFTER4_PRICE};

	if(ticketType==1) {
		switch(ticketDayType) {
			case 1 : 
				priceBeforeDC = compDayPrice[agegroup-1];
				break;
			
			case 2 : {
				priceBeforeDC = compNightPrice[agegroup-1];
				break;
			}
		}
	}	
	if(ticketType==2) {
		switch(ticketDayType) {
			case 1 : {
				priceBeforeDC = parkDayPrice[agegroup-1];
				break;
			}
			case 2 : {
				priceBeforeDC = compNightPrice[agegroup-1];
				break;
			}
		}
	}
	return priceBeforeDC;
}

int calDiscount(int price, int preferenceType) {
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
	if (age < StaticValue.MAX_BABY) {
		group = 1;
	} else if (age >= StaticValue.MIN_KIDS && age <= StaticValue.MAX_KIDS) {
		group = 2;
	} else if (age >= StaticValue.MIN_TEEN && age <= StaticValue.MAX_TEEN) {
		group = 3;
	} else if (age >= StaticValue.MIN_OLD) {
		group = 4;
	} else if (age >= StaticValue.MIN_ADULT && age <= StaticValue.MAX_ADULT) {
		group = 5;
	}
	return group;
}

}
