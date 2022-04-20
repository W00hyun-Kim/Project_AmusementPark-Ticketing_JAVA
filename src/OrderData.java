package Ticketing;

public class OrderData {	
	private int ticketType;				
	private int ticketDayType;			
	private String num;			//주민번호		
	private int preferenceType;		
	private int amount;					
	private int totalSum;
	private int price;
	private int agegroup;
	private int age;
	
	public OrderData() {
		
	}
	
	//TicketType
	public int getTicketType() {
		return ticketType;
	}
	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}
	
	//TicketDayType
	public int getTicketDayType() {
		return ticketDayType;
	}
	public void setTicketDayType(int ticketDayType) {
		this.ticketDayType = ticketDayType;
	}
	
	//주민등록번호
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	//우대사항
	public int getPreferenceType() {
		return preferenceType;
	}
	public void setPreferenceType(int preferenceType) {
		this.preferenceType = preferenceType;
	}
	
	//티켓 개수
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
			
	//나이
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	//나이 그룹
	public int getAgegroup() {
		return agegroup;
	}
	public void setAgegroup(int agegroup) {
		this.agegroup = agegroup;
	}
	
	//가격
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	//총합
	public int getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}
	
	
			
	

}