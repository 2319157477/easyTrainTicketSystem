/*
 * Created by JFormDesigner on Sat Jul 06 19:51:40 CST 2024
 */

package page.Actions.Ticket.UserAction;


import page.Actions.Ticket.AdminAction.searchPage;
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
public class myTickets extends JFrame {
    int alter_id = -1;
    Statement adminState;
    ResultSet adminRs;
    int count = 0;
    Object[][] ticket = new Object[count][9];
    int c_id = -1;
    String type;
    String user_phone_number;
    String train_id;
    String start_station_name;
    String end_station_name;
    int carriage_id;
    int seat_id;
    int order_money;
    String train_start_date;
    Color gray = new Color(54, 54, 54);
    String[] columnNames =
            {"票号", "电话号码", "列车id", "始发站", "终点站", "车厢", "座位", "票型", "发车时间"};
    public myTickets(String phone) {
        user_phone_number = phone;
        initComponents();
    }
    private void Search(ActionEvent e) {
        int search_id = Integer.parseInt(search_id_input.getText());
        if(!search_id_input.getText().isEmpty()) {
            scrollPane1.setViewportView(null);
            scrollPane1.remove(table1);
            scrollPane1.revalidate();
            scrollPane1.repaint();
            try {
                adminState = DBUtil.getStatement();
                String sql = "SELECT * FROM order_list WHERE order_id = " + search_id + " AND user_phone_number = '" + user_phone_number + "'";
                System.out.println(sql);
                adminRs = adminState.executeQuery(sql);
                if (adminRs.next()) {
                    c_id = adminRs.getInt(1);
                    train_id = adminRs.getString(3);
                    start_station_name = adminRs.getString(4);
                    end_station_name = adminRs.getString(5);
                    carriage_id = adminRs.getInt(6);
                    seat_id = adminRs.getInt(7);
                    order_money = adminRs.getInt(8);
                    type = (order_money == 300)? "二等" : "一等";
                    train_start_date = adminRs.getString(9);
                }
                ticket = new Object[1][9];
                for (int j = 0; j < 9; j++) {
                    switch (j) {
                        case 0:
                            ticket[0][j] = c_id;
                            break;
                        case 1:
                            ticket[0][j] = user_phone_number;
                            break;
                        case 2:
                            ticket[0][j] = train_id;
                            break;
                        case 3:
                            ticket[0][j] = start_station_name;
                            break;
                        case 4:
                            ticket[0][j] = end_station_name;
                            break;
                        case 5:
                            ticket[0][j] = carriage_id;
                            break;
                        case 6:
                            ticket[0][j] = seat_id;
                            break;
                        case 7:
                            ticket[0][j] = type;
                            break;
                        case 8:
                            ticket[0][j] = train_start_date;
                            break;
                    }
                }
                table1 = new JTable(ticket, columnNames) {
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
                            int c_id = (int) table1.getValueAt(row, 0);
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
            conditions = conditions + " AND user_phone_number = '" + user_phone_number + "'";
            try {
                scrollPane1.setViewportView(null);
                scrollPane1.remove(table1);
                scrollPane1.revalidate();
                scrollPane1.repaint();
                Statement countState;
                countState = DBUtil.getStatement();
                adminState = DBUtil.getStatement();
                String sql = "SELECT * FROM order_list WHERE " + conditions;
                String sqlC = "SELECT COUNT(*) FROM order_list WHERE " + conditions;
                System.out.println(sql);

                ResultSet adminRs = countState.executeQuery(sqlC);
                if (adminRs.next()) {
                    count = adminRs.getInt(1);
                }
                ticket = new Object[count][9];

                adminRs = adminState.executeQuery(sql);
                int i = 0;
                while (adminRs.next()) {
                    c_id = adminRs.getInt(1);
                    train_id = adminRs.getString(3);
                    start_station_name = adminRs.getString(4);
                    end_station_name = adminRs.getString(5);
                    carriage_id = adminRs.getInt(6);
                    seat_id = adminRs.getInt(7);
                    order_money = adminRs.getInt(8);
                    String type = (order_money == 300)? "二等" : "一等";
                    train_start_date = adminRs.getString(9);

                    for (int j = 0; j < 9; j++) {
                        switch (j) {
                            case 0:
                                ticket[i][j] = c_id;
                                break;
                            case 1:
                                ticket[i][j] = user_phone_number;
                                break;
                            case 2:
                                ticket[i][j] = train_id;
                                break;
                            case 3:
                                ticket[i][j] = start_station_name;
                                break;
                            case 4:
                                ticket[i][j] = end_station_name;
                                break;
                            case 5:
                                ticket[i][j] = carriage_id;
                                break;
                            case 6:
                                ticket[i][j] = seat_id;
                                break;
                            case 7:
                                ticket[i][j] = type;
                                break;
                            case 8:
                                ticket[i][j] = train_start_date;
                                break;
                        }
                    }
                    i++;
                }
                table1 = new JTable(ticket, columnNames){
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
                            int c_id = (int) table1.getValueAt(row, 0);
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
            ResultSet adminRs = trainState.executeQuery("SELECT COUNT(*) FROM order_list WHERE user_phone_number = '" + user_phone_number + "'");
            if (adminRs.next()) {
                count = adminRs.getInt(1);
            }
            ticket = new Object[count][9];

            adminRs = trainState.executeQuery("SELECT * FROM order_list WHERE user_phone_number = '" + user_phone_number + "'");
            while (adminRs.next()) {
                c_id = adminRs.getInt(1);
                train_id = adminRs.getString(3);
                start_station_name = adminRs.getString(4);
                end_station_name = adminRs.getString(5);
                carriage_id = adminRs.getInt(6);
                seat_id = adminRs.getInt(7);
                order_money = adminRs.getInt(8);
                String type = (order_money == 300)? "二等" : "一等";
                train_start_date = adminRs.getString(9);

                for (int j = 0; j < 9; j++) {
                    switch (j) {
                        case 0:
                            ticket[i][j] = c_id;
                            break;
                        case 1:
                            ticket[i][j] = user_phone_number;
                            break;
                        case 2:
                            ticket[i][j] = train_id;
                            break;
                        case 3:
                            ticket[i][j] = start_station_name;
                            break;
                        case 4:
                            ticket[i][j] = end_station_name;
                            break;
                        case 5:
                            ticket[i][j] = carriage_id;
                            break;
                        case 6:
                            ticket[i][j] = seat_id;
                            break;
                        case 7:
                            ticket[i][j] = type;
                            break;
                        case 8:
                            ticket[i][j] = train_start_date;
                            break;
                    }
                }
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        table1 = new JTable(ticket, columnNames){
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
                    int c_id = (int)table1.getValueAt(row, 0);
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
        if (alter_id != -1) {
            Object[] options = {"确定", "取消"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    "是否确定退掉这张票: " + alter_id + "?",
                    "确定操作",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (result == JOptionPane.OK_OPTION) {
                String sql = "DELETE FROM order_list WHERE order_id = " + alter_id;
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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTextField search_id_input;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JLabel label1;
    private JButton button3;
    private JButton button4;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        search_id_input = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        createUIComponents();
        button1 = new JButton();
        label1 = new JLabel();
        button3 = new JButton();
        button4 = new JButton();
        button2 = new JButton();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(search_id_input);
        search_id_input.setBounds(130, 20, 120, search_id_input.getPreferredSize().height);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(20, 70, 955, 600);

        //---- button1 ----
        button1.setText("\u67e5\u627e");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getStyle() | Font.BOLD, button1.getFont().getSize() + 10f));
        button1.addActionListener(e -> Search(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(265, 15), button1.getPreferredSize()));

        //---- label1 ----
        label1.setText("\u8f93\u5165\u8f66\u7968\u7f16\u53f7");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 20), label1.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u8fc7\u6ee4\u5668");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getStyle() | Font.BOLD, button3.getFont().getSize() + 10f));
        button3.addActionListener(e -> Filter(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(610, 15), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u663e\u793a\u5168\u90e8");
        button4.setFont(button4.getFont().deriveFont(button4.getFont().getStyle() | Font.BOLD, button4.getFont().getSize() + 10f));
        button4.addActionListener(e -> ShowAll(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(740, 15), button4.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u9000\u7968");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getStyle() | Font.BOLD, button2.getFont().getSize() + 10f));
        button2.addActionListener(e -> Delete(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(890, 15), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(995, 675));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
}
