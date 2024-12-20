import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FectchDataInCorrectOrder {

	public static void main(String[] args) {
		
		Connection con=null;
		ResultSet res=null;
		Statement stmt=null;
		String q="select * from jdbc.student";
		int stuid;
		String	stuname,studept,stuclgname;
		try {
		con=Connector.myConnector();
		stmt=con.createStatement();
		res=stmt.executeQuery(q);
		System.out.println("Fetching the Data From DATABASE!!");
		while(res.next()==true) {
			stuid=res.getInt(1);
			stuname=res.getString(2);
			studept=res.getString(3);
			stuclgname=res.getString(4);
			
			System.out.printf("%-4d  %-10s  %-5s  %-5s \n",stuid,stuname,studept,stuclgname);
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
