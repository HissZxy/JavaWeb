package 弹球游戏;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/*------------------------ 数据库的连接和插入名字，修改分数的方法--------------------------------------*/
public class DataBaseLoding {
    private String driver;
    private String url;
    private String user;
    private String pass;
    //登陆数据库
    public void initParam(String paramFile) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream(paramFile));
        driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
    }
    //将名字插入到数据库中
    public int insertName(String Name) throws Exception {
        Class.forName(driver);
        String sql ="insert into 弹球游戏(name) values("+"'"+Name+"'"+")"+";";
        try (
            Connection conn =DriverManager.getConnection(url,user,pass);
            Statement stmt =conn.createStatement();
            )
        {
            System.out.println(Name);
            return stmt.executeUpdate(sql);
        }
    }
    //把score插到主键为name的那一行
    public int insertScore(int Score) throws Exception {
            Class.forName(driver);
            String sql ="update 弹球游戏 set score ="+Score

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
        String sql ="select * from 弹球游戏  ORDER BY score DESC ;";
        String result ="";
        try (
                Connection conn =DriverManager.getConnection(url,user,pass);
                Statement stmt =conn.createStatement();
                ResultSet rs =stmt.executeQuery(sql);
                )
            {
                int i=1;
                while(rs.next()) {
                    result +="第"+i+"名"+ rs.getString(1)+"\t"+"\t"+rs.getInt(2)+"\r";
                    i++;
                    if(i==11) {
                        break;
                    }
                }
                return result;
            }
    }
}