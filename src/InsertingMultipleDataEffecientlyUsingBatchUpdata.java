
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class InsertingMultipleDataEffecientlyUsingBatchUpdata {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet res=null;
		String sqlInsert="insert into student(stuid,stuname,studdept,stuclgname) values(?,?,?,?)";
		String sqlSelect="select * from jdbc.student";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","2327");
			pstmt=con.prepareStatement(sqlInsert);
			stmt=con.createStatement();
			res=stmt.executeQuery(sqlSelect);
			System.out.println("Fetching all the data from jdbc.student TABLE!!");
			while(res.next()==true) {
				System.out.printf("%-5d %-9s %-5s %-5s \n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
			}
			System.out.println();
			for(;;) {
				System.out.println("Inserting the data into jdbc.student TABLE");
				System.out.println("ENTER THE STUDENT ID");
				int sid=new Scanner(System.in).nextInt();
				System.out.println("ENTER THE STUDENT NAME");
				String sname=new Scanner(System.in).nextLine();
				System.out.println("ENTER THE STUDENT DEPARTMENT");
				String sdept=new Scanner(System.in).nextLine();
				System.out.println("ENTER THE STUDENT COLLEGE NAME");
				String sclg=new Scanner(System.in).nextLine();

				pstmt.setInt(1, sid);
				pstmt.setString(2,sname);
				pstmt.setString(3, sdept);
				pstmt.setString(4, sclg);
				//adding all the data into the (truke)
				pstmt.addBatch();
				
				
				
				System.out.println("DO YOU WANT INSERT MORE ->click(y/n)");
				String option=new Scanner(System.in).nextLine();
				if(option.equalsIgnoreCase("n")) {
					break;
				}
			}
			System.out.println();
			//executing all the data once to avoid the crash on database!1
			int[] a=pstmt.executeBatch();
			for(int row:a) {
				System.out.println(row +" row is affected");
			}
			System.out.println();
			res=stmt.executeQuery(sqlSelect);
			System.out.println("Fetching all the data from jdbc.student TABLE!!");
			while(res.next()==true) {
				System.out.printf("%-5d %-9s %-5s %-5s \n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
				Connector.close(res, stmt, con);
		}
	}

}
