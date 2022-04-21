package Ticketing;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Print {
	Scanner sc = new Scanner(System.in);
	CsvFileWriter csv = new CsvFileWriter();
	final String fileName = "C:\\Users\\whKim\\Desktop\\티케팅 프로젝트\\Result.csv";	
	
	//결과값을 모니터에 출력하는 함수
	public void printToMonitor(int totalSum) {

		System.out.println("==============================================================");	
		System.out.println("================== L O T T E  W O R L D ======================");
		System.out.println("==============================================================");
		System.out.println("  이 용 권  |  권 종   |  조 건  |  개 수  |  가 격  |   할 인 적 용   ");
		System.out.println("--------------------------------------------------------------");
		
		//ArrayList값 출력 for문
		for(int index=0; index < Input.orderList.size(); index ++) {	
			System.out.printf(" %5s ",(Input.orderList.get(index).getTicketType()==1)?"종합이용권":"파크이용권");		//삼항연산자 사용			
			System.out.printf(" %5s ",(Input.orderList.get(index).getTicketDayType()==1)?"주간권":"야간권");					
			System.out.printf("%7s ",agegroupConverter(Input.orderList.get(index).getAgegroup()));		
			System.out.printf("  %5d개 ", Input.orderList.get(index).getAmount());		
			System.out.printf("%9d원 ",Input.orderList.get(index).getPrice()*Input.orderList.get(index).getAmount());	//할인가 * 개수	
			System.out.printf(" %10s\n",preferenceTypeConverter(Input.orderList.get(index).getPreferenceType()));			
		}
			
		//ArrayList 초기화
		ArrayList<OrderData> orderList = new ArrayList<>();
		
		System.out.println("==============================================================");			
		System.out.printf("입장료 총액은 %d원입니다. \n\n", totalSum);			
	}
	
	public int printRepeat() {
		int input;
		do {	
				System.out.println("계속 발권하시겠습니까?");
				System.out.println("1. 티켓발권");
				System.out.print("2. 종료\n>> ");
				input = sc.nextInt();
				System.out.println();
				
				if (input>2) {
					System.out.println("보기에서 선택해주세요.\n");
				} 
		} while(input>2);
		return input;			
	}	
	
	public int inputEnd() {
		int input;
		System.out.print("계속 진행(1: 새로운 주문, 2: 프로그램 종료)\n>>");
		input = sc.nextInt();
		System.out.println();
		if(input==2) {
			printIncomeToMonitor();
			printReport_TicketType();
			printReport_preferenceType();
		}
		return input;
	}
	
	String agegroupConverter(int agegroup) {
		String age[] = {"유아","어린이","청소년","중장년","성인"};	
		return age[agegroup-1];
	}
	
	String preferenceTypeConverter(int preferenceType) {
		String prefer[] = {"우대적용 없음","장애인 우대적용","국가유공자 우대적용","휴가장병 우대적용","임산부 우대적용","다자녀 우대적용"};	
		return prefer[preferenceType-1];
	}
	
	
	//모니터에 출력된 결과값을 csv파일로 출력하는 함수
	public void printToCSV() {
		for(int index=0; index < Input.orderList.size(); index ++) {	
			csv.makeArr(index);		//csv 출력을 위한 arr
		}
		csv.writing();
	}
	
	//프로그램 종료 시 모니터에 출력되는 report.csv 값
	public void printIncomeToMonitor() {						
		try {						
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			System.out.println("=========================== Result.csv ==============================");
			System.out.printf("%4s %13s %5s %6s %8s %8s %8s\n","날짜","이용권","권종","조건","개수(장)","가격(원)","할인적용");
			System.out.println("---------------------------------------------------------------------");
			while ((line = br.readLine()) != null) {
				if(line.contains("날짜")) {
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
	
	//권종 별 판매 현황
	public void printReport_TicketType() {				
		int babyCount_comp = 0; int childCount_comp = 0; int teenCount_comp = 0;
		int adultCount_comp = 0; int elderCount_comp = 0;
		
		int babyCount_park = 0; int childCount_park = 0; int teenCount_park = 0;
		int adultCount_park = 0; int elderCount_park = 0;
		
		int outcomeComp = 0; int outcomePark = 0;
						
		try {						
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				//카테고리 출력 라인 무시하고 넘어가기
				if(line.contains("날짜")) {
					continue;
				}
				
				String tmp[] = line.split(",");
				
				if(line.contains("종합이용권")) {
					outcomeComp += Integer.parseInt(tmp[5]);
					if(line.contains("유아")) {
						babyCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("어린이")) {
						childCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("청소년")) {
						teenCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("성인")) {
						adultCount_comp+=Integer.parseInt(tmp[4]);
					} else if(line.contains("중장년")) {
						elderCount_comp+=Integer.parseInt(tmp[4]);
					}															
				}								
			}	
			
			BufferedReader br2 = new BufferedReader(new FileReader(fileName));
			String line2;
			while ((line2 = br2.readLine()) != null) {
				//카테고리 출력 라인 무시하고 넘어가기
				if(line2.contains("날짜")) {
					continue;
				}
				
				String tmp[] = line2.split(",");
				
				if(line2.contains("파크이용권")) {
					outcomePark += Integer.parseInt(tmp[5]);
					if(line2.contains("유아")) {
						babyCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("어린이")) {
						childCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("청소년")) {
						teenCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("성인")) {
						adultCount_park+=Integer.parseInt(tmp[4]);
					} else if(line2.contains("중장년")) {
						elderCount_park+=Integer.parseInt(tmp[4]);
					}															
				}								
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\n=========================권종 별 판매현황================================");		
		System.out.printf("종합권 총 %d매\n",babyCount_comp+childCount_comp+teenCount_comp+adultCount_comp+elderCount_comp);
		System.out.printf("유아 %d매, 어린이 %d매, 청소년 %d매, 성인 %d매, 중장년 %d매\n",babyCount_comp,childCount_comp,teenCount_comp,adultCount_comp,elderCount_comp);
		System.out.printf("종합권 매출 : %d 원\n",outcomeComp);
		System.out.println();		
		
		System.out.printf("파크권 총 %d매\n",babyCount_park+childCount_park+teenCount_park+adultCount_park+elderCount_park);
		System.out.printf("유아 %d매, 어린이 %d매, 청소년 %d매, 성인 %d매, 중장년 %d매\n",babyCount_park,childCount_park,teenCount_park,adultCount_park,elderCount_park);
		System.out.printf("파크권 매출 : %d 원",outcomePark);
		System.out.println();	
		System.out.println("=====================================================================");	

	}
	//추가할 예정
	public void printReport_Date() {
		
	}
	
	public void printReport_preferenceType() {
		int none = 0; int disabled = 0; int merit = 0; int bigfamily = 0; int pregnant = 0; int soldier = 0;
		int total = 0;				
		try {						
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				//카테고리 출력 라인 무시하고 넘어가기
				if(line.contains("날짜")) {
					continue;
				}
				
				String tmp[] = line.split(",");
			
				if(line.contains("우대적용 없음")) {
					none+=Integer.parseInt(tmp[4]);
				} else if(line.contains("장애인")) {
					disabled+=Integer.parseInt(tmp[4]);
				} else if(line.contains("국가유공자")) {
					merit+=Integer.parseInt(tmp[4]);
				} else if(line.contains("다자녀")) {
					bigfamily+=Integer.parseInt(tmp[4]);
				} else if(line.contains("임산부")) {
					pregnant+=Integer.parseInt(tmp[4]);
				} else if(line.contains("휴가장병")) {
					soldier+=Integer.parseInt(tmp[4]);
				}
			}
			
			total = none+disabled+merit+bigfamily+pregnant+soldier;			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\n=========================우대권 판매현황================================");		
		System.out.printf(" 총 판매 티켓 수  :  %d 매\n",total);
		System.out.printf(" 우대 없음       :  %d 매\n",none);
		System.out.printf(" 장애인         :  %d 매\n",disabled);
		System.out.printf(" 국가유공자      :  %d 매\n",merit);
		System.out.printf(" 휴가장병        :  %d 매\n",soldier);
		System.out.printf(" 임산부         :  %d 매\n",pregnant);
		System.out.printf(" 다둥이행복카드   :  %d 매\n",bigfamily);
		System.out.println("=====================================================================");
	}
	
}