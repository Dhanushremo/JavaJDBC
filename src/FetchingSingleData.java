import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FetchingSingleData {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Connection con=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		ResultSet res=null;
		String q="select * from jdbc.student where stuid=?";
		int stuid;
		String stuname,studept,stuclgname;
		try {
			con=Connector.myConnector();
			pstmt=con.prepareStatement(q);
			stmt=con.createStatement();
			System.out.println("Fecthing the Data From DATABASE");
			res=stmt.executeQuery("select * from jdbc.student");

			while(res.next()==true) {
				stuid=res.getInt(1);
				stuname=res.getString(2);
				studept=res.getString(3);
				stuclgname=res.getString(4);

				System.out.printf("%-4d  %-10s  %-5s  %-5s \n",stuid,stuname,studept,stuclgname);
			}
			System.out.println("Fectching the Single Data!!");
			System.out.println("Enter The Student ID");
			int id=sc.nextInt();
			pstmt.setInt(1, id);
			res=pstmt.executeQuery();
			while(res.next()) {
				int sid=res.getInt("stuid");
				String sname=res.getString("stuname");
				String sdept=res.getString("studdept");
				String sclg=res.getString("stuclgname");

				System.out.print(sid+" "+sname+" "+sdept+" "+sclg);

			}
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
			Connector.close(res, stmt, con);
		}

	}

}
