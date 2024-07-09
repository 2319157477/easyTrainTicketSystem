/*
 * Created by JFormDesigner on Sat Jul 06 22:20:45 CST 2024
 */

package page.Actions.User.AdminActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author 23191
 */
public class searchPage extends JDialog {
    int type = 2;
    int gender = 2;
    String c_name = "";
    String c_upper = "";
    String date = "";
    String start = "";
    String end = "";
    boolean isC = false;

    public searchPage(JFrame parent) {
        super(parent, "选择条件", true);
        initComponents();  // 确保在构造方法中调用 initComponents()
        // 调用 pack() 和 setLocationRelativeTo() 以确保对话框布局正确
        pack();
        setLocationRelativeTo(parent);
        setVisible(true); // 确保在所有组件初始化和布局完成后，再调用 setVisible(true)
    }
    public String getSearchCondition() {
        String conditions = "";
        if (!c_name.isEmpty())
            conditions = conditions + "user_name LIKE '%" + c_name + "%'";
        if (type != 2)
            conditions = conditions + " AND user_type = " + type;
        if (gender != 2)
            conditions = conditions + " AND user_gender = " + gender;
        if (conditions.startsWith(" AND "))
            conditions = conditions.substring(5);
        return conditions;
    }

    private void Search(ActionEvent e) {
        c_name = nameInput.getText();
        if (!c_name.isEmpty() || type != 2 || gender != 2) {
            isC = true;
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "请至少选择一项信息进行过滤！");
        }
    }

    private void exit(ActionEvent e) {
        this.dispose();
    }

    private void Man(ActionEvent e) {
        gender = 1;
    }

    private void Woman(ActionEvent e) {
        gender = 0;
    }

    private void Default(ActionEvent e) {
        type = 0;
    }

    private void Admin(ActionEvent e) {
        type = 1;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label6 = new JLabel();
        ButtonGroup group = new ButtonGroup();
        radioButton1 = new JRadioButton();
        group.add(radioButton1);
        radioButton2 = new JRadioButton();
        group.add(radioButton2);
        nameInput = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        label2 = new JLabel();
        ButtonGroup group1 = new ButtonGroup();
        radioButton3 = new JRadioButton();
        group1.add(radioButton3);
        radioButton4 = new JRadioButton();
        group1.add(radioButton4);

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" \u7528\u6237\u8fc7\u6ee4\u5668");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u7c7b\u578b");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 100), label1.getPreferredSize()));

        //---- label6 ----
        label6.setText("\u540d\u5b57\u5305\u542b");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getStyle() | Font.BOLD, label6.getFont().getSize() + 5f));
        contentPane.add(label6);
        label6.setBounds(new Rectangle(new Point(20, 20), label6.getPreferredSize()));

        //---- radioButton1 ----
        radioButton1.setText("\u666e\u901a");
        radioButton1.setFont(radioButton1.getFont().deriveFont(radioButton1.getFont().getStyle() | Font.BOLD, radioButton1.getFont().getSize() + 5f));
        radioButton1.addActionListener(e -> Default(e));
        contentPane.add(radioButton1);
        radioButton1.setBounds(new Rectangle(new Point(115, 100), radioButton1.getPreferredSize()));

        //---- radioButton2 ----
        radioButton2.setText("\u7ba1\u7406\u5458");
        radioButton2.setFont(radioButton2.getFont().deriveFont(radioButton2.getFont().getStyle() | Font.BOLD, radioButton2.getFont().getSize() + 5f));
        radioButton2.addActionListener(e -> Admin(e));
        contentPane.add(radioButton2);
        radioButton2.setBounds(new Rectangle(new Point(195, 100), radioButton2.getPreferredSize()));
        contentPane.add(nameInput);
        nameInput.setBounds(115, 20, 125, nameInput.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u641c\u7d22");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> Search(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(20, 145), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u8fd4\u56de");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> exit(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(190, 140), button2.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u7528\u6237\u6027\u522b");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD, label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(20, 60), label2.getPreferredSize()));

        //---- radioButton3 ----
        radioButton3.setText("\u7537");
        radioButton3.setFont(radioButton3.getFont().deriveFont(radioButton3.getFont().getStyle() | Font.BOLD, radioButton3.getFont().getSize() + 5f));
        radioButton3.addActionListener(e -> Man(e));
        contentPane.add(radioButton3);
        radioButton3.setBounds(115, 60, 49, 21);

        //---- radioButton4 ----
        radioButton4.setText("\u5973");
        radioButton4.setFont(radioButton4.getFont().deriveFont(radioButton4.getFont().getStyle() | Font.BOLD, radioButton4.getFont().getSize() + 5f));
        radioButton4.addActionListener(e -> Woman(e));
        contentPane.add(radioButton4);
        radioButton4.setBounds(195, 60, 49, 21);

        contentPane.setPreferredSize(new Dimension(285, 195));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label6;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JTextField nameInput;
    private JButton button1;
    private JButton button2;
    private JLabel label2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
