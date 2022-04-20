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
	
	//Main func���� �ݺ��Ǵ� �κ� loop �Լ��� ����
	public int loop() { 
		Input inputData = new Input();
		Print printUi = new Print();
		Calculate calc = new Calculate();
		
		int totalSum = 0;		//���� �ʱ�ȭ
		while(true) {
			//Make
			OrderData orderItem = new OrderData();
			
			orderItem.setTicketType(inputData.selectTicket());							//���� �� �����ϱ�
			orderItem.setTicketDayType(inputData.selectDayNightTicket());				//�̿�ð� �� �����ϱ�
			orderItem.setNum(inputData.inputID());										//�ֹι�ȣ �Է¹޾� String �����ϱ�
			orderItem.setAmount(inputData.ticketAmount());								//Ƽ���� ���� �����ϱ�
			if(orderItem.getTicketType()==1) { 
				orderItem.setPreferenceType(inputData.selectPreference_COMP());			//���ձ� �̿��� ���� ������ 6��
			} else {
				orderItem.setPreferenceType(inputData.selectPreference_PARK());			//��ũ�� �̿��� ���� ������ 3��
			}
			orderItem.setAge(calc.CalAge(orderItem.getNum()));							//���� int�� �����ϱ�
			orderItem.setAgegroup(calc.calAgeGroup(orderItem.getAge()));				//Agegroup �����ϱ�
			//Price before discount
			int price = calc.calcTicketPrice(orderItem);								//���� �� Ƽ�ϰ��� �����ϱ�
			//set the discount Price
			orderItem.setPrice(calc.calDiscount(price, orderItem.getPreferenceType()));	//���� �� ���� �����ϱ�
			totalSum += orderItem.getPrice()*orderItem.getAmount();						//���� ���� �����ϱ�
			//TotalSum = discount price * amount
			orderItem.setTotalSum(totalSum);
			
			int tmp = printUi.printRepeat();
			Input.orderList.add(orderItem);			//ArrayList�� ������ �� �ֱ�
			if(tmp==2) break;			
		}
		printUi.printTicket(totalSum);				
		return printUi.inputEnd();		
	}
		

}