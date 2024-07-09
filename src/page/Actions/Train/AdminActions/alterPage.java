/*
 * Created by JFormDesigner on Sat Jul 06 22:20:56 CST 2024
 */

package page.manage.Train.AdminActions;

import tools.FormattedTime;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * @author 23191
 */
public class alterPage extends JDialog {

    String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
    String user = "root";
    String password = "lbc041103";
    Connection alter_con;
    Statement alterState;
    PreparedStatement pstmt = null;
    String c_id;
    String c_type;
    String c_carriages;
    String c_start_station;
    String c_end_station;
    String c_start_time;
    String c_end_time;
    String c_running_time;
    String c_arrive_day;
    public alterPage(String alter_id, JFrame parent) {
        super(parent, "修改列车信息", true);
        try {
            alter_con = DriverManager.getConnection(url, user, password);
            //System.out.println("成功连接!");
            alterState = alter_con.createStatement();
            String sql = "SELECT * FROM train_info WHERE train_id = '" + alter_id + "'";
            //System.out.println(sql);
            ResultSet rs1 = alterState.executeQuery(sql);
            if (rs1.next()) {
                c_id = alter_id;
                c_type = rs1.getString(2);
                c_carriages = rs1.getString(3);
                c_start_station = rs1.getString(5);
                c_end_station = rs1.getString(6);
                c_start_time = rs1.getString(7);
                c_end_time = rs1.getString(8);
                c_running_time = rs1.getString(10);
                c_arrive_day = rs1.getString(9);
            }
        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
        initComponents(alter_id);
        setVisible(true);
    }
    private void SureButton(ActionEvent e) {
        String a_type = String.valueOf(typeCBox.getSelectedIndex());
        int a_carriages = Integer.parseInt(Carriages.getText());
        String a_start_station = start_station.getText();
        String a_end_station = end_station.getText();
        String a_start_time = start_h.getSelectedItem() + ":" + start_m.getSelectedItem();
        String a_end_time = end_h.getSelectedItem() + ":" + end_m.getSelectedItem();
        String a_arrive = "2024-" + arriveMonth.getSelectedItem() + "-" + arriveDay.getSelectedItem();

        String sql = "UPDATE train_info SET train_type = ?, train_carriage = ?, train_start_station = ?, train_end_station = ?, train_start_time = ?, train_end_time = ?, train_arrive_day = ? WHERE train_id = '" + c_id + "'";
        System.out.println(sql);
        try {
            pstmt = alter_con.prepareStatement(sql);

            pstmt.setString(1, a_type);
            pstmt.setInt(2, a_carriages);
            pstmt.setString(3, a_start_station);
            pstmt.setString(4, a_end_station);
            pstmt.setString(5, a_start_time);
            pstmt.setString(6, a_end_time);
            pstmt.setString(7, a_arrive);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        this.dispose();
    }
    private void createUIComponents() {
        start_station.setText(c_start_station);
        end_station.setText(c_end_station);
        typeCBox.addItem("普快");
        typeCBox.addItem("高铁");
        if(c_type.equals("1")) {typeCBox.setSelectedItem("高铁");}else {typeCBox.setSelectedItem("普快");}
        for (int i = 15; i < 21; i ++) Carriages.setText(c_carriages);
        FormattedTime time = new FormattedTime();
        for (int i = 0; i <= 24; i++) {
            String timeString = String.format("%02d", i);
            end_h.addItem(timeString);
        }
        end_h.setSelectedItem(time.getFormattedTime(c_end_time, "hour"));
        for (int i = 0; i <= 50; i += 10) {
            String numberString = String.format("%02d", i);
            end_m.addItem(numberString);
        }
        end_m.setSelectedItem(time.getFormattedTime(c_end_time, "m"));
        for (int i = 0; i <= 50; i += 10) {
            String numberString = String.format("%02d", i);
            start_m.addItem(numberString);
        }
        start_m.setSelectedItem(time.getFormattedTime(c_start_time, "m"));
        for (int i = 0; i <= 24; i++) {
            String timeString = String.format("%02d", i);
            start_h.addItem(timeString);
        }
        start_h.setSelectedItem(time.getFormattedTime(c_start_time, "hour"));
        for (int i = 0; i <= 12; i++) {
            String monthString = String.format("%02d", i);
            arriveMonth.addItem(monthString);
        }
        arriveMonth.setSelectedItem(time.getFormattedDay(c_arrive_day, "M"));
        for (int i = 0; i <= 31; i++) {
            String dayString = String.format("%02d", i);
            arriveDay.addItem(dayString);
        }
        arriveDay.setSelectedItem(time.getFormattedDay(c_arrive_day, "D"));
        alter_id.setText(c_id);
    }
    private void initComponents(String id) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        typeCBox = new JComboBox();
        label3 = new JLabel();
        Carriages = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        start_station = new JTextField();
        end_station = new JTextField();
        label6 = new JLabel();
        label7 = new JLabel();
        end_h = new JComboBox();
        label8 = new JLabel();
        end_m = new JComboBox();
        label9 = new JLabel();
        start_m = new JComboBox();
        start_h = new JComboBox();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        button1 = new JButton();
        alter_id = new JLabel();
        arriveMonth = new JComboBox();
        arriveDay = new JComboBox();
        label14 = new JLabel();
        label15 = new JLabel();
        createUIComponents();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5f53\u524d\u4fee\u6539\u5217\u8f66\u7f16\u53f7:");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 15), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5217\u8f66\u7c7b\u578b");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(20, 50), label2.getPreferredSize()));
        contentPane.add(typeCBox);
        typeCBox.setBounds(new Rectangle(new Point(115, 45), typeCBox.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u5217\u8f66\u8f66\u53a2\u6570");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(20, 90), label3.getPreferredSize()));
        contentPane.add(Carriages);
        Carriages.setBounds(115, 85, 75, Carriages.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u59cb\u53d1\u7ad9");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(20, 130), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u7ec8\u70b9\u7ad9");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(20, 175), label5.getPreferredSize()));
        contentPane.add(start_station);
        start_station.setBounds(115, 125, 75, start_station.getPreferredSize().height);
        contentPane.add(end_station);
        end_station.setBounds(115, 170, 75, end_station.getPreferredSize().height);

        //---- label6 ----
        label6.setText("\u53d1\u8f66\u65f6\u95f4");
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(250, 50), label6.getPreferredSize()));

        //---- label7 ----
        label7.setText("\u5230\u7ad9\u65f6\u95f4");
        contentPane.add(label7);
        label7.setBounds(new Rectangle(new Point(250, 90), label7.getPreferredSize()));
        contentPane.add(end_h);
        end_h.setBounds(330, 85, 65, end_h.getPreferredSize().height);

        //---- label8 ----
        label8.setText("\u65f6");
        contentPane.add(label8);
        label8.setBounds(405, 90, 15, 17);
        contentPane.add(end_m);
        end_m.setBounds(430, 85, 65, end_m.getPreferredSize().height);

        //---- label9 ----
        label9.setText("\u5206");
        contentPane.add(label9);
        label9.setBounds(new Rectangle(new Point(510, 90), label9.getPreferredSize()));
        contentPane.add(start_m);
        start_m.setBounds(430, 45, 65, 30);
        contentPane.add(start_h);
        start_h.setBounds(330, 45, 65, 30);

        //---- label10 ----
        label10.setText("\u65f6");
        contentPane.add(label10);
        label10.setBounds(405, 50, 15, 17);

        //---- label11 ----
        label11.setText("\u5206");
        contentPane.add(label11);
        label11.setBounds(510, 50, 13, 17);

        //---- label12 ----
        label12.setText("\u53d1\u8f66\u65e5\u671f");
        contentPane.add(label12);
        label12.setBounds(new Rectangle(new Point(250, 130), label12.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(235, 235), button1.getPreferredSize()));
        contentPane.add(alter_id);
        alter_id.setBounds(140, 15, 50, alter_id.getPreferredSize().height);
        contentPane.add(arriveMonth);
        arriveMonth.setBounds(330, 125, 65, 30);
        contentPane.add(arriveDay);
        arriveDay.setBounds(430, 125, 65, 30);

        //---- label14 ----
        label14.setText("\u6708");
        contentPane.add(label14);
        label14.setBounds(new Rectangle(new Point(405, 130), label14.getPreferredSize()));

        //---- label15 ----
        label15.setText("\u65e5");
        contentPane.add(label15);
        label15.setBounds(new Rectangle(new Point(510, 130), label15.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(550, 340));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JComboBox typeCBox;
    private JLabel label3;
    private JTextField Carriages;
    private JLabel label4;
    private JLabel label5;
    private JTextField start_station;
    private JTextField end_station;
    private JLabel label6;
    private JLabel label7;
    private JComboBox end_h;
    private JLabel label8;
    private JComboBox end_m;
    private JLabel label9;
    private JComboBox start_m;
    private JComboBox start_h;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JButton button1;
    private JLabel alter_id;
    private JComboBox arriveMonth;
    private JComboBox arriveDay;
    private JLabel label14;
    private JLabel label15;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
