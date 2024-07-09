/*
 * Created by JFormDesigner on Mon Jul 08 22:16:08 CST 2024
 */

package page.Actions.Train.AdminActions;

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
        setTitle("\u8be6\u60c5\u9875");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d2d\u7968\u4eba\u53f7\u7801\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(20, 60, label1.getPreferredSize().width, 22);

        //---- label2 ----
        label2.setText("\u8f66\u7968\u7f16\u53f7\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(20, 20, label2.getPreferredSize().width, 20);

        //---- id ----
        id.setFont(id.getFont().deriveFont(id.getFont().getStyle() | Font.BOLD, id.getFont().getSize() + 5f));
        contentPane.add(id);
        id.setBounds(new Rectangle(new Point(105, 20), id.getPreferredSize()));

        //---- phoneNum ----
        phoneNum.setFont(phoneNum.getFont().deriveFont(phoneNum.getFont().getStyle() | Font.BOLD, phoneNum.getFont().getSize() + 5f));
        contentPane.add(phoneNum);
        phoneNum.setBounds(new Rectangle(new Point(120, 60), phoneNum.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u5220\u9664");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> delete(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(15, 100), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> pause(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(150, 100), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(250, 155));
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
