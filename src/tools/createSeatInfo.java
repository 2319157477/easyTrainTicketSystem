package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class createSeatInfo {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312"; // 替换为你的数据库URL
    private static final String DB_USER = "root"; // 替换为你的数据库用户名
    private static final String DB_PASSWORD = "lbc041103"; // 替换为你的数据库密码
    static Connection conn = null;
    static PreparedStatement pstmtSelect = null;
    static PreparedStatement pstmtInsert = null;
    static ResultSet rs = null;
    public static void main(String[] args) {
        createAll();
    }

    static void createAll() {
        try {
            // 1. 获取数据库连接
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2. 查询train_info表中的train_id和train_carriage
            String selectSQL = "SELECT train_id, train_carriage FROM train_info";
            pstmtSelect = conn.prepareStatement(selectSQL);
            rs = pstmtSelect.executeQuery();

            // 3. 准备插入seat表的SQL
            String insertSQL = "INSERT INTO seat (train_id, carriage_id, seat_type, seat_count) VALUES (?, ?, ?, ?)";
            pstmtInsert = conn.prepareStatement(insertSQL);

            // 4. 遍历结果集，并插入seat表
            while (rs.next()) {
                String trainId = rs.getString("train_id");
                int trainCarriage = rs.getInt("train_carriage");

                for (int carriageNumber = 1; carriageNumber <= trainCarriage; carriageNumber++) {
                    int seatType = (carriageNumber <= 2) ? 1 : 0; // 前两条记录seat_type为1，其余为0
                    pstmtInsert.setString(1, trainId);
                    pstmtInsert.setInt(2, carriageNumber);
                    pstmtInsert.setInt(3, seatType);
                    pstmtInsert.setInt(4, 60); // seat_count设置为60
                    pstmtInsert.executeUpdate();
                }
            }

            System.out.println("Records inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmtSelect != null) pstmtSelect.close();
                if (pstmtInsert != null) pstmtInsert.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createSeat(String searchId) {
        try {
            // 1. 获取数据库连接
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2. 查询train_info表中的train_id和train_carriage
            String selectSQL = "SELECT train_carriage FROM train_info WHERE train_id = '" + searchId + "'";
            pstmtSelect = conn.prepareStatement(selectSQL);
            rs = pstmtSelect.executeQuery();

            // 3. 准备插入seat表的SQL
            String insertSQL = "INSERT INTO seat (train_id, carriage_id, seat_type, seat_count) VALUES (?, ?, ?, ?)";
            pstmtInsert = conn.prepareStatement(insertSQL);

            // 4. 遍历结果集，并插入seat表
            while (rs.next()) {
                int trainCarriage = rs.getInt("train_carriage");

                for (int carriageNumber = 1; carriageNumber <= trainCarriage; carriageNumber++) {
                    int seatType = (carriageNumber <= 2) ? 1 : 0; // 前两条记录seat_type为1，其余为0
                    pstmtInsert.setString(1, searchId);
                    pstmtInsert.setInt(2, carriageNumber);
                    pstmtInsert.setInt(3, seatType);
                    pstmtInsert.setInt(4, 60); // seat_count设置为60
                    pstmtInsert.executeUpdate();
                }
            }

            System.out.println("Records inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmtSelect != null) pstmtSelect.close();
                if (pstmtInsert != null) pstmtInsert.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}