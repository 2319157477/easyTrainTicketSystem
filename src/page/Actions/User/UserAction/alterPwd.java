/*
 * Created by JFormDesigner on Mon Jul 08 17:01:05 CST 2024
 */

package page.Actions.User.UserAction;

import sql.DBUtil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author 23191
 */
public class alterPwd extends JDialog {
    String phoneNumber, O_pwd, A_pwd;
    boolean isSame = false;
    boolean isTrue = false;
    public alterPwd(JFrame owner, String phone) {
        super(owner, "修改密码", true);
        phoneNumber = phone;
        try {
            Statement st1 = DBUtil.getStatement();
            String sql = "SELECT user_pwd FROM user WHERE user_phone = '" + phoneNumber + "'";
            //System.out.println(sql);
            ResultSet rs1 = st1.executeQuery(sql);
            if (rs1.next()) {
                O_pwd = rs1.getString(1);
            }
        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
        initComponents();
        setTitle("欢迎用户：" + phoneNumber + " 使用本系统！");
        setVisible(true);
    }

    private void O_passwordFocusLost(FocusEvent e) {
        char[] Input = passwordField1.getPassword();
        String I_pwd = new String(Input);
        if (I_pwd.equals(O_pwd)) {
            isTrue = true;
            O_pwdErr.setVisible(false);
            repaint();
        }
        else {
            isTrue = false;
            O_pwdErr.setVisible(true);
            repaint();
        }
    }

    private void R_passwordFocusLost(FocusEvent e) {
        String A_pwd_1 = new String(passwordField2.getPassword());
        String A_pwd_2 = new String(passwordField3.getPassword());
        if (A_pwd_1.equals(A_pwd_2)) {
            A_pwd = A_pwd_2;
            A_pwdErr.setVisible(false);
            isSame = true;
            repaint();
        }
        else {
            A_pwdErr.setVisible(true);
            isSame = false;
            repaint();
        }
    }

    private void SureButton(ActionEvent e) {
        if (isSame && isTrue) {
            String sql = "UPDATE user SET user_pwd = '" + A_pwd + "' WHERE user_phone = '" + phoneNumber + "'";
            try {
                PreparedStatement pstmt = DBUtil.getPstmt(sql);;
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "成功修改密码为：" + A_pwd);
                this.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "密码输入错误！");
        }
    }

    private void QuitButton(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();
        passwordField2 = new JPasswordField();
        passwordField3 = new JPasswordField();
        O_pwdErr = new JLabel();
        A_pwdErr = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u539f\u5bc6\u7801");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 20), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u65b0\u5bc6\u7801");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(20, 65), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u518d\u6b21\u8f93\u5165");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getStyle() | Font.BOLD, label3.getFont().getSize() + 5f));
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(20, 110), label3.getPreferredSize()));

        //---- passwordField1 ----
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                O_passwordFocusLost(e);
            }
        });
        contentPane.add(passwordField1);
        passwordField1.setBounds(100, 20, 190, passwordField1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(15, 155), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> QuitButton(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(210, 155), button2.getPreferredSize()));
        contentPane.add(passwordField2);
        passwordField2.setBounds(100, 65, 190, 30);

        //---- passwordField3 ----
        passwordField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                R_passwordFocusLost(e);
            }
        });
        contentPane.add(passwordField3);
        passwordField3.setBounds(100, 110, 190, 30);

        //---- O_pwdErr ----
        O_pwdErr.setText("\u539f\u5bc6\u7801\u9519\u8bef\uff01");
        O_pwdErr.setVisible(false);
        contentPane.add(O_pwdErr);
        O_pwdErr.setBounds(new Rectangle(new Point(305, 25), O_pwdErr.getPreferredSize()));

        //---- A_pwdErr ----
        A_pwdErr.setText("\u4e24\u6b21\u8f93\u5165\u4e0d\u76f8\u540c\uff01");
        A_pwdErr.setVisible(false);
        contentPane.add(A_pwdErr);
        A_pwdErr.setBounds(new Rectangle(new Point(305, 115), A_pwdErr.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(400, 210));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JButton button1;
    private JButton button2;
    private JPasswordField passwordField2;
    private JPasswordField passwordField3;
    private JLabel O_pwdErr;
    private JLabel A_pwdErr;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
