import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    public static void main(String[] args) {
        try {
            Connection connection = Dbutil.getConnection();

            PreparedStatement ps = connection preparScatment(sql);
            ps.setInt(parameterindex1, 3);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt(columnLabel"id");
                Integer sn = rs.getInt(columnLabel"sn");
                System.out.println(String.format);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

    }

}
