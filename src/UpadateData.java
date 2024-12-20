import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpadateData {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		ResultSet res=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","2327");
			stmt=con.createStatement();
			res=stmt.executeQuery("select * from jdbc.student");
			while(res.next())
			{
				System.out.printf("%-5d %-8s %-5s %-5s \n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
			}
			int x = stmt.executeUpdate("UPDATE student SET stuname = 'jd' WHERE stuid = 11");
			System.out.println(x+" row is affected");
			res=stmt.executeQuery("select * from jdbc.student");
			while(res.next())
			{
				System.out.printf("%-5d %-8s %-5s %-5s \n",res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(res!=null) {
					res.close();
				}
				if(stmt!=null) {
					stmt.close();
				}
				if(con!=null) {
					con.close();
				}
			}
			catch(Exception e ) {
				e.printStackTrace();
			}
		}


	}

}
