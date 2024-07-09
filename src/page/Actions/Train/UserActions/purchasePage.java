/*
 * Created by JFormDesigner on Mon Jul 08 09:02:32 CST 2024
 */

package page.Actions.Train.UserActions;

import sql.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/**
 * @author 23191
 */
public class purchasePage extends JDialog {
    int order_id = 1;
    String phoneNumber;
    String trainId;
    int carriages;
    int carriage_id = 0;
    int selectSeat = 0;
    boolean isP = false;
    String start_station;
    String end_station;
    String start_date;
    int t_price;
    public purchasePage(JFrame owner, String id, String phoneNumber) {
        super(owner, "购买车票", true);
        this.phoneNumber = phoneNumber;
        trainId = id;
        try {
            Statement pState = DBUtil.getStatement();
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
        isP = seatDialog.getPurchaseStat();
        t_price = (carriage_id <= 2)? 500 : 300;
        if (selectSeat != -1 && isP) {
            price.setText("价格 :" + t_price);
            price.repaint();
            price.setVisible(true);
        }
    }

    private void setComponents() {
        for (int i = 1; i <= carriages; i ++) comboBox1.addItem(i);
        c_trainId.setText(trainId);
        String text = "从 " + start_station + " 到 " + end_station;
        start_end.setText(text);
    }
    private void SureButton(ActionEvent e) {
        if (carriage_id != 0 && selectSeat != 0 && isP) {
            String sql = "INSERT INTO order_list (order_id, user_phone_number, train_id, start_station_name, end_station_name, carriage_id, seat_id, order_money, train_start_date) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ptmt = DBUtil.getPstmt(sql);
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
                    JOptionPane.showMessageDialog(this, "购票成功！");
                    dispose();
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
        setTitle("\u8d2d\u4e70\u8f66\u7968");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5f53\u524d\u6d4f\u89c8\u5217\u8f66\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(25, 20), label1.getPreferredSize()));

        //---- c_trainId ----
        c_trainId.setFont(c_trainId.getFont().deriveFont(c_trainId.getFont().getStyle() | Font.BOLD, c_trainId.getFont().getSize() + 5f));
        contentPane.add(c_trainId);
        c_trainId.setBounds(145, 20, 70, c_trainId.getPreferredSize().height);

        //---- start_end ----
        start_end.setFont(start_end.getFont().deriveFont(start_end.getFont().getStyle() | Font.BOLD, start_end.getFont().getSize() + 5f));
        contentPane.add(start_end);
        start_end.setBounds(new Rectangle(new Point(25, 60), start_end.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u9009\u62e9\u8f66\u53a2");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 5f));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(25, 95), label4.getPreferredSize()));
        contentPane.add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(110, 95), comboBox1.getPreferredSize()));

        //---- chooseSeat ----
        chooseSeat.setText("\u9009\u62e9\u5ea7\u4f4d");
        chooseSeat.setFont(chooseSeat.getFont().deriveFont(chooseSeat.getFont().getStyle() | Font.BOLD, chooseSeat.getFont().getSize() + 10f));
        chooseSeat.addActionListener(e -> chooseSeat(e));
        contentPane.add(chooseSeat);
        chooseSeat.setBounds(new Rectangle(new Point(20, 140), chooseSeat.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u786e\u5b9a\u8d2d\u7968");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(20, 200), button1.getPreferredSize()));

        //---- price ----
        price.setFont(price.getFont().deriveFont(price.getFont().getStyle() | Font.BOLD, price.getFont().getSize() + 5f));
        price.setText(" ");
        contentPane.add(price);
        price.setBounds(160, 145, 85, price.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(270, 260));
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
