package c.test;

import java.awt.Container;
import java.sql.*;
import java.util.*;

import javax.swing.RootPaneContainer;

public class Client {

    /**
     * 用以实现用户的注册和登录
     */
    private static String username;// 用户登录注册的姓名
    private static String password;// 用户密码
    private static String url = "jdbc:mysql://localhost:3306/db_test";// 连接数据库的url，test是我自己的一个数据库啊宝宝们。
    private static String user = "root";// mysql登录名
    private static String pass = "123456";// mysql登录密码（写自己之前设置的）
    private static Connection con;//
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // 加载数据库连接驱动并连接
        System.out.println("数据库连接中....");

        try {
            System.out.println("请输入连接数据库用户名：");
            String a = input.nextLine();
            System.out.println("请输入连接数据库密码：");          
            String b = input.nextLine();
            if (a.equals(user) && b.equals(pass)) {

                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
                System.out.println("数据库连接成功！");
            }else {
                System.out.println("输入的用户名或密码错误！请重新输入....");
                return;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("数据库连接失败！");
            return;
        }finally {

        }

        System.out.println("用户界面");
        System.out.println("请选择：\n 1:用户登录\n 2：用户注册");
        System.out.println("************");

        int i = input.nextInt();
        switch (i) {
        case 1:
            denglu();
            break;
        case 2:
            zhuce();
            break;
        default:
            System.out.println("输入有误！");
            System.exit(0);
        }

    }

    // 用户注册
    public static void zhuce() throws SQLException {
        System.out.println("请输入你的姓名：");
        username = input.next();
        System.out.println("请输入你的登录密码：");
        String p1 = input.next();
        System.out.println("请再次输入你的确认密码：");
        String p2 = input.next();
        if (p1.equals(p2)) {
            // 两次输入的密码相同才可以注册
            password = p1;
            String sql = "insert into user(username,passwd) values(?,?)";
            PreparedStatement ptmt = con.prepareStatement(sql);
            ptmt.setString(1, username);
            ptmt.setString(2, password);
            ptmt.execute();
            System.out.println("注册成功！\n请登录：");
            denglu();
        } else {
            System.out.println("你输入的密码与确认密码不相符，请重新注册：");
            zhuce();
        }

    }

    // 用户登录
    public static void denglu() throws SQLException {
        System.out.println("请输入你的姓名：");
        username = input.next();
        System.out.println("请输入你的密码：");
        password = input.next();

        String sql = "select username,passwd from user where username=? and passwd=?";
        PreparedStatement ptmt = con.prepareStatement(sql);
        ptmt.setString(1, username);
        ptmt.setString(2, password);
        ResultSet rs = ptmt.executeQuery();
        // 从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
        if (rs.next()) {
            System.out.println("登录成功！");
        } else {
            System.out.println("姓名或密码错误！\n请重新登录：");
            denglu();
        }

    }

}