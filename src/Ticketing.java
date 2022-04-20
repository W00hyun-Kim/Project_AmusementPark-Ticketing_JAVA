package Ticketing;

import java.util.ArrayList;

public class Ticketing {		
	
	//Main function
	public void ticketingSystem() {
		Ticketing ticketing = new Ticketing();
		
		int isExit = 0;		
		do {
			isExit = ticketing.loop();
			Input.orderList = new ArrayList<>();
		} while(isExit==1); 			
	}
	
	//Main func에서 반복되는 부분 loop 함수로 빼기
	public int loop() { 
		Input inputData = new Input();
		Print printUi = new Print();
		Calculate calc = new Calculate();
		
		int totalSum = 0;		//총합 초기화
		while(true) {
			//Make
			OrderData orderItem = new OrderData();
			
			orderItem.setTicketType(inputData.selectTicket());							//권종 값 세팅하기
			orderItem.setTicketDayType(inputData.selectDayNightTicket());				//이용시간 값 세팅하기
			orderItem.setNum(inputData.inputID());										//주민번호 입력받아 String 세팅하기
			orderItem.setAmount(inputData.ticketAmount());								//티켓의 개수 세팅하기
			if(orderItem.getTicketType()==1) { 
				orderItem.setPreferenceType(inputData.selectPreference_COMP());			//종합권 이용할 때는 우대사항 6개
			} else {
				orderItem.setPreferenceType(inputData.selectPreference_PARK());			//파크권 이용할 때는 우대사항 3개
			}
			orderItem.setAge(calc.CalAge(orderItem.getNum()));							//나이 int로 세팅하기
			orderItem.setAgegroup(calc.calAgeGroup(orderItem.getAge()));				//Agegroup 세팅하기
			//Price before discount
			int price = calc.calcTicketPrice(orderItem);								//할인 전 티켓가격 세팅하기
			//set the discount Price
			orderItem.setPrice(calc.calDiscount(price, orderItem.getPreferenceType()));	//할인 후 가격 세팅하기
			totalSum += orderItem.getPrice()*orderItem.getAmount();						//총합 가격 세팅하기
			//TotalSum = discount price * amount
			orderItem.setTotalSum(totalSum);
			
			int tmp = printUi.printRepeat();
			Input.orderList.add(orderItem);			//ArrayList에 값들을 다 넣기
			if(tmp==2) break;			
		}
		printUi.printTicket(totalSum);				
		return printUi.inputEnd();		
	}
		

}