
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchDataInDataBase {

	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
		con=Connector.myConnector();
		stmt=con.createStatement();
		rs=stmt.executeQuery("SELECT * FROM jdbc.college");
		System.out.println("Fetching the data from the DATABASE!!");
		System.out.println();
		while(rs.next()) {
			System.out.println("CollegeNumber "+rs.getInt(1));
			System.out.println("CollageLocation "+rs.getString(2));
			System.out.println("CollageId "+rs.getInt(3));
			System.out.println("-------------------------");
		}
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally {
		Connector.close(rs, stmt, con);
		}

	}

}
