
import ver03.PhoneBookManager;
import ver03.PhoneInfo;
import java.util.Scanner;

public class PhoneBookVer03 {
	
	public static void printMenu() {
		System.out.println("선택하세요...");
		System.out.println("1.데이터 입력");
		System.out.println("2.데이터 검색");
		System.out.println("3.데이터 삭제");
		System.out.println("4.주소록 출력");
		System.out.println("5.프로그램 종료");
	}

	public static void main(String[] args) {
		
		PhoneBookManager manager = new PhoneBookManager(100);
		
		int choice;
		Scanner scan = new Scanner(System.in);
		while(true) {
			printMenu();
			choice = scan.nextInt();
			switch(choice) {
			case 1:
				manager.dataInput();
				break;
			case 2:
				manager.dataSearch();
				break;
			case 3:
				manager.deleteInfo();
				break;
			case 4:
				manager.dataAllShow();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				return;
			}
		}
	}
}