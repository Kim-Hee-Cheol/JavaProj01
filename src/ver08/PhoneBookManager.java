package ver08;

import ver08.PhoneInfo;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Scanner;




public class PhoneBookManager {
	
	public PhoneBookManager(int num) {
		myAddress = new PhoneInfo[num];
		numOfAddress = 0;
	}
	
	private PhoneInfo[] myAddress;
	private int numOfAddress;
	
	HashSet<String> set = new HashSet<String>();
	
	public void callin() {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("src/ver08/PhoneBook.obj"));
				for(int i=0 ; i<numOfAddress ; i++) {
					PhoneInfo info = (PhoneInfo)in.readObject();
				}
			in.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
		}
		catch(Exception e) {
			System.out.println("오류발생");
		}
		
	}
	
	public void printMenu() {
		 System.out.println("1.데이터 입력");
		 System.out.println("2.데이터 검색");
		 System.out.println("3.데이터 삭제");
		 System.out.println("4.데이터 전체출력");
		 System.out.println("5.프로그램 종료");
	}
	 
	public void dataInput(){
		
		
		 Scanner scan = new Scanner(System.in);
		 String iName,iPhone,iCompany,iMajor;
		 int iGrade, select;

		 System.out.println("데이터 입력을 시작합니다...");
		 System.out.println("1.일반,2.학교동창,3.회사동료");
		 System.out.println("선택>> ");
		 select = scan.nextInt();
		 scan.nextLine();
		 
		 System.out.print("이름:");
		 iName = scan.nextLine();
		 System.out.print("전화번호:");
		 iPhone = scan.nextLine();
		 
		 if(set.add(iName)) {
			 System.out.println("중복된 이름x");
	     }
		 else {
			 System.out.println("중복된 이름을 덮어쓰시겠습니까? 1.yes / 2.no");
			 int ol = scan.nextInt();
			 scan.nextLine();
			 switch(ol) {
			 case 1:
				 for(int i =0; i< iName.length(); i++) {
					 if(myAddress[i].name.equals(iName)) {
		                myAddress[i] = new PhoneInfo(iName,iPhone);
		                System.out.println("중복저장되었음.");
		                break;
					 }
					 else {
						 System.out.println("이름이 없습니다.");
						 break;
					 }
				 }
			 case 2:
				 break;
			 }
		 }

		 
		 switch(select) {
		 case SubMenuItem.NOMAL:
			 PhoneInfo info = new PhoneInfo(iName, iPhone);
			 myAddress[numOfAddress++] = info;
			 break;
		 case SubMenuItem.SCHOOL:
			 System.out.print("전공:");iMajor = scan.nextLine();
			 System.out.print("학년:");iGrade = scan.nextInt();
			 PhoneSchoolInfo sc = new PhoneSchoolInfo(iName, iPhone, iMajor, iGrade);
			 myAddress[numOfAddress++] = sc;
			 break;
		 case SubMenuItem.COMPANY: 
			 System.out.print("회사:"); iCompany = scan.nextLine();
			 PhoneCompanyInfo co = new PhoneCompanyInfo(iName,iPhone,iCompany);
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
	
	public void saveAddressInfo() {
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("src/ver08/PhoneBook.obj"));
			for(int i=0 ; i<numOfAddress ; i++) {
				out.writeObject(myAddress[i]);
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
	}
}




