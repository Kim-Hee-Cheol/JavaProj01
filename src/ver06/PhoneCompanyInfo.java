package ver06;

public class PhoneCompanyInfo extends PhoneInfo{

	String comName;

	public PhoneCompanyInfo(String name, String phoneNumber,
			String comName) {
		super(name, phoneNumber);
		this.comName = comName;
	}
	
	@Override
	public void showAllData() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phoneNumber);
		System.out.println("회사:"+ comName);
	}
	
}
	

