
import ver06.PhoneBookManager;
import ver06.SubMenuItem;
import ver06.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookVer06 implements MenuItem,SubMenuItem{
	
	public static void main(String[] args) {
		
		while(true) {
			PhoneBookManager manager = new PhoneBookManager(100);
			Scanner scan = new Scanner(System.in);
			manager.printMenu();
			try {
				int choice = scan.nextInt();
				switch(choice) {
				case MenuItem.INPUT:
					manager.dataInput();
					break;
				case MenuItem.SEARCH:
					manager.dataSearch();
					break;
				case MenuItem.DELETE:
					manager.deleteInfo();
					break;
				case MenuItem.EXIT:
					System.out.println("프로그램을 종료합니다.");
					return;
				}
			}
			catch(InputMismatchException e) {
				System.out.println("숫자만 입력해주세요");
			}
		}
	}
}




