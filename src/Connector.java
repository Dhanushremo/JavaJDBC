import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	 static String url="jdbc:mysql://localhost:3306/jdbc";
	 static String name="root";
	 static String password="2327";
	
	static Connection con=null;
	
		static Connection myConnector() throws ClassNotFoundException,SQLException{				
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,name,password);
			return con;
		}
		
		static void close(ResultSet rs,Statement stmt,Connection con) {
			 
				try {
					if(rs!=null)rs.close();
					if(stmt!=null)stmt.close();
					if(con!=null)con.close();
				} 
				catch (SQLException e) {
					
					e.printStackTrace();
					}
		}
		
	}
	
