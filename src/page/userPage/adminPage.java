/*
 * Created by JFormDesigner on Sat Jul 06 19:39:58 CST 2024
 */

package page.userPage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import page.Actions.Ticket.AdminAction.SearchAlter_Ticket;
import page.Actions.Train.SearchAlter;
import page.Actions.User.*;
import tools.createLabelImage;

/**
 * @author 23191
 */
public class adminPage extends JFrame {
    public adminPage() {
        initComponents();
    }

    private void manage_train(ActionEvent e) {
        new SearchAlter();
    }

    private void manage_user(ActionEvent e) {
        new SearchAlter_User();
    }

    private void manage_ticket(ActionEvent e) {
        new SearchAlter_Ticket();
    }

    private void exit(ActionEvent e) {
        this.dispose();
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setTitle("\u7ba1\u7406\u5458\u754c\u9762");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u5217\u8f66\u7ba1\u7406");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> manage_train(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(25, 25), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u7528\u6237\u7ba1\u7406");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> manage_user(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(25, 85), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u9000\u51fa\u7cfb\u7edf");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 10f));
        button3.addActionListener(e -> exit(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(25, 205), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u8f66\u7968\u7ba1\u7406");
        button4.setFont(button4.getFont().deriveFont(button4.getFont().getStyle() | Font.BOLD, button4.getFont().getSize() + 10f));
        button4.addActionListener(e -> manage_ticket(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(25, 145), button4.getPreferredSize()));
        contentPane.add(label1);
        label1.setBounds(165, 25, 225, 220);

        //---- label2 ----
        new createLabelImage(label1, "chilun.png");
        label2.setText("text");
        label2.setVisible(false);
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(30, 250), label2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(420, 275));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
