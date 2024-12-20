import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UserLogin {
	static Connection con;
	static Statement stmt;
	static ResultSet res;
	static PreparedStatement pstmt;
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","2327");
			stmt=con.createStatement();
			res=stmt.executeQuery("select * from college");
			System.out.println("Fetching all the Data");
			while(res.next()) {
				System.out.printf("%-2d %-7s %-2d\n",res.getInt(1),res.getString(2),res.getInt(3));
			}
			System.out.println("Enter College Number");
			int num=sc.nextInt();
			pstmt=con.prepareStatement("select * from college where clgnum=?");
			pstmt.setInt(1, num);
			res=pstmt.executeQuery();
			int c1=0;
			while(res.next()) {
				 c1=res.getInt(1);
				
			}
			if(c1==num) {
				System.out.println("LOGGED SUCCESSFULLY");
			}
			else {
				System.out.println("LOGGED FAILED!!");
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
