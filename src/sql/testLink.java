package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class testLink { // 创建类Conn
    Connection con; // 声明Connection对象
    private static final String url = "jdbc:mysql://localhost:3306/train_station?useUnicode=true&characterEncoding=gbk";
    public boolean test_Link(String user, String pwd) {
        boolean isC = true;
        try { // 加载数据库驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            isC = false;
            e.printStackTrace();
        }
        try { // 通过访问数据库的URL获取数据库连接对象
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            isC = false;
            e.printStackTrace();
        }
        return isC;
    }
}

