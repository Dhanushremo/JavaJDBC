import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class StoredProcedures {
	private static  Connection con=null;
	private static CallableStatement prepCall=null;
	static Scanner sc=new Scanner(System.in);
	private static Statement stmt;
	private static ResultSet res;
	private static ResultSetMetaData rsmd;
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","2327");
			 
			 String sql="select * from employee";
			 stmt=con.createStatement();
			 res=stmt.executeQuery(sql);
			 rsmd=res.getMetaData();
			 
			 System.out.println("TABLE:- "+rsmd.getTableName(rsmd.getColumnCount()));
			 while(res.next()==true) {
				 System.out.printf("%-3d %-9s %-9s %-9s\n",
						 res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
			 }
			 System.out.println();
			 prepCall=con.prepareCall("{call emp_count_dept(?,?)}");
			 //{} for procedures,we have to mention the/call procedure in the{} only!!
			 System.out.print("Enter the Department Name to the Employess Count!!:-");
			 String dep=sc.next();
			 
			 prepCall.setString(1, dep);
			 //prepCall.registerOutParameter(2, 4);
			 prepCall.registerOutParameter(2,Types.INTEGER);
			 
			 prepCall.execute();
			 
			 int x=prepCall.getInt(2);
			 System.out.println("No.OF Employess in "+dep+" department are: "+x);
			 
			 prepCall=con.prepareCall("{call emp_salary_count(?,?)}");
			 System.out.println("Enter the Salary for the Employess Count!!:-");
			 int sal=sc.nextInt();
			 prepCall.setInt(1, sal);
			 prepCall.registerOutParameter(2,Types.INTEGER);
			 prepCall.execute();
			int y= prepCall.getInt(2);
			System.out.println("No.OF Employess in "+dep+" department are having salry "+sal+" is " +y);
			
			prepCall=con.prepareCall("{call emp_name_sal(?)}");
			System.out.println("Enter the Salary to See the Employee!!");
			int a=sc.nextInt();
			prepCall.setInt(1,a);
			
			
			prepCall.execute();
			
			res=prepCall.getResultSet();
			while(res.next()) {
				System.out.printf("%s\n",res.getString(1));
			}
			
			
	
			 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			Connector.close(null, prepCall, con);
		}
	}

}
