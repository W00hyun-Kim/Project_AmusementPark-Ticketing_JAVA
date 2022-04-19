package Ticketing;

public class OrderData {	
	private static int ticketType;				
	private static int ticketDayType;			
	private static String num;			//주민번호		
	private static int preferenceType;		
	private static int amount;					
	private static int totalSum;
	private static int price;
	private static int agegroup;
	private static int age;
		
	//TicketType
	public static int getTicketType() {
		return ticketType;
	}
	public static void setTicketType(int ticketType) {
		OrderData.ticketType = ticketType;
	}
	
	//TicketDayType
	public static int getTicketDayType() {
		return ticketDayType;
	}
	public static void setTicketDayType(int ticketDayType) {
		OrderData.ticketDayType = ticketDayType;
	}
	
	//주민등록번호
	public static String getNum() {
		return num;
	}
	public static void setNum(String num) {
		OrderData.num = num;
	}
	
	//우대사항
	public static int getPreferenceType() {
		return preferenceType;
	}
	public static void setPreferenceType(int preferenceType) {
		OrderData.preferenceType = preferenceType;
	}
	
	//티켓 개수
	public static int getAmount() {
		return amount;
	}
	public static void setAmount(int amount) {
		OrderData.amount = amount;
	}
			
	//나이
	public static int getAge() {
		return age;
	}
	public static void setAge(int age) {
		OrderData.age = age;
	}
	
	//나이 그룹
	public static int getAgegroup() {
		return agegroup;
	}
	public static void setAgegroup(int agegroup) {
		OrderData.agegroup = agegroup;
	}
	
	//가격
	public static int getPrice() {
		return price;
	}
	public static void setPrice(int price) {
		OrderData.price = price;
	}
	
	//총합
	public static int getTotalSum() {
		return totalSum;
	}
	public static void setTotalSum(int totalSum) {
		OrderData.totalSum = totalSum;
	}
	
	
			
	

}
