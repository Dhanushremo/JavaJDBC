import java.sql.Connection;
import java.sql.Statement;

public class DeleteData {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		String sql="delete from student where stuid=578";
		try {
		con=Connector.myConnector();
		stmt=con.createStatement();
		System.out.println(stmt.executeUpdate(sql)+" row is deleted");
		
		}
		catch(Exception e){
			e.printStackTrace();		
		}
		finally {
			Connector.close(null, stmt, con);
		}

	}

}
