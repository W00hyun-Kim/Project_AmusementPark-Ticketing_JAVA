package Ticketing;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
 
public class CsvFileWriter {
	static Print Write = new Print();
	static ArrayList<String> list = new ArrayList<String>();

		
	public static void csv(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		String today = sdf.format(cal.getTime());
		

		 File csv = new File("C:\\Users\\�����\\Desktop\\capture\\Result.csv");
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
	
	public static void csvCategory() {
	
		File csv = new File("C:\\Users\\�����\\Desktop\\capture\\Result.csv");
        BufferedWriter bw = null; 
		try {				
			bw = new BufferedWriter(new FileWriter(csv, true));
			
			bw.write("��¥, �̿��, ����, ����, ����(��), ����(��), ��������\n");

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
		String one = (Input.orderList.get(index).getTicketType()==1)?"�����̿��":"��ũ�̿��";
		String two = (Input.orderList.get(index).getTicketDayType()==1)?"�ְ���":"�߰���";
		String three = Write.agegroupConverter(Input.orderList.get(index).getAgegroup());
		String four = "" + Input.orderList.get(index).getAmount();
		String five = "" +Input.orderList.get(index).getPrice()*Input.orderList.get(index).getAmount();
		String six = Write.preferenceTypeConverter(Input.orderList.get(index).getPreferenceType());
		String tmp = one +"," + two + "," + three + "," + four + "," + five + "," + six ;
		list.add(tmp);
	}
	
	public static void writing(){
		for (int j = 0; j < list.size(); j++) {
			if(j==0) {
				csvCategory();
			}
			csv(list.get(j));
		}
	}
}