package Ticketing;

import java.util.ArrayList;

//Main class 
public class Ticketing {		
	public static ArrayList<OrderData> orderList = new ArrayList<>();
	
	public static void main(String[] args) {
		Input inputData = new Input();
		inputData.ticketingSystem();

	}
}