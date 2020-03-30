package ver07;

public class PhoneInfo {
	
	//멤버변수
	String name;
	String phoneNumber ;
	//생성자
	public PhoneInfo(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		
	}
	public void showAllData() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		
	}
	
	
	
}


	