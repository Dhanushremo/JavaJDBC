import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateByUser {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		String sql="update student set stuclgname=? where stuid=? ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","2327");
			pstmt=con.prepareStatement(sql);
			stmt=con.createStatement();
			System.out.println("Fetching all the data from the DATABASE!!");
			res=stmt.executeQuery("select * from jdbc.student");
			while(res.next()) {
				System.out.printf("%-5d %-8s %-5s %-5s \n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
			}
			System.out.println();
			System.out.println("Enter the Clg Name to Update");
			String cname=new Scanner(System.in).next();
			System.out.println("Enter the Student Id to update the CollegeName");
			int sid=new Scanner(System.in).nextInt();
			
			pstmt.setString(1, cname);
			pstmt.setInt(2, sid);
			
			System.out.println(pstmt.executeUpdate()+" rows is affected");
			System.out.println();
			System.out.println("Fetching all the data from the DATABASE!!");
			res=stmt.executeQuery("select * from jdbc.student");
			while(res.next()) {
				System.out.printf("%-5d %-8s %-5s %-5s \n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Connector.close(null, stmt, con);
		}

	}

}
