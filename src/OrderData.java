package Ticketing;

public class OrderData {	
	private int ticketType;				
	private int ticketDayType;			
	private String num;			//�ֹι�ȣ		
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
	
	//�ֹε�Ϲ�ȣ
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	//������
	public int getPreferenceType() {
		return preferenceType;
	}
	public void setPreferenceType(int preferenceType) {
		this.preferenceType = preferenceType;
	}
	
	//Ƽ�� ����
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
			
	//����
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	//���� �׷�
	public int getAgegroup() {
		return agegroup;
	}
	public void setAgegroup(int agegroup) {
		this.agegroup = agegroup;
	}
	
	//����
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	//����
	public int getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}
	
	
			
	

}