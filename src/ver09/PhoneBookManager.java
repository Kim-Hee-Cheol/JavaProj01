package ver09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PhoneBookManager {
	
	PreparedStatement psmt;
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	//데이터베이스 연결
	public PhoneBookManager() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin://@localhost:1521:orcl", 
							"kosmo","1234"
					);
			System.out.println("오라클 DB 연결성공");

			
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알수 없는 예외 발생");
		}
	}
	
	public void close() {
		try {
			if(psmt != null) psmt.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
			if(rs != null) rs.close();
         
		}
		catch(SQLException e) {
			System.out.println("자원 반납 시 오류가 발생하였습니다.");
		}
	}////end of close
	
	//메뉴출력
	public void menuShow() {
		System.out.println("선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 주소록 출력");
		System.out.println("5. 프로그램종료");
		System.out.print("선택 : ");
	}
	
	public void start() {
		
		while(true) {

			menuShow();

			Scanner scan = new Scanner(System.in);
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch (choice) {
			case 1: 
				dataInput();
				break;
			case 2:
				dataSearch();
				break;
			case 3:
				dataDelete();
				break;
			case 4:
				dataShow();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				close();
				return;
			}
		}
	}
	
	//친구정보 저장
	public void dataInput() {
		
		try {
			
			String query = "INSERT INTO phonebook_tb VALUES "
					+ "( ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			
			System.out.println("연락처 등록을 시작합니다..");
			Scanner scan = new Scanner(System.in);
			System.out.println("이름 : ");
			String name = scan.nextLine();
			System.out.println("전화번호 : ");
			String phone = scan.nextLine();
			System.out.println("생년월일 : ");
			String birth = scan.nextLine();
			
			psmt.setString(1, name);
			psmt.setString(2, phone);
			psmt.setString(3, birth);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
			System.out.println("연락처 등록 완료!");
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//친구정보 검색
	public void dataSearch() {
		try {
			
			stmt = con.createStatement();
			Scanner scan = new Scanner(System.in);
			System.out.println("검색할 이름 입력 : ");
			String inputName = scan.nextLine();
			
			String query = "SELECT * FROM phonebook_tb "
					+ "WHERE name LIKE " + "'" + inputName + "'";
			
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String name = rs.getString(2);
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");
				
				System.out.printf("이름 : %s\n전화번호 : %s\n생년월일 : %s\n",
						name, phone, birth);
				System.out.println("검색을 완료했습니다.");
				
			}
	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//친구정보 삭제
	public void dataDelete() {
		
		try {
			String query = "DELETE FROM phonebook_tb WHERE name = ?";
			
			Scanner scan = new Scanner(System.in);
			System.out.println("삭제할 이름 입력 : ");
			String inputStr = scan.nextLine();
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, inputStr);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 삭제되었습니다.");
			System.out.println("연락처 삭제 완료!");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dataShow() {
		
		try {

			System.out.println("주소록 출력을 시작합니다..");
			String query = "SELECT * FROM phonebook_tb";
			psmt = con.prepareStatement(query);

			rs = psmt.executeQuery(query);	
			while(rs.next()) {
				String name = rs.getString(2);
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");
				
				System.out.printf("이름 : %s\n전화번호 : %s\n생년월일 : %s\n",
						name, phone, birth);
			}
			
			System.out.println("주소록 출력이 완료되었습니다.");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}