import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertDataByUser {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Connection con=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet res=null;
		String q="insert into student(stuid,stuname,studdept,stuclgname) values(?,?,?,?)";
		System.out.println("Enter The Details to Store in the DATABASE!!");
		System.out.println();
		System.out.println("Enter the Student ID");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Student NAME");
		String name=sc.next();
		System.out.println("Enter the Student DEPT");
		String dept=sc.next();
		System.out.println("Enter the Student CollegeNAME");
		String clg=sc.next();
		try {
			con=Connector.myConnector();
			//giving the data to preparedstatement by user!!
			pstmt=con.prepareStatement(q);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, dept);
			pstmt.setString(4, clg);
			
			int x=pstmt.executeUpdate();
			System.out.println(x+" row affected");
			
			stmt=con.createStatement();
			res=stmt.executeQuery("select * from jdbc.student");
			
			while(res.next()==true) {
				int sid=res.getInt(1);
				String sname=res.getString(2);
				String sdept=res.getString(3);
				String sclg=res.getString(4);
				System.out.printf("%-11d %-7s %-7s %-5s \n",sid,sname,sdept,sclg);
			}
			
			
		} 
		catch(ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		} 
		finally {
			Connector.close(res, stmt, con);
		}
	}

}
