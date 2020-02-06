package ������Ϸ;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/*------------------------ ���ݿ�����ӺͲ������֣��޸ķ����ķ���--------------------------------------*/
public class DataBaseLoding {
    private String driver;
    private String url;
    private String user;
    private String pass;
    //��½���ݿ�
    public void initParam(String paramFile) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    }
    //�����ֲ��뵽���ݿ���
    public int insertName(String Name) throws Exception {
        Class.forName(driver);
        String sql ="insert into ������Ϸ(name) values("+"'"+Name+"'"+")"+";";
        try (
            Connection conn =DriverManager.getConnection(url,user,pass);
            Statement stmt =conn.createStatement();
            )
        {
            System.out.println(Name);
            return stmt.executeUpdate(sql);
        }
    }
    //��score�嵽����Ϊname����һ��
    public int insertScore(int Score) throws Exception {
            Class.forName(driver);
            String sql ="update ������Ϸ set score ="+Score

                        +" where score = -1"+";";
            try (
                Connection conn =DriverManager.getConnection(url,user,pass);
                Statement stmt =conn.createStatement();
                )
            {
                System.out.println(Score);
                return stmt.executeUpdate(sql);
            }
        }
    public String select() throws Exception{
        Class.forName(driver);
        String sql ="select * from ������Ϸ  ORDER BY score DESC ;";
        String result ="";
        try (
                Connection conn =DriverManager.getConnection(url,user,pass);
                Statement stmt =conn.createStatement();
                ResultSet rs =stmt.executeQuery(sql);
                )
            {
                int i=1;
                while(rs.next()) {
                    result +="��"+i+"��"+ rs.getString(1)+"\t"+"\t"+rs.getInt(2)+"\r";
                    i++;
                    if(i==11) {
                        break;
                    }
                }
                return result;
            }
    }
}