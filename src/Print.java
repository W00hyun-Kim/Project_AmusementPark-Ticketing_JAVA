package Ticketing;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Print {
	Scanner sc = new Scanner(System.in);
	CsvFileWriter csv = new CsvFileWriter();
	
	//������� ����Ϳ� ����ϴ� �Լ�
	public void printToMonitor(int totalSum) {

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
		}
			
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
		if(input==2) {
			printIncomeToMonitor();
			printReport_TicketType();
		}
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
	
	
	//����Ϳ� ��µ� ������� csv���Ϸ� ����ϴ� �Լ�
	public void printToCSV() {
		for(int index=0; index < Input.orderList.size(); index ++) {	
			csv.makeArr(index);		//csv ����� ���� arr
		}
		csv.writing();
	}
	
	//���α׷� ���� �� ����Ϳ� ��µǴ� report.csv ��
	public void printIncomeToMonitor() {						
		try {
			final String fileName = "C:\\Users\\�����\\Desktop\\capture\\Result.csv";	
						
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			System.out.println("=========================== Result.csv ==============================");
			System.out.printf("%4s %13s %5s %6s %8s %8s %8s\n","��¥","�̿��","����","����","����(��)","����(��)","��������");
			System.out.println("---------------------------------------------------------------------");
			while ((line = br.readLine()) != null) {
				if(line.contains("��¥")) {
					continue;
				}
				String rv =line.replaceAll(",", "\t");
				System.out.println(rv);
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=====================================================================");	

	}
	
	//���� �� �Ǹ� ��Ȳ
	public void printReport_TicketType() {				
		int babyCount_comp = 0; int childCount_comp = 0; int teenCount_comp = 0;
		int adultCount_comp = 0; int elderCount_comp = 0;
		
		int babyCount_park = 0; int childCount_park = 0; int teenCount_park = 0;
		int adultCount_park = 0; int elderCount_park = 0;
		
		int outcomeComp = 0; int outcomePark = 0;
						
		try {
			final String fileName = "C:\\Users\\�����\\Desktop\\capture\\Result.csv";	
						
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				//ī�װ� ��� ���� �����ϰ� �Ѿ��
				if(line.contains("��¥")) {
					continue;
				}
				
				String tmp[] = line.split(",");
				
				if(line.contains("�����̿��")) {
					outcomeComp += Integer.parseInt(tmp[5]);
					if(line.contains("����")) {
						babyCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("���")) {
						childCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("û�ҳ�")) {
						teenCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("����")) {
						adultCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("�����")) {
						elderCount_comp+=Integer.parseInt(tmp[4]);
					}															
				}								
			}	
			
			BufferedReader br2 = new BufferedReader(new FileReader(fileName));
			String line2;
			while ((line2 = br2.readLine()) != null) {
				//ī�װ� ��� ���� �����ϰ� �Ѿ��
				if(line2.contains("��¥")) {
					continue;
				}
				
				String tmp[] = line2.split(",");
				
				if(line2.contains("��ũ�̿��")) {
					outcomePark += Integer.parseInt(tmp[5]);
					if(line2.contains("����")) {
						babyCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("���")) {
						childCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("û�ҳ�")) {
						teenCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("����")) {
						adultCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("�����")) {
						elderCount_park+=Integer.parseInt(tmp[4]);
					}															
				}								
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\n=========================���� �� �Ǹ���Ȳ================================");		
		System.out.printf("���ձ� �� %d��\n",babyCount_comp+childCount_comp+teenCount_comp+adultCount_comp+elderCount_comp);
		System.out.printf("���� %d��, ��� %d��, û�ҳ� %d��, ���� %d��, ����� %d��\n",babyCount_comp,childCount_comp,teenCount_comp,adultCount_comp,elderCount_comp);
		System.out.printf("���ձ� ���� : %d ��\n",outcomeComp);
		System.out.println();		
		
		System.out.printf("��ũ�� �� %d��\n",babyCount_park+childCount_park+teenCount_park+adultCount_park+elderCount_park);
		System.out.printf("���� %d��, ��� %d��, û�ҳ� %d��, ���� %d��, ����� %d��\n",babyCount_park,childCount_park,teenCount_park,adultCount_park,elderCount_park);
		System.out.printf("��ũ�� ���� : %d ��",outcomePark);
		System.out.println();	
		System.out.println("=====================================================================");	

	}
	
	
	
	
}