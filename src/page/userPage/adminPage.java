/*
 * Created by JFormDesigner on Sat Jul 06 19:39:58 CST 2024
 */

package page.userPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import page.manage.*;

/**
 * @author 23191
 */
public class adminPage extends JFrame {
    public adminPage() {
        initComponents();
    }

    private void manage_train(ActionEvent e) {
        new trainManagement();
    }

    private void manage_user(ActionEvent e) {
        new userManagement();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setTitle("\u7ba1\u7406\u5458\u754c\u9762");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u706b\u8f66\u7ba1\u7406");
        button1.addActionListener(e -> manage_train(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(30, 25), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u7528\u6237\u7ba1\u7406");
        button2.addActionListener(e -> manage_user(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(30, 70), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u9000\u51fa\u7cfb\u7edf");
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(30, 115), button3.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(410, 310));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
