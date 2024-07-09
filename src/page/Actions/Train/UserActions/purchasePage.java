/*
 * Created by JFormDesigner on Mon Jul 08 09:02:32 CST 2024
 */

package page.manage.Train.UserActions;

import tools.createSeatInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 23191
 */
public class purchasePage extends JDialog {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312"; // 替换为你的数据库URL
    private static final String DB_USER = "root"; // 替换为你的数据库用户名
    private static final String DB_PASSWORD = "lbc041103"; // 替换为你的数据库密码
    int order_id = 1;
    String phoneNumber;
    String trainId;
    int carriages;
    int carriage_id = 0;
    int selectSeat = 0;
    String start_station;
    String end_station;
    String start_date;
    int t_price;
    Connection conn;
    public purchasePage(JFrame owner, String id, String phoneNumber) {
        super(owner, "购买车票", true);
        this.phoneNumber = phoneNumber;
        trainId = id;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement pState = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM order_list";
            ResultSet rs2 = pState.executeQuery(sql);
            if (rs2.next()) {
                order_id = rs2.getInt(1) + 1;
            }
            sql = "SELECT * FROM train_info WHERE train_id = '" + id + "'";
            ResultSet rs1 = pState.executeQuery(sql);
            if (rs1.next()) {
                carriages = rs1.getInt(3);
                start_station = rs1.getString(5);
                end_station = rs1.getString(6);
                start_date = rs1.getString(9);
                System.out.println(carriages);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initComponents();
        setVisible(true);
    }

    private void chooseSeat(ActionEvent e) {
        carriage_id = comboBox1.getSelectedIndex() + 1;
        TrainSeatDialog seatDialog = new TrainSeatDialog(this, trainId, carriage_id);
        selectSeat = seatDialog.getSelectedSeat();
        t_price = (carriage_id <= 2)? 500 : 300;
        price.setText("价格 :" + t_price);
    }

    private void setComponents() {
        for (int i = 1; i <= carriages; i ++) comboBox1.addItem(i);
        c_trainId.setText(trainId);
        String text = "从 " + start_station + " 到 " + end_station;
        start_end.setText(text);
    }
    private void SureButton(ActionEvent e) {
        if (carriage_id != 0 && selectSeat != 0) {
            String sql = "INSERT INTO order_list (order_id, user_phone_number, train_id, start_station_name, end_station_name, carriage_id, seat_id, order_money, train_start_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ptmt = conn.prepareStatement(sql);
                {
                    ptmt.setInt(1, order_id);
                    ptmt.setString(2, phoneNumber);
                    ptmt.setString(3, trainId);
                    ptmt.setString(4, start_station);
                    ptmt.setString(5, end_station);
                    ptmt.setInt(6, carriage_id);
                    ptmt.setInt(7, selectSeat);
                    ptmt.setInt(8, t_price);
                    ptmt.setString(9, start_date);
                    int rowsAffected = ptmt.executeUpdate();
                    System.out.println("Inserted " + rowsAffected + " row(s).");
                }
                
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "缺少关键信息！");
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        c_trainId = new JLabel();
        start_end = new JLabel();
        label4 = new JLabel();
        comboBox1 = new JComboBox();
        chooseSeat = new JButton();
        button1 = new JButton();
        price = new JLabel();
        setComponents();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5f53\u524d\u6d4f\u89c8\u5217\u8f66\uff1a");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(30, 20), label1.getPreferredSize()));
        contentPane.add(c_trainId);
        c_trainId.setBounds(new Rectangle(new Point(125, 20), c_trainId.getPreferredSize()));
        contentPane.add(start_end);
        start_end.setBounds(new Rectangle(new Point(30, 50), start_end.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u9009\u62e9\u8f66\u53a2");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(30, 95), label4.getPreferredSize()));
        contentPane.add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(105, 90), comboBox1.getPreferredSize()));

        //---- chooseSeat ----
        chooseSeat.setText("\u9009\u62e9\u5ea7\u4f4d");
        chooseSeat.addActionListener(e -> chooseSeat(e));
        contentPane.add(chooseSeat);
        chooseSeat.setBounds(new Rectangle(new Point(25, 145), chooseSeat.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u786e\u5b9a\u8d2d\u7968");
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(25, 210), button1.getPreferredSize()));

        //---- price ----
        price.setText("\u4ef7\u683c");
        contentPane.add(price);
        price.setBounds(125, 150, 70, price.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(470, 355));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel c_trainId;
    private JLabel start_end;
    private JLabel label4;
    private JComboBox comboBox1;
    private JButton chooseSeat;
    private JButton button1;
    private JLabel price;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
