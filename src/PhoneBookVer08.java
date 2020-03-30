
import ver08.PhoneBookManager;
import ver08.PhoneInfo;
import ver08.SubMenuItem;
import ver08.MenuItem;
import ver08.MenuSelectException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookVer08 implements MenuItem,SubMenuItem{
	
	public static void main(String[] args) {
		
		PhoneBookManager manager = new PhoneBookManager(100);
		while(true) {
			Scanner scan = new Scanner(System.in);
			manager.printMenu();
			try {
				int choice = scan.nextInt();
				if(choice>=1 && choice<=5) {
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
						case 4:
							manager.callin();
							break;
						case MenuItem.EXIT:
							manager.saveAddressInfo();
							System.out.println("프로그램을 종료합니다.");
						return;	
					}
					
				}
				else {
					throw new MenuSelectException("1~5사이의 값만 입력해주세요"); 
				}
			}
			catch(InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
			}
			catch(MenuSelectException e){
				System.out.println(e.getMessage());
			}
			continue;
		}
		
	}
}