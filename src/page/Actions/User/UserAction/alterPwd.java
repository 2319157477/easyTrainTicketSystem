/*
 * Created by JFormDesigner on Mon Jul 08 17:01:05 CST 2024
 */

package page.manage.User.UserAction;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author 23191
 */
public class alterPwd extends JDialog {
    String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
    String username = "root";
    String password = "lbc041103";
    Connection user_con;
    String phoneNumber, O_pwd, A_pwd;
    boolean isSame = false;
    public alterPwd(JFrame owner, String phone) {
        super(owner, "修改密码", true);
        phoneNumber = phone;
        try {
            user_con = DriverManager.getConnection(url, username, password);
            Statement st1 = user_con.createStatement();
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
        setVisible(true);
    }

    private void O_passwordFocusLost(FocusEvent e) {
        char[] Input = passwordField1.getPassword();
        String I_pwd = new String(Input);
        //System.out.println(O_pwd + " " + I_pwd);
        if (I_pwd.equals(O_pwd)) {
            isSame = true;
            O_pwdErr.setVisible(false);
            repaint();
        }
        else {
            isSame = false;
            O_pwdErr.setVisible(true);
            repaint();
        }
    }

    private void R_passwordFocusLost(FocusEvent e) {
        String A_pwd_1 = Arrays.toString(passwordField2.getPassword());
        String A_pwd_2 = Arrays.toString(passwordField3.getPassword());
        if (A_pwd_1.equals(A_pwd_2)) {
            A_pwd = A_pwd_2;
            A_pwdErr.setVisible(false);
            repaint();
        }
        else {
            A_pwdErr.setVisible(true);
            repaint();
        }
    }

    private void SureButton(ActionEvent e) {
        if (isSame) {
            String sql = "UPDATE user SET pwd = '" + A_pwd + "' WHERE user_phone = '" + phoneNumber + "'";
            try {
                PreparedStatement pstmt = user_con.prepareStatement(sql);
                pstmt.executeUpdate();
                this.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "原密码输入错误！");
        }
    }

    private void QuitButton(ActionEvent e) {
        // TODO add your code here
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
        contentPane.add(label1);
        label1.setBounds(55, 40, 50, 20);

        //---- label2 ----
        label2.setText("\u65b0\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(55, 85), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u518d\u6b21\u8f93\u5165");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(55, 130), label3.getPreferredSize()));

        //---- passwordField1 ----
        passwordField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                O_passwordFocusLost(e);
            }
        });
        contentPane.add(passwordField1);
        passwordField1.setBounds(120, 35, 190, passwordField1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(50, 170), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addActionListener(e -> QuitButton(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(230, 170), button2.getPreferredSize()));
        contentPane.add(passwordField2);
        passwordField2.setBounds(120, 80, 190, 30);

        //---- passwordField3 ----
        passwordField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                R_passwordFocusLost(e);
            }
        });
        contentPane.add(passwordField3);
        passwordField3.setBounds(120, 125, 190, 30);

        //---- O_pwdErr ----
        O_pwdErr.setText("\u539f\u5bc6\u7801\u9519\u8bef\uff01");
        O_pwdErr.setVisible(false);
        contentPane.add(O_pwdErr);
        O_pwdErr.setBounds(new Rectangle(new Point(320, 40), O_pwdErr.getPreferredSize()));

        //---- A_pwdErr ----
        A_pwdErr.setText("\u4e24\u6b21\u8f93\u5165\u4e0d\u76f8\u540c\uff01");
        A_pwdErr.setVisible(false);
        contentPane.add(A_pwdErr);
        A_pwdErr.setBounds(new Rectangle(new Point(320, 130), A_pwdErr.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(450, 260));
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
