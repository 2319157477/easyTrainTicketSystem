/*
 * Created by JFormDesigner on Mon Jul 08 16:46:35 CST 2024
 */

package page.userPage.extra;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import page.Actions.User.UserAction.*;
import page.Actions.Ticket.UserAction.myTickets;
import tools.createLabelImage;

/**
 * @author 23191
 */
public class userCenterPage extends JFrame {
    String phoneNumber;
    public userCenterPage(String phone) {
        phoneNumber = phone;
        initComponents();
        setTitle("欢迎用户：" + phoneNumber + " 使用本系统！");
    }

    private void alterPwd(ActionEvent e) {
        //System.out.println(phoneNumber);
        new alterPwd(this, phoneNumber);
    }

    private void myTickets(ActionEvent e) {
        new myTickets(phoneNumber);
    }

    private void exit(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u4fee\u6539\u5bc6\u7801");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> alterPwd(e));
        contentPane.add(button1);
        button1.setBounds(30, 30, button1.getPreferredSize().width, 60);

        //---- button2 ----
        button2.setText("\u6211\u7684\u8f66\u7968");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> myTickets(e));
        contentPane.add(button2);
        button2.setBounds(30, 115, button2.getPreferredSize().width, 60);

        //---- button3 ----
        button3.setText("\u8fd4\u56de");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 10f));
        button3.addActionListener(e -> exit(e));
        contentPane.add(button3);
        button3.setBounds(30, 200, 120, 60);
        contentPane.add(label1);
        label1.setBounds(185, 35, 120, 55);

        //---- label2 ----
        new createLabelImage(label1, "pwd.png");
        contentPane.add(label2);
        label2.setBounds(185, 120, 120, 55);

        //---- label3 ----
        new createLabelImage(label2, "train.png");
        contentPane.add(label3);
        label3.setBounds(185, 205, 120, 50);

        //---- label4 ----
        new createLabelImage(label3, "exit.png");
        label4.setText("text");
        label4.setVisible(false);
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(350, 100), label4.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(345, 300));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
