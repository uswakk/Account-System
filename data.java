package u;

import java.sql.*;

public class main {
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uswa","root","hello123");
			Statement stmt = conn.createStatement();
			String str ="Insert into Studentinfo value('uswa','20i-0809', '20', 'E')";
			stmt.execute(str);
			conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			
			
		}
	}

}
