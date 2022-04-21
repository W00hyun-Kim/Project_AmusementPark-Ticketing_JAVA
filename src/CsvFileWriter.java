package Ticketing;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
 
public class CsvFileWriter {
	static Print Write = new Print();
	static ArrayList<String> list = new ArrayList<String>();
	final static String fileName = "C:\\Users\\whKim\\Desktop\\티케팅 프로젝트\\Result.csv";	

		
	public static void csvResult(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String today = sdf.format(cal.getTime());
		
		 File csv = new File(fileName);
	        BufferedWriter bw = null; 
			try {
				
				bw = new BufferedWriter(new FileWriter(csv, true));
//				bw.newLine();
				bw.write(today+","+str);
				bw.newLine();
 
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (bw != null) {
	                    bw.flush(); 
	                    bw.close(); 
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	//프로그램 처음 시작할때만 카테고리 적히게 하는 확인용 메서드
	public static boolean csvCategoryCheck() {
		try {				
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			
			if((line = br.readLine()) != null) {}
					
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//카테고리를 적는 메서드
	public static void csvCategory() {
		
		File csv = new File(fileName);
        BufferedWriter bw = null; 
               
		try {	
			
			bw = new BufferedWriter(new FileWriter(csv, true));
			
			bw.write("날짜, 이용권, 권종, 조건, 개수(장), 가격(원), 할인적용\n");
				
			
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.flush(); 
                    bw.close(); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void makeArr(int index) {
		String one = (Input.orderList.get(index).getTicketType()==1)?"종합이용권":"파크이용권";
		String two = (Input.orderList.get(index).getTicketDayType()==1)?"주간권":"야간권";
		String three = Write.agegroupConverter(Input.orderList.get(index).getAgegroup());
		String four = "" + Input.orderList.get(index).getAmount();
		String five = "" +Input.orderList.get(index).getPrice()*Input.orderList.get(index).getAmount();
		String six = Write.preferenceTypeConverter(Input.orderList.get(index).getPreferenceType());
		String tmp = one +"," + two + "," + three + "," + four + "," + five + "," + six ;
		list.add(tmp);
	}
	
	public static void writing(){
		for (int j = 0; j < list.size(); j++) {			
			csvResult(list.get(j));
		}
	}
	
	
	
}