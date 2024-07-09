/*
 * Created by JFormDesigner on Sun Jul 07 22:22:38 CST 2024
 */

package page.manage.Train.AdminActions;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import tools.createSeatInfo;

/**
 * @author 23191
 */
public class addPage extends JDialog {
    String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
    String user = "root";
    String password = "lbc041103";
    String trainId = "";
    String trainType = "";
    int trainCarriage = 0;
    String trainNumber = "NO.1";
    String startStation = "";
    String endStation = "";
    String startTime = "";
    String endTime = "";
    String arrive_Day = "";
    String runningTime = "";
    String runningType = "1";
    // SQL 插入语句
    String sql = "INSERT INTO train_info (train_id, train_type, train_carriage, train_number, train_start_station, train_end_station, train_start_time, train_end_time, train_arrive_day, train_running_time, train_running_type) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    Connection conn;

    private static boolean areAllFieldsNonEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private static String calculateRunningTime(String endTime, String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            long diffInMillis = endDate.getTime() - startDate.getTime();
            long hours = diffInMillis / (60 * 60 * 1000);
            long minutes = (diffInMillis / (60 * 1000)) % 60;

            return String.format("%02d:%02d", hours, minutes);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public addPage(JFrame owner) {
        super(owner, "添加列车", true);
        try {
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        initComponents();
        setVisible(true);
    }
    private void createUIComponents() {
        typeCBox.addItem("普快");
        typeCBox.addItem("高铁");
        //if(c_type.equals("1")) {typeCBox.setSelectedItem("高铁");}else {typeCBox.setSelectedItem("普快");}
        //for (int i = 15; i < 21; i ++) Carriages.setText(c_carriages);
        //FormattedTime time = new FormattedTime();
        for (int i = 0; i <= 24; i++) {
            String timeString = String.format("%02d", i);
            end_h.addItem(timeString);
        }
        //end_h.setSelectedItem(time.getFormattedTime(c_end_time, "hour"));
        for (int i = 0; i <= 50; i += 10) {
            String numberString = String.format("%02d", i);
            end_m.addItem(numberString);
        }
        //end_m.setSelectedItem(time.getFormattedTime(c_end_time, "m"));
        for (int i = 0; i <= 50; i += 10) {
            String numberString = String.format("%02d", i);
            start_m.addItem(numberString);
        }
        //start_m.setSelectedItem(time.getFormattedTime(c_start_time, "m"));
        for (int i = 0; i <= 24; i++) {
            String timeString = String.format("%02d", i);
            start_h.addItem(timeString);
        }
        //start_h.setSelectedItem(time.getFormattedTime(c_start_time, "hour"));
        for (int i = 0; i <= 12; i++) {
            String monthString = String.format("%02d", i);
            arriveMonth.addItem(monthString);
        }
        //arriveMonth.setSelectedItem(time.getFormattedDay(c_arrive_day, "M"));
        for (int i = 0; i <= 31; i++) {
            String dayString = String.format("%02d", i);
            arriveDay.addItem(dayString);
        }
        //arriveDay.setSelectedItem(time.getFormattedDay(c_arrive_day, "D"));
        //alter_id.setText(c_id);
    }

    private void SureButton(ActionEvent e) {
        trainId = idInput.getText();
        trainType = String.valueOf(typeCBox.getSelectedIndex());
        trainCarriage = Integer.parseInt(Carriages.getText());
        startStation = start_station.getText();
        endStation = end_station.getText();
        startTime = start_h.getSelectedItem() + ":" + start_m.getSelectedItem();
        endTime = end_h.getSelectedItem() + ":" + end_m.getSelectedItem();
        arrive_Day = "2024-" + arriveMonth.getSelectedItem() + "-" + arriveDay.getSelectedItem();
        if (!startTime.isEmpty() && !endTime.isEmpty()) {
            runningTime = calculateRunningTime(endTime, startTime);
        }
        boolean allFieldsNonEmpty = areAllFieldsNonEmpty(trainId, trainType, startStation, endStation, startTime, endTime, arrive_Day, runningTime);
        if (allFieldsNonEmpty && trainCarriage != 0) {
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                {
                    // 设置参数
                    stmt.setString(1, trainId);
                    stmt.setString(2, trainType);
                    stmt.setInt(3, trainCarriage);
                    stmt.setString(4, trainNumber);
                    stmt.setString(5, startStation);
                    stmt.setString(6, endStation);
                    stmt.setString(7, startTime);
                    stmt.setString(8, endTime);
                    stmt.setString(9, arrive_Day);
                    stmt.setString(10, runningTime);
                    stmt.setString(11, runningType);
                    // 执行插入操作
                    int rowsAffected = stmt.executeUpdate();
                    createSeatInfo creator = new createSeatInfo();
                    creator.createSeat(trainId);
                    System.out.println("Inserted " + rowsAffected + " row(s).");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "关键信息缺失！");
        }
    }
    private void train_idFocusLost(FocusEvent e) {
        trainId = idInput.getText();
        boolean isSame = false;
        try {
            Statement usernameState = conn.createStatement();
            ResultSet rs = usernameState.executeQuery("SELECT * FROM train_info");
            //System.out.println("成功连接！");
            while (rs.next()) {
                String id = rs.getNString(1);
                //System.out.println(id);
                if (trainId.equals(id)) {
                    isSame = true;
                    //zhuce_con.close();
                    break;
                }
            }
            if (isSame) {
                idErr.setVisible(true);
                trainId = "";
                repaint();
            }
            else {
                idErr.setVisible(false);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void initComponents() {
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
        arriveMonth = new JComboBox();
        arriveDay = new JComboBox();
        label14 = new JLabel();
        label15 = new JLabel();
        idInput = new JTextField();
        idErr = new JLabel();
        createUIComponents();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5217\u8f66\u7f16\u53f7:");
        contentPane.add(label1);
        label1.setBounds(20, 60, 60, 17);

        //---- label2 ----
        label2.setText("\u5217\u8f66\u7c7b\u578b");
        contentPane.add(label2);
        label2.setBounds(320, 60, 52, 17);
        contentPane.add(typeCBox);
        typeCBox.setBounds(400, 55, 86, 30);

        //---- label3 ----
        label3.setText("\u5217\u8f66\u8f66\u53a2\u6570");
        contentPane.add(label3);
        label3.setBounds(20, 95, 65, 17);
        contentPane.add(Carriages);
        Carriages.setBounds(115, 90, 75, 30);

        //---- label4 ----
        label4.setText("\u59cb\u53d1\u7ad9");
        contentPane.add(label4);
        label4.setBounds(20, 135, 39, 17);

        //---- label5 ----
        label5.setText("\u7ec8\u70b9\u7ad9");
        contentPane.add(label5);
        label5.setBounds(20, 180, 39, 17);
        contentPane.add(start_station);
        start_station.setBounds(115, 130, 75, 30);
        contentPane.add(end_station);
        end_station.setBounds(115, 175, 75, 30);

        //---- label6 ----
        label6.setText("\u53d1\u8f66\u65f6\u95f4");
        contentPane.add(label6);
        label6.setBounds(320, 100, 52, 17);

        //---- label7 ----
        label7.setText("\u5230\u7ad9\u65f6\u95f4");
        contentPane.add(label7);
        label7.setBounds(320, 140, 52, 17);
        contentPane.add(end_h);
        end_h.setBounds(400, 135, 65, 30);

        //---- label8 ----
        label8.setText("\u65f6");
        contentPane.add(label8);
        label8.setBounds(475, 140, 15, 17);
        contentPane.add(end_m);
        end_m.setBounds(500, 135, 65, 30);

        //---- label9 ----
        label9.setText("\u5206");
        contentPane.add(label9);
        label9.setBounds(580, 140, 13, 17);
        contentPane.add(start_m);
        start_m.setBounds(500, 95, 65, 30);
        contentPane.add(start_h);
        start_h.setBounds(400, 95, 65, 30);

        //---- label10 ----
        label10.setText("\u65f6");
        contentPane.add(label10);
        label10.setBounds(475, 100, 15, 17);

        //---- label11 ----
        label11.setText("\u5206");
        contentPane.add(label11);
        label11.setBounds(580, 100, 13, 17);

        //---- label12 ----
        label12.setText("\u53d1\u8f66\u65e5\u671f");
        contentPane.add(label12);
        label12.setBounds(320, 180, 52, 17);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(235, 240, 78, 30);
        contentPane.add(arriveMonth);
        arriveMonth.setBounds(400, 175, 65, 30);
        contentPane.add(arriveDay);
        arriveDay.setBounds(500, 175, 65, 30);

        //---- label14 ----
        label14.setText("\u6708");
        contentPane.add(label14);
        label14.setBounds(475, 180, 13, 17);

        //---- label15 ----
        label15.setText("\u65e5");
        contentPane.add(label15);
        label15.setBounds(580, 180, 13, 17);

        //---- idInput ----
        idInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                train_idFocusLost(e);
            }
        });
        contentPane.add(idInput);
        idInput.setBounds(115, 55, 75, idInput.getPreferredSize().height);

        //---- idErr ----
        idErr.setText("\u5df2\u5b58\u5728\uff01");
        idErr.setVisible(false);
        contentPane.add(idErr);
        idErr.setBounds(new Rectangle(new Point(205, 60), idErr.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(660, 365));
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
    private JComboBox arriveMonth;
    private JComboBox arriveDay;
    private JLabel label14;
    private JLabel label15;
    private JTextField idInput;
    private JLabel idErr;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
