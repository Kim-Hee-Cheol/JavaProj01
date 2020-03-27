
import ver05.PhoneBookManager;
import ver05.PhoneInfo;
import ver05.SubMenuItem;
import ver05.MenuItem;

import java.util.Scanner;

public class PhoneBookVer05 implements MenuItem,SubMenuItem{
	


	public static void main(String[] args) {
		
		PhoneBookManager manager = new PhoneBookManager(100);
		
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			manager.printMenu();
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
	}
}