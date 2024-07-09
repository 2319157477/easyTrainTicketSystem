/*
 * Created by JFormDesigner on Sat Jul 06 22:20:56 CST 2024
 */

package page.manage.User.AdminActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

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
    String c_pwd;
    String c_name;
    int c_type, a_type;
    int c_gender, a_gender;
    ButtonGroup g1, g2;
    public alterPage(String alter_id, JFrame parent) {
        super(parent, "修改用户信息", true);
        try {
            alter_con = DriverManager.getConnection(url, user, password);
            //System.out.println("成功连接!");
            alterState = alter_con.createStatement();
            String sql = "SELECT * FROM user WHERE user_phone = '" + alter_id + "'";
            //System.out.println(sql);
            ResultSet rs1 = alterState.executeQuery(sql);
            if (rs1.next()) {
                c_id = alter_id;
                c_pwd = rs1.getString(2);
                c_name = rs1.getString(3);
                a_type = rs1.getInt(4);
                a_gender = rs1.getInt(5);
            }
        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
        initComponents(alter_id);
        setVisible(true);
    }
    private void SureButton(ActionEvent e) {
        String a_pwd = pwdInput.getText();
        String a_name = nameInput.getText();
        String sql = "UPDATE user SET user_pwd = ?, user_name = ?, user_type = ?, user_gender = ? WHERE user_phone = '" + c_id + "'";
        //System.out.println(sql);
        try {
            pstmt = alter_con.prepareStatement(sql);

            pstmt.setString(1, a_pwd);
            pstmt.setString(2, a_name);
            pstmt.setInt(3, a_type);
            pstmt.setInt(4, a_gender);

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        this.dispose();
    }
    private void createUIComponents() {
        pwdInput.setText(c_pwd);
        nameInput.setText(c_name);
        if (a_type == 0) radioButtonDefault.doClick();
        else radioButtonAdmin.doClick();
        if (a_gender == 0) radioButtonWoman.doClick();
        else radioButtonMan.doClick();
        alter_id.setText(c_id);
    }

    private void man_G(ActionEvent e) {
        a_gender = 1;
    }

    private void woman_G(ActionEvent e) {
        a_gender = 0;
    }

    private void default_T(ActionEvent e) {
        a_type = 0;
    }

    private void admin_T(ActionEvent e) {
        a_type = 1;
    }
    private void initComponents(String id) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label3 = new JLabel();
        pwdInput = new JTextField();
        label4 = new JLabel();
        nameInput = new JTextField();
        button1 = new JButton();
        alter_id = new JLabel();
        label2 = new JLabel();
        label6 = new JLabel();
        g2 = new ButtonGroup();
        radioButtonMan = new JRadioButton();
        g2.add(radioButtonMan);
        radioButtonWoman = new JRadioButton();
        g2.add(radioButtonWoman);
        g1 = new ButtonGroup();
        radioButtonDefault = new JRadioButton();
        g1.add(radioButtonDefault);
        radioButtonAdmin = new JRadioButton();
        g1.add(radioButtonAdmin);
        createUIComponents();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5f53\u524d\u4fee\u6539\u7528\u6237\u53f7\u7801:");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 35), label1.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u5bc6\u7801");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(20, 90), label3.getPreferredSize()));
        contentPane.add(pwdInput);
        pwdInput.setBounds(115, 85, 75, pwdInput.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u59d3\u540d");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(20, 130), label4.getPreferredSize()));
        contentPane.add(nameInput);
        nameInput.setBounds(115, 125, 75, nameInput.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(200, 185), button1.getPreferredSize()));
        contentPane.add(alter_id);
        alter_id.setBounds(140, 15, 50, alter_id.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u7528\u6237\u7c7b\u578b");
        contentPane.add(label2);
        label2.setBounds(250, 90, 52, 17);

        //---- label6 ----
        label6.setText("\u7528\u6237\u6027\u522b");
        contentPane.add(label6);
        label6.setBounds(250, 130, 52, 17);

        //---- radioButtonMan ----
        radioButtonMan.setText("\u7537");
        radioButtonMan.addActionListener(e -> man_G(e));
        contentPane.add(radioButtonMan);
        radioButtonMan.setBounds(315, 130, 50, 21);

        //---- radioButtonWoman ----
        radioButtonWoman.setText("\u5973");
        radioButtonWoman.addActionListener(e -> woman_G(e));
        contentPane.add(radioButtonWoman);
        radioButtonWoman.setBounds(415, 130, 60, 21);

        //---- radioButtonDefault ----
        radioButtonDefault.setText("\u666e\u901a");
        radioButtonDefault.addActionListener(e -> default_T(e));
        contentPane.add(radioButtonDefault);
        radioButtonDefault.setBounds(315, 90, 65, 21);

        //---- radioButtonAdmin ----
        radioButtonAdmin.setText("\u7ba1\u7406\u5458");
        radioButtonAdmin.addActionListener(e -> admin_T(e));
        contentPane.add(radioButtonAdmin);
        radioButtonAdmin.setBounds(415, 90, 75, 21);

        contentPane.setPreferredSize(new Dimension(570, 295));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label3;
    private JTextField pwdInput;
    private JLabel label4;
    private JTextField nameInput;
    private JButton button1;
    private JLabel alter_id;
    private JLabel label2;
    private JLabel label6;
    private JRadioButton radioButtonMan;
    private JRadioButton radioButtonWoman;
    private JRadioButton radioButtonDefault;
    private JRadioButton radioButtonAdmin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
