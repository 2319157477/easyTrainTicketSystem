/*
 * Created by JFormDesigner on Sat Jul 06 22:20:56 CST 2024
 */

package page.Actions.User.AdminActions;

import sql.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/**
 * @author 23191
 */
public class alterPage extends JDialog {
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
            //System.out.println("成功连接!");
            alterState = DBUtil.getStatement();
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
            pstmt = DBUtil.getPstmt(sql);

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

    private void exit(ActionEvent e) {
        dispose();
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
        button2 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5f53\u524d\u4fee\u6539\u7528\u6237\u53f7\u7801:");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 20), label1.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u5bc6\u7801");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 5f));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(20, 60), label3.getPreferredSize()));
        contentPane.add(pwdInput);
        pwdInput.setBounds(75, 60, 120, pwdInput.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u59d3\u540d");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD, label4.getFont().getSize() + 5f));
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(20, 105), label4.getPreferredSize()));
        contentPane.add(nameInput);
        nameInput.setBounds(75, 105, 120, nameInput.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(125, 150), button1.getPreferredSize()));

        //---- alter_id ----
        alter_id.setFont(alter_id.getFont().deriveFont(alter_id.getFont().getStyle() | Font.BOLD, alter_id.getFont().getSize() + 5f));
        contentPane.add(alter_id);
        alter_id.setBounds(new Rectangle(new Point(170, 20), alter_id.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u7528\u6237\u7c7b\u578b");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(225, 60), label2.getPreferredSize()));

        //---- label6 ----
        label6.setText("\u7528\u6237\u6027\u522b");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 5f));
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(225, 105), label6.getPreferredSize()));

        //---- radioButtonMan ----
        radioButtonMan.setText("\u7537");
        radioButtonMan.setFont(radioButtonMan.getFont().deriveFont(radioButtonMan.getFont().getStyle() | Font.BOLD, radioButtonMan.getFont().getSize() + 5f));
        radioButtonMan.addActionListener(e -> man_G(e));
        contentPane.add(radioButtonMan);
        radioButtonMan.setBounds(305, 110, 50, 21);

        //---- radioButtonWoman ----
        radioButtonWoman.setText("\u5973");
        radioButtonWoman.setFont(radioButtonWoman.getFont().deriveFont(radioButtonWoman.getFont().getStyle() | Font.BOLD, radioButtonWoman.getFont().getSize() + 5f));
        radioButtonWoman.addActionListener(e -> woman_G(e));
        contentPane.add(radioButtonWoman);
        radioButtonWoman.setBounds(375, 110, 60, 21);

        //---- radioButtonDefault ----
        radioButtonDefault.setText("\u666e\u901a");
        radioButtonDefault.setFont(radioButtonDefault.getFont().deriveFont(radioButtonDefault.getFont().getStyle() | Font.BOLD, radioButtonDefault.getFont().getSize() + 5f));
        radioButtonDefault.addActionListener(e -> default_T(e));
        contentPane.add(radioButtonDefault);
        radioButtonDefault.setBounds(305, 60, 65, 21);

        //---- radioButtonAdmin ----
        radioButtonAdmin.setText("\u7ba1\u7406\u5458");
        radioButtonAdmin.setFont(radioButtonAdmin.getFont().deriveFont(radioButtonAdmin.getFont().getStyle() | Font.BOLD, radioButtonAdmin.getFont().getSize() + 5f));
        radioButtonAdmin.addActionListener(e -> admin_T(e));
        contentPane.add(radioButtonAdmin);
        radioButtonAdmin.setBounds(375, 60, 75, 21);

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(265, 150), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(470, 210));
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
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
