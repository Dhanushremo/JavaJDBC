import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PhonePayDemo {
	final static String  USERNAME ="root";
	final static String  PASSWORD="2327";
	final static String  URL="jdbc:mysql://localhost:3306/jdbc";
	final static String PASS="2327";
	static Connection con;
	static PreparedStatement s_pstmt;
	static PreparedStatement r_pstmt;
	public static void main(String[] args) {
		try {
			Scanner sc=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			con.setAutoCommit(false);//halt the executeUpdate Method until the user say!!
			String sql1="update bank set balance=balance-? where uname=?";
			String sql2="update bank set balance=balance+? where uname=?";        
			s_pstmt =con.prepareStatement(sql1);
			r_pstmt =con.prepareStatement(sql2);
			System.out.println("----Simple PhonePay Transcation----");
			System.out.println("Enter the Sender Name!!");
			String sname=sc.next();
			System.out.println("Enter amount to be Sent!!");
			int samt=sc.nextInt();
			s_pstmt.setInt(1, samt);
			s_pstmt.setString(2, sname);
			if(s_pstmt.executeUpdate()==1) {
				System.out.println("Amount Sending Suucessfully!!");
			}
			else {
				System.out.println("Amount NotSending Suucessfully!!");
			}
			System.out.println("Enter the Receiver Name");
			sc.nextLine();
			String rname=sc.next();
			sc.nextLine();
			r_pstmt.setString(2,rname);
			r_pstmt.setInt(1, samt);
			System.out.println("Enter the UPI PIN!!");
			String pin=sc.next();
			if(pin.equals(PASS)) {
				//con.setAutoCommit(true);
				con.commit();
				int res=r_pstmt.executeUpdate();
				System.out.println(res==1?"MonedReceviedSuccesfully":"MoneyNotReceviedSuccesfully");
			}
			else {
				con.rollback();
			}		
		}
		catch(Exception  e) {
			System.out.println(e.getMessage());
		}
	}

}
