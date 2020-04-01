package ver08;

import ver08.PhoneInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;



public class PhoneBookManager implements Serializable{
	
	HashSet<PhoneInfo> set;
	
	public PhoneBookManager() {
		try {
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("src/ver08/PhoneBook.txt")
					);
			set = (HashSet<PhoneInfo>)in.readObject();
		}
		catch(Exception e) {
			System.out.println("불러올 파일이없습니다.");
			set = new HashSet<PhoneInfo>();
		}
	}
	
	
	
	public void saveAddressInfo() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("src/ver08/PhoneBook.txt"));
			
			
			Iterator<PhoneInfo> itr = set.iterator();
			for(PhoneInfo pi : set) {
				
			
			out.writeObject(set);
			
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
	}
	
	
	public void printMenu() {
		 System.out.println("1.데이터 입력");
		 System.out.println("2.데이터 검색");
		 System.out.println("3.데이터 삭제");
		 System.out.println("4.데이터 전체출력");
		 System.out.println("5.프로그램 종료");
	}
	 
	public void nameCheck(String iName) {
		Scanner scan = new Scanner(System.in);
		boolean searchFlag = false;
		 
		 Iterator<PhoneInfo> itr = set.iterator();
		 
		 while(itr.hasNext()) {
			 
			 PhoneInfo phoneInfo = itr.next();
			 
			 if(iName.equals(phoneInfo.name)) {
		         searchFlag = true;
			 }
		     if(searchFlag = true){
				 System.out.println("중복된 이름을 덮어쓰시겠습니까? 1.yes / 2.no");
				 int ol = scan.nextInt();
				 scan.nextLine();
				 switch(ol) {
					 case 1:
						 for(int i = 0; i< iName.length(); i++) {
							 itr.remove();
				             return;
						 }
					 case 2:
						 dataInput();
				}
			 }
		     else {
				return;
			}
		 }
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
		 nameCheck(iName);
		 System.out.print("전화번호:");
		 iPhone = scan.nextLine();
		 
		 
		
		 switch(select) {
		 case SubMenuItem.NOMAL:
             set.add(new PhoneInfo(iName,iPhone));
			 break;
		 case SubMenuItem.SCHOOL:
			 System.out.print("전공:");iMajor = scan.nextLine();
			 System.out.print("학년:");iGrade = scan.nextInt();
             set.add(new PhoneSchoolInfo(iName,iPhone,iMajor, iGrade));
			 break;
		 case SubMenuItem.COMPANY: 
			 System.out.print("회사:"); iCompany = scan.nextLine();
             set.add(new PhoneCompanyInfo(iName,iPhone,iCompany));
			 break;
		 }
		 System.out.println("데이터 입력이 완료되었습니다.");
		 
	}
	
	public void dataSearch() {
		Scanner scan = new Scanner(System.in);
		System.out.println("데이터 검색을 시작합니다..");
		System.out.print("이름: ");
		String searchName = scan.nextLine();
		
		boolean searchFlag = false; //검색유무가 안됨
		
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) {
			PhoneInfo phoneInfo = itr.next();
			if(searchName.equals(phoneInfo.name)) {
				searchFlag = true;
			}
			if(searchFlag = true) {
				System.out.println(phoneInfo.toString()); 
			}else {
				System.out.println("해당 데이터는 없습니다.");
			}
		}
	}

	
	
	public void deleteInfo() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제를 시작합니다.");
		String deleteName = scan.nextLine();
		
		
		
		boolean searchFlag = false;
		
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) {
			PhoneInfo phoneInfo = itr.next();
			if(deleteName.equals(phoneInfo.name)) {
				searchFlag = true;
			}
			if(searchFlag = true) {
				itr.remove();
				System.out.println("삭제되었습니다.");
			}
			else {//삭제될 이름이 같이 않으면
				System.out.println("삭제된 데이터가 없습니다.");
			}
		}
	
	}
	

	public void dataAllShow() {
		
		
		for(PhoneInfo pi : set) {
			System.out.println(pi.toString());
		
		System.out.println("전체정보가 출력되었습니다.");
		}
	}
}



