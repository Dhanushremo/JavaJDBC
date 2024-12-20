import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class JDBC {
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/jdbc";
		String username="root";
		String password="2327";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the UserName:-");
		String name=sc.next();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet res=null;
		
		try {
		//Loading the Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Mr."+name+" Driver is LOADED Successfully");
		
		//Establishing the connection to database
		con=DriverManager.getConnection(url,username,password);
		System.out.println("Mr."+name+" Connection Established Successfully");
		System.out.println(con);
		
		//Creating a statement
		stmt=con.createStatement();
		System.out.println("Statement is Created Successfully");
		System.out.println(stmt);
		
		//executing the Query
		res=stmt.executeQuery("SELECT * FROM jdbc.employee;");
		System.out.println("Executed Successfulyy");
		System.out.println(res);
		
		//Process the Result
		System.out.println("Processing the ResultSet");
		while(res.next()) {
			System.out.println(res.getInt(1)+"   "+
								res.getString(2)+"  "+
								res.getString(3)+"  "+
								res.getInt(4)
					);
		}
		
		}
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch(SQLException s) {
			System.out.println(s.getMessage());
		}
		finally {
			try {
				res.close();
				System.out.println("ResultSet Is Closed!!");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
				System.out.println("Statement Is Closed!!");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				con.close();
				System.out.println("Connection Is Closed!!");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
