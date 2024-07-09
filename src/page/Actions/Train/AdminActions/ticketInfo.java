/*
 * Created by JFormDesigner on Mon Jul 08 22:16:08 CST 2024
 */

package page.manage.Train.AdminActions;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 23191
 */
public class ticketInfo extends JDialog {

    private final int ticket_id;
    private final String phoneNumber;
    boolean doDelete = false;
    public ticketInfo(JFrame owner, int t_id, String phone) {
        super(owner, "删除车票", true);
        ticket_id = t_id;
        phoneNumber = phone;
        initComponents();
        setVisible(true);
    }

    private void delete(ActionEvent e) {
        doDelete = true;
        dispose();
    }

    private void pause(ActionEvent e) {
        dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        id = new JLabel();
        id.setText(String.valueOf(ticket_id));
        phoneNum = new JLabel();
        phoneNum.setText(phoneNumber);
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d2d\u7968\u4eba\u53f7\u7801\uff1a");
        contentPane.add(label1);
        label1.setBounds(25, 70, label1.getPreferredSize().width, 22);

        //---- label2 ----
        label2.setText("\u8f66\u7968\u7f16\u53f7\uff1a");
        contentPane.add(label2);
        label2.setBounds(25, 30, label2.getPreferredSize().width, 20);
        contentPane.add(id);
        id.setBounds(new Rectangle(new Point(110, 30), id.getPreferredSize()));
        contentPane.add(phoneNum);
        phoneNum.setBounds(new Rectangle(new Point(115, 70), phoneNum.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u5220\u9664");
        button1.addActionListener(e -> delete(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(25, 105), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.addActionListener(e -> pause(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(155, 105), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(275, 185));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel id;
    private JLabel phoneNum;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
