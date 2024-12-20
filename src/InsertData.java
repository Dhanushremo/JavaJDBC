import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

	public static void main(String[] args) {
		
		Connection con=null;
		Statement stmt=null;
		String q="insert into student(stuid,stuname,studdept,stuclgname) values(555,'REMO','AI/ML','MTIET')";
		try {
			con=Connector.myConnector();
			stmt=con.createStatement();
			int x=stmt.executeUpdate(q);
			System.out.println("The OutPut is "+x);
			
			
		} 
		catch (ClassNotFoundException | SQLException e) {	
			e.printStackTrace();
		} 
		finally {
			Connector.close(null, stmt, con);
		}

	}

}
