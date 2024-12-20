import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FetchData {

	public static void main(String[] args) {
	
		String url="jdbc:mysql://localhost:3306/jdbc";
		String name="root";
		String password="2327";
		Connection con=null;
		Statement stmt=null;
		ResultSet res=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,name,password);
			stmt=con.createStatement();
			res=stmt.executeQuery("SELECT * FROM jdbc.student");
			System.out.println("Fetching the data from the DATABASE");
			System.out.println();
				while(res.next()==true) {
					System.out.println("StudentID "+res.getInt(1));
					System.out.println("StudentName "+res.getString(2));
					System.out.println("StudentDepartment "+res.getString(3));
					System.out.println("StudentCollegeName "+res.getString(4));
					System.out.println("------------------");
				}
			}
			catch(ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		finally {
			try {
				res.close();
				stmt.close();
				con.close();
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
				}
		}
	}
	}
	


