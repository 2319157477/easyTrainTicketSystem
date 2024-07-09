/*
 * Created by JFormDesigner on Sat Jul 06 19:51:40 CST 2024
 */

package page.manage.Train;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import page.manage.Train.AdminActions.*;

/**
 * @author 23191
 */
public class SearchAlter extends JFrame {
    String url = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312";
    String user = "root";
    String password = "lbc041103";
    Connection admin_con;
    String alter_id = "";
    Statement adminState;
    ResultSet adminRs;
    int count = 0;
    Object[][] train = new Object[count][9];
    String c_id;
    String c_type;
    String c_carriages;
    String c_start_station;
    String c_end_station;
    String c_start_time;
    String c_end_time;
    String c_running_time;
    String c_arrive_day;
    String[] columnNames =
            {"车次", "列车类型", "车箱数", "始发站", "终点站", "发车时间", "到达时间", "路途时间", "发车日期"};
    public SearchAlter() {
        try {
            admin_con = DriverManager.getConnection(url, user, password);
        } catch (SQLException var10) {
            throw new RuntimeException(var10);
        }
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
                adminState = admin_con.createStatement();
                String sql = "SELECT * FROM train_info WHERE train_id = '" + search_id + "'";
                System.out.println(sql);
                adminRs = adminState.executeQuery(sql);
                if (adminRs.next()) {
                    c_id = adminRs.getString(1);
                    String typeNum = adminRs.getString(2);
                    if (typeNum.equals("0")) c_type = "普快";
                    else c_type = "高铁";
                    c_carriages = adminRs.getString(3);
                    c_start_station = adminRs.getString(5);
                    c_end_station = adminRs.getString(6);
                    c_start_time = adminRs.getString(7);
                    c_end_time = adminRs.getString(8);
                    c_running_time = adminRs.getString(10);
                    c_arrive_day = adminRs.getString(9);
                }
                train = new Object[1][9];
                for (int j = 0; j < 9; j ++) {
                    switch (j) {
                        case 0:
                            train[0][j] = c_id;
                            break;
                        case 1:
                            train[0][j] = c_type;
                            break;
                        case 2:
                            train[0][j] = c_carriages;
                            break;
                        case 3:
                            train[0][j] = c_start_station;
                            break;
                        case 4:
                            train[0][j] = c_end_station;
                            break;
                        case 5:
                            train[0][j] = c_start_time;
                            break;
                        case 6:
                            train[0][j] = c_end_time;
                            break;
                        case 7:
                            train[0][j] = c_running_time;
                            break;
                        case 8:
                            train[0][j] = c_arrive_day;
                    }
                }
                table1 = new JTable(train, columnNames) {
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
                            c.setBackground(Color.YELLOW);
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
                countState = admin_con.createStatement();
                adminState = admin_con.createStatement();
                String sql = "SELECT * FROM train_info WHERE " + conditions;
                String sqlC = "SELECT COUNT(*) FROM train_info WHERE " + conditions;
                System.out.println(sql);

                ResultSet rs2 = countState.executeQuery(sqlC);
                if (rs2.next()) {
                    count = rs2.getInt(1);
                }
                train = new Object[count][9];

                adminRs = adminState.executeQuery(sql);
                int i = 0;
                while (adminRs.next()) {
                    c_id = adminRs.getString(1);
                    String typeNum = adminRs.getString(2);
                    if (typeNum.equals("0")) c_type = "普快";
                    else c_type = "高铁";
                    c_carriages = adminRs.getString(3);
                    c_start_station = adminRs.getString(5);
                    c_end_station = adminRs.getString(6);
                    c_start_time = adminRs.getString(7);
                    c_end_time = adminRs.getString(8);
                    c_running_time = adminRs.getString(10);
                    c_arrive_day = adminRs.getString(9);

                    for (int j = 0; j < 9; j++) {
                        switch (j) {
                            case 0:
                                train[i][j] = c_id;
                                break;
                            case 1:
                                train[i][j] = c_type;
                                break;
                            case 2:
                                train[i][j] = c_carriages;
                                break;
                            case 3:
                                train[i][j] = c_start_station;
                                break;
                            case 4:
                                train[i][j] = c_end_station;
                                break;
                            case 5:
                                train[i][j] = c_start_time;
                                break;
                            case 6:
                                train[i][j] = c_end_time;
                                break;
                            case 7:
                                train[i][j] = c_running_time;
                                break;
                            case 8:
                                train[i][j] = c_arrive_day;
                        }
                    }
                    i ++;
                }
                table1 = new JTable(train, columnNames){
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
                            c.setBackground(Color.YELLOW);
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
        //table1 = new JTable() ;
        Statement trainState;
        int i = 0;
        try {
            trainState = admin_con.createStatement();
            ResultSet rs2 = trainState.executeQuery("SELECT COUNT(*) FROM train_info");
            if (rs2.next()) {
                count = rs2.getInt(1);
            }
            train = new Object[count][9];

            ResultSet rs1 = trainState.executeQuery("SELECT * FROM train_info");
            while (rs1.next()) {
                String c_id = rs1.getString(1);
                String typeNum = rs1.getString(2);
                if (typeNum.equals("0")) c_type = "普快";
                else c_type = "高铁";
                String c_carriages = rs1.getString(3);
                String c_start_station = rs1.getString(5);
                String c_end_station = rs1.getString(6);
                String c_start_time = rs1.getString(7);
                String c_end_time = rs1.getString(8);
                String c_running_time = rs1.getString(10);
                String c_arrive_day = rs1.getString(9);

                for (int j = 0; j < 9; j ++) {
                    switch (j) {
                        case 0:
                            train[i][j] = c_id;
                            break;
                        case 1:
                            train[i][j] = c_type;
                            break;
                        case 2:
                            train[i][j] = c_carriages;
                            break;
                        case 3:
                            train[i][j] = c_start_station;
                            break;
                        case 4:
                            train[i][j] = c_end_station;
                            break;
                        case 5:
                            train[i][j] = c_start_time;
                            break;
                        case 6:
                            train[i][j] = c_end_time;
                            break;
                        case 7:
                            train[i][j] = c_running_time;
                            break;
                        case 8:
                            train[i][j] = c_arrive_day;
                    }
                }
                i ++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table1 = new JTable(train, columnNames){
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
                    c.setBackground(Color.YELLOW);
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
                    "是否确定删除列车id:" + alter_id + "?",
                    "确定操作",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (result == JOptionPane.OK_OPTION) {
                String sql = "DELETE FROM train_info WHERE train_id = '" + alter_id + "'";
                try {
                    PreparedStatement pstmt = admin_con.prepareStatement(sql);
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

    private void moreInfo(ActionEvent e) {
        new moreInfo(alter_id);
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
    private JButton button7;
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
        button7 = new JButton();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(search_id_input);
        search_id_input.setBounds(95, 35, 120, search_id_input.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(35, 95, 955, 615);

        //---- button1 ----
        button1.setText("\u67e5\u627e");
        button1.addActionListener(e -> Search(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(225, 35), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u4fee\u6539");
        button2.addActionListener(e -> Alter(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(550, 35), button2.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u8f93\u5165\u7f16\u53f7");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(35, 40), label1.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u8fc7\u6ee4\u5668");
        button3.addActionListener(e -> Filter(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(330, 35), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u663e\u793a\u5168\u90e8");
        button4.addActionListener(e -> ShowAll(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(430, 35), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u5220\u9664");
        button5.addActionListener(e -> Delete(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(800, 35), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("\u6dfb\u52a0");
        button6.addActionListener(e -> Add(e));
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(915, 35), button6.getPreferredSize()));

        //---- button7 ----
        button7.setText("\u67e5\u770b\u8be6\u60c5");
        button7.addActionListener(e -> moreInfo(e));
        contentPane.add(button7);
        button7.setBounds(new Rectangle(new Point(670, 35), button7.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(1025, 780));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
}
