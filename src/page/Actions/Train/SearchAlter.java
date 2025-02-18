/*
 * Created by JFormDesigner on Sat Jul 06 19:51:40 CST 2024
 */

package page.Actions.Train;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import page.Actions.Train.AdminActions.*;
import sql.DBUtil;

/**
 * @author 23191
 */
public class SearchAlter extends JFrame {
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
    Color gray = new Color(54, 54, 54);
    String[] columnNames =
            {"车次", "列车类型", "车箱数", "始发站", "终点站", "发车时间", "到达时间", "路途时间", "发车日期"};
    public SearchAlter() {
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
                        case 0 -> train[0][j] = c_id;
                        case 1 -> train[0][j] = c_type;
                        case 2 -> train[0][j] = c_carriages;
                        case 3 -> train[0][j] = c_start_station;
                        case 4 -> train[0][j] = c_end_station;
                        case 5 -> train[0][j] = c_start_time;
                        case 6 -> train[0][j] = c_end_time;
                        case 7 -> train[0][j] = c_running_time;
                        case 8 -> train[0][j] = c_arrive_day;
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
                            case 0 -> train[i][j] = c_id;
                            case 1 -> train[i][j] = c_type;
                            case 2 -> train[i][j] = c_carriages;
                            case 3 -> train[i][j] = c_start_station;
                            case 4 -> train[i][j] = c_end_station;
                            case 5 -> train[i][j] = c_start_time;
                            case 6 -> train[i][j] = c_end_time;
                            case 7 -> train[i][j] = c_running_time;
                            case 8 -> train[i][j] = c_arrive_day;
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
        //table1 = new JTable() ;
        Statement trainState;
        int i = 0;
        try {
            trainState = DBUtil.getStatement();
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
                        case 0 -> train[i][j] = c_id;
                        case 1 -> train[i][j] = c_type;
                        case 2 -> train[i][j] = c_carriages;
                        case 3 -> train[i][j] = c_start_station;
                        case 4 -> train[i][j] = c_end_station;
                        case 5 -> train[i][j] = c_start_time;
                        case 6 -> train[i][j] = c_end_time;
                        case 7 -> train[i][j] = c_running_time;
                        case 8 -> train[i][j] = c_arrive_day;
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
                    PreparedStatement pstmt = DBUtil.getPstmt(sql);
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
        setTitle("\u5217\u8f66\u7ba1\u7406");
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(search_id_input);
        search_id_input.setBounds(95, 20, 120, search_id_input.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 65, 955, 615);

        //---- button1 ----
        button1.setText("\u67e5\u627e");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> Search(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(225, 15), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u4fee\u6539");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> Alter(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(595, 15), button2.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u8f93\u5165\u7f16\u53f7");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 20), label1.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u8fc7\u6ee4\u5668");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 10f));
        button3.addActionListener(e -> Filter(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(355, 15), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u663e\u793a\u5168\u90e8");
        button4.setFont(button4.getFont().deriveFont(button4.getFont().getStyle() | Font.BOLD, button4.getFont().getSize() + 10f));
        button4.addActionListener(e -> ShowAll(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(465, 15), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u5220\u9664");
        button5.setFont(button5.getFont().deriveFont(button5.getFont().getStyle() | Font.BOLD, button5.getFont().getSize() + 10f));
        button5.addActionListener(e -> Delete(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(810, 15), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("\u6dfb\u52a0");
        button6.setFont(button6.getFont().deriveFont(button6.getFont().getStyle() | Font.BOLD, button6.getFont().getSize() + 10f));
        button6.addActionListener(e -> Add(e));
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(895, 15), button6.getPreferredSize()));

        //---- button7 ----
        button7.setText("\u67e5\u770b\u8be6\u60c5");
        button7.setFont(button7.getFont().deriveFont(button7.getFont().getStyle() | Font.BOLD, button7.getFont().getSize() + 10f));
        button7.addActionListener(e -> moreInfo(e));
        contentPane.add(button7);
        button7.setBounds(new Rectangle(new Point(680, 15), button7.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(995, 700));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
}
