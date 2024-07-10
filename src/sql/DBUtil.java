package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBUtil {
    private static String USER = "root";
    private static String PWD = "lbc041103";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/train_station?useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
    private static Statement stmt;
    private static Connection con;

    private static DBUtil utils = null;
    private static PreparedStatement pstmt; // 预编译语句对象

    private DBUtil() {
    }

    public static void getInfo(String user, String pwd) {
        USER = user;
        PWD = pwd;
    }

    // 不是线程安全的 如果有并发访问实例化的时候会出现线程安全的问题，解决办法加同步锁synchronized
    public synchronized static DBUtil getDBUtil() {
        if (utils == null) {
            utils = new DBUtil();
            return utils;
        }
        return utils;
    }

    public static Connection getConn() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PWD);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return con;
    }

    public static Statement getStatement() {
        if (stmt == null) {
            try {
                if (con == null) {
                    con = getConn();
                }
                stmt = con.createStatement();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stmt;
    }

    // 预编译语句对象
    public static PreparedStatement getPstmt(String sql) {
        pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }


}