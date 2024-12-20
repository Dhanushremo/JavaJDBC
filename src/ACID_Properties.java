import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ACID_Properties {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
	private static final String SYSTEM = "root";
	private static final String PASSWORD = "2327";
	static Scanner sc = new Scanner(System.in);
	private static Connection con = null;
	private static PreparedStatement pstmt_s = null;
	private static PreparedStatement pstmt_r = null;
	private static int pin = 2327;
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, SYSTEM, PASSWORD);
			con.setAutoCommit(false);//halt the executeUpdate() until user say
			String sql1="update bank set balance=balance-? where uname=?";
			
			pstmt_s=con.prepareStatement(sql1);
			System.out.println("*****SIMPLE/PHONEPAY/TRANSACTION*****");
			System.out.print("Enter the Sender UserName:-");
			String sname=sc.nextLine();
			System.out.print("Enter the Money to be Sent:-");
			int sAmnt=sc.nextInt();
			sc.nextLine();
			pstmt_s.setInt(1, sAmnt);
			pstmt_s.setString(2, sname);
			
			int x=pstmt_s.executeUpdate();
			System.out.println(x==1?"MoneySendingSuccessfulyy!!":"MoneyNotSendingSuccessfulyy!!");
			
			String sql2="update bank set balance=balance+? where uname=?";
			
			pstmt_r=con.prepareStatement(sql2);
			
			System.out.print("Enter the Recevier UserName:-");
			String rname=sc.nextLine();

			pstmt_r.setInt(1, sAmnt);
			pstmt_r.setString(2, rname);
			
			int y=pstmt_r.executeUpdate();
			System.out.print("Enter the UPI pin Mr.Dhanush:-");
			int upin=sc.nextInt();
			if(upin==pin && x==1 && y==1) {
			con.setAutoCommit(true);
				//con.commit();
				System.out.println(y==1?"MoneyReceivedSuccessfulyy!!":"MoneyNotReceivedSuccessfulyy!!");
			}
			else {
				System.out.println(y==1?"MoneyReceivedSuccessfulyy!!":"MoneyNotReceivedSuccessfulyy!!");
				con.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connector.close(null, pstmt_r, con);
		}
	}
}
