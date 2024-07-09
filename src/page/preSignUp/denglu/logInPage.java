package page.preSignUp.denglu;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import page.userPage.*;
/*
 * Created by JFormDesigner on Sat Jul 06 16:37:56 CST 2024
 */



/**
 * @author 23191
 */
public class denluPage extends JFrame {
    String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
    String user = "root";
    String password = "lbc041103";
    public String c_username = "";
    public String c_pwd = "";
    Connection denlu_con;

    int userType;
    public denluPage() {
        try {
            denlu_con = DriverManager.getConnection(url, user, password);
        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
        initComponents();
    }

    private void pwdInputFocusLost(FocusEvent e) {
        c_pwd = new String(pwdInput.getPassword());
    }

    private void usernameInputFocusLost(FocusEvent e) {
        c_username = usernameInput.getText();
    }

    private void SignUp(ActionEvent e) {
        Statement userState;
        boolean isC = false;
        try {
            userState = denlu_con.createStatement();
            ResultSet rs = userState.executeQuery("SELECT * FROM user WHERE user_phone = " + c_username);
            //System.out.println("成功连接！");
            if (rs.next()) {
                String e_user_pwd = rs.getString(2);
                if (c_pwd.equals(e_user_pwd)) {
                    JOptionPane.showMessageDialog(this,"登录成功！","消息对话框", JOptionPane.INFORMATION_MESSAGE);
                    isC = true;
                    userType = rs.getInt(4);
                    //System.out.println(userType);
                    denlu_con.close();
                    //this.dispose();
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        window.dispose();
                    }
                    if (userType == 1) new adminPage();
                    else new defaultPage(c_username);
                    //System.out.println(c_username);
                }
            }
            if (!isC) {
                JOptionPane.showMessageDialog(this,"错误的用户名或密码！","消息对话框", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void exit(ActionEvent e) {
        this.dispose();
    }

    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        usernameInput = new JTextField();
        pwdInput = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u7528\u6237\u767b\u5f55");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7535\u8bdd\u53f7\u7801");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(45, 45), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(45, 110), label2.getPreferredSize()));

        //---- usernameInput ----
        usernameInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                usernameInputFocusLost(e);
            }
        });
        contentPane.add(usernameInput);
        usernameInput.setBounds(110, 40, 140, usernameInput.getPreferredSize().height);

        //---- pwdInput ----
        pwdInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                pwdInputFocusLost(e);
            }
        });
        contentPane.add(pwdInput);
        pwdInput.setBounds(110, 105, 140, pwdInput.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        button1.addActionListener(e -> SignUp(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(50, 205), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(220, 205), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField usernameInput;
    private JPasswordField pwdInput;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
