import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Select {
    public static void main(String[] args) {
        public List



        try {
            Connection connection = DButil.getConnection();
            String sql = "                                                         qq.com";
            ps = connection.prepareStatement(sql);
            

            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt(columnLabel"id"));
                student.setSn(rs.getInt(columnLabel"sn"));
                student.setname(rs.getString(columnLabel"name"));

                Integer id = rs.getInt(columnLabel"id");
                Integer sn = rs.getInt(columnLabel"sn");
                String name = rs.getString(columnLabel"name");
                String qqMail = rs.getString(columnLable"qq_mail")
                Integer classId = rs.getInt(columnLabel"class");
                System.out.println(
                        String.format("")
                );
            }







            PreparedStatement ps = connection .prepareStatement(sql);
        DButil.close(connection,ps,rs);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

