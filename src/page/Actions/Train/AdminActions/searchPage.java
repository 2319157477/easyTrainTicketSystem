/*
 * Created by JFormDesigner on Sat Jul 06 22:20:45 CST 2024
 */

package page.Actions.Train.AdminActions;

import tools.FormattedTime;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 23191
 */
public class searchPage extends JDialog {
    String type = "";
    String c_lower = "";
    String c_upper = "";
    String date = "";
    String start = "";
    String end = "";
    boolean isC = false;

    public searchPage(JFrame parent) {
        super(parent, "选择条件", true);
        initComponents();
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    public String getSearchCondition() {
        String conditions = "";
        if (!type.isEmpty()) {
            conditions = conditions + "train_type = '" + type + "'";
        }
        if (!date.isEmpty()) {
            FormattedTime time = new FormattedTime();
            String year = time.getFormattedDay(date, "Y");
            String month = time.getFormattedDay(date, "M");
            String day = time.getFormattedDay(date, "D");
            String after = time.getFormattedDay(date, "P");
            String before = time.getFormattedDay(date, "B");
            String date_day = year + "-" + month + "-" + day;
            if (!after.equals("00:00") && !before.equals("00:00")) {
                conditions = conditions + " AND train_arrive_day = '" + date_day + "' AND (STR_TO_DATE(train_start_time, '%H:%i') BETWEEN STR_TO_DATE('" + after + "', '%H:%i') AND STR_TO_DATE('" + before + "', '%H:%i'))";
            }
            else {
                conditions = conditions + " AND train_arrive_day = '" + date_day + "'";
            }
        }
        if (!c_lower.isEmpty()) {
            conditions = conditions + " AND train_carriage >= " + c_lower;
        }
        if (!c_upper.isEmpty()) {
            conditions = conditions + " AND train_carriage <= " + c_upper;
        }
        if (!start.isEmpty()) {
            conditions = conditions + " AND train_start_station = '" + start + "'";
        }
        if (!end.isEmpty()) {
            conditions = conditions + " AND train_end_station = '" + end + "'";
        }
        if (conditions.startsWith(" AND ")) {
            // 去掉开头的 "AND "
            conditions = conditions.substring(5);
        }
        return conditions;
    }
    private void DateSelect(ActionEvent e) {
        DateSystem dateSystem = new DateSystem(this);
        dateSystem.initializeUI();
        date = dateSystem.getFormattedDateTime();
        //System.out.println(date);
    }

    private void Search(ActionEvent e) {

        c_lower = carriage_lower.getText();
        c_upper = carriage_upper.getText();
        start = start_station.getText();
        end = end_station.getText();
        if (!type.isEmpty() || !date.isEmpty() || !c_lower.isEmpty() || !c_upper.isEmpty() || !start.isEmpty() || !end.isEmpty()) {
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
        label2 = new JLabel();
        label3 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        ButtonGroup group = new ButtonGroup();
        radioButton1 = new JRadioButton();
        group.add(radioButton1);
        radioButton2 = new JRadioButton();
        group.add(radioButton2);
        carriage_lower = new JTextField();
        label7 = new JLabel();
        carriage_upper = new JTextField();
        start_station = new JTextField();
        end_station = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("\u5217\u8f66\u8fc7\u6ee4\u5668");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5217\u8f66\u7c7b\u578b");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(25, 20), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u8f66\u53a2\u6570\u91cf");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(25, 60), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u53d1\u8f66\u65f6\u95f4");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 5f));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(25, 100), label3.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u8d77\u70b9\u7ad9");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD, label5.getFont().getSize() + 5f));
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(25, 140), label5.getPreferredSize()));

        //---- label6 ----
        label6.setText("\u7ec8\u70b9\u7ad9");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 5f));
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(25, 180), label6.getPreferredSize()));

        //---- radioButton1 ----
        radioButton1.setText("\u666e\u5feb");
        radioButton1.setFont(radioButton1.getFont().deriveFont(radioButton1.getFont().getStyle() | Font.BOLD, radioButton1.getFont().getSize() + 5f));
        radioButton1.addActionListener(e -> pukuai(e));
        contentPane.add(radioButton1);
        radioButton1.setBounds(new Rectangle(new Point(105, 20), radioButton1.getPreferredSize()));

        //---- radioButton2 ----
        radioButton2.setText("\u9ad8\u94c1");
        radioButton2.setFont(radioButton2.getFont().deriveFont(radioButton2.getFont().getStyle() | Font.BOLD, radioButton2.getFont().getSize() + 5f));
        radioButton2.addActionListener(e -> gaotie(e));
        contentPane.add(radioButton2);
        radioButton2.setBounds(new Rectangle(new Point(180, 20), radioButton2.getPreferredSize()));
        contentPane.add(carriage_lower);
        carriage_lower.setBounds(105, 60, 55, carriage_lower.getPreferredSize().height);

        //---- label7 ----
        label7.setText("~");
        contentPane.add(label7);
        label7.setBounds(new Rectangle(new Point(165, 65), label7.getPreferredSize()));
        contentPane.add(carriage_upper);
        carriage_upper.setBounds(180, 60, 55, carriage_upper.getPreferredSize().height);
        contentPane.add(start_station);
        start_station.setBounds(105, 140, 130, start_station.getPreferredSize().height);
        contentPane.add(end_station);
        end_station.setBounds(105, 180, 130, end_station.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u641c\u7d22");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> Search(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(20, 225), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(155, 225), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u9009\u62e9\u65e5\u671f\u65f6\u95f4");
        button3.addActionListener(e -> DateSelect(e));
        contentPane.add(button3);
        button3.setBounds(105, 100, 130, button3.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(260, 280));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label5;
    private JLabel label6;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JTextField carriage_lower;
    private JLabel label7;
    private JTextField carriage_upper;
    private JTextField start_station;
    private JTextField end_station;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
