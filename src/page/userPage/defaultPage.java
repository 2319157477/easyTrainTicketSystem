/*
 * Created by JFormDesigner on Mon Jul 08 08:49:56 CST 2024
 */

package page.userPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import page.Actions.Train.UserActions.*;
import page.userPage.extra.*;
import tools.createLabelImage;

/**
 * @author 23191
 */
public class defaultPage extends JFrame {

    String phoneNumber;
    public defaultPage(String phone) {
        phoneNumber = phone;
        initComponents();
        setTitle("欢迎用户：" + phoneNumber + " 使用本系统！");
    }

    private void train_list(ActionEvent e) {
        new SearchAlter(phoneNumber);
    }

    private void userCenter(ActionEvent e) {
        new userCenterPage(phoneNumber);
    }

    private void exit(ActionEvent e) {
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        button3 = new JButton();

        //======== this ========
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u5217\u8f66\u67e5\u8be2");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> train_list(e));
        contentPane.add(button1);
        button1.setBounds(20, 20, 130, 40);

        //---- button2 ----
        button2.setText("\u4e2a\u4eba\u4e2d\u5fc3");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> userCenter(e));
        contentPane.add(button2);
        button2.setBounds(20, 105, 130, 40);
        contentPane.add(label1);
        label1.setBounds(170, 15, 220, 215);

        //---- label2 ----
        new createLabelImage(label1, "user.png");
        label2.setText("text");
        label2.setVisible(false);
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(10, 175), label2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u9000\u51fa\u7cfb\u7edf");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 10f));
        button3.addActionListener(e -> exit(e));
        contentPane.add(button3);
        button3.setBounds(20, 190, 130, 40);

        contentPane.setPreferredSize(new Dimension(405, 270));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JLabel label2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
