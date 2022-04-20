package Ticketing;
import java.util.*;

public class Print {
	Scanner sc = new Scanner(System.in);
	CsvFileWriter csv = new CsvFileWriter();
	
	public void printTicket(int totalSum) {

		System.out.println("==============================================================");	
		System.out.println("================== L O T T E  W O R L D ======================");
		System.out.println("==============================================================");
		System.out.println("  �� �� ��  |  �� ��   |  �� ��  |  �� ��  |  �� ��  |   �� �� �� ��   ");
		System.out.println("--------------------------------------------------------------");
		
		//ArrayList�� ��� for��
		for(int index=0; index < Input.orderList.size(); index ++) {	
			System.out.printf(" %5s ",(Input.orderList.get(index).getTicketType()==1)?"�����̿��":"��ũ�̿��");		//���׿����� ���			
			System.out.printf(" %5s ",(Input.orderList.get(index).getTicketDayType()==1)?"�ְ���":"�߰���");					
			System.out.printf("%7s ",agegroupConverter(Input.orderList.get(index).getAgegroup()));		
			System.out.printf("  %5d�� ", Input.orderList.get(index).getAmount());		
			System.out.printf("%9d�� ",Input.orderList.get(index).getPrice()*Input.orderList.get(index).getAmount());	//���ΰ� * ����	
			System.out.printf(" %10s\n",preferenceTypeConverter(Input.orderList.get(index).getPreferenceType()));
			
			csv.makeArr(index);		//csv ����� ���� arr
		}
		csv.writing();
	
		
		//ArrayList �ʱ�ȭ
		ArrayList<OrderData> orderList = new ArrayList<>();
		
		System.out.println("==============================================================");			
		System.out.printf("����� �Ѿ��� %d���Դϴ�. \n\n", totalSum);			
	}
	
	public int printRepeat() {
		int input;
		do {	
				System.out.println("��� �߱��Ͻðڽ��ϱ�?");
				System.out.println("1. Ƽ�Ϲ߱�");
				System.out.print("2. ����\n>> ");
				input = sc.nextInt();
				System.out.println();
				
				if (input>2) {
					System.out.println("���⿡�� �������ּ���.\n");
				} 
		} while(input>2);
		return input;			
	}	
	
	public int inputEnd() {
		int input;
		System.out.print("��� ����(1: ���ο� �ֹ�, 2: ���α׷� ����)\n>>");
		input = sc.nextInt();
		System.out.println();
		return input;
	}
	
	String agegroupConverter(int agegroup) {
		String age[] = {"����","���","û�ҳ�","�����","����"};	
		return age[agegroup-1];
	}
	
	String preferenceTypeConverter(int preferenceType) {
		String prefer[] = {"������� ����","����� �������","���������� �������","�ް��庴 �������","�ӻ�� �������","���ڳ� �������"};	
		return prefer[preferenceType-1];
	}

}