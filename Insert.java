import java.sql.Connection;
import java.sql.PreparedStatement;

public class Insert {


}
public boolean insertStudent(Student student){
    Connection connection = null;
    PreparedStatement ps = null;
    try{
        connection = DButil.getConnection();
        String sql = "insert into student(id," + "sn,name,qq_mail,classes_id)values" + "(?,?,?,?,?,)";
        ps = connection.prepareStatement(sql);
        ps.setInt(parameterIndex:1sutdent.getId());
        ps.setInt(parameterIndex:2student.getSn());
        ps.setString())
    }
}