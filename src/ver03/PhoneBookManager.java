package ver03;

import ver03.PhoneInfo;
import java.util.Scanner;


public class PhoneBookManager {
	
	public  PhoneBookManager(int num) {
		myAddress = new PhoneInfo[num];
		numOfAddress = 0;
	}

	private PhoneInfo[] myAddress;
	private int numOfAddress;
	
	public void printMenu() {
		System.out.println("선택하세요...");
		System.out.println("1.데이터 입력");
		System.out.println("2.데이터 검색");
		System.out.println("3.데이터 삭제");
		System.out.println("4.주소록 출력");
		System.out.println("5.프로그램 종료");
		System.out.println("선택 :");
	}
	
	public  void dataInput() {
		 
		 Scanner scan = new Scanner(System.in);
		 String Name,Phone,Bir;

		 System.out.println("데이터 입력을 시작합니다...");
		 System.out.print("이름:");Name = scan.nextLine();
		 System.out.print("전화번호:");Phone = scan.nextLine();
		 System.out.print("생년월일:");Bir = scan.nextLine();		 
		 
		 PhoneInfo info = new PhoneInfo(Name, Phone, Bir);
		 myAddress[numOfAddress++] = info;

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
