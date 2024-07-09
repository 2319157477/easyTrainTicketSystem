package page.Actions.Train.UserActions;

import sql.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TrainSeatDialog extends JDialog {
    private JPanel seatPanel;
    private JButton selectedSeatButton;
    public int selectedSeatId = -1;
    private String trainId;
    private int carriageId;
    boolean isP = false;
    Color dark_green = new Color(180, 238, 180);
    Color indian_red = new Color(255, 106, 106);
    Color steel_blue = new Color(99, 184, 255);

    public TrainSeatDialog(JDialog parent, String trainId, int carriageId) {
        super(parent, "选择座位", true);
        this.trainId = trainId;
        this.carriageId = carriageId;
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        seatPanel = new JPanel(new GridLayout(10, 6));
        JScrollPane scrollPane = new JScrollPane(seatPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("确定");
        confirmButton.addActionListener(new ConfirmButtonListener());
        buttonPanel.add(confirmButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadSeats();
        setVisible(true);
    }

    public int getSelectedSeat() {
        return selectedSeatId;
    }

    public boolean getPurchaseStat() {
        return isP;
    }
    private void loadSeats() {
        String query = "SELECT seat_id FROM order_list WHERE train_id = ? AND carriage_id = ?";
        try (PreparedStatement pstmt = DBUtil.getPstmt(query)) {
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
                    seatButton.setForeground(Color.BLACK);
                    seatButton.setFont(seatButton.getFont().deriveFont(seatButton.getFont().getStyle() | Font.BOLD, seatButton.getFont().getSize() + 5f));
                    seatButton.setBackground(indian_red);
                } else {
                    seatButton.setBackground(dark_green);
                    seatButton.setForeground(Color.BLACK);
                    seatButton.setFont(seatButton.getFont().deriveFont(seatButton.getFont().getStyle() | Font.BOLD, seatButton.getFont().getSize() + 5f));
                    seatButton.addActionListener(new SeatButtonListener(i, seatButton));
                }
                seatPanel.add(seatButton);
            }
        }
         catch (SQLException e) {
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
                selectedSeatButton.setBackground(dark_green);
            }
            seatButton.setBackground(steel_blue);
            selectedSeatButton = seatButton;
            selectedSeatId = seatId;
        }
    }

    private class ConfirmButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedSeatId != -1) {
                isP = true;
                System.out.println("Selected Seat: " + selectedSeatId);
                dispose();
                // 这里你可以处理选中的座位，例如保存到数据库或进行进一步处理
            } else {
                JOptionPane.showMessageDialog(TrainSeatDialog.this, "请选择一个座位");
            }
        }
    }
}

