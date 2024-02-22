import java.sql.*;


public class App {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/<uswa>","root","hello");
            Statement stmt = conn.createStatement();
            String a ="Insert into student value('Uswa','20i-0809')";
            stmt.execute(a);
            conn.close();
        }
        catch(Exception e )
        {
            System.out.println(e);
            
        }
    }
}
