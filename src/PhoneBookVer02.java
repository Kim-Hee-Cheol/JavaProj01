
import java.util.Scanner;
import ver02.PhoneInfo;

public class PhoneBookVer02 {
	
public static void showMenu() {
	System.out.println("1.데이터 입력");
	System.out.println("2.프로그램 종료");
}

public static void addAdress() {
	 
	 Scanner scan = new Scanner(System.in);
	 String Name,Phone,Bir;
	 
	 System.out.print("이름:");Name = scan.nextLine();
	 System.out.print("전화번호:");Phone = scan.nextLine();
	 System.out.print("생년월일:");Bir = scan.nextLine();
	 PhoneInfo phoneInfo = new PhoneInfo(Name, Phone, Bir);
	 System.out.println("입력된 정보 출력...");
	 phoneInfo.showPhoneInfo();
}
	public static void main(String[] args) {
		
		int choice;
		Scanner scan = new Scanner(System.in);
		while(true) {
			showMenu();
			choice = scan.nextInt();
			switch(choice) {
			case 1:
				addAdress();
				break;
			case 2:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}
}