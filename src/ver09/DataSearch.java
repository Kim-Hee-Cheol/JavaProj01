package ver09;

import java.sql.SQLException;

public class DataSearch extends ConnectDB{

		//생성자메소드
		public DataSearch() {
			super();
		}
		
		@Override
		public void execute() {
			try { 
				stmt = con.createStatement();
				
				String query = "SELECT name, phonenumber, birthday"
						+ " FROM phonebook_tb"
						+ " WHERE name like('%홍길동%')";
				
				rs = stmt.executeQuery(query);
				while(rs.next()) {
					String name = rs.getString("name");
					String phonenumber = rs.getString("phonenumber");
					/*
					오라클의 date타입을 getDate()로 추출하면
						2020-03-25 형태로 출력된다. 이 경우 date형 자료가 되기
						때문에 java.sql.Date클래스의 참조변수로 저장해야한다.
					 */
					java.sql.Date birthday = rs.getDate("birthday");
					System.out.printf("%-10s %-10s %-13s\n",
							name, phonenumber, birthday);
				}
			}
			catch(SQLException e) {
				System.out.println("쿼리오류발생");
				e.printStackTrace();
			}
			finally {
				close();
			}
		}

	public static void main(String[] args) {
		DataSearch dataSearch = new DataSearch();
		dataSearch.execute();
	}

}


