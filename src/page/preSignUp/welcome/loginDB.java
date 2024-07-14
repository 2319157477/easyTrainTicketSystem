/*
 * Created by JFormDesigner on Wed Jul 10 09:54:03 CST 2024
 */

package page.preSignUp.welcome;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

import sql.DBUtil;
import sql.testLink;

/**
 * @author 23191
 */
public class loginDB extends JDialog {
    private String username;
    private String pwd;
    boolean isA;
    public loginDB(JFrame owner) {
        super(owner, "登录数据库", true);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void sureButton(ActionEvent e) {
        username = textField1.getText();
        pwd = new String(passwordField1.getPassword());
        testLink test = new testLink();
        boolean isC = test.test_Link(username, pwd);
        if (isC) {
            try {
                try (FileWriter fileWriter = new FileWriter("src/sql/mysqlLogin.txt")) {
                    String str = username + ":" + pwd;
                    System.out.println(str);
                    fileWriter.write(str);
                    DBUtil.getInfo(username, pwd);
                    JOptionPane.showMessageDialog(this, "登录信息保存成功！");
                    isA = true;
                    this.dispose();
                }
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "错误的用户名或密码！");
        }
    }

    public boolean getStat() {
        return isA;
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        passwordField1 = new JPasswordField();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u6570\u636e\u5e93\u7528\u6237\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 20), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u6570\u636e\u5e93\u5bc6\u7801\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(20, 65), label2.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(135, 20, 165, 27);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> sureButton(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(40, 110), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u9000\u51fa");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(200, 110), button2.getPreferredSize()));
        contentPane.add(passwordField1);
        passwordField1.setBounds(135, 65, 165, 27);

        contentPane.setPreferredSize(new Dimension(325, 175));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
