
import ver07.PhoneBookManager;
import ver07.PhoneInfo;
import ver07.SubMenuItem;
import ver07.MenuItem;
import ver07.MenuSelectException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PhoneBookVer07 implements MenuItem,SubMenuItem{
	
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
							manager.dataAllShow();
							break;
						case MenuItem.EXIT:
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