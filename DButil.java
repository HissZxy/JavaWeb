import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButil {
    private static final String URL = "jdbc:mysql://";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static MysqlDataSource DATASOURCE =
            new MysqlDataSource();

    static {
        DATASOURCE.setUrl(URL);
        DATASOURCE.setUser(USER_NAME);
        DATASOURCE.SetPassword(PASSWORD);
    }

    /**
     * 封装一个connection链接来获取数据库链接
     * 1.class.forname(com.mysql.jdbc.Driver);
     * DriverManager.getConnection
     * rs操作结果集
     * ps数据库操作对象
     */
    public static Connection getConnection() {

        try {
            return DATASOURCE.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("获取数据库异常");
        }
    }
public static void close (Connection connection
PreparedStatement ps, ResultSet rs){
        try{
            if(rs != null)
                rs.close();
            is(ps != null)
                ps.close();
            if(connection != null)
                connection.close();
        }catch (SQLException e){
            throw new RuntimeException("释放数据库资源错误");
        }
}
    public static void main(String[] args) {
        System.out.println(getConnection());

    }
}