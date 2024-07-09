package sys.init;

import page.welcome.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class init {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
        String user = "root";
        String password = "lbc041103";

        Connection con;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }

        new welcomePage();
    }

}

class zhuCe {
    public zhuCe(String username, String userPhoneNumber, String pwd) {

    }
}