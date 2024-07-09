/*
 * Created by JFormDesigner on Sat Jul 06 22:20:45 CST 2024
 */

package page.Actions.Ticket.AdminAction;

import page.Actions.Train.AdminActions.DateSystem;
import tools.FormattedTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 23191
 */
public class searchPage extends JDialog {
    String trainId = "";
    String phoneNumber = "";
    String type = "";
    String date = "";
    String start = "";
    String end = "";
    boolean isC = false;

    public searchPage(JFrame parent) {
        super(parent, "选择条件", true);
        initComponents();
        // 调用 pack() 和 setLocationRelativeTo() 以确保对话框布局正确
        pack();
        setLocationRelativeTo(parent);
        setVisible(true); // 确保在所有组件初始化和布局完成后，再调用 setVisible(true)
    }
    public String getSearchCondition() {
        String conditions = "";
        if (!trainId.isEmpty()) {
            conditions = conditions + "train_id = '" + trainId + "'";
        }
        if (!phoneNumber.isEmpty()) {
            conditions = conditions + " AND user_phone_number = '" + phoneNumber + "'";
        }
        if (!date.isEmpty()) {
            FormattedTime time = new FormattedTime();
            String year = time.getFormattedDay(date, "Y");
            String month = time.getFormattedDay(date, "M");
            String day = time.getFormattedDay(date, "D");
            String date_day = year + "-" + month + "-" + day;
            conditions = conditions + " AND train_start_date = '" + date_day + "'";
        }
        if (!start.isEmpty()) {
            conditions = conditions + " AND start_station_name = '" + start + "'";
        }
        if (!end.isEmpty()) {
            conditions = conditions + " AND end_station_name = '" + end + "'";
        }
        if (!type.isEmpty()) {
            int price = (type.equals("0"))? 500 : 300;
            conditions = conditions + " AND order_money = " + price;
        }
        if (conditions.startsWith(" AND ")) {
            conditions = conditions.substring(5);
        }
        return conditions;
    }
    private void DateSelect(ActionEvent e) {
        DateSystem dateSystem = new DateSystem(this);
        dateSystem.initializeUI();
        date = dateSystem.getFormattedDateTime();
    }

    private static boolean notAllFieldsNonEmpty(String... fields) {
        for (String field : fields) {
            if (field != null || !field.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void Search(ActionEvent e) {
        trainId = trainIdInput.getText();
        phoneNumber = phoneInput.getText();
        start = start_station.getText();
        end = end_station.getText();
        if (notAllFieldsNonEmpty(trainId, phoneNumber, type, start, end, date)) {
            isC = true;
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "请至少选择一项信息进行过滤！");
        }
    }

    private void exit(ActionEvent e) {
        this.dispose();
    }

    private void pukuai(ActionEvent e) {
        type = "0";
    }

    private void gaotie(ActionEvent e) {
        type = "1";
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        ButtonGroup group = new ButtonGroup();
        radioButton1 = new JRadioButton();
        group.add(radioButton1);
        radioButton2 = new JRadioButton();
        group.add(radioButton2);
        start_station = new JTextField();
        end_station = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        trainIdInput = new JTextField();
        label4 = new JLabel();
        label8 = new JLabel();
        phoneInput = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5217\u8f66\u7f16\u53f7");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 20), label1.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u53d1\u8f66\u65f6\u95f4");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 5f));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(20, 125), label3.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u8d77\u70b9\u7ad9");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 5f));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(20, 165), label5.getPreferredSize()));

        //---- label6 ----
        label6.setText("\u7ec8\u70b9\u7ad9");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 5f));
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(20, 205), label6.getPreferredSize()));

        //---- radioButton1 ----
        radioButton1.setText("\u4e00\u7b49");
        radioButton1.setFont(radioButton1.getFont().deriveFont(radioButton1.getFont().getStyle() | Font.BOLD, radioButton1.getFont().getSize() + 5f));
        radioButton1.addActionListener(e -> pukuai(e));
        contentPane.add(radioButton1);
        radioButton1.setBounds(new Rectangle(new Point(105, 90), radioButton1.getPreferredSize()));

        //---- radioButton2 ----
        radioButton2.setText("\u4e8c\u7b49");
        radioButton2.setFont(radioButton2.getFont().deriveFont(radioButton2.getFont().getStyle() | Font.BOLD, radioButton2.getFont().getSize() + 5f));
        radioButton2.addActionListener(e -> gaotie(e));
        contentPane.add(radioButton2);
        radioButton2.setBounds(new Rectangle(new Point(185, 90), radioButton2.getPreferredSize()));
        contentPane.add(start_station);
        start_station.setBounds(105, 165, 130, start_station.getPreferredSize().height);
        contentPane.add(end_station);
        end_station.setBounds(105, 205, 130, end_station.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u641c\u7d22");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> Search(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(20, 250), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(155, 250), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u9009\u62e9\u65e5\u671f\u65f6\u95f4");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 1f));
        button3.addActionListener(e -> DateSelect(e));
        contentPane.add(button3);
        button3.setBounds(105, 125, 130, button3.getPreferredSize().height);
        contentPane.add(trainIdInput);
        trainIdInput.setBounds(105, 20, 130, trainIdInput.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u7968\u578b");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 5f));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(20, 90), label4.getPreferredSize()));

        //---- label8 ----
        label8.setText("\u7528\u6237\u7535\u8bdd");
        label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD, label8.getFont().getSize() + 5f));
        contentPane.add(label8);
        label8.setBounds(new Rectangle(new Point(20, 55), label8.getPreferredSize()));
        contentPane.add(phoneInput);
        phoneInput.setBounds(105, 55, 130, phoneInput.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(255, 305));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label3;
    private JLabel label5;
    private JLabel label6;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JTextField start_station;
    private JTextField end_station;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTextField trainIdInput;
    private JLabel label4;
    private JLabel label8;
    private JTextField phoneInput;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
