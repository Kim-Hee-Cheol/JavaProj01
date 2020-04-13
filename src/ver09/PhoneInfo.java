package ver09;
/*
9단계[JDBC] : PhoneBookVer09.java
테이블 생성 
이름, 전화번호, 생년월일을 저장할수 있는 테이블을 생성한다.
테이블명 : phonebook_tb
primary key와 같은 제약조건도 걸어준다.
시퀀스 생성
시퀀스명 : seq_phonebook

입력 : dataInput()
PreparedStatement 클래스 이용
검색 : dataSearch()
Statement 클래스 이용
삭제 : dataDelete()
PreparedStatement 클래스 이용

위 부분을 DML문을 이용하여 구현한다. 
입력은 insert, 검색은 like를 이용한 select, 삭제는 delete로 구현하면 된다.
 */
public class PhoneInfo {
	
	//멤버변수
	String name;
	String phoneNumber; 
	String birthday;
	
	//생성자
	public PhoneInfo
		(String name, String phoneNumber, String birthday) {
		
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
	}
	
	//생년월일은 필수사항 아니므로 인자가 없는 경우 null로 초기화
	public PhoneInfo
		(String name, String phoneNumber) {
	
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthday = null;
	}
	
	//정보출력용 메소드
	public void showPhoneInfo() {
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phoneNumber);
		
		if(birthday == null) {
			System.out.println("생년월일 입력되지 않음");
		}
		else {
			System.out.println("생년월일 : " + birthday);
		}
	}    
}