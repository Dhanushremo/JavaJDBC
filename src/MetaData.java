import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MetaData {
	private static final String URL="jdbc:mysql://localhost:3306/jdbc";
	private static final String SYSTEM="root";
	private static final String PASSWORD="2327";
	
	private static  Connection con = null;
	private static Statement stmt = null;
	private static ResultSet res = null;
	private static ResultSetMetaData rsmd = null;
	private static final  String QUERY = " select * from jdbc.student";
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(URL,SYSTEM,PASSWORD);
			stmt=con.createStatement();
			res=stmt.executeQuery(QUERY);
			
			rsmd=res.getMetaData();
//			System.out.println(rsmd);
			int x=rsmd.getColumnCount();
			System.out.println("No.Of Columns in the "+rsmd.getTableName(x)+" is "+x);
			for(int i=1;i<=x;i++) {
				System.out.println((i) +") Column\n\tColumnName:- "+rsmd.getColumnName(i)+"\n\tColumnDataType:- "+rsmd.getColumnTypeName(i)+"\n\tColumnTypeLenght:- "+
						rsmd.getColumnType(i)+"\n\tis ColumnNullable:- "+(rsmd.isNullable(i)==1?"Yes":"No")+"\n\tis Coulmn AI:- "
						+(rsmd.isAutoIncrement(i)?"Yes":"No")+"\n\n");
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Connector.close(res, stmt, con);
		}

	}

}
