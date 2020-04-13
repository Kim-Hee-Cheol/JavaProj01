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
	
	HashSet<PhoneInfo> set; //중복된 원소를 허용하지 않는 HashSet선언
	
	
	public PhoneBookManager() { //PhoneBookManager생성자에서 
		try {					//	try~catch로 파일을 불러옴 
			ObjectInputStream in = new ObjectInputStream(
					new FileInputStream("src/ver08/PhoneBook.obj")
					);
			//파일 경로 찾기
			set = (HashSet<PhoneInfo>)in.readObject();
		}
		catch(Exception e) { //파일이 없으면 예외처리
			System.out.println("불러올 파일이 없습니다.");
			set = new HashSet<PhoneInfo>();
		}
	}
	
	public void saveAddressInfo() { //파일저장메소드
		try {  
			ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream("src/ver08/PhoneBook.obj"));
			//파일을 저장할 경로설정
			
			Iterator<PhoneInfo> itr = set.iterator();  //자료들을 순차적으로 접근하면서
													  //처리할 때 사용
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
	
	
	public void printMenu() { //메뉴출력
		 System.out.println("1.데이터 입력");
		 System.out.println("2.데이터 검색");
		 System.out.println("3.데이터 삭제");
		 System.out.println("4.데이터 전체출력");
		 System.out.println("5.프로그램 종료");
	}
	 

	
	public boolean duplicateCheck(String name) {  //boolean타입 이름중복체크 메소드
												
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) { //다음 요소가 있는지 판별하는 메소드
			PhoneInfo phoneInfo = (PhoneInfo) itr.next();
			if(name.equals(phoneInfo.name)) { //매개변수의 name과 PhoneInfo의 이름이 같으면			
				return true; //true를 반환
	         }
		}	
		return false; //그렇지 않으면 false반환
	}
	
	public boolean deletePhoneInfo(String name) { //boolean타입 삭제메소드
	
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) { //다음 요소가 있는지 판별하는 메소드
			PhoneInfo phoneInfo = (PhoneInfo)itr.next();
			if(name.equals(phoneInfo.name)) { //name과 PhoneInfo가 
				itr.remove(); //같으면 입력 할라고 한 값을 무효처리(삭제)
				return true;
	         }
		}		
		return false;
	}
	
	
	public void dataInput(){ //데이터 입력 메소드
		
		
		 Scanner scan = new Scanner(System.in);
		 String iName,iPhone,iCompany,iMajor;
		 int iGrade, select;

		 System.out.println("데이터 입력을 시작합니다...");
		 System.out.println("1.일반,2.학교동창,3.회사동료");
		 System.out.println("선택>> ");
		 select = scan.nextInt();
		 scan.nextLine();
		 
		 switch(select) { // 1,2,3 입력 이외에 다른값을 입력했을 때 
			 case SubMenuItem.NORMAL:
			 case SubMenuItem.SCHOOL:
			 case SubMenuItem.COMPANY:
				 break;
			 default:
				 System.out.println("선택이 잘못되었습니다.");	//'선택이 잘못되었다.'라는 구문을 출력하는 switch문	
				 return;
		 }
		 
		 System.out.print("이름:");
		 iName = scan.nextLine();
		
		 
		 //중복체크
		 if(true == duplicateCheck(iName)) //이름이 중복되었을 때
		 {			 
			 //사용자입력을받고
			 System.out.println("덮어쓰기? (Y/N)");
			 System.out.println("선택>> ");
			 String overwriteYn = scan.nextLine();

			 //삭제할지 결정
			 if(overwriteYn.toUpperCase().equals("Y")) { //입력값이 Y일 때
				 if(true == deletePhoneInfo(iName)) {
					 System.out.println("[" + iName + "] 데이터가 삭제되었습니다."); 				 
				 }else {
					 return;
				 }
			 }else {
				System.out.println("데이터 삭제가 실패하였습니다."); // 입력값이 N일 때
				return;
			 }
		 }
		 

		 System.out.print("전화번호:");
		 iPhone = scan.nextLine();
		 
		 switch(select) {
		 case SubMenuItem.NORMAL:
             set.add(new PhoneInfo(iName,iPhone));
    		 System.out.println("데이터 입력이 완료되었습니다.");
			 break;
		 case SubMenuItem.SCHOOL:
			 System.out.print("전공:");iMajor = scan.nextLine();
			 System.out.print("학년:");iGrade = scan.nextInt();
             set.add(new PhoneSchoolInfo(iName,iPhone,iMajor, iGrade));
    		 System.out.println("데이터 입력이 완료되었습니다.");
			 break;
		 case SubMenuItem.COMPANY: 
			 System.out.print("회사:"); iCompany = scan.nextLine();
             set.add(new PhoneCompanyInfo(iName,iPhone,iCompany));
    		 System.out.println("데이터 입력이 완료되었습니다.");
			 break;
		 default:
			 System.out.println("선택이 잘못되었습니다.");
			 break;
		 }
		 
	}
	
	//이름을 가지고 비교를 해서 검색 삭제를 하기 때문에 코드는 비슷하다.
	
	public void dataSearch() { //데이터 검색
		Scanner scan = new Scanner(System.in);
		System.out.println("데이터 검색을 시작합니다..");
		System.out.print("이름: ");
		String searchName = scan.nextLine();
		
		boolean searchFlag = false; //boolean타입 변수의 초기값을 false로 선언
		
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) { 
			PhoneInfo phoneInfo = (PhoneInfo)itr.next();
			if(searchName.equals(phoneInfo.name)) { //이름이 같은지 검사
				searchFlag = true;
				System.out.println(phoneInfo.toString()); //이름이 같으면 PhoneInfo.toString을 출력
				//toString()은 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드
			}
		}
		if(searchFlag == true) {
			System.out.println("검색한 정보를 찾았습니다"); 
		}else {
			System.out.println("해당 데이터는 없습니다."); 
		}
		
	}

	
	
	public void deleteInfo() { //데이터삭제
		Scanner scan = new Scanner(System.in);
		System.out.println("데이터 삭제를 시작합니다...");
		System.out.println("이름 : ");
		String deleteName = scan.nextLine();

		boolean deleteFlag = false;
	      
		Iterator<PhoneInfo> itr = set.iterator();
		while(itr.hasNext()) {
			PhoneInfo phoneInfo = (PhoneInfo) itr.next();
			if(deleteName.equals(phoneInfo.name)) {
				deleteFlag = true;
				itr.remove();
	         }
		}
		if(deleteFlag == true) {
			System.out.println("데이터를 삭제했습니다.");
			System.out.println("삭제 후 연락처 수 : " + set.size());
		}
		else {
			System.out.println("삭제할 데이터가 없습니다.");
		}
	}

	public void dataAllShow() {//데이터 전체출력
		
		for(PhoneInfo pi : set) { //set에 들어 있는 객체를 꺼내서 PhoneInfo에다 대입 후 출력
			System.out.println(pi.toString());
	
		}
		System.out.println("전체정보가 출력되었습니다.");
	}
}



