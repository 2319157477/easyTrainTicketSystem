package page.manage.Train.UserActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TrainSeatDialog extends JDialog {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station? useSSL = false&serverTimezone = GMT&characterEncoding = gb2312"; // 替换为你的数据库URL
    private static final String DB_USER = "root"; // 替换为你的数据库用户名
    private static final String DB_PASSWORD = "lbc041103"; // 替换为你的数据库密码

    private JPanel seatPanel;
    private JButton selectedSeatButton;
    public int selectedSeatId;
    private String trainId;
    private int carriageId;

    public TrainSeatDialog(JDialog parent, String trainId, int carriageId) {
        super(parent, "选择座位", true);
        this.trainId = trainId;
        this.carriageId = carriageId;
        setTitle("Train Seats");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        seatPanel = new JPanel(new GridLayout(10, 6));
        JScrollPane scrollPane = new JScrollPane(seatPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ConfirmButtonListener());
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadSeats();
        setVisible(true);
    }

    public int getSelectedSeat() {
        return selectedSeatId;
    }
    private void loadSeats() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT seat_id FROM order_list WHERE train_id = ? AND carriage_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, trainId);
                pstmt.setInt(2, carriageId);
                ResultSet rs = pstmt.executeQuery();

                Set<Integer> occupiedSeats = new HashSet<>();
                while (rs.next()) {
                    occupiedSeats.add(rs.getInt("seat_id"));
                }

                for (int i = 1; i <= 60; i++) {
                    JButton seatButton = new JButton(String.valueOf(i));
                    if (occupiedSeats.contains(i)) {
                        seatButton.setBackground(Color.RED);
                        seatButton.setEnabled(false);
                    } else {
                        seatButton.setBackground(Color.GREEN);
                        seatButton.addActionListener(new SeatButtonListener(i, seatButton));
                    }
                    seatPanel.add(seatButton);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class SeatButtonListener implements ActionListener {
        private int seatId;
        private JButton seatButton;

        public SeatButtonListener(int seatId, JButton seatButton) {
            this.seatId = seatId;
            this.seatButton = seatButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedSeatButton != null) {
                selectedSeatButton.setBackground(Color.GREEN);
            }
            seatButton.setBackground(Color.BLUE);
            selectedSeatButton = seatButton;
            selectedSeatId = seatId;
        }
    }

    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedSeatId != -1) {
                System.out.println("Selected Seat: " + selectedSeatId);
                // 这里你可以处理选中的座位，例如保存到数据库或进行进一步处理
            } else {
                JOptionPane.showMessageDialog(TrainSeatDialog.this, "Please select a seat.");
            }
            dispose();
        }
    }
}

