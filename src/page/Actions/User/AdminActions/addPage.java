/*
 * Created by JFormDesigner on Sun Jul 07 22:22:38 CST 2024
 */

package page.manage.User.AdminActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;

/**
 * @author 23191
 */
public class addPage extends JDialog {
    String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
    String user = "root";
    String password = "lbc041103";
    String phone = "";
    String pwd = "";
    String name = "";
    int userType = 2;
    int userGender = 2;

    ButtonGroup g1, g2;
    // SQL 插入语句
    String sql = "INSERT INTO user (user_phone, user_pwd, user_name, user_type, user_gender) " +
            "VALUES (?, ?, ?, ?, ?)";
    Connection conn;

    private static boolean areAllFieldsNonEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public addPage(JFrame owner) {
        super(owner, "添加用户", true);
        try {
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        initComponents();
        setVisible(true);
    }
    private void createUIComponents() {

    }

    private void SureButton(ActionEvent e) {
        phone = idInput.getText();
        pwd = pwdInput.getText();
        name = nameInput.getText();
        boolean allFieldsNonEmpty = areAllFieldsNonEmpty(phone, pwd, name);
        if (allFieldsNonEmpty && userType != 2 && userGender != 2) {
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                {
                    // 设置参数
                    stmt.setString(1, phone);
                    stmt.setString(2, pwd);
                    stmt.setString(3, name);
                    stmt.setInt(4, userType);
                    stmt.setInt(5, userGender);
                    // 执行插入操作
                    int rowsAffected = stmt.executeUpdate();
                    System.out.println("Inserted " + rowsAffected + " row(s).");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            this.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "关键信息缺失！");
        }
    }
    private void train_idFocusLost(FocusEvent e) {
        phone = idInput.getText();
        boolean isSame = false;
        try {
            Statement usernameState = conn.createStatement();
            ResultSet rs = usernameState.executeQuery("SELECT * FROM user");
            //System.out.println("成功连接！");
            while (rs.next()) {
                String id = rs.getNString(1);
                //System.out.println(id);
                if (phone.equals(id)) {
                    isSame = true;
                    //zhuce_con.close();
                    break;
                }
            }
            if (isSame) {
                idErr.setVisible(true);
                phone = "";
                repaint();
            }
            else {
                idErr.setVisible(false);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void default_T(ActionEvent e) {
        userType = 0;
    }

    private void admin_T(ActionEvent e) {
        userType = 1;
    }

    private void man_G(ActionEvent e) {
        userGender = 1;
    }

    private void woman_G(ActionEvent e) {
        userGender = 0;
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label3 = new JLabel();
        pwdInput = new JTextField();
        label4 = new JLabel();
        nameInput = new JTextField();
        button1 = new JButton();
        idInput = new JTextField();
        idErr = new JLabel();
        createUIComponents();
        label2 = new JLabel();
        label5 = new JLabel();
        g2 = new ButtonGroup();
        radioButtonMan = new JRadioButton();
        g2.add(radioButtonMan);
        radioButtonWoman = new JRadioButton();
        g2.add(radioButtonWoman);
        g1 = new ButtonGroup();
        radioButtonDefault = new JRadioButton();
        g1.add(radioButtonDefault);
        radioButtonAdmin = new JRadioButton();
        g1.add(radioButtonAdmin);

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u7528\u6237\u53f7\u7801");
        contentPane.add(label1);
        label1.setBounds(20, 60, 60, 17);

        //---- label3 ----
        label3.setText("\u7528\u6237\u5bc6\u7801");
        contentPane.add(label3);
        label3.setBounds(20, 95, 65, 17);
        contentPane.add(pwdInput);
        pwdInput.setBounds(115, 90, 130, 30);

        //---- label4 ----
        label4.setText("\u7528\u6237\u59d3\u540d");
        contentPane.add(label4);
        label4.setBounds(20, 135, 60, 17);
        contentPane.add(nameInput);
        nameInput.setBounds(115, 130, 130, 30);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.addActionListener(e -> SureButton(e));
        contentPane.add(button1);
        button1.setBounds(225, 190, 78, 30);

        //---- idInput ----
        idInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                train_idFocusLost(e);
            }
        });
        contentPane.add(idInput);
        idInput.setBounds(115, 55, 130, idInput.getPreferredSize().height);

        //---- idErr ----
        idErr.setText("\u5df2\u5b58\u5728\uff01");
        idErr.setVisible(false);
        contentPane.add(idErr);
        idErr.setBounds(new Rectangle(new Point(205, 60), idErr.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u7528\u6237\u7c7b\u578b");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(290, 75), label2.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u7528\u6237\u6027\u522b");
        contentPane.add(label5);
        label5.setBounds(new Rectangle(new Point(290, 125), label5.getPreferredSize()));

        //---- radioButtonMan ----
        radioButtonMan.setText("\u7537");
        radioButtonMan.addActionListener(e -> man_G(e));
        contentPane.add(radioButtonMan);
        radioButtonMan.setBounds(new Rectangle(new Point(355, 125), radioButtonMan.getPreferredSize()));

        //---- radioButtonWoman ----
        radioButtonWoman.setText("\u5973");
        radioButtonWoman.addActionListener(e -> woman_G(e));
        contentPane.add(radioButtonWoman);
        radioButtonWoman.setBounds(new Rectangle(new Point(420, 125), radioButtonWoman.getPreferredSize()));

        //---- radioButtonDefault ----
        radioButtonDefault.setText("\u666e\u901a");
        radioButtonDefault.addActionListener(e -> default_T(e));
        contentPane.add(radioButtonDefault);
        radioButtonDefault.setBounds(new Rectangle(new Point(355, 75), radioButtonDefault.getPreferredSize()));

        //---- radioButtonAdmin ----
        radioButtonAdmin.setText("\u7ba1\u7406\u5458");
        radioButtonAdmin.addActionListener(e -> admin_T(e));
        contentPane.add(radioButtonAdmin);
        radioButtonAdmin.setBounds(new Rectangle(new Point(420, 75), radioButtonAdmin.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(550, 295));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label3;
    private JTextField pwdInput;
    private JLabel label4;
    private JTextField nameInput;
    private JButton button1;
    private JTextField idInput;
    private JLabel idErr;
    private JLabel label2;
    private JLabel label5;
    private JRadioButton radioButtonMan;
    private JRadioButton radioButtonWoman;
    private JRadioButton radioButtonDefault;
    private JRadioButton radioButtonAdmin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}