/*
 * Created by JFormDesigner on Sat Jul 06 19:51:40 CST 2024
 */

package page.Actions.User;

import page.Actions.User.AdminActions.*;
import sql.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

/**
 * @author 23191
 */
public class SearchAlter_User extends JFrame {
    String alter_id = "";
    Statement adminState;
    ResultSet adminRs;
    int count = 0;
    Object[][] user = new Object[count][5];
    String c_id;
    String c_pwd;
    String c_name;
    int c_type;
    int c_gender;
    String userType;
    String userGender;
    Color gray = new Color(54, 54, 54);
    String[] columnNames =
            {"号码", "密码", "姓名", "类型", "性别"};
    public SearchAlter_User() {
        initComponents();
    }
    private void Alter(ActionEvent e) {
        if (!alter_id.isEmpty()) {
            new alterPage(alter_id, this);
            scrollPane1.setViewportView(null);
            createUIComponents();
            scrollPane1.setViewportView(table1);
        }
    }
    private void Search(ActionEvent e) {
        String search_id = search_id_input.getText();
        if(!search_id.isEmpty()) {
            scrollPane1.setViewportView(null);
            scrollPane1.remove(table1);
            scrollPane1.revalidate();
            scrollPane1.repaint();
            try {
                adminState = DBUtil.getStatement();
                String sql = "SELECT * FROM user WHERE user_phone = '" + search_id + "'";
                System.out.println(sql);
                adminRs = adminState.executeQuery(sql);
                if (adminRs.next()) {
                    c_id = adminRs.getString(1);
                    c_pwd = adminRs.getString(2);
                    c_name = adminRs.getString(3);
                    c_type = adminRs.getInt(4);
                    if (c_type == 0) userType = "普通";
                    else userType = "管理员";
                    c_gender = adminRs.getInt(5);
                    if (c_gender == 0) userGender = "女";
                    else userGender = "男";
                }
                user = new Object[1][5];
                for (int j = 0; j < 9; j ++) {
                    switch (j) {
                        case 0 -> user[0][j] = c_id;
                        case 1 -> user[0][j] = c_pwd;
                        case 2 -> user[0][j] = c_name;
                        case 3 -> user[0][j] = userType;
                        case 4 -> user[0][j] = userGender;
                    }
                }
                table1 = new JTable(user, columnNames) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.getTableHeader().setReorderingAllowed(false);

                table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        if (isSelected) {
                            c.setBackground(gray);
                        } else {
                            c.setBackground(table.getBackground());
                        }
                        return c;
                    }
                });
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table1.getSelectedRow();
                        if (row != -1) {
                            String c_id = (String) table1.getValueAt(row, 0);
                            System.out.println("Selected c_id: " + c_id);
                            alter_id = c_id;
                        }
                    }
                });

                scrollPane1.setViewportView(table1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private void Filter(ActionEvent e) {
        searchPage searchPage1 = new searchPage(this);
        String conditions = searchPage1.getSearchCondition();
        System.out.println(conditions);
        if (!conditions.isEmpty()) {
            try {
                scrollPane1.setViewportView(null);
                scrollPane1.remove(table1);
                scrollPane1.revalidate();
                scrollPane1.repaint();
                Statement countState;
                countState = DBUtil.getStatement();
                adminState = DBUtil.getStatement();
                String sql = "SELECT * FROM user WHERE " + conditions;
                String sqlC = "SELECT COUNT(*) FROM user WHERE " + conditions;
                System.out.println(sql);

                ResultSet adminRs = countState.executeQuery(sqlC);
                if (adminRs.next()) {
                    count = adminRs.getInt(1);
                }
                user = new Object[count][9];

                adminRs = adminState.executeQuery(sql);
                int i = 0;
                while (adminRs.next()) {
                    c_id = adminRs.getString(1);
                    c_pwd = adminRs.getString(2);
                    c_name = adminRs.getString(3);
                    c_type = adminRs.getInt(4);
                    if (c_type == 0) userType = "普通";
                    else userType = "管理员";
                    c_gender = adminRs.getInt(5);
                    if (c_gender == 0) userGender = "女";
                    else userGender = "男";

                    for (int j = 0; j < 5; j++) {
                        switch (j) {
                            case 0 -> user[i][j] = c_id;
                            case 1 -> user[i][j] = c_pwd;
                            case 2 -> user[i][j] = c_name;
                            case 3 -> user[i][j] = userType;
                            case 4 -> user[i][j] = userGender;
                        }
                    }
                    i ++;
                }
                table1 = new JTable(user, columnNames){
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table1.getTableHeader().setReorderingAllowed(false);

                table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        if (isSelected) {
                            c.setBackground(gray);
                        } else {
                            c.setBackground(table.getBackground());
                        }
                        return c;
                    }
                });
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int row = table1.getSelectedRow();
                        if (row != -1) {
                            String c_id = (String) table1.getValueAt(row, 0);
                            System.out.println("Selected c_id: " + c_id);
                            alter_id = c_id;
                        }
                    }
                });

                scrollPane1.setViewportView(table1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private void createUIComponents() {
        Statement trainState;
        int i = 0;
        try {
            trainState = DBUtil.getStatement();
            ResultSet adminRs = trainState.executeQuery("SELECT COUNT(*) FROM user");
            if (adminRs.next()) {
                count = adminRs.getInt(1);
            }
            user = new Object[count][9];

            ResultSet rs1 = trainState.executeQuery("SELECT * FROM user");
            while (rs1.next()) {
                c_id = rs1.getString(1);
                c_pwd = rs1.getString(2);
                c_name = rs1.getString(3);
                c_type = rs1.getInt(4);
                if (c_type == 0) userType = "普通";
                else userType = "管理员";
                c_gender = rs1.getInt(5);
                if (c_gender == 0) userGender = "女";
                else userGender = "男";

                for (int j = 0; j < 5; j++) {
                    switch (j) {
                        case 0 -> user[i][j] = c_id;
                        case 1 -> user[i][j] = c_pwd;
                        case 2 -> user[i][j] = c_name;
                        case 3 -> user[i][j] = userType;
                        case 4 -> user[i][j] = userGender;
                    }
                }
                i ++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table1 = new JTable(user, columnNames){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.getTableHeader().setReorderingAllowed(false);

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(gray);
                } else {
                    c.setBackground(table.getBackground());
                }
                return c;
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table1.getSelectedRow();
                if (row != -1) {
                    String c_id = (String) table1.getValueAt(row, 0);
                    System.out.println("Selected c_id: " + c_id);
                    alter_id = c_id;
                }
            }
        });
    }
    private void ShowAll(ActionEvent e) {
        scrollPane1.setViewportView(null);
        createUIComponents();
        scrollPane1.setViewportView(table1);
    }
    private void Delete(ActionEvent e) {
        if (!alter_id.isEmpty()) {
            Object[] options = {"确定", "取消"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    "是否确定删除用户号码: " + alter_id + "?",
                    "确定操作",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (result == JOptionPane.OK_OPTION) {
                String sql = "DELETE FROM user WHERE user_phone = '" + alter_id + "'";
                try {
                    PreparedStatement pstmt = DBUtil.getPstmt(sql);;
                    pstmt.executeUpdate();
                    scrollPane1.setViewportView(null);
                    createUIComponents();
                    scrollPane1.setViewportView(table1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    private void Add(ActionEvent e) {
        new addPage(this);
        scrollPane1.setViewportView(null);
        createUIComponents();
        scrollPane1.setViewportView(table1);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTextField search_id_input;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        search_id_input = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        createUIComponents();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("\u7528\u6237\u7ba1\u7406");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(search_id_input);
        search_id_input.setBounds(135, 25, 120, search_id_input.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 75, 955, 600);

        //---- button1 ----
        button1.setText("\u67e5\u627e");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> Search(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(275, 20), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u4fee\u6539");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> Alter(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(785, 20), button2.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u8f93\u5165\u7535\u8bdd\u53f7\u7801");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 25), label1.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u8fc7\u6ee4\u5668");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 10f));
        button3.addActionListener(e -> Filter(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(490, 20), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u663e\u793a\u5168\u90e8");
        button4.setFont(button4.getFont().deriveFont(button4.getFont().getStyle() | Font.BOLD, button4.getFont().getSize() + 11f));
        button4.addActionListener(e -> ShowAll(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(625, 20), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u5220\u9664");
        button5.setFont(button5.getFont().deriveFont(button5.getFont().getStyle() | Font.BOLD, button5.getFont().getSize() + 10f));
        button5.addActionListener(e -> Delete(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(895, 20), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("\u6dfb\u52a0");
        button6.addActionListener(e -> Add(e));
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(1020, 35), button6.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(995, 700));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
}
