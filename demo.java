import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
public class demo {
    public static void main(String[] args) {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager
                .getConnection(URL,USER_NAME,PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}



public static void main(String[] args){
    try{
        Connection connection = DATASOURCE.getConnection
        System.out.println(connection);

             }catch(SQLEception)


        }
    public
        }