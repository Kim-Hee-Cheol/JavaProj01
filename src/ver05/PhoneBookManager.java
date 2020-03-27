package ver05;

import ver05.PhoneInfo;
import java.util.Scanner;


public class PhoneBookManager {
	
	public PhoneBookManager(int num) {
		myAddress = new PhoneInfo[num];
		numOfAddress = 0;
	}
	
	private PhoneInfo[] myAddress;
	private int numOfAddress;
	
	public static void printMenu() {
		 System.out.println("1.데이터 입력");
		 System.out.println("2.데이터 검색");
		 System.out.println("3.데이터 삭제");
		 System.out.println("4.프로그램 종료");
	}
	 
	public void dataInput() {
		 
		 Scanner scan = new Scanner(System.in);
		 String IName,IPhone,ICompany,Imajor;
		 int Igrade, select;

		 System.out.println("데이터 입력을 시작합니다...");
		 System.out.println("1.일반,2.학교동창,3.회사동료");
		 System.out.println("선택>> ");
		 select = scan.nextInt();
		 scan.nextLine();
		 
		 System.out.print("이름:");IName = scan.nextLine();
		 System.out.print("전화번호:");IPhone = scan.nextLine();
		 
	
		 switch(select) {
		 case SubMenuItem.NOMAL:
			 PhoneInfo info = new PhoneInfo(IName, IPhone);
			 myAddress[numOfAddress++] = info;
			 break;
		 case SubMenuItem.SCHOOL:
			 System.out.print("전공:");Imajor = scan.nextLine();
			 System.out.print("학년:");Igrade = scan.nextInt();
			 PhoneSchoolInfo sc = new PhoneSchoolInfo(IName, IPhone, Imajor, Igrade);
			 myAddress[numOfAddress++] = sc;
			 break;
		 case SubMenuItem.COMPANY: 
			 System.out.print("회사:"); ICompany = scan.nextLine();
			 PhoneCompanyInfo co = new PhoneCompanyInfo(IName,IPhone,ICompany);
			 myAddress[numOfAddress++] = co;
			 break;
		 }
		 System.out.println("데이터 입력이 완료되었습니다.");
		 
	}
	
	public void dataSearch() {
		Scanner scan = new Scanner(System.in);
		System.out.println("데이터 검색을 시작합니다..");
		String searchName = scan.nextLine();
		for(int i=0 ; i<numOfAddress ; i++) {			
			if(searchName.compareTo(myAddress[i].name)==0) {
				myAddress[i].showAllData();
				System.out.println("데이터 검색이 완료되었습니다.");
			 }
		}
	}
	
	public void deleteInfo() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제를 시작합니다.");
		String deleteName = scan.nextLine();
		
		int deleteIndex = -1;
		
		for(int i=0 ; i<numOfAddress ; i++) {
			if(deleteName.compareTo(myAddress[i].name)==0) {
				myAddress[i] = null;
				deleteIndex = i;
				numOfAddress--;
			}
		}
		if(deleteIndex==-1) {
			System.out.println("삭제된 데이터가 없습니다.");
		}
		else {
			for(int i=deleteIndex ; i<numOfAddress ; i++) {
				myAddress[i] = myAddress[i+1];
			}
			System.out.println("데이터삭제가 완료되었습니다.");
		}
	}

	public void dataAllShow() {
		for(int i=0 ; i<numOfAddress ; i++) {
			myAddress[i].showAllData();
		}
		System.out.println("전체정보가 출력되었습니다.");
	}



	
	
	
	
}