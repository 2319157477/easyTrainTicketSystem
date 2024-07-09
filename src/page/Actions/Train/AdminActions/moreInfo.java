/*
 * Created by JFormDesigner on Mon Jul 08 21:52:16 CST 2024
 */

package page.manage.Train.AdminActions;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import page.manage.Train.AdminActions.TrainSeatDialog;

/**
 * @author 23191
 */
public class moreInfo extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312"; // 替换为你的数据库URL
    private static final String DB_USER = "root"; // 替换为你的数据库用户名
    private static final String DB_PASSWORD = "lbc041103"; // 替换为你的数据库密码
    public int selectedSeatId;
    private final String trainId;
    private int carriageId;
    private int carriageNum;
    Connection conn;
    public moreInfo(String train_id) {
        trainId = train_id;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet rs1 = statement.executeQuery("SELECT train_carriage from train_info WHERE train_id = '" + train_id + "'");
            if (rs1.next()) {
                carriageNum = rs1.getInt(1);
                System.out.println("num " + carriageNum);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initComponents();
    }
    private void sureButton(ActionEvent e) {
        int c_id;
        String c_phone;
        selectedSeatId = panel1.getSelectedSeat();
        carriageId = comboBox1.getSelectedIndex() + 1;
        if (selectedSeatId != -1) {
            String sql = "SELECT * FROM order_list WHERE train_id = '" + trainId + "' AND carriage_id = " + carriageId + " AND seat_id = " + selectedSeatId;
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs1 = pstmt.executeQuery();
                if (rs1.next()) {
                    c_id = rs1.getInt(1);
                    c_phone = rs1.getString(2);
                    ticketInfo t1 = new ticketInfo(this, c_id, c_phone);
                    if (t1.doDelete) {
                        sql = "DELETE FROM order_list WHERE order_id = " + c_id;
                        pstmt = conn.prepareStatement(sql);
                        pstmt.executeUpdate();
                        this.remove(panel1);
                        panel1 = new TrainSeatDialog(trainId, carriageId);
                        panel1.setBounds(30, 65, 555, 540);
                        this.add(panel1);
                        this.panel1.repaint();
                        this.panel1.revalidate();
                    }
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a seat.");
        }
    }

    private void createUIComponents() {
        panel1 = new TrainSeatDialog(trainId, 1);
    }

    private void carriageSelect(ActionEvent e) {
        carriageId = comboBox1.getSelectedIndex() + 1;
        this.remove(panel1);
        panel1 = new TrainSeatDialog(trainId, carriageId);
        panel1.setBounds(30, 65, 555, 540);
        this.add(panel1);
        this.panel1.repaint();
        this.panel1.revalidate();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        createUIComponents();

        label1 = new JLabel();
        comboBox1 = new JComboBox();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9009\u62e9\u8f66\u53a2");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(30, 20), label1.getPreferredSize()));

        //---- comboBox1 ----
        comboBox1.addActionListener(e -> carriageSelect(e));
        System.out.println("num " + carriageNum);
        for (int i = 1; i <= carriageNum; i ++) {
            comboBox1.addItem(i);
        }
        contentPane.add(comboBox1);
        comboBox1.setBounds(110, 15, 115, comboBox1.getPreferredSize().height);
        contentPane.add(panel1);
        panel1.setBounds(30, 65, 555, 540);

        //---- button1 ----
        button1.setText("\u786e\u5b9a\u67e5\u8be2");
        button1.addActionListener(e -> sureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(40, 620), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(485, 620), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(630, 720));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JComboBox comboBox1;
    private TrainSeatDialog panel1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private static class TrainSeatDialog extends JPanel {


        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off

            //======== this ========
            setLayout(null);
            // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        }

        private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312"; // 替换为你的数据库URL
        private static final String DB_USER = "root"; // 替换为你的数据库用户名
        private static final String DB_PASSWORD = "lbc041103"; // 替换为你的数据库密码
        private JButton selectedSeatButton;
        private int selectedSeatId = -1;
        private final String trainId;
        private final int carriageId;
        private Connection conn;

        ArrayList<String> phoneList = new ArrayList<>();

        public TrainSeatDialog(String trainId, int carriageId) {
            this.trainId = trainId;
            this.carriageId = carriageId;
            setSize(800, 600);

            setLayout(new GridLayout(10, 6));
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            loadSeats();
            setVisible(true);
        }

        public int getSelectedSeat() {
            return selectedSeatId;
        }
        private void loadSeats() {
            try {
                String query = "SELECT * FROM order_list WHERE train_id = ? AND carriage_id = ?";
                System.out.println(query);
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, trainId);
                    pstmt.setInt(2, carriageId);
                    ResultSet rs = pstmt.executeQuery();
                    Set<Integer> occupiedSeats = new HashSet<>();
                    while (rs.next()) {
                        occupiedSeats.add(rs.getInt(7));
                        String c_phone = rs.getString(2);
                        phoneList.add(c_phone);
                    }

                    for (int i = 1, j = 0; i <= 60; i++) {
                        JButton seatButton = new JButton(String.valueOf(i));
                        if (occupiedSeats.contains(i)) {
                            seatButton.setBackground(Color.RED);
                            //seatButton.setText(phoneList.get(j));
                            seatButton.addActionListener(new SeatButtonListener(i, seatButton));
                            j ++;
                        } else {
                            seatButton.setBackground(Color.GREEN);
                            seatButton.setEnabled(false);
                        }
                        this.add(seatButton);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private class SeatButtonListener implements ActionListener {
            private int seatId;
            private JButton seatButton;

            public SeatButtonListener(int seatId, JButton seatButton) {
                this.seatId = seatId;
                this.seatButton = seatButton;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedSeatButton != null) {
                    selectedSeatButton.setBackground(Color.RED);
                }
                seatButton.setBackground(Color.BLUE);
                selectedSeatButton = seatButton;
                selectedSeatId = seatId;
            }
        }

        // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
        // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    }
}
